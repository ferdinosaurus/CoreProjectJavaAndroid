package com.example.coreproject.parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile {

    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("env")
    @Expose
    private String env;
    @SerializedName("type_id")
    @Expose
    private String type_id;


    public UserProfile() {

        this.code           = null;
        this.user_id        = "user_id";
        this.fullname       = "fullname";
        this.type_id        = "type_id";
        this.env            = "env";
    }

    public UserProfile(String id, String user_id, String fullname, String env, String type_id) {
        this.id = id;
        this.user_id = user_id;
        this.fullname = fullname;
        this.env = env;
        this.type_id = type_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }
}
