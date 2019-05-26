package com.example.coreproject.presenter;

import android.app.Activity;

import com.example.coreproject.realm.RealmController;
import com.example.coreproject.realm.model.ExampleModel;
import com.example.coreproject.view.RealmView;

import io.realm.RealmModel;
import io.realm.RealmResults;

public class RealmPresenter {
    Activity activity;
    RealmView realmView;
    RealmController realmController;

    public RealmPresenter(Activity activity,RealmView realmView) {
        this.realmView = realmView;
        this.activity = activity;
        realmController = new RealmController(activity);
    }
    public void getAll(RealmModel realmModel){
        RealmResults<?> realmResults = (RealmResults<?>) realmController.getAll(realmModel);
        realmView.getAll(realmResults);
    }

    public void insertExampleModel(String nama,String email,String phone,String alamat){
        ExampleModel exampleModel = new ExampleModel();
        exampleModel.setNama(nama);
        exampleModel.setEmail(email);
        exampleModel.setPhone(phone);
        exampleModel.setAlamat(alamat);

        realmController.insert(exampleModel);
    }
}
