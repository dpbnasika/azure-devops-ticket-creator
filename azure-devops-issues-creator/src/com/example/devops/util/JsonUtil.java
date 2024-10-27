package com.example.devops.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.devops.config.AzureConfig;
import com.example.devops.model.Epic;
import com.example.devops.model.Issue;
import com.example.devops.model.Task;
import com.example.devops.model.WorkItem;
import com.example.devops.model.WorkItemType;

import java.util.Base64;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();
   

    public static String createWorkItemJson(WorkItem workItem, Integer parentId) {
        System.out.println("Received WorkItem of type: " + workItem.getClass().getSimpleName());

        List<Object> operations = new ArrayList<>();
        operations.add(createAddOperation("System.Title", workItem.getTitle()));
        
        if (workItem instanceof Epic) {
            Epic epic = (Epic) workItem;
        	
            if (epic.getFields().getReason() != null) {
                operations.add(createAddOperation("System.Description", epic.getFields().getReason()));
            }
            if (epic.getFields().getState() != null) {
                operations.add(createAddOperation("System.State", epic.getFields().getState()));
            }
            
        }
        if (workItem instanceof Task) {
            Task task = (Task) workItem;
        	
            if (task.getDescription() != null) {
                operations.add(createAddOperation("System.Description", task.getDescription()));
            }
            
        }
        if (workItem instanceof Issue) {
            Issue issue = (Issue) workItem;
        	
            if (issue.getDescription() != null) {
                operations.add(createAddOperation("System.Description", issue.getDescription()));
            }
            
        }
        
        // Add a link if there's a parent ID
        if (parentId != null) {
            operations.add(createLinkOperation(parentId));
        }

        try {
            return objectMapper.writeValueAsString(operations);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Object createAddOperation(String path, String value) {
        return new HashMap<String, Object>() {{
            put("op", "add");
            put("path", "/fields/" + path);
            put("value", value);
        }};
    }

    private static Object createLinkOperation(Integer parentId) {
        return new HashMap<String, Object>() {{
            put("op", "add");
            put("path", "/relations/-");
            put("value", new HashMap<String, Object>() {{
                put("rel", "System.LinkTypes.Hierarchy-Reverse");
                put("url", "https://dev.azure.com/" + AzureConfig.ORGANIZATION + "/" + AzureConfig.PROJECT + "/_apis/wit/workitems/" + parentId);
            }});
        }};
    }

    public static String encodeBase64(String username, String token) {
        String authString = username + ":" + token;
        return Base64.getEncoder().encodeToString(authString.getBytes());
    }

    public static int extractIdFromJson(String json) {
    	try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            return rootNode.path("id").asInt(-1); // Return -1 if ID is not found
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return -1 on error
        }
    }
}