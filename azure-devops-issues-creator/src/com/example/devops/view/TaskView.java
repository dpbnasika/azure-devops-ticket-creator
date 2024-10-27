package com.example.devops.view;

import org.jdatepicker.impl.JDatePickerImpl;

import com.example.devops.model.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TaskView {
    public interface TaskSavedListener {
        void onTaskSaved(Task task);
    }

    private JFrame taskFrame;
    private JComboBox<String> taskDropdown;
    private JTextField taskDescField;
    private JDatePickerImpl taskStartDatePicker;
    private JDatePickerImpl taskDueDatePicker;
    private JTextField taskCommentField;
    private JTextField taskLinkField;
    private TaskSavedListener taskSavedListener;

    public TaskView(JDatePickerImpl startDatePicker, JDatePickerImpl dueDatePicker, TaskSavedListener listener) {
        this.taskStartDatePicker = startDatePicker;
        this.taskDueDatePicker = dueDatePicker;
        this.taskSavedListener = listener;
        initializeUI();
    }

    private void initializeUI() {
        taskFrame = new JFrame("Add Phone");
        taskFrame.setSize(600, 400);
        taskFrame.setLayout(null);

        JLabel taskLabel = new JLabel("Phone Name:");
        taskLabel.setBounds(10, 20, 100, 25);
        taskFrame.add(taskLabel);

        taskDropdown = new JComboBox<>(new String[]{"Apple", "Samsung", "Oppo"});
        taskDropdown.setBounds(120, 20, 200, 25);
        taskFrame.add(taskDropdown);

        JLabel taskDescLabel = new JLabel("Description:");
        taskDescLabel.setBounds(10, 60, 100, 25);
        taskFrame.add(taskDescLabel);

        taskDescField = new JTextField();
        taskDescField.setBounds(120, 60, 200, 25);
        taskFrame.add(taskDescField);

        JLabel taskStartDateLabel = new JLabel("Start Date:");
        taskStartDateLabel.setBounds(10, 100, 100, 25);
        taskFrame.add(taskStartDateLabel);

        taskStartDatePicker.setBounds(120, 100, 200, 25);
        taskFrame.add(taskStartDatePicker);

        JLabel taskDueDateLabel = new JLabel("Due Date:");
        taskDueDateLabel.setBounds(10, 140, 100, 25);
        taskFrame.add(taskDueDateLabel);

        taskDueDatePicker.setBounds(120, 140, 200, 25);
        taskFrame.add(taskDueDatePicker);

        JLabel taskCommentLabel = new JLabel("Comment:");
        taskCommentLabel.setBounds(10, 180, 100, 25);
        taskFrame.add(taskCommentLabel);

        taskCommentField = new JTextField();
        taskCommentField.setBounds(120, 180, 200, 25);
        taskFrame.add(taskCommentField);

        JLabel taskLinkLabel = new JLabel("Link:");
        taskLinkLabel.setBounds(10, 220, 100, 25);
        taskFrame.add(taskLinkLabel);

        taskLinkField = new JTextField();
        taskLinkField.setBounds(120, 220, 200, 25);
        taskFrame.add(taskLinkField);

        JButton saveTaskButton = new JButton("Save Task");
        saveTaskButton.setBounds(150, 260, 100, 25);
        taskFrame.add(saveTaskButton);

        saveTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTaskData();
                taskFrame.dispose();
            }
        });

        taskFrame.setVisible(true);
    }

    private void saveTaskData() {
        String taskName = (String) taskDropdown.getSelectedItem();
        String taskDesc = taskDescField.getText();
        Date startDate = (Date) taskStartDatePicker.getModel().getValue();
        Date dueDate = (Date) taskDueDatePicker.getModel().getValue();
        String comment = taskCommentField.getText();
        String link = taskLinkField.getText();

        Task task = new Task(taskName, taskDesc, startDate, dueDate, comment, link);

        if (taskSavedListener != null) {
            taskSavedListener.onTaskSaved(task);
        }
    }
}