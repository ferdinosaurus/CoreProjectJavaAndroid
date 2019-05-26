package com.example.coreproject.presenter;

import android.app.Activity;
import android.util.Log;

import com.example.coreproject.retrofit.ApiUtils;
import com.example.coreproject.retrofit.response.JsonArrayResponse;
import com.example.coreproject.retrofit.response.JsonObjectResponse;
import com.example.coreproject.retrofit.response.PopResponse;
import com.example.coreproject.utils.Constant;
import com.example.coreproject.view.ResponseView;
import com.google.gson.JsonObject;


import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitPresenter {
    private Activity activity;
    private ResponseView responseView;

    public RetrofitPresenter(Activity activity, ResponseView responseView) {
        this.activity = activity;
        this.responseView = responseView;
    }

    public void requestLogin(String imei,String spg){
        if(imei.equals("")||spg.equals("")){
            responseView.onFailure("imei dan spg harus diisi");
        }else{
            HashMap<String,String> params = new HashMap<>();
            params.put("imei1",imei);
            params.put("username",spg);

            ApiUtils.getAPIService(Constant.base_url).login(params).enqueue(new Callback<JsonObjectResponse>() {
                @Override
                public void onResponse(Call<JsonObjectResponse> call, Response<JsonObjectResponse> response) {
                    if(response.code()== 200){
                        responseView.onSuccessJsonObject(response.body().getData(),response.body().getMessage());
                    }else{
                        responseView.onFailure("response "+ response.code());
                    }
                }
                @Override
                public void onFailure(Call<JsonObjectResponse> call, Throwable t) {
                    responseView.onFailure("gagal request");
                }
            });
        }
    }

    public void requestDownloadPop(){
        String header = "Bearer eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJpbWVpMSI6IjM1ODc5NjA4NDIyMDU5MCIsInVzZXJuYW1lIjoiU1BHMDAwNSIsInR5cGVfaWQiOiIxIiwiZW52IjoiUUEiLCJpYXQiOjE1NTQ0MzcxNzAsIm5iZiI6bnVsbCwiZXhwIjpudWxsfQ.GtbhLL4SgvftpeXx8zdjC3O8DIGLyzOnw9T8gr-I830"; // code sementara dulu
        ApiUtils.getAPIService(Constant.base_url).downloadPop(header).enqueue(new Callback<PopResponse>() {
            @Override
            public void onResponse(Call<PopResponse> call, Response<PopResponse> response) {
                responseView.onSuccessListObject(response.body().getData(),response.body().getMessage());
            }
            @Override
            public void onFailure(Call<PopResponse> call, Throwable t) {
                Log.d("onResponse",t.toString());
                responseView.onFailure(t.toString());
            }
        });
    }
}
