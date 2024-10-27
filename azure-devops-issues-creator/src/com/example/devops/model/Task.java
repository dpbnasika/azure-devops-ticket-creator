package com.example.devops.model;

import java.util.Date;

public class Task extends WorkItem {
    private String description;
    private Date startDate;
    private Date dueDate;
    private String comment;
    private String link;

    public Task(String title, String description, Date startDate, Date dueDate, String comment, String link) {
        super(title, WorkItemType.Task);
        this.description = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.comment = comment;
        this.link = link;
    }

    // Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}