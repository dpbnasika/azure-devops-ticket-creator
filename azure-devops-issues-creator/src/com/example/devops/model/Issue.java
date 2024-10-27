package com.example.devops.model;

import java.util.Date;

public class Issue extends WorkItem {
    private String description;
    private String status;
    private String priority;
    private String comment;
    private Task parentTask;
    private Date createdDate;

    public Issue(String title, String description, String status, String priority, String comment, Task parentTask, Date createdDate) {
        super(title, WorkItemType.Issue);
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.comment = comment;
        this.parentTask = parentTask;
        this.createdDate = createdDate;
    }

    public Issue(String title, String description) {
        super(title, WorkItemType.Issue);
        this.description = description;
    }

    // Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}