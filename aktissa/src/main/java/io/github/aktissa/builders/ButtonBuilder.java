package io.github.aktissa.builders;

import io.github.aktissa.theme.ThemeManager;

import java.awt.Color;

import javax.swing.JButton;

public class ButtonBuilder {
    private final JButton component;

    public ButtonBuilder(String text) {
        this.component = new JButton(text);
        this.component.setBackground(ThemeManager.current().backgroundComponent());
        this.component.setForeground(ThemeManager.current().textPrimary());
        this.component.setFocusPainted(false);
    }
    
    public ButtonBuilder onClick(Runnable action) {
        this.component.addActionListener(e -> action.run());
        return this;
    }

    public JButton build() {
        return this.component;
    }

}
