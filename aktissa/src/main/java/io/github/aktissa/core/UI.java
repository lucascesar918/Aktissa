package io.github.aktissa.core;

import io.github.aktissa.builders.ButtonBuilder;
import io.github.aktissa.builders.CheckBoxBuilder;
import io.github.aktissa.builders.ComboBoxBuilder;
import io.github.aktissa.builders.DialogBuilder;
import io.github.aktissa.builders.PanelBuilder;
import io.github.aktissa.builders.ScrollPaneBuilder;
import io.github.aktissa.builders.TableBuilder;
import io.github.aktissa.builders.TextFieldBuilder;
import io.github.aktissa.builders.WindowBuilder;

public class UI {
    private UI() {}

    public static ButtonBuilder button(String text) {
        return new ButtonBuilder(text);
    }

    public static PanelBuilder panel() {
        return new PanelBuilder();
    }

    public static WindowBuilder window(String title) {
        return new WindowBuilder(title);
    }

    public static TextFieldBuilder textField(String label, int cols) {
        return new TextFieldBuilder(label, cols);
    }

    public static CheckBoxBuilder checkBox(String text) {
        return new CheckBoxBuilder(text);
    }

    @SafeVarargs
    public static <T> ComboBoxBuilder<T> comboBox(T... items) {
        return new ComboBoxBuilder<>(items);
    }

    public static TableBuilder table() {
        return new TableBuilder();
    }

    public static ScrollPaneBuilder scroll(java.awt.Component view) {
        return new ScrollPaneBuilder(view);
    }

    public static ScrollPaneBuilder scroll(TableBuilder builder) {
        return new ScrollPaneBuilder(builder.build());
    }

    public static ScrollPaneBuilder scroll(PanelBuilder builder) {
        return new ScrollPaneBuilder(builder.build());
    }

    public static void alert(WindowBuilder parent, String title, String message) {
        new DialogBuilder(parent, title)
            .text(message)
            .addCloseAction("OK")
            .show();
    }

    public static void confirm(WindowBuilder parent, String title, String message, Runnable onConfirm, String cancelText, String confirmText) {
        new DialogBuilder(parent, title)
            .text(message)
            .addCloseAction(cancelText)
            .addAction(confirmText, onConfirm)
            .show();
    }
}
