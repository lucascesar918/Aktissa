# Aktissa

## Sobre o Projeto

Biblioteca para simplificar a implementação de componentes do Java Swing, agilizando o desenvolvimento de interfaces gráficas. O foco do projeto é mitigar falhas do design ultrapassado do Swing, usando padrões de design para diminuir a verbosidade e tornar o código mais fluido e previsível. Enquanto você desenha a tela de forma despreocupada, o biblioteca foca nos pormenores do Swing.

### Sistema de Temas
A biblioteca abstrai as cores necessárias para um programa e adota uma forma de customizar as cores de um software com o uso de paletas de cores customizadas ou com o uso dos temas inclusos na biblioteca. O suporte a temas claro e escuro é nativo. Pensando em acessibilidade e legibilidade, o projeto também inclui:
- Temas de alto contraste (claro e escuro) para usuários com baixa visão
- Tema especializado para daltonismo

## Demonstração do Uso

Aqui está o exemplo da criação da tela de um gerenciador de sprints. Os componentes da biblioteca interagem entre si instantaneamente e a interface unificada torna desnecessário o excesso de chamadas intermediárias.

A [demo completa](aktissa/src/test/java/io/github/aktissa/demo/Main.java) (funcionando de forma interativa) está disponível no repositório.

```java
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
```

Em poucas linhas a seguinte tela é gerada:

<img width="849" height="528" alt="demo aktissa" src="https://github.com/user-attachments/assets/b04436d4-2f07-456b-ba22-7333fcd7b631" />


## Recursos Principais

- **Construção declarativa:** monte componentes e layouts de forma semântica
- **Complexidade reduzida:** componentes podem ser criados de maneira mais facilidade por conta da abstração
- **Versatilidade de cores:** temas podem ser criados do zero ou escolhidos dentre os que vem por padrão

## Próximos Objetivos
- [ ] Criar documentação do projeto
- [ ] Tornar possível a troca de temas durante a execução do programa
- [ ] Publicar em algum repositório (quando o projeto se tornar maduro o suficiente ou quando eu tiver paciência pra isso)
