package io.github.aktissa.builders;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import io.github.aktissa.theme.Theme;
import io.github.aktissa.theme.ThemeManager;

public class DialogBuilder {
    private final JDialog dialog;
    private final JPanel contentPanel;
    private final JPanel buttonPanel;

    public DialogBuilder(WindowBuilder parent, String title) {
        this.dialog = new JDialog(parent.getRawFrame(), title, true);
        this.dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.dialog.getContentPane().setBackground(ThemeManager.current().backgroundBase());
        this.dialog.setLayout(new BorderLayout());

        this.contentPanel = new JPanel(new BorderLayout());
        this.contentPanel.setBackground(ThemeManager.current().backgroundBase());
        this.contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.buttonPanel.setBackground(ThemeManager.current().backgroundComponent());
        this.buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.dialog.add(this.contentPanel, BorderLayout.CENTER);
        this.dialog.add(this.buttonPanel, BorderLayout.SOUTH);
    }

    public DialogBuilder text(String message) {
        JLabel label = new JLabel(message);
        label.setForeground(ThemeManager.current().textPrimary());
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.contentPanel.add(label, BorderLayout.CENTER);
        return this;
    }

    public DialogBuilder addAction(String buttonText, Runnable action) {
        ButtonBuilder btn = new ButtonBuilder(buttonText).onClick(() -> {
            action.run();
            this.dialog.dispose();
        });
        this.buttonPanel.add(btn.build());
        return this;
    }

    public DialogBuilder addCloseAction(String buttonText) {
        ButtonBuilder btn = new ButtonBuilder(buttonText).onClick(this.dialog::dispose);
        this.buttonPanel.add(btn.build());
        return this;
    }

    public void show() {
        this.dialog.pack();
        this.dialog.setLocationRelativeTo(this.dialog.getParent());
        this.dialog.setVisible(true);
    }
}