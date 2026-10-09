# Getting Started

<p align="center">
    <a href="getting-started.md"><strong>🇬🇧 English</strong></a>
    &nbsp;·&nbsp;
    <a href="../pt-BR/primeiros-passos.md">🇧🇷 Português</a>
</p>

<p align="center"><a href="../../README.md">Project README</a></p>

<hr>

This guide is for Java developers who already know Swing and want to build a small screen with Aktissa. The example uses the library's default component styling and panel spacing; it only configures the behavior specific to this screen.

## Requirements

- Java 17 or later
- Maven

## Install Aktissa Locally

> **Not published yet:** Aktissa is not available from a remote Maven repository. From the repository root, install the library into your local Maven repository:

```sh
cd aktissa
mvn install
```

Add this dependency to the `pom.xml` of the application that will use Aktissa:

```xml
<dependency>
    <groupId>io.github.aktissa</groupId>
    <artifactId>aktissa</artifactId>
    <version>1.2.1</version>
</dependency>
```

## Build a Small Screen

This focus-session screen uses a window, titled panels, a slider, a progress bar, and a button. The slider updates its neighboring label, and the button completes the progress and opens a dialog.

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
            WindowBuilder window = UI.window("Focus Session");
            LabelBuilder duration = UI.label("Session length: 45 min").secondary();
            ProgressBarBuilder progress = UI.progressBar(0, 45).value(12);
            SliderBuilder durationSlider = UI.slider(15, 90, 45)
                    .onChange(minutes -> duration.text("Session length: " + minutes + " min"));

            window.content(
                    UI.panel().border()
                            .add(UI.panel().title("TODAY'S FOCUS").vertical()
                                            .add(UI.label("Prepare the next release").bold())
                                            .add(UI.label("One task. One uninterrupted block.").secondary()),
                                    BorderLayout.NORTH)
                            .add(UI.panel().title("SESSION").vertical()
                                            .add(duration)
                                            .add(durationSlider)
                                            .add(progress)
                                            .add(UI.panel().flowRight()
                                                    .add(UI.button("Complete session").onClick(() -> {
                                                        progress.value(45);
                                                        UI.alert(window, "Session complete", "Take a short break.");
                                                    }))),
                                    BorderLayout.CENTER)
            ).show();
        });
    }
}
```

The outer panel uses Swing's `BorderLayout`; the inner panels use Aktissa's titled borders and vertical layout. Those vertical panels add spacing automatically, and the components receive the current theme when they are created. No explicit color, font, or padding setup is needed for this example.

## Run the Repository Demo

The interactive task-management demo is in `aktissa/src/test/java/io/github/aktissa/demo/Main.java`. Open that class in an IDE and run its `main` method. Running `mvn test` from `aktissa/` compiles the module and its test sources, but does not launch the Swing window.

## Theme Behavior

Aktissa starts with `DarkTheme`. To select a preset, call `ThemeManager.set(...)` before constructing the components, for example:

```java
ThemeManager.set(new LightTheme());
```

> **Current limitation:** Theme changes are not propagated to components that have already been constructed. Set the theme once, before building the window.

## Next Guides

- [Components and Layouts](components-and-layouts.md)
- [Themes](themes.md)