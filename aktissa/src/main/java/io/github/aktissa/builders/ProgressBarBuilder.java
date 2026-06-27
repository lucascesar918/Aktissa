package io.github.aktissa.builders;

import io.github.aktissa.theme.ThemeManager;
import javax.swing.JProgressBar;
import javax.swing.JComponent;

public class ProgressBarBuilder implements ComponentBuilder {
    private final JProgressBar component;

    public ProgressBarBuilder(int min, int max) {
        this.component = new JProgressBar(min, max);
        this.component.setBackground(ThemeManager.current().backgroundBase());
        this.component.setForeground(ThemeManager.current().accent());
        this.component.setStringPainted(true);
        this.component.setBorderPainted(false);
    }

    public ProgressBarBuilder value(int value) {
        this.component.setValue(value);
        return this;
    }

    public ProgressBarBuilder indeterminate(boolean flag) {
        this.component.setIndeterminate(flag);
        this.component.setStringPainted(!flag);
        return this;
    }

    public JProgressBar getRawBar() {
        return this.component;
    }

    @Override
    public JComponent build() {
        return this.component;
    }
}