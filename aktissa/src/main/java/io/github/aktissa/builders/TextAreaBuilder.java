package io.github.aktissa.builders;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import io.github.aktissa.theme.ThemeManager;

public class TextAreaBuilder {
    private final JTextArea area;
    private final JScrollPane scroll;

    public TextAreaBuilder(int rows, int cols) {
        this.area = new JTextArea(rows, cols);
        this.area.setBackground(ThemeManager.current().backgroundInput());
        this.area.setForeground(ThemeManager.current().textPrimary());
        this.area.setCaretColor(ThemeManager.current().accent());
        this.area.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        this.area.setLineWrap(true);
        this.area.setWrapStyleWord(true);

        this.scroll = new JScrollPane(this.area);
        this.scroll.setBorder(ThemeManager.current().defaultLineBorder());
        this.scroll.getViewport().setBackground(ThemeManager.current().backgroundInput());
    }

    public JTextArea getRawArea() {
        return this.area;
    }

    public JComponent build() {
        return this.scroll;
    }

    public TextAreaBuilder readOnly() {
        this.area.setEditable(false);
        return this;
    }

    public TextAreaBuilder readWrite() {
        this.area.setEditable(true);
        return this;
    }
}