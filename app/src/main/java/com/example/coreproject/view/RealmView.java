package com.example.coreproject.view;

import java.util.List;

import io.realm.RealmObject;

public interface RealmView {
    void getAll(List<?> objects);
    void get(Object object);
}
