package com.example.coreproject.retrofit;

import com.example.coreproject.retrofit.response.JsonArrayResponse;
import com.example.coreproject.retrofit.response.JsonObjectResponse;
import com.example.coreproject.retrofit.response.PopResponse;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @POST("user/login_post")
    Call<JsonObjectResponse> login(@QueryMap Map<String, String> param);

    @GET("v2/download/pop")
    Call<PopResponse> downloadPop(@Header("Authorization")String token);



}
