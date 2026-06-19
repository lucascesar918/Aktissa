package io.github.aktissa.theme;

public class ThemeManager {
    private static Theme currentTheme = new DarkTheme();

    private ThemeManager() {}

    public static Theme current() {
        return currentTheme;
    }

    public static void set(Theme theme) {
        currentTheme = theme;
    }
}