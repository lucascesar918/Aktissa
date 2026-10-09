# Themes

<p align="center">
    <a href="themes.md"><strong>🇬🇧 English</strong></a>
    &nbsp;·&nbsp;
    <a href="../pt-BR/temas.md">🇧🇷 Português</a>
</p>

<p align="center"><a href="components-and-layouts.md">Components and Layouts</a></p>

<hr>

Aktissa applies theme colors while its components are constructed. The default theme is `DarkTheme`; choose another theme before creating the interface.

## Presets

| Theme | Palette summary |
| --- | --- |
| `DarkTheme` | Dark surfaces, white primary text, blue accent. This is the default. |
| `LightTheme` | Light surfaces, dark text, blue accent. |
| `HighContrastDarkTheme` | Black surfaces, white text and border, yellow accent, 2-pixel border. |
| `HighContrastLightTheme` | White surfaces, black text and border, blue accent, 2-pixel border. |
| `ColorblindTheme` | Dark surfaces, white primary text, orange accent, blue border. |

These names describe the included palettes; they are not a claim of accessibility certification or tested contrast compliance.

## Choose a Preset

Set the theme before creating builders, windows, or other components:

```java
import javax.swing.SwingUtilities;

import io.github.aktissa.core.UI;
import io.github.aktissa.theme.LightTheme;
import io.github.aktissa.theme.ThemeManager;

public class LightScreen {
    public static void main(String[] args) {
        ThemeManager.set(new LightTheme());

        SwingUtilities.invokeLater(() -> UI.window("Light screen")
                .content(UI.panel().vertical()
                        .add(UI.label("The selected theme styles this screen.")))
                .show());
    }
}
```

`ThemeManager` stores one static current theme for the application. Components use that current theme as they are created.

## Create a Custom Theme

For a small change, extend one of the concrete themes and override the colors you want to replace:

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

Select it with `ThemeManager.set(new ForestTheme())` before constructing the UI. To implement `Theme` directly instead, provide all of its color methods (`backgroundBase`, `backgroundComponent`, `backgroundInput`, `backgroundReadOnly`, `textPrimary`, `textSecondary`, `textMuted`, `accent`, `border`) and `defaultLineBorder()`.

## Runtime Theme Changes

> **Current limitation:** Calling `ThemeManager.set(...)` changes the theme returned by `ThemeManager.current()`, but it does not traverse or restyle components that already exist. For now, select the theme before building the window. Live theme switching is not currently provided.