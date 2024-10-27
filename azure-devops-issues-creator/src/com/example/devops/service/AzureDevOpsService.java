package com.example.devops.service;

import com.example.devops.config.AzureConfig;
import com.example.devops.exceptions.WorkItemCreationException;
import com.example.devops.model.Epic;
import com.example.devops.model.WorkItem;
import com.example.devops.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class AzureDevOpsService {
	private final CloseableHttpClient httpClient;

	public AzureDevOpsService() {
		this.httpClient = HttpClients.createDefault();
	}

	public int createWorkItem(WorkItem workItem, Integer parentId) throws IOException, WorkItemCreationException {
		String url = String.format("%s/$%s?%s", AzureConfig.BASE_URL, workItem.getType(), AzureConfig.API_VERSION);
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/json-patch+json");
		post.setHeader("Authorization", "Basic " + JsonUtil.encodeBase64("", AzureConfig.PERSONAL_ACCESS_TOKEN));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json = JsonUtil.createWorkItemJson(workItem, parentId);
		post.setEntity(new org.apache.http.entity.StringEntity(json));


		try {
			HttpResponse response = httpClient.execute(post);
			String responseString = EntityUtils.toString(response.getEntity());

			if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 201) {
				return JsonUtil.extractIdFromJson(responseString);
			} else {
				// Log or handle the error response as needed
				System.out.println("Error creating work item: " + responseString);
				throw new WorkItemCreationException("Failed to create work item: " + responseString);
			}

		} catch (IOException e) {
			// Handle IOException here if necessary, or propagate it
			e.printStackTrace();
			// Exit on exception
		}
		return -1;
	}



	// Asynchronous version of createWorkItem
	public CompletableFuture<Integer> createWorkItemAsync(WorkItem workItem, Integer parentId) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return createWorkItem(workItem, parentId);
			} catch (IOException e) {
				e.printStackTrace();
				return -1;
			} catch (WorkItemCreationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return -1;
		});
	}

	public void close() throws IOException {
		httpClient.close();
	}
}