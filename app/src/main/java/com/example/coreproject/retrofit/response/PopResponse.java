package com.example.coreproject.retrofit.response;

import com.example.coreproject.parcelable.PopParcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopResponse {
    @SerializedName("status")
    @Expose
    public int status;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("data")
    @Expose
    List<PopParcelable> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PopParcelable> getData() {
        return data;
    }

    public void setData(List<PopParcelable> data) {
        this.data = data;
    }
}
