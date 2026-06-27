package io.github.aktissa.builders;

import java.awt.Cursor;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import io.github.aktissa.theme.ThemeManager;

public class CheckBoxBuilder implements ComponentBuilder {
    private final JCheckBox component;

    public CheckBoxBuilder(String text) {
        this.component = new JCheckBox(text);
        this.component.setBackground(ThemeManager.current().backgroundBase());
        this.component.setForeground(ThemeManager.current().textPrimary());
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

    @Override
    public JComponent build() {
        return this.component;
    }
}