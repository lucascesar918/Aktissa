package io.github.aktissa.builders;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;

import io.github.aktissa.theme.ThemeManager;

public class WindowBuilder {
    private final JFrame frame;
    private Dimension customSize;

    public WindowBuilder(String title) {
        this.frame = new JFrame(title);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().setBackground(ThemeManager.current().backgroundBase());
    }

    public WindowBuilder size(int width, int height) {
        this.customSize = new Dimension(width, height);
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
        if (this.customSize != null) {
            this.frame.setSize(this.customSize);
            this.frame.setLocationRelativeTo(null);
        } else {
            this.frame.pack();
            this.frame.setLocationRelativeTo(null);
        }
        this.frame.setVisible(true);
    }

    public JFrame getRawFrame() {
        return this.frame;
    }
}