package com.example.coreproject.view;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public interface ResponseView {
    void onSuccessListObject(List<?> objectList, String message);
    void onSuccessJsonObject(JSONObject jsonObject,String message);
    void onFailure(String message);
}
