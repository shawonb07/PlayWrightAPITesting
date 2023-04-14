package com.qa.playwright.api.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.APIRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GETAPICall {

    @Test
    public void getUsersApiTests() throws IOException {

        Playwright playwright = Playwright.create();
        APIRequest apiRequest = playwright.request();
        APIRequestContext apiRequestContext = apiRequest.newContext();
        APIResponse apiResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users");

        int statusCode = apiResponse.status();
        System.out.println("response status code: " + statusCode);
        Assert.assertEquals(statusCode, 200);

        String statusResponseText = apiResponse.statusText();
        System.out.println(statusResponseText);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
        String jsonPrettyResponse = jsonResponse.toPrettyString();
        System.out.println(jsonPrettyResponse);
    }
}
