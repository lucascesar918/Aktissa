package io.github.aktissa.builders;

import java.awt.BorderLayout;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import io.github.aktissa.theme.ThemeManager;

public class ComboBoxBuilder {
    private final JPanel container;
    private final JComboBox<String> component;
    private final JLabel label;

    public ComboBoxBuilder(String labelText, String... items) {
        this.container = new JPanel(new BorderLayout(0, 5));
        this.container.setOpaque(false);

        this.component = new JComboBox<>(items);
        this.component.setBackground(ThemeManager.current().backgroundInput());
        this.component.setForeground(ThemeManager.current().textPrimary());
        this.component.setFocusable(false);

        this.label = new JLabel(labelText);
        this.label.setForeground(ThemeManager.current().textSecondary());

        this.container.add(this.label, BorderLayout.NORTH);
        this.container.add(this.component, BorderLayout.CENTER);
    }

    public ComboBoxBuilder onSelect(Consumer<String> action) {
        this.component.addActionListener(e -> {
            String selected = (String) this.component.getSelectedItem();
            if (selected != null) {
                action.accept(selected);
            }
        });
        return this;
    }

    public ComboBoxBuilder horizontal() {
        this.container.remove(this.label);
        this.container.add(this.label, BorderLayout.WEST);
        this.container.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        return this;
    }

    public JComboBox<String> getRawComboBox() {
        return this.component;
    }

    public JComponent build() {
        return this.container;
    }
}