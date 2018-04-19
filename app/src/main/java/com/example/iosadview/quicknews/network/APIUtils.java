package com.example.iosadview.quicknews.network;

public class APIUtils {

    private static APIService API_SERVICE;
    public static final String BASE_URL = "https://newsapi.org/";

    private APIUtils() {

    }

    public static APIService getAPIService() {
        if (API_SERVICE == null) {
            synchronized (APIUtils.class) {
                if (API_SERVICE == null) {
                    API_SERVICE = RetrofitClient.getClient(BASE_URL).create(APIService.class);
                }
            }
        }
        return API_SERVICE;
    }
}
