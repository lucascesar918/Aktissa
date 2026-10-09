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

            // Keep references to components whose state is needed elsewhere in the screen.
            TableBuilder taskTable = UI.table().columns("Tarefa", "Categoria", "Prioridade", "Esforço");
            RadioGroupBuilder priorityGroup = UI.radioGroup();
            TextFieldBuilder taskNameField = UI.textField("Descrição da Tarefa", 20);
            ComboBoxBuilder categoryComboBox = UI.comboBox("Categoria", "Desenvolvimento", "Infraestrutura", "Design");
            ProgressBarBuilder progressBar = UI.progressBar(0, 10).value(0);
            
            LabelBuilder effortLabel = UI.label("Tempo estimado: 5 horas").secondary();
            SliderBuilder effortSlider = UI.slider(1, 40, 5)
                    .onChange(effortValue -> effortLabel.text("Tempo estimado: " + effortValue + " horas").getRawLabel().repaint());

            // Build the interface.
            mainWindow.size(850, 550).content(
                    UI.panel().border()
                            .add(UI.panel().title("Nova Demanda").vertical()
                                            .add(taskNameField)
                                            .add(categoryComboBox)
                                            .add(UI.panel().flowLeft()
                                                    .add(UI.label("Prioridade:").secondary())
                                                    .add(priorityGroup.add("Baixa"))
                                                    .add(priorityGroup.add("Média (Padrão)").accentText())
                                                    .add(priorityGroup.add("Alta"))
                                            )
                                            .add(UI.panel().vertical()
                                                    .add(effortLabel)
                                                    .add(effortSlider)
                                            )
                                            .add(UI.panel().flowRight()
                                                    .add(UI.button("Registrar Tarefa")
                                                            .onClick(() -> registerTask(
                                                                    mainWindow, taskNameField, categoryComboBox,
                                                                    priorityGroup, effortSlider, taskTable, progressBar
                                                            )))
                                            )
                            , BorderLayout.WEST)

                            .add(UI.panel().title("Visão Geral do Sprint").vertical()
                                            .add(UI.scroll(taskTable).withBorder())
                                            .add(UI.panel().flowLeft()
                                                    .add(UI.checkBox("Sincronizar na nuvem").checked(true))
                                                    .add(UI.checkBox("Gerar log"))
                                            )
                                            .add(UI.panel().title("Capacidade da Equipe").vertical()
                                                    .add(progressBar)
                                                    .add(UI.panel().flowRight()
                                                            .add(UI.button("Deletar Tarefas").onClick(() -> {
                                                                UI.confirm(mainWindow, "Atenção", 
                                                                        "Isso apagará todas as tarefas da memória. Continuar?",
                                                                        () -> {
                                                                            taskTable.clear();
                                                                            progressBar.value(0);
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

        private static void registerTask(WindowBuilder window, TextFieldBuilder taskNameField,
                                                                         ComboBoxBuilder categoryComboBox, RadioGroupBuilder priorityGroup,
                                                                         SliderBuilder effortSlider, TableBuilder taskTable,
                                                                         ProgressBarBuilder progressBar) {
        
                String taskName = taskNameField.getRawField().getText().trim();
                String selectedPriority = priorityGroup.getSelectedValue();

                if (taskName.isEmpty() || selectedPriority == null) {
                        UI.alert(window, "Falha de Validação", "Preencha o nome da tarefa e selecione a prioridade.");
            return;
        }

                String selectedCategory = (String) categoryComboBox.getRawComboBox().getSelectedItem();
                String effortValue = effortSlider.getRawSlider().getValue() + "h";

                taskTable.row(taskName, selectedCategory, selectedPriority, effortValue);
                taskNameField.text("");

                int currentProgress = progressBar.getRawBar().getValue();
                if (currentProgress < progressBar.getRawBar().getMaximum()) {
                        progressBar.value(currentProgress + 1);
        }
    }
}
