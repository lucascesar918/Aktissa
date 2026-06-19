# Aktissa
Wrapper para simplificar a implementação de componentes do Java Swing, agilizando o desenvolvimento de interfaces gráficas. O foco do projeto é mitigar falhas do design ultrapassado do Swing, usando padrões de design como Builder e Factory para diminuir a verbosidade e tornar o código mais fluido e previsível. Enquanto você desenha a tela de forma despreocupada, o wrapper foca nos pormenores do toolkit de widgets.

Já é inclusa a funcionalidade de temas, sendo possível evitar o uso de cores datadas por padrão. Alguns temas de acessibilidade são incluídos por padrão, dentre eles:
- Temas de alto contraste (claro e escuro) para pessoas com baixa visão
- Tema para daltonismo

## Demonstração do Uso

Aqui está o exemplo da criação de uma janela completa, com componentes interativos e layouts, construída com uma única instrução.

```java
package io.github.aktissa.demo;

import io.github.aktissa.core.UI;
import io.github.aktissa.theme.ThemeManager;
import io.github.aktissa.theme.DarkTheme;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ThemeManager.set(new DarkTheme());

            UI.window("Sistema de Gestão")
              .size(500, 500)
              .content(
                  UI.panel().title("Cadastro de Funcionário").vertical()
                    .add(UI.textField("Nome Completo").text("João da Silva"))
                    .add(UI.textField("E-mail Corporativo").text("joao@empresa.com"))
                    .add(UI.comboBox("Desenvolvedor", "Designer", "Gerente").build())
                    
                    .add(UI.panel().title("Permissões de Acesso").vertical()
                        .add(UI.scroll(
                            UI.table()
                              .columns("Módulo", "Nível de Acesso")
                              .row("Recursos Humanos", "Leitura")
                              .row("Banco de Dados", "Administrador")
                        ).withBorder().build())
                    )
                    
                    .add(UI.panel().flow()
                           .add(UI.button("Salvar Registro").cyanText())
                           .add(UI.button("Cancelar Operação")))
              )
              .show();
        });
    }
}
```

## Recursos Principais

- **Construção declarativa:** monte componentes e layouts de forma semântica
- **Complexidade reduzida:** componentes podem ser criados de maneira mais facilidade por conta da abstração
- **Versatilidade de cores:** temas podem ser criados do zero ou escolhidos dentre os que vem por padrão

## Próximos Objetivos
- [ ] Criar documentação do projeto
- [ ] Tornar possível a troca de temas durante a execução do programa
- [ ] Publicar no Maven Central (quando o projeto se tornar maduro o suficiente)
