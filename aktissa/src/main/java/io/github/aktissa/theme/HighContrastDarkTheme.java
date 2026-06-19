package io.github.aktissa.theme;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class HighContrastDarkTheme implements Theme {
    public Color backgroundBase() { return Color.BLACK; }
    public Color backgroundComponent() { return Color.BLACK; }
    public Color backgroundInput() { return Color.BLACK; }
    public Color backgroundReadOnly() { return Color.BLACK; }
    public Color textPrimary() { return Color.WHITE; }
    public Color textSecondary() { return Color.WHITE; }
    public Color textMuted() { return Color.WHITE; }
    public Color accent() { return Color.YELLOW; }
    public Color border() { return Color.WHITE; }
    
    public Border defaultLineBorder() {
        return BorderFactory.createLineBorder(border(), 2);
    }
}