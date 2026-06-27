package io.github.aktissa.demo;

import java.awt.BorderLayout;

import javax.swing.SwingUtilities;

import io.github.aktissa.builders.ComboBoxBuilder;
import io.github.aktissa.builders.LabelBuilder;
import io.github.aktissa.builders.ProgressBarBuilder;
import io.github.aktissa.builders.RadioGroupBuilder;
import io.github.aktissa.builders.SliderBuilder;
import io.github.aktissa.builders.TableBuilder;
import io.github.aktissa.builders.TextFieldBuilder;
import io.github.aktissa.builders.WindowBuilder;
import io.github.aktissa.core.UI;
import io.github.aktissa.theme.DarkTheme;
import io.github.aktissa.theme.ThemeManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ThemeManager.set(new DarkTheme());

            WindowBuilder mainWindow = UI.window("Demo - Gestão de Tarefas");

            // Declaração dos componentes que precisam reter estado para interagir entre si
            TableBuilder tabelaTarefas = UI.table().columns("Tarefa", "Categoria", "Prioridade", "Esforço");
            RadioGroupBuilder grupoPrioridade = UI.radioGroup();
            TextFieldBuilder campoNome = UI.textField("Descrição da Tarefa", 20);
            ComboBoxBuilder comboCategoria = UI.comboBox("Categoria", "Desenvolvimento", "Infraestrutura", "Design");
            ProgressBarBuilder barraProgresso = UI.progressBar(0, 10).value(0);
            
            LabelBuilder rotuloEsforço = UI.label("Tempo estimado: 5 horas").secondary();
            SliderBuilder sliderEsforço = UI.slider(1, 40, 5)
                    .onChange(valor -> rotuloEsforço.text("Tempo estimado: " + valor + " horas").getRawLabel().repaint());

            // Montagem da interface
            mainWindow.size(850, 550).content(
                    UI.panel().border()
                            .add(UI.panel().title("Nova Demanda").vertical()
                                            .add(campoNome)
                                            .add(comboCategoria)
                                            .add(UI.panel().flowLeft()
                                                    .add(UI.label("Prioridade:").secondary())
                                                    .add(grupoPrioridade.add("Baixa"))
                                                    .add(grupoPrioridade.add("Média (Padrão)").accentText())
                                                    .add(grupoPrioridade.add("Alta"))
                                            )
                                            .add(UI.panel().vertical()
                                                    .add(rotuloEsforço)
                                                    .add(sliderEsforço)
                                            )
                                            .add(UI.panel().flowRight()
                                                    .add(UI.button("Registrar Tarefa")
                                                            .onClick(() -> registrarDemanda(
                                                                    mainWindow, campoNome, comboCategoria, 
                                                                    grupoPrioridade, sliderEsforço, tabelaTarefas, barraProgresso
                                                            )))
                                            )
                            , BorderLayout.WEST)

                            .add(UI.panel().title("Visão Geral do Sprint").vertical()
                                            .add(UI.scroll(tabelaTarefas).withBorder())
                                            .add(UI.panel().flowLeft()
                                                    .add(UI.checkBox("Sincronizar na nuvem").checked(true))
                                                    .add(UI.checkBox("Gerar log"))
                                            )
                                            .add(UI.panel().title("Capacidade da Equipe").vertical()
                                                    .add(barraProgresso)
                                                    .add(UI.panel().flowRight()
                                                            .add(UI.button("Deletar Tarefas").onClick(() -> {
                                                                UI.confirm(mainWindow, "Atenção", 
                                                                        "Isso apagará todas as tarefas da memória. Continuar?",
                                                                        () -> {
                                                                            tabelaTarefas.clear();
                                                                            barraProgresso.value(0);
                                                                        },
                                                                        "Cancelar", "Sim, Deletar"
                                                                );
                                                            }))
                                                    )
                                            )
                            , BorderLayout.CENTER)
            ).show();
        });
    }

    private static void registrarDemanda(WindowBuilder janela, TextFieldBuilder nome, ComboBoxBuilder categoria, 
                                         RadioGroupBuilder prioridade, SliderBuilder esforço, 
                                         TableBuilder tabela, ProgressBarBuilder progresso) {
        
        String textoNome = nome.getRawField().getText().trim();
        String textoPrioridade = prioridade.getSelectedValue();

        if (textoNome.isEmpty() || textoPrioridade == null) {
            UI.alert(janela, "Falha de Validação", "Preencha o nome da tarefa e selecione a prioridade.");
            return;
        }

        String textoCategoria = (String) categoria.getRawComboBox().getSelectedItem();
        String valorEsforco = esforço.getRawSlider().getValue() + "h";

        tabela.row(textoNome, textoCategoria, textoPrioridade, valorEsforco);
        nome.text(""); 

        int progressoAtual = progresso.getRawBar().getValue();
        if (progressoAtual < progresso.getRawBar().getMaximum()) {
            progresso.value(progressoAtual + 1);
        }
    }
}
