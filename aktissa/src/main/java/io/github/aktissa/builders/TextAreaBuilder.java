package io.github.aktissa.builders;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class TextAreaBuilder {
    private final javax.swing.JPanel container;
    private final javax.swing.JTextArea field;
    private final JLabel label;

    public TextAreaBuilder(String labelText, int rows, int cols) {
        this.field = new JTextArea(rows, cols);
        this.label = new JLabel(labelText);
        this.container = new javax.swing.JPanel();
        this.container.setLayout(new javax.swing.BoxLayout(this.container, javax.swing.BoxLayout.Y_AXIS));
        this.container.add(this.label);
        this.container.add(this.field);
    }

    public javax.swing.JPanel build() {
        return this.container;
    }

    public TextAreaBuilder readOnly() {
        this.field.setEditable(false);
        return this;
    }

    public TextAreaBuilder text(String value) {
        this.field.setText(value);
        return this;
    }

    public JTextArea getRawField() {
        return this.field;
    }

    public TextAreaBuilder horizontal() {
        this.container.remove(this.label);
        this.container.setLayout(new javax.swing.BoxLayout(this.container, javax.swing.BoxLayout.X_AXIS));
        this.container.add(this.label);
        this.container.add(this.field);
        return this;
    }

}
