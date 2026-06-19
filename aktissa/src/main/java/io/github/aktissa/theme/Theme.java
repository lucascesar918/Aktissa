package io.github.aktissa.theme;

import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class Theme {
    private Theme() {}

    // Fundos de tela e contêineres
    public static final Color BACKGROUND_BASE = new Color(20, 20, 20);
    public static final Color BACKGROUND_COMPONENT = new Color(30, 30, 30);
    public static final Color BACKGROUND_INPUT = new Color(40, 40, 40);
    public static final Color BACKGROUND_READONLY = new Color(25, 25, 25);

    // Tipografia
    public static final Color TEXT_PRIMARY = Color.WHITE;
    public static final Color TEXT_SECONDARY = new Color(200, 200, 200);
    public static final Color TEXT_MUTED = new Color(130, 130, 130);

    // Destaques e elementos estruturais
    public static final Color ACCENT_CYAN = Color.CYAN;
    public static final Color BORDER_COLOR = new Color(60, 60, 60);

    // Bordas padronizadas reutilizáveis
    public static Border defaultLineBorder() {
        return BorderFactory.createLineBorder(BORDER_COLOR, 1);
    }
}