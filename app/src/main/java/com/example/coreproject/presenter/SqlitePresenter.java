package com.example.coreproject.presenter;

import android.app.Activity;

import com.example.coreproject.sqlite.dao.ExampleDao;
import com.example.coreproject.sqlite.parcelable.ExampleParcelable;
import com.example.coreproject.view.LocalView;

import java.util.List;

public class SqlitePresenter {
    Activity activity;
    LocalView localView;

    ExampleDao exampleDao;

    public SqlitePresenter(Activity activity, LocalView localView) {
        this.activity = activity;
        this.localView = localView;
        exampleDao = new ExampleDao(activity);
    }

    public void insertExample(String nama,String email,String phone,String alamat){

        ExampleParcelable exampleParcelable = new ExampleParcelable();

        exampleParcelable.setName(nama);
        exampleParcelable.setEmail(email);
        exampleParcelable.setPhone(phone);
        exampleParcelable.setAlamat(alamat);

        exampleDao.insert(exampleParcelable);
    }

    public void delete(long id){
        exampleDao.delete(id);
    }

    public void getAllExample(){
        List<ExampleParcelable> exampleParcelableList = exampleDao.getAll();
        localView.getAll(exampleParcelableList);
    }

    public void getByID(long id){
        ExampleParcelable exampleParcelable = exampleDao.getByID(id);
        localView.get(exampleParcelable);
    }

    public void update(long id,String nama,String email,String phone,String alamat){
        ExampleParcelable exampleParcelable = new ExampleParcelable();

        exampleParcelable.setId(id);
        exampleParcelable.setName(nama);
        exampleParcelable.setEmail(email);
        exampleParcelable.setPhone(phone);
        exampleParcelable.setAlamat(alamat);

        exampleDao.update(exampleParcelable);
    }
}
