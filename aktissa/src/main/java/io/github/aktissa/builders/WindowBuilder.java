package io.github.aktissa.builders;

import java.awt.Component;

import javax.swing.JFrame;

import io.github.aktissa.theme.Theme;

public class WindowBuilder {
    private final JFrame frame;

    public WindowBuilder(String title) {
        this.frame = new JFrame(title);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().setBackground(Theme.BACKGROUND_BASE);
    }

    public WindowBuilder size(int width, int height) {
        this.frame.setSize(width, height);
        this.frame.setLocationRelativeTo(null);
        return this;
    }

    public WindowBuilder content(PanelBuilder panelBuilder) {
        this.frame.add(panelBuilder.build());
        return this;
    }

    public WindowBuilder content(Component component) {
        this.frame.add(component);
        return this;
    }

    public void show() {
        this.frame.pack();
        this.frame.setVisible(true);
    }

    public JFrame getRawFrame() {
        return this.frame;
    }
}