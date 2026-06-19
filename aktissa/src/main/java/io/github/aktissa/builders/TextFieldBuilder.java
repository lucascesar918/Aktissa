package io.github.aktissa.builders;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import io.github.aktissa.theme.ThemeManager;

public class TextFieldBuilder {
    private final JPanel container;
    private final JTextField field;
    private final JLabel label;

    public TextFieldBuilder(String labelText, int cols) {
        this.container = new JPanel(new BorderLayout(0, 5));
        this.container.setOpaque(false); 

        this.field = new JTextField(cols);
        this.field.setBackground(ThemeManager.current().backgroundInput());
        this.field.setForeground(ThemeManager.current().textPrimary());
        this.field.setCaretColor(ThemeManager.current().textSecondary());
        this.field.setBorder(BorderFactory.createCompoundBorder(
            ThemeManager.current().defaultLineBorder(),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        this.label = new JLabel(labelText);
        this.label.setForeground(ThemeManager.current().textSecondary());

        this.container.add(this.label, BorderLayout.NORTH);
        this.container.add(this.field, BorderLayout.CENTER);
    }

    public TextFieldBuilder readOnly() {
        this.field.setEditable(false);
        this.field.setBackground(ThemeManager.current().backgroundReadOnly());
        this.field.setForeground(ThemeManager.current().textMuted());
        return this;
    }

    public TextFieldBuilder text(String value) {
        this.field.setText(value);
        return this;
    }

    public TextFieldBuilder horizontal() {
        this.container.remove(this.label);
        this.container.add(this.label, BorderLayout.WEST);
        this.container.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        return this;
    }

    public JTextField getRawField() {
        return this.field;
    }

    public JComponent build() {
        return this.container;
    }
}