package io.github.aktissa.builders;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import io.github.aktissa.theme.Theme;

public class PanelBuilder {
    private final JPanel component;

    public PanelBuilder() {
        this.component = new JPanel();
        this.component.setBackground(Theme.BACKGROUND_BASE);
    }

    public PanelBuilder vertical() {
        this.component.setLayout(new BoxLayout(this.component, BoxLayout.Y_AXIS));
        return this;
    }

    public PanelBuilder horizontal() {
        this.component.setLayout(new BoxLayout(this.component, BoxLayout.X_AXIS));
        return this;
    }

    public PanelBuilder flow() {
        this.component.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        return this;
    }

    public PanelBuilder grid(int rows, int cols) {
        this.component.setLayout(new GridLayout(rows, cols, 5, 5));
        return this;
    }

    public PanelBuilder border() {
        this.component.setLayout(new BorderLayout(5, 5));
        return this;
    }

    public PanelBuilder title(String text) {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(Theme.defaultLineBorder(), text);
        titledBorder.setTitleColor(Theme.TEXT_SECONDARY);
        
        Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.component.setBorder(BorderFactory.createCompoundBorder(titledBorder, padding));
        
        return this;
    }

    public PanelBuilder add(Component child) {
        this.component.add(child);
        return this;
    }

    public PanelBuilder add(Component child, Object constraints) {
        this.component.add(child, constraints);
        return this;
    }

    public PanelBuilder add(ButtonBuilder builder) {
        this.component.add(builder.build());
        return this;
    }

    public PanelBuilder add(ButtonBuilder builder, Object constraints) {
        this.component.add(builder.build(), constraints);
        return this;
    }

    public PanelBuilder add(TextFieldBuilder builder) {
        this.component.add(builder.build());
        return this;
    }

    public PanelBuilder add(TextFieldBuilder builder, Object constraints) {
        this.component.add(builder.build(), constraints);
        return this;
    }

    public PanelBuilder add(PanelBuilder builder) {
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