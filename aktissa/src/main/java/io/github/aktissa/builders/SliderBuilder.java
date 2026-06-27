package io.github.aktissa.builders;

import io.github.aktissa.theme.ThemeManager;
import javax.swing.JSlider;
import javax.swing.JComponent;
import java.util.function.Consumer;

public class SliderBuilder implements ComponentBuilder {
    private final JSlider component;

    public SliderBuilder(int min, int max, int value) {
        this.component = new JSlider(min, max, value);
        this.component.setBackground(ThemeManager.current().backgroundBase());
        this.component.setForeground(ThemeManager.current().accent());
        this.component.setFocusable(false);
    }

    public SliderBuilder onChange(Consumer<Integer> action) {
        this.component.addChangeListener(e -> {
            if (!this.component.getValueIsAdjusting()) {
                action.accept(this.component.getValue());
            }
        });
        return this;
    }

    public JSlider getRawSlider() {
        return this.component;
    }

    @Override
    public JComponent build() {
        return this.component;
    }
}