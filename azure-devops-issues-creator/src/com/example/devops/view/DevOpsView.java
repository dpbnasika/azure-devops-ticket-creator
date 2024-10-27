package com.example.devops.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import org.jdatepicker.impl.*;

import com.example.devops.exceptions.WorkItemCreationException;
import com.example.devops.model.Epic;
import com.example.devops.model.Task;
import com.example.devops.util.DateLabelFormatter;
import com.example.devops.viewmodel.DevOpsViewModel;

public class DevOpsView {
    private final DevOpsViewModel viewModel;
    private List<Task> tasks = new ArrayList<>();
    private JProgressBar progressBar; // Progress bar declaration

    public DevOpsView() {
        viewModel = new DevOpsViewModel();
        createUI();
    }

    private void createUI() {
        JFrame frame = new JFrame("Ticket Creation Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setMaximumSize(new Dimension(800, 600));
        frame.setLayout(new GridBagLayout());

        // Add Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        optionsMenu.add(exitItem);
        menuBar.add(optionsMenu);
        frame.setJMenuBar(menuBar);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header label
        JLabel headerLabel = new JLabel("Ticket Creation Manager", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(headerLabel, gbc);

        // Epic Name
        JLabel nameLabel = new JLabel("Epic Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frame.add(nameLabel, gbc);

        JTextField nameField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(nameField, gbc);

        // Epic Description
        JLabel descLabel = new JLabel("Epic Description:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(descLabel, gbc);

        JTextField descField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(descField, gbc);

        // Start Date
        JLabel startDateLabel = new JLabel("Planned Start Date:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(startDateLabel, gbc);

        JDatePickerImpl startDatePicker = createDatePicker();
        gbc.gridx = 1;
        gbc.gridy = 3;
        frame.add(startDatePicker, gbc);

        // Due Date
        JLabel dueDateLabel = new JLabel("Due Date:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(dueDateLabel, gbc);

        JDatePickerImpl dueDatePicker = createDatePicker();
        gbc.gridx = 1;
        gbc.gridy = 4;
        frame.add(dueDatePicker, gbc);

        // Epic Comment
        JLabel commentLabel = new JLabel("Comment:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        frame.add(commentLabel, gbc);
        
        JComboBox<String> taskDropdown = new JComboBox<>(new String[]{"To Do", "Samsung", "Oppo"});
        //taskDropdown.setBounds(120, 20, 200, 25);
        gbc.gridx = 1;
        gbc.gridy = 5;
        frame.add(taskDropdown, gbc);

        /*JTextField commentField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 5;
        frame.add(commentField, gbc);*/
        
        JLabel runsLabel = new JLabel("Number of Runs:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        frame.add(runsLabel, gbc);

        JTextField runsField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 6;
        frame.add(runsField, gbc);

        // Add Task Button
        JButton addTaskButton = new JButton("Add Task");
        gbc.gridx = 0;
        gbc.gridy = 7;
        frame.add(addTaskButton, gbc);

        addTaskButton.addActionListener(e -> {
            TaskView taskView = new TaskView(createDatePicker(), createDatePicker(), new TaskView.TaskSavedListener() {
                @Override
                public void onTaskSaved(Task task) {
                    tasks.add(task); // Add task to the list
                }
            });
        });

        // Create Order Button
        JButton createOrderButton = new JButton("Create Order");
        gbc.gridx = 1;
        gbc.gridy = 7;
        frame.add(createOrderButton, gbc);
        
        // Progress Bar
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisible(false); // Hide the progress bar initially
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        frame.add(progressBar, gbc);
        
        createOrderButton.addActionListener(e -> {
            String epicName = nameField.getText();
            String epicDescription = descField.getText();
            Date startDate = (Date) startDatePicker.getModel().getValue();
            Date dueDate = (Date) dueDatePicker.getModel().getValue();
            String comment = (String) taskDropdown.getSelectedItem();
            int runs;
            
            try {
                runs = Integer.parseInt(runsField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid number of runs!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Epic epic = new Epic(epicName, epicDescription, startDate, dueDate, comment, runs);

            // Add all collected tasks to the Epic
            for (Task task : tasks) {
                epic.addTask(task);
            }

            // Disable buttons and show progress bar
            addTaskButton.setEnabled(false);
            createOrderButton.setEnabled(false);
            progressBar.setVisible(true);

            // Use SwingWorker for background processing
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    try {
                        // Call the method and check the result
                        Boolean result = viewModel.createEpicWithTasksAndIssues(epic);
                        if (!result) {
                            throw new WorkItemCreationException("Creation failed due to server error.");
                        }
                    } catch (Exception ex) {
                        throw new Exception("Error creating epic: " + ex.getMessage());
                    }
                    return null;
                }

                @Override
                protected void done() {
                    progressBar.setVisible(false); // Hide the progress bar
                    addTaskButton.setEnabled(true); // Re-enable buttons
                    createOrderButton.setEnabled(true); // Re-enable buttons

                    try {
                        get(); // Check for exceptions thrown in doInBackground
                        JOptionPane.showMessageDialog(frame, "Created successfully!");
                        tasks.clear(); // Clear tasks after creation
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            };
            worker.execute(); // Start the background process
        });

        // Final Frame settings
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JDatePickerImpl createDatePicker() {
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        return new JDatePickerImpl(datePanel, new DateLabelFormatter());
    }
}