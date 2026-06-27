package io.github.aktissa.builders;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import io.github.aktissa.theme.ThemeManager;

public class ScrollPaneBuilder implements ComponentBuilder {
    private final JScrollPane scrollPane;

    public ScrollPaneBuilder(Component view) {
        this.scrollPane = new JScrollPane(view);
        this.scrollPane.getViewport().setBackground(ThemeManager.current().backgroundBase());
        this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.scrollPane.setBackground(ThemeManager.current().backgroundBase());
    }

    public ScrollPaneBuilder withBorder() {
        this.scrollPane.setBorder(ThemeManager.current().defaultLineBorder());
        return this;
    }

    public JScrollPane getRawScroll() {
        return this.scrollPane;
    }

    @Override
    public JComponent build() {
        return this.scrollPane;
    }
}