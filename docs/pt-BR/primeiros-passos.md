# Primeiros passos

<p align="center">
    <a href="../en/getting-started.md">🇬🇧 English</a>
    &nbsp;·&nbsp;
    <a href="primeiros-passos.md"><strong>🇧🇷 Português</strong></a>
</p>

<p align="center"><a href="../../README.pt-BR.md">README do projeto</a></p>

<hr>

Este guia é para pessoas desenvolvedoras Java que já conhecem Swing e querem montar uma tela pequena com Aktissa. O exemplo aproveita o estilo padrão dos componentes e o espaçamento automático dos painéis; só configura o comportamento específico desta tela.

## Requisitos

- Java 17 ou superior
- Maven

## Instalar Aktissa localmente

> **Ainda não publicada:** Aktissa não está disponível em um repositório Maven remoto. A partir da raiz do repositório, instale a biblioteca no repositório Maven local:

```sh
cd aktissa
mvn install
```

Adicione esta dependência ao `pom.xml` da aplicação que usará Aktissa:

```xml
<dependency>
    <groupId>io.github.aktissa</groupId>
    <artifactId>aktissa</artifactId>
    <version>1.2.1</version>
</dependency>
```

## Monte uma tela pequena

Esta tela de sessão de foco usa uma janela, painéis com título, um slider, uma barra de progresso e um botão. O slider atualiza o rótulo ao lado, e o botão completa o progresso e abre um diálogo.

```java
import java.awt.BorderLayout;

import javax.swing.SwingUtilities;

import io.github.aktissa.builders.LabelBuilder;
import io.github.aktissa.builders.ProgressBarBuilder;
import io.github.aktissa.builders.SliderBuilder;
import io.github.aktissa.builders.WindowBuilder;
import io.github.aktissa.core.UI;

public class FocusSession {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WindowBuilder window = UI.window("Sessão de foco");
            LabelBuilder duration = UI.label("Duração da sessão: 45 min").secondary();
            ProgressBarBuilder progress = UI.progressBar(0, 45).value(12);
            SliderBuilder durationSlider = UI.slider(15, 90, 45)
                    .onChange(minutes -> duration.text("Duração da sessão: " + minutes + " min"));

            window.content(
                    UI.panel().border()
                            .add(UI.panel().title("FOCO DE HOJE").vertical()
                                            .add(UI.label("Preparar a próxima versão").bold())
                                            .add(UI.label("Uma tarefa. Um bloco sem interrupções.").secondary()),
                                    BorderLayout.NORTH)
                            .add(UI.panel().title("SESSÃO").vertical()
                                            .add(duration)
                                            .add(durationSlider)
                                            .add(progress)
                                            .add(UI.panel().flowRight()
                                                    .add(UI.button("Concluir sessão").onClick(() -> {
                                                        progress.value(45);
                                                        UI.alert(window, "Sessão concluída", "Faça uma pausa curta.");
                                                    }))),
                                    BorderLayout.CENTER)
            ).show();
        });
    }
}
```

O painel externo usa o `BorderLayout` do Swing; os painéis internos usam bordas com título e layout vertical da Aktissa. Esses painéis verticais adicionam espaçamento automaticamente, e os componentes recebem o tema atual ao serem criados. Este exemplo não precisa configurar cores, fontes ou margens manualmente.

## Executar a demonstração do repositório

A demonstração interativa de gerenciamento de tarefas está em `aktissa/src/test/java/io/github/aktissa/demo/Main.java`. Abra essa classe em uma IDE e execute seu método `main`. Executar `mvn test` dentro de `aktissa/` compila o módulo e os fontes de teste, mas não abre a janela Swing.

## Comportamento dos temas

Aktissa inicia com `DarkTheme`. Para selecionar um tema pronto, chame `ThemeManager.set(...)` antes de construir os componentes, por exemplo:

```java
ThemeManager.set(new LightTheme());
```

> **Limitação atual:** mudanças de tema não são propagadas aos componentes que já foram construídos. Defina o tema uma vez, antes de montar a janela.

## Próximos guias

- [Componentes e layouts](componentes-e-layouts.md)
- [Temas](temas.md)