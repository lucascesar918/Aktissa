package io.github.aktissa.builders;

import io.github.aktissa.theme.ThemeManager;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import java.awt.BorderLayout;

public class PasswordFieldBuilder {
    private final JPanel container;
    private final JPasswordField field;
    private final JLabel label;

    public PasswordFieldBuilder(String labelText, int cols) {
        this.container = new JPanel(new BorderLayout(0, 5));
        this.container.setOpaque(false);

        this.field = new JPasswordField(cols);
        this.field.setBackground(ThemeManager.current().backgroundInput());
        this.field.setForeground(ThemeManager.current().textPrimary());
        this.field.setCaretColor(ThemeManager.current().accent());
        this.field.setBorder(BorderFactory.createCompoundBorder(
            ThemeManager.current().defaultLineBorder(),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        this.label = new JLabel(labelText);
        this.label.setForeground(ThemeManager.current().textSecondary());

        this.container.add(this.label, BorderLayout.NORTH);
        this.container.add(this.field, BorderLayout.CENTER);
    }

    public PasswordFieldBuilder horizontal() {
        this.container.remove(this.label);
        this.container.add(this.label, BorderLayout.WEST);
        this.container.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        return this;
    }

    public JPasswordField getRawField() {
        return this.field;
    }

    public JComponent build() {
        return this.container;
    }
}