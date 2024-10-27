package com.example.devops.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Epic extends WorkItem {
	
    private int id;
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRevision() {
		return revision;
	}

	public void setRevision(int revision) {
		this.revision = revision;
	}

	public Fields getFields() {
		return fields;
	}

	public void setFields(Fields fields) {
		this.fields = fields;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private int revision;
    private Fields fields;
    private List<Relation> relations;
    private Links links;
    private String url;

    // Inner classes for structured fields and nested properties
    public static class Fields {
        public String getAreaPath() {
			return areaPath;
		}
		public void setAreaPath(String areaPath) {
			this.areaPath = areaPath;
		}
		public String getTeamProject() {
			return teamProject;
		}
		public void setTeamProject(String teamProject) {
			this.teamProject = teamProject;
		}
		public String getIterationPath() {
			return iterationPath;
		}
		public void setIterationPath(String iterationPath) {
			this.iterationPath = iterationPath;
		}
		public String getWorkItemType() {
			return workItemType;
		}
		public void setWorkItemType(String workItemType) {
			this.workItemType = workItemType;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}
		public User getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(User createdBy) {
			this.createdBy = createdBy;
		}
		public Date getChangedDate() {
			return changedDate;
		}
		public void setChangedDate(Date changedDate) {
			this.changedDate = changedDate;
		}
		public User getChangedBy() {
			return changedBy;
		}
		public void setChangedBy(User changedBy) {
			this.changedBy = changedBy;
		}
		public int getCommentCount() {
			return commentCount;
		}
		public void setCommentCount(int commentCount) {
			this.commentCount = commentCount;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public Date getStateChangeDate() {
			return stateChangeDate;
		}
		public void setStateChangeDate(Date stateChangeDate) {
			this.stateChangeDate = stateChangeDate;
		}
		public int getPriority() {
			return priority;
		}
		public void setPriority(int priority) {
			this.priority = priority;
		}
		public int getParent() {
			return parent;
		}
		public void setParent(int parent) {
			this.parent = parent;
		}
		private String areaPath;
        private String teamProject;
        private String iterationPath;
        private String workItemType;
        private String state;
        private String reason;
        private Date createdDate;
        private User createdBy;
        private Date changedDate;
        private User changedBy;
        private int commentCount;
        private String title;
        private Date stateChangeDate;
        private int priority;
        private int parent;

        // Getters and Setters for each field
        // ...
    }

    public static class User {
        public String getDisplayName() {
			return displayName;
		}
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUniqueName() {
			return uniqueName;
		}
		public void setUniqueName(String uniqueName) {
			this.uniqueName = uniqueName;
		}
		public String getImageUrl() {
			return imageUrl;
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		public String getDescriptor() {
			return descriptor;
		}
		public void setDescriptor(String descriptor) {
			this.descriptor = descriptor;
		}
		private String displayName;
        private String url;
        private String id;
        private String uniqueName;
        private String imageUrl;
        private String descriptor;

        // Getters and Setters for each field
        // ...
    }

    public static class Relation {
        public String getRel() {
			return rel;
		}
		public void setRel(String rel) {
			this.rel = rel;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public Map<String, Object> getAttributes() {
			return attributes;
		}
		public void setAttributes(Map<String, Object> attributes) {
			this.attributes = attributes;
		}
		private String rel;
        private String url;
        private Map<String, Object> attributes;

        // Getters and Setters for each field
        // ...
    }

    public static class Links {
        public Link getSelf() {
			return self;
		}

		public void setSelf(Link self) {
			this.self = self;
		}

		public Link getWorkItemUpdates() {
			return workItemUpdates;
		}

		public void setWorkItemUpdates(Link workItemUpdates) {
			this.workItemUpdates = workItemUpdates;
		}

		public Link getWorkItemRevisions() {
			return workItemRevisions;
		}

		public void setWorkItemRevisions(Link workItemRevisions) {
			this.workItemRevisions = workItemRevisions;
		}

		public Link getWorkItemComments() {
			return workItemComments;
		}

		public void setWorkItemComments(Link workItemComments) {
			this.workItemComments = workItemComments;
		}

		public Link getHtml() {
			return html;
		}

		public void setHtml(Link html) {
			this.html = html;
		}

		public Link getWorkItemType() {
			return workItemType;
		}

		public void setWorkItemType(Link workItemType) {
			this.workItemType = workItemType;
		}

		public Link getFields() {
			return fields;
		}

		public void setFields(Link fields) {
			this.fields = fields;
		}

		private Link self;
        private Link workItemUpdates;
        private Link workItemRevisions;
        private Link workItemComments;
        private Link html;
        private Link workItemType;
        private Link fields;

        public static class Link {
            private String href;

            // Getter and Setter
            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }
    }

    // Constructors
    public Epic(String title) {
        super(title, WorkItemType.Epic); // Set the work item type to "Epic"
        this.fields = new Fields();
        this.fields.setTitle(title);
        this.fields.setWorkItemType("Epic");
        this.tasks = new ArrayList<>(); // Initialize tasks
    }

 // Constructor for Epic that calls the superclass constructor
    public Epic(String name, String description, Date startDate, Date dueDate, String comment, int count) {
        super(name, WorkItemType.Epic); // Call to WorkItem constructor with title and type
        this.fields = new Fields(); // Initialize the fields object
        this.fields.setReason(description);
        this.fields.setPriority(count);
        this.fields.setCreatedDate(startDate);
        this.fields.setChangedDate(dueDate);
        this.fields.setState(comment);
        this.tasks = new ArrayList<>(); // Initialize tasks
    }

    // Getters and Setters for the main Epic fields
    // ...
    
	private List<Task> tasks ;
    
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}