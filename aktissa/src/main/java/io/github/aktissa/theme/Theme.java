package io.github.aktissa.theme;

import java.awt.Color;

import javax.swing.border.Border;

public interface Theme {
    Color backgroundBase();
    Color backgroundComponent();
    Color backgroundInput();
    Color backgroundReadOnly();
    Color textPrimary();
    Color textSecondary();
    Color textMuted();
    Color accent();
    Color border();
    Border defaultLineBorder();
}