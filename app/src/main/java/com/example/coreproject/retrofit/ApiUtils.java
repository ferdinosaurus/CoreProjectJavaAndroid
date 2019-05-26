package com.example.coreproject.retrofit;

public class ApiUtils {

    private ApiUtils() {
    }

    public static ApiInterface getAPIService(String baseURL) {

        //ApiClient.changeApiBaseUrl(baseURL);
        return ApiClient.getClient(baseURL).create(ApiInterface.class);
    }

}
