package io.github.aktissa.demo;

import java.awt.BorderLayout;

import javax.swing.SwingUtilities;

import io.github.aktissa.builders.ComboBoxBuilder;
import io.github.aktissa.builders.LabelBuilder;
import io.github.aktissa.builders.ProgressBarBuilder;
import io.github.aktissa.builders.RadioGroupBuilder;
import io.github.aktissa.builders.SliderBuilder;
import io.github.aktissa.builders.TableBuilder;
import io.github.aktissa.builders.TextFieldBuilder;
import io.github.aktissa.builders.WindowBuilder;
import io.github.aktissa.core.UI;

public class TaskManagerDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WindowBuilder mainWindow = UI.window("Task Manager Demo");

            // Keep references to components whose state is needed elsewhere in the screen.
            TableBuilder taskTable = UI.table().columns("Task", "Category", "Priority", "Effort");
            RadioGroupBuilder priorityGroup = UI.radioGroup();
            TextFieldBuilder taskNameField = UI.textField("Task description", 20);
            ComboBoxBuilder categoryComboBox = UI.comboBox("Category", "Development", "Infrastructure", "Design");
            ProgressBarBuilder progressBar = UI.progressBar(0, 10);

            LabelBuilder effortLabel = UI.label("Estimated effort: 5 hours").secondary();
            SliderBuilder effortSlider = UI.slider(1, 40, 5)
                    .onChange(effortValue -> effortLabel
                            .text("Estimated effort: " + effortValue + " hours")
                            .getRawLabel()
                            .repaint());

            // Build the interface.
            mainWindow.size(850, 550).content(
                    UI.panel().border()
                            .add(
                                    UI.panel().title("New Task").vertical()
                                            .add(taskNameField)
                                            .add(categoryComboBox)
                                            .add(UI.panel().flowLeft()
                                                    .add(UI.label("Priority:").secondary())
                                                    .add(priorityGroup.add("Low"))
                                                    .add(priorityGroup.add("Medium (Default)").accentText())
                                                    .add(priorityGroup.add("High")))
                                            .add(UI.panel().vertical()
                                                    .add(effortLabel)
                                                    .add(effortSlider))
                                            .add(UI.panel().flowRight()
                                                    .add(UI.button("Add Task")
                                                            .onClick(() -> registerTask(
                                                                    mainWindow,
                                                                    taskNameField,
                                                                    categoryComboBox,
                                                                    priorityGroup,
                                                                    effortSlider,
                                                                    taskTable,
                                                                    progressBar)))),
                                    BorderLayout.WEST)
                            .add(
                                    UI.panel().title("Sprint Overview").vertical()
                                            .add(UI.scroll(taskTable).withBorder())
                                            .add(UI.panel().flowLeft()
                                                    .add(UI.checkBox("Sync to cloud").checked(true))
                                                    .add(UI.checkBox("Generate log")))
                                            .add(UI.panel().title("Team Capacity").vertical()
                                                    .add(progressBar)
                                                    .add(UI.panel().flowRight()
                                                            .add(UI.button("Delete Tasks").onClick(() -> {
                                                                UI.confirm(
                                                                        mainWindow,
                                                                        "Warning",
                                                                        "This will delete all tasks from memory. Continue?",
                                                                        () -> {
                                                                            taskTable.clear();
                                                                            progressBar.value(0);
                                                                        },
                                                                        "Cancel",
                                                                        "Delete");
                                                            })))),
                                    BorderLayout.CENTER)
            ).show();
        });
    }

    private static void registerTask(
            WindowBuilder window,
            TextFieldBuilder taskNameField,
            ComboBoxBuilder categoryComboBox,
            RadioGroupBuilder priorityGroup,
            SliderBuilder effortSlider,
            TableBuilder taskTable,
            ProgressBarBuilder progressBar) {
        String taskName = taskNameField.getRawField().getText().trim();
        String selectedPriority = priorityGroup.getSelectedValue();

        if (taskName.isEmpty() || selectedPriority == null) {
            UI.alert(window, "Validation Error", "Enter a task description and select a priority.");
            return;
        }

        String selectedCategory = (String) categoryComboBox.getRawComboBox().getSelectedItem();
        String effortValue = effortSlider.getRawSlider().getValue() + "h";

        taskTable.row(taskName, selectedCategory, selectedPriority, effortValue);
        taskNameField.text("");

        int currentProgress = progressBar.getRawBar().getValue();
        if (currentProgress < progressBar.getRawBar().getMaximum()) {
            progressBar.value(currentProgress + 1);
        }
    }
}
