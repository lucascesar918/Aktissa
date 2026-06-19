package io.github.aktissa.demo;

import javax.swing.SwingUtilities;

import io.github.aktissa.core.UI;
import io.github.aktissa.theme.DarkTheme;
import io.github.aktissa.theme.ThemeManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ThemeManager.set(new DarkTheme());

            UI.window("Sistema de Gestão")
                    .content(
                            UI.panel().title("Cadastro de Funcionário").vertical()
                                    .add(UI.textField("Nome Completo", 30).text("João da Silva"))
                                    .add(UI.textField("E-mail Corporativo", 30).text("joao@empresa.com"))
                                        .add(UI.comboBox("Nível de Acesso", "Desenvolvedor", "Designer", "Gerente").build())

                                    .add(UI.panel().title("Permissões de Acesso").vertical()
                                            .add(UI.scroll(
                                                    UI.table()
                                                            .columns("Módulo", "Nível de Acesso")
                                                            .row("Recursos Humanos", "Leitura")
                                                            .row("Banco de Dados", "Administrador")
                                            ).withBorder().build())
                                    )

                                    .add(UI.panel().flowCenter()
                                            .add(UI.button("Salvar Registro"))
                                            .add(UI.button("Cancelar Operação")))
                    )
                    .show();
        });
    }
}