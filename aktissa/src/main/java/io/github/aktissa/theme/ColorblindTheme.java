package io.github.aktissa.theme;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class ColorblindTheme implements Theme {
    public Color backgroundBase() { return new Color(20, 20, 20); }
    public Color backgroundComponent() { return new Color(30, 30, 30); }
    public Color backgroundInput() { return new Color(40, 40, 40); }
    public Color backgroundReadOnly() { return new Color(25, 25, 25); }
    public Color textPrimary() { return Color.WHITE; }
    public Color textSecondary() { return new Color(200, 200, 200); }
    public Color textMuted() { return new Color(130, 130, 130); }
    public Color accent() { return new Color(230, 159, 0); }
    public Color border() { return new Color(86, 180, 233); }
    
    public Border defaultLineBorder() {
        return BorderFactory.createLineBorder(border(), 1);
    }
}