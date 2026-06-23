package io.github.aktissa.builders;

import javax.swing.JLabel;

import io.github.aktissa.theme.ThemeManager;

public class LabelBuilder {
    private final JLabel component;

    public LabelBuilder(String text) {
        this.component = new JLabel(text);
        this.component.setForeground(ThemeManager.current().textPrimary());
    }

    public LabelBuilder secondary() {
        this.component.setForeground(ThemeManager.current().textSecondary());
        return this;
    }

    public LabelBuilder muted() {
        this.component.setForeground(ThemeManager.current().textMuted());
        return this;
    }

    public LabelBuilder accent() {
        this.component.setForeground(ThemeManager.current().accent());
        return this;
    }

    public JLabel getRawLabel() {
        return this.component;
    }

    public JLabel build() {
        return this.component;
    }
}