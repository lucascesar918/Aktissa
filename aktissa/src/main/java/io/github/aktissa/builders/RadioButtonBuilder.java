package io.github.aktissa.builders;

import io.github.aktissa.theme.ThemeManager;
import javax.swing.JRadioButton;
import java.awt.Cursor;

public class RadioButtonBuilder implements ComponentBuilder {
    private final JRadioButton component;

    public RadioButtonBuilder(String text) {
        this.component = new JRadioButton(text);
        
        this.component.setBackground(ThemeManager.current().backgroundBase());
        this.component.setForeground(ThemeManager.current().textPrimary());
        this.component.setFocusPainted(false);
        this.component.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public RadioButtonBuilder accentText() {
        this.component.setForeground(ThemeManager.current().accent());
        return this;
    }

    public RadioButtonBuilder mutedText() {
        this.component.setForeground(ThemeManager.current().textMuted());
        return this;
    }

    public RadioButtonBuilder onSelect(Runnable action) {
        this.component.addActionListener(e -> action.run());
        return this;
    }

    public JRadioButton getRawButton() {
        return this.component;
    }

    @Override
    public JRadioButton build() {
        return this.component;
    }
}