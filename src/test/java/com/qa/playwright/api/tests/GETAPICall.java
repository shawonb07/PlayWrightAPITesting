package com.qa.playwright.api.tests;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.APIRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GETAPICall {

    @Test
    public void getUsersApiTests(){

        Playwright playwright = Playwright.create();
        APIRequest apiRequest = playwright.request();
        APIRequestContext apiRequestContext = apiRequest.newContext();
        APIResponse apiResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users");

        int statusCode = apiResponse.status();
        System.out.println("response status code: " + statusCode);
        Assert.assertEquals(statusCode, 200);

        String statusResponseText = apiResponse.statusText();
        System.out.println(statusResponseText);

        apiResponse.body();
    }
}
