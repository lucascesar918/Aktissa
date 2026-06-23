package io.github.aktissa.builders;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import io.github.aktissa.theme.ThemeManager;

public class LabelBuilder {
    private final JLabel component;

    public LabelBuilder(String text) {
        this.component = new JLabel(text);
        this.component.setForeground(ThemeManager.current().textPrimary());
    }

    public LabelBuilder text(String text) {
        this.component.setText(text);
        return this;
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

    public LabelBuilder bold() {
        Font currentFont = this.component.getFont();
        this.component.setFont(currentFont.deriveFont(Font.BOLD));
        return this;
    }

    public LabelBuilder fontScale(float size) {
        Font currentFont = this.component.getFont();
        this.component.setFont(currentFont.deriveFont(size));
        return this;
    }

    public LabelBuilder alignLeft() {
        this.component.setHorizontalAlignment(SwingConstants.LEFT);
        return this;
    }

    public LabelBuilder alignCenter() {
        this.component.setHorizontalAlignment(SwingConstants.CENTER);
        return this;
    }

    public LabelBuilder alignRight() {
        this.component.setHorizontalAlignment(SwingConstants.RIGHT);
        return this;
    }

    public JLabel getRawLabel() {
        return this.component;
    }

    public JLabel build() {
        return this.component;
    }
}