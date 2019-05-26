package com.example.coreproject.retrofit;

import com.example.coreproject.utils.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static  String BASE_URL_PROD  = "https://api.japfacomfeed.co.id/sgfqa/salestracking/index.php/";
    public static String BASE_URL_QA  = "https://api.japfacomfeed.co.id/sgfqa/salestrackingqa/index.php/";
    public static String BASE_URL_DEV  = "https://portalsgf.japfacomfeed.co.id/salestrackingdev/index.php/";

    public static final boolean isEnableLogging = true;
    private static Retrofit retrofit;

    public static Retrofit getClient(String newApiBaseUrl) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (isEnableLogging)
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        else
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(getInterceptor()).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(newApiBaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit;
    }




    protected static Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request request = chain.request().newBuilder()
                        .addHeader("User-Agent", "Retrofit-Sample-App")
                        .addHeader(Constant.HEADER_ACCEPT, "application/json")
                        .addHeader(Constant.HEADER_CONTENT_TYPE, "application/json")
                        .build();

                return chain.proceed(request);
            }
        };
    }



}
