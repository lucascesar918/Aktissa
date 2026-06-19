package io.github.aktissa.builders;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import io.github.aktissa.theme.ThemeManager;

public class PanelBuilder {
    private final JPanel component;
    private int currentAxis = -1;

    public PanelBuilder() {
        this.component = new JPanel();
        this.component.setBackground(ThemeManager.current().backgroundBase());
        this.component.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
    }

    public PanelBuilder vertical() {
        this.currentAxis = BoxLayout.Y_AXIS;
        this.component.setLayout(new BoxLayout(this.component, this.currentAxis));
        return this;
    }

    public PanelBuilder horizontal() {
        this.currentAxis = BoxLayout.X_AXIS;
        this.component.setLayout(new BoxLayout(this.component, this.currentAxis));
        return this;
    }

    public PanelBuilder flow() {
        return this.flowLeft();
    }

    public PanelBuilder flowLeft() {
        this.currentAxis = -1;
        this.component.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        return this;
    }

    public PanelBuilder flowCenter() {
        this.currentAxis = -1;
        this.component.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        return this;
    }

    public PanelBuilder flowRight() {
        this.currentAxis = -1;
        this.component.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        return this;
    }

    public PanelBuilder grid(int rows, int cols) {
        this.currentAxis = -1;
        this.component.setLayout(new GridLayout(rows, cols, 15, 15));
        return this;
    }

    public PanelBuilder border() {
        this.currentAxis = -1;
        this.component.setLayout(new BorderLayout(15, 15));
        return this;
    }

    public PanelBuilder title(String text) {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(ThemeManager.current().defaultLineBorder(), text);
        titledBorder.setTitleColor(ThemeManager.current().textSecondary());
        
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        this.component.setBorder(BorderFactory.createCompoundBorder(titledBorder, padding));
        
        return this;
    }

    private void injectAutoSpacing() {
        if (this.component.getComponentCount() > 0) {
            if (this.currentAxis == BoxLayout.Y_AXIS) {
                this.component.add(Box.createRigidArea(new Dimension(0, 15)));
            } else if (this.currentAxis == BoxLayout.X_AXIS) {
                this.component.add(Box.createRigidArea(new Dimension(15, 0)));
            }
        }
    }

    public PanelBuilder add(Component child) {
        injectAutoSpacing();
        this.component.add(child);
        return this;
    }

    public PanelBuilder add(Component child, Object constraints) {
        this.component.add(child, constraints);
        return this;
    }

    public PanelBuilder add(ButtonBuilder builder) {
        injectAutoSpacing();
        this.component.add(builder.build());
        return this;
    }

    public PanelBuilder add(ButtonBuilder builder, Object constraints) {
        this.component.add(builder.build(), constraints);
        return this;
    }

    public PanelBuilder add(TextFieldBuilder builder) {
        injectAutoSpacing();
        this.component.add(builder.build());
        return this;
    }

    public PanelBuilder add(TextFieldBuilder builder, Object constraints) {
        this.component.add(builder.build(), constraints);
        return this;
    }

    public PanelBuilder add(PanelBuilder builder) {
        injectAutoSpacing();
        this.component.add(builder.build());
        return this;
    }

    public PanelBuilder add(PanelBuilder builder, Object constraints) {
        this.component.add(builder.build(), constraints);
        return this;
    }

    public JPanel build() {
        return this.component;
    }
}