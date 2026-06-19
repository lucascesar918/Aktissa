package io.github.aktissa.theme;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class LightTheme implements Theme {
    public Color backgroundBase() { return new Color(240, 240, 240); }
    public Color backgroundComponent() { return Color.WHITE; }
    public Color backgroundInput() { return Color.WHITE; }
    public Color backgroundReadOnly() { return new Color(220, 220, 220); }
    public Color textPrimary() { return Color.BLACK; }
    public Color textSecondary() { return new Color(100, 100, 100); }
    public Color textMuted() { return new Color(150, 150, 150); }
    public Color accent() { return new Color(0, 120, 215); }
    public Color border() { return new Color(200, 200, 200); }
    
    public Border defaultLineBorder() {
        return BorderFactory.createLineBorder(border(), 1);
    }
}