package io.github.aktissa.theme;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class HighContrastLightTheme implements Theme {
    public Color backgroundBase() { return Color.WHITE; }
    public Color backgroundComponent() { return Color.WHITE; }
    public Color backgroundInput() { return Color.WHITE; }
    public Color backgroundReadOnly() { return Color.WHITE; }
    public Color textPrimary() { return Color.BLACK; }
    public Color textSecondary() { return Color.BLACK; }
    public Color textMuted() { return Color.BLACK; }
    public Color accent() { return Color.BLUE; }
    public Color border() { return Color.BLACK; }
    
    public Border defaultLineBorder() {
        return BorderFactory.createLineBorder(border(), 2);
    }
}