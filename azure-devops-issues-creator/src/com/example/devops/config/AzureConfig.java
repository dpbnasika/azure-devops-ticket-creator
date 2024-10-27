package com.example.devops.config;

public class AzureConfig {
    private static String organization = "";
    private static String project = "";
    private static String personalAccessToken = "";

    public static final String API_VERSION = "api-version=6.0";

    // Getter methods
    public static String getOrganization() { return organization; }
    public static String getProject() { return project; }
    public static String getPersonalAccessToken() { return personalAccessToken; }

    // Setter methods to set values during runtime
    public static void setOrganization(String org) { organization = org; }
    public static void setProject(String proj) { project = proj; }
    public static void setPersonalAccessToken(String token) { personalAccessToken = token; }

    // Method to return the full base URL, dynamically constructed
    public static String getBaseUrl() {
        return "https://dev.azure.com/" + getOrganization() + "/" + getProject() + "/_apis/wit/workitems";
    }
}