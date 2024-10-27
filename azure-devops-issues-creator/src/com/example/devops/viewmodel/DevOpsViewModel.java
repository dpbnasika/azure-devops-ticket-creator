package com.example.devops.viewmodel;

import com.example.devops.model.Epic;
import com.example.devops.model.Issue;
import com.example.devops.model.Task;
import com.example.devops.model.WorkItem;
import com.example.devops.service.AzureDevOpsService;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

public class DevOpsViewModel {
	private final AzureDevOpsService service;

	public DevOpsViewModel() {
		this.service = new AzureDevOpsService();
	}

	// New method to create an Epic item
	public int createEpic(Epic epic, String epicTitle) {
		try {
			//Epic epic = new Epic(epicTitle);
			int epicId = service.createWorkItem(epic, null);
			return epicId; // Return the Epic ID
		} catch (Exception e) {
			e.printStackTrace();
			return -1; // Return -1 to indicate failure
		}
	}

	// New method to create a Task item
	public int createTask(Task task, String taskTitle, int epicId) {
		try {
			//Task task = new Task(taskTitle, "Description for " + taskTitle, new Date(), new Date(), "Task comment", null);
			int taskId = service.createWorkItem(task, epicId);
			return taskId; // Return the Task ID
		} catch (Exception e) {
			e.printStackTrace();
			return -1; // Return -1 to indicate failure
		}
	}

	// New method to create an Issue item
	public int createIssue(Issue issue, String issueTitle, int taskId) {
		try {
			//Issue issue = new Issue(issueTitle, "Description for " + issueTitle);
			int issueId = service.createWorkItem(issue, taskId);
			return issueId; // Return the Issue ID
		} catch (Exception e) {
			e.printStackTrace();
			return -1; // Return -1 to indicate failure
		}
	}

	/*// Existing synchronous method to create hierarchy
    public void createHierarchy(String epicTitle, String taskTitle, int issueCount) {
        try {
            // Create an Epic
            int epicId = createEpic(epicTitle);
            if (epicId < 0) return; // Exit if Epic creation failed

            // Create a Task under the Epic
            int taskId = createTask(taskTitle, epicId);
            if (taskId < 0) return; // Exit if Task creation failed

            // Create Issues under the Task
            for (int i = 1; i <= issueCount; i++) {
                int issueId = createIssue("Issue " + i + " under " + taskTitle, taskId);
                if (issueId < 0) {
                    System.out.println("Failed to create Issue " + i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

	// New asynchronous method to create hierarchy
	public CompletableFuture<Void> createHierarchyAsync(String epicTitle, String taskTitle, int issueCount) {
		// Create the Epic asynchronously
		return service.createWorkItemAsync(new Epic(epicTitle), null).thenCompose(epicId -> {
			if (epicId < 0) {
				return CompletableFuture.failedFuture(new RuntimeException("Epic creation failed"));
			}

			Task task = new Task(taskTitle, "Description for " + taskTitle, new Date(), new Date(), "Task comment", null);
			// Create Task asynchronously
			return service.createWorkItemAsync(task, epicId).thenCompose(taskId -> {
				if (taskId < 0) {
					return CompletableFuture.failedFuture(new RuntimeException("Task creation failed"));
				}

				// Create Issues asynchronously
				CompletableFuture<Void> issuesFuture = CompletableFuture.completedFuture(null);
				for (int i = 1; i <= issueCount; i++) {
					Issue issue = new Issue("Issue " + i + " under " + taskTitle, "Description for Issue " + i);
					issuesFuture = issuesFuture.thenCompose(v ->
					service.createWorkItemAsync(issue, taskId).thenApply(issueId -> {
						// Handle each issue ID if needed
						return null;
					})
							);
				}
				return issuesFuture; // Return the future for all issues
			});
		});
	}

	// Ensure to close the service when application exits
	public void close() {
		try {
			service.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int createEpic(Epic epic) {
		try {
			int epicId = service.createWorkItem(epic, null);
			return epicId; // Return the Epic ID
		} catch (Exception e) {
			e.printStackTrace();
			return -1; // Return -1 to indicate failure
		}
	}

	public Boolean createEpicWithTasksAndIssues(Epic epic) {
		int numberOfRuns = epic.getFields().getPriority();
		// Create an Epic
		int epicId = createEpic(epic,epic.getTitle());
		if (epicId < 0) {
			return false; // Exit if Epic creation failed
		}
		else {
			for (Task task : epic.getTasks()) {
				// Create a Task under the Epic
				int taskId = createTask(task, task.getTitle(), epicId);
				if (taskId < 0) return false; // Exit if Task creation failed

				for (int i = 0; i < numberOfRuns; i++) {
					Issue issue = new Issue(task.getTitle() + " - Issue " + (i + 1), task.getDescription());

					int issueId = createIssue(issue, "Issue " + i + " under " + task.getTitle(), taskId);
					if (issueId < 0) {
						System.out.println("Failed to create Issue " + i);
					}
					// Save the issue if necessary
				}
			}
			return true;
		}
	}
}