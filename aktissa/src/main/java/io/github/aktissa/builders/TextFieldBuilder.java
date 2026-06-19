package io.github.aktissa.builders;

import io.github.aktissa.theme.Theme;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import java.awt.BorderLayout;

public class TextFieldBuilder {
    private final JPanel container;
    private final JTextField field;
    private final JLabel label;

    public TextFieldBuilder(String labelText) {
        this.container = new JPanel(new BorderLayout(0, 5));
        this.container.setOpaque(false); 

        this.field = new JTextField();
        this.field.setBackground(Theme.BACKGROUND_INPUT);
        this.field.setForeground(Theme.TEXT_PRIMARY);
        this.field.setCaretColor(Theme.ACCENT_CYAN);
        this.field.setBorder(BorderFactory.createCompoundBorder(
            Theme.defaultLineBorder(),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        this.label = new JLabel(labelText);
        this.label.setForeground(Theme.TEXT_SECONDARY);

        this.container.add(this.label, BorderLayout.NORTH);
        this.container.add(this.field, BorderLayout.CENTER);
    }

    public TextFieldBuilder readOnly() {
        this.field.setEditable(false);
        this.field.setBackground(Theme.BACKGROUND_READONLY);
        this.field.setForeground(Theme.TEXT_MUTED);
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