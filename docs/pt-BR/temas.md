# Temas

<p align="center">
    <a href="../en/themes.md">🇬🇧 English</a>
    &nbsp;·&nbsp;
    <a href="temas.md"><strong>🇧🇷 Português</strong></a>
</p>

<p align="center"><a href="componentes-e-layouts.md">Componentes e layouts</a></p>

<hr>

A Aktissa aplica as cores do tema enquanto constrói seus componentes. O tema padrão é `DarkTheme`; selecione outro tema antes de criar a interface.

## Temas prontos

| Tema | Resumo da paleta |
| --- | --- |
| `DarkTheme` | Superfícies escuras, texto principal branco e acento azul. É o tema padrão. |
| `LightTheme` | Superfícies claras, texto escuro e acento azul. |
| `HighContrastDarkTheme` | Superfícies pretas, texto e borda brancos, acento amarelo e borda de 2 pixels. |
| `HighContrastLightTheme` | Superfícies brancas, texto e borda pretos, acento azul e borda de 2 pixels. |
| `ColorblindTheme` | Superfícies escuras, texto principal branco, acento laranja e borda azul. |

Esses nomes descrevem as paletas incluídas; não representam certificação de acessibilidade nem garantia de conformidade de contraste testada.

## Selecionar um tema pronto

Defina o tema antes de criar builders, janelas ou outros componentes:

```java
import javax.swing.SwingUtilities;

import io.github.aktissa.core.UI;
import io.github.aktissa.theme.LightTheme;
import io.github.aktissa.theme.ThemeManager;

public class TelaClara {
    public static void main(String[] args) {
        ThemeManager.set(new LightTheme());

        SwingUtilities.invokeLater(() -> UI.window("Tela clara")
                .content(UI.panel().vertical()
                        .add(UI.label("O tema selecionado estiliza esta tela.")))
                .show());
    }
}
```

`ThemeManager` mantém um único tema atual estático para a aplicação. Os componentes usam esse tema ao serem criados.

## Criar um tema personalizado

Para fazer uma pequena alteração, estenda um dos temas concretos e sobrescreva as cores que deseja trocar:

```java
import java.awt.Color;

import io.github.aktissa.theme.DarkTheme;

public class ForestTheme extends DarkTheme {
    @Override
    public Color accent() {
        return new Color(46, 184, 133);
    }
}
```

Selecione-o com `ThemeManager.set(new ForestTheme())` antes de construir a interface. Para implementar `Theme` diretamente, forneça todos os métodos de cor (`backgroundBase`, `backgroundComponent`, `backgroundInput`, `backgroundReadOnly`, `textPrimary`, `textSecondary`, `textMuted`, `accent`, `border`) e `defaultLineBorder()`.

## Troca de tema durante a execução

> **Limitação atual:** chamar `ThemeManager.set(...)` altera o tema retornado por `ThemeManager.current()`, mas não percorre nem atualiza os estilos de componentes já existentes. Por enquanto, selecione o tema antes de montar a janela. A troca de tema em tempo de execução ainda não é oferecida.