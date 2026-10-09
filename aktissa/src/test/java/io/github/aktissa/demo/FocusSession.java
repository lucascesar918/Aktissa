package io.github.aktissa.demo;

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