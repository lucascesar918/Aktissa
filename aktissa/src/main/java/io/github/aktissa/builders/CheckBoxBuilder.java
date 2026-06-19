package io.github.aktissa.builders;

import java.awt.Cursor;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

import io.github.aktissa.theme.Theme;

public class CheckBoxBuilder {
    private final JCheckBox component;

    public CheckBoxBuilder(String text) {
        this.component = new JCheckBox(text);
        this.component.setBackground(Theme.BACKGROUND_BASE);
        this.component.setForeground(Theme.TEXT_PRIMARY);
        this.component.setFocusPainted(false);
        this.component.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public CheckBoxBuilder checked(boolean isChecked) {
        this.component.setSelected(isChecked);
        return this;
    }

    public CheckBoxBuilder onToggle(Runnable action) {
        this.component.addActionListener(e -> action.run());
        return this;
    }

    public JCheckBox getRawBox() {
        return this.component;
    }

    public JComponent build() {
        return this.component;
    }
}