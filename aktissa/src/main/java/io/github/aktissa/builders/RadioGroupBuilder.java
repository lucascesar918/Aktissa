package io.github.aktissa.builders;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

public class RadioGroupBuilder {
    private final ButtonGroup group;

    public RadioGroupBuilder() {
        this.group = new ButtonGroup();
    }

    public RadioButtonBuilder add(String text) {
        RadioButtonBuilder builder = new RadioButtonBuilder(text);
        builder.getRawButton().setActionCommand(text);
        this.group.add(builder.getRawButton());
        
        return builder;
    }

    public String getSelectedValue() {
        ButtonModel selection = this.group.getSelection();
        if (selection != null) {
            return selection.getActionCommand();
        }
        return null;
    }

    public ButtonGroup getRawGroup() {
        return this.group;
    }
}