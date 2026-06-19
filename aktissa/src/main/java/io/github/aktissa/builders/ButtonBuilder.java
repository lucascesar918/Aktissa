package io.github.aktissa.builders;

import java.awt.Color;

import javax.swing.JButton;

public class ButtonBuilder {
    private final JButton component;

    public ButtonBuilder(String text) {
        this.component = new JButton(text);
        this.component.setBackground(new Color(30, 30, 30));
        this.component.setForeground(Color.WHITE);
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
