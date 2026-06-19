package io.github.aktissa.builders;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

import io.github.aktissa.theme.Theme;

public class ScrollPaneBuilder {
    private final JScrollPane scrollPane;

    public ScrollPaneBuilder(Component view) {
        this.scrollPane = new JScrollPane(view);
        this.scrollPane.getViewport().setBackground(Theme.BACKGROUND_BASE);
        this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.scrollPane.setBackground(Theme.BACKGROUND_BASE);
    }

    public ScrollPaneBuilder withBorder() {
        this.scrollPane.setBorder(Theme.defaultLineBorder());
        return this;
    }

    public JScrollPane getRawScroll() {
        return this.scrollPane;
    }

    public JComponent build() {
        return this.scrollPane;
    }
}