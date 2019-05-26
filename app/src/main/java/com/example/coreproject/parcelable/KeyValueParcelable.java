package com.example.coreproject.parcelable;

public class KeyValueParcelable {

    private String key;
    private Object value;

    public KeyValueParcelable(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValueParcelable(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public KeyValueParcelable(String key, Long value) {
        this.key = key;
        this.value = value;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


    public void setValue(Long value) {
        this.value = value;
    }

    public String getValue() {
        return (String)value;
    }
}