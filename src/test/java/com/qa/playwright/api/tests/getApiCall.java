package com.qa.playwright.api.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class getApiCall {

    Playwright playwright;
    APIRequest apiRequest;
    APIRequestContext apiRequestContext;
    @BeforeTest
    public void setUp(){
        playwright = Playwright.create();
        apiRequest = playwright.request();
        apiRequestContext = apiRequest.newContext();
    }

    @Test
    public void getUsersApiTests() throws IOException {

        APIResponse apiResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users");

        int statusCode = apiResponse.status();
        System.out.println("response status code: " + statusCode);
        Assert.assertEquals(statusCode, 200);
        Assert.assertTrue(apiResponse.ok());

        String statusResponseText = apiResponse.statusText();
        System.out.println(statusResponseText);

        System.out.println("----------Print API RESPONSE With Plain Text----------");

        System.out.println(apiResponse.text());

        System.out.println("----------Print API RESPONSE----------");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
        String jsonPrettyResponse = jsonResponse.toPrettyString();
        System.out.println(jsonPrettyResponse);

        System.out.println("----------Print API URL----------");
        System.out.println(apiResponse.url());

        System.out.println("----------Print API Headers----------");
        Map<String, String> headersMap = apiResponse.headers();
        System.out.println(headersMap);
        Assert.assertEquals(headersMap.get("content-type"), "application/json; charset=utf-8");
        Assert.assertEquals(headersMap.get("x-download-options"), "noopen");
    }

    @Test
    public void getSpecificUserApiTest() throws IOException {

        APIResponse apiResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setQueryParam("gender", "male")
                        .setQueryParam("status", "active"));

        System.out.println("----------Print API RESPONSE With Plain Text----------");
        System.out.println(apiResponse.text());

        System.out.println("----------Print API RESPONSE----------");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
        String jsonPrettyResponse = jsonResponse.toPrettyString();
        System.out.println(jsonPrettyResponse);
    }
}
