package io.github.aktissa.builders;

import java.awt.Color;
import java.awt.Component;
import java.util.function.Consumer;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;

import io.github.aktissa.theme.Theme;
import io.github.aktissa.theme.ThemeManager;

public class ComboBoxBuilder<T> {
    private final JComboBox<T> component;

    @SafeVarargs
    public ComboBoxBuilder(T... items) {
        this.component = new JComboBox<>(items);
        this.component.setBackground(ThemeManager.current().backgroundInput());
        this.component.setForeground(ThemeManager.current().textPrimary());
        this.component.setBorder(ThemeManager.current().defaultLineBorder());
        this.component.setFocusable(false);

        this.component.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    c.setBackground(ThemeManager.current().accent());
                    c.setForeground(ThemeManager.current().textPrimary());
                } else {
                    c.setBackground(ThemeManager.current().backgroundInput());
                    c.setForeground(ThemeManager.current().textPrimary());
                }
                return c;
            }
        });
    }

    public ComboBoxBuilder<T> onSelect(Consumer<T> action) {
        this.component.addActionListener(e -> {
            @SuppressWarnings("unchecked")
            T selected = (T) this.component.getSelectedItem();
            if (selected != null) {
                action.accept(selected);
            }
        });
        return this;
    }

    public JComboBox<T> getRawCombo() {
        return this.component;
    }

    public JComponent build() {
        return this.component;
    }
}