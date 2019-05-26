package com.example.coreproject.parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopParcelable {
    @SerializedName("POP_ID")
    @Expose
    String id;

    @SerializedName("POP_NAME")
    @Expose
    String name;

    public PopParcelable(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
