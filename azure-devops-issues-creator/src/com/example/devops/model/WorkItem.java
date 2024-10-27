package com.example.devops.model;

public abstract class WorkItem {
    private String title;
    private WorkItemType type; // Changed to WorkItemType enum

    public WorkItem(String title, WorkItemType type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public WorkItemType getType() { // Updated to return enum type
        return type;
    }
}