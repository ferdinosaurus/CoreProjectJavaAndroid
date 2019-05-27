package com.example.coreproject.realm;

import android.app.Activity;

import com.example.coreproject.parcelable.KeyValueParcelable;
import com.example.coreproject.realm.model.ExampleModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmController{

    private Realm realm;
    private Activity activity;

    public RealmController(Activity activity) {
        this.activity = activity;
        realm = Realm.getDefaultInstance();
    }

    public Realm getRealm() {
        return realm;
    }

    public void openRealm(){
        realm.beginTransaction();
    }

    public void closeRealm(){
        realm.commitTransaction();
    }

    public void insert(final RealmModel realmModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(realmModel);
            }
        });
    }

    public List<?> getAll(RealmModel realmModel){
        List<?> result = realm.where(realmModel.getClass()).findAll();
        return result;
    }
    public void deleteExampleModelById(final String id) {
        final RealmResults<ExampleModel> exampleModelRealmResults = realm.where(ExampleModel.class)
                .equalTo(ExampleModel.KEY_ID, id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                exampleModelRealmResults.deleteAllFromRealm();
            }
        });
    }

    public Object getByID(RealmModel realmModel,String key,String value){
        return realm.where(realmModel.getClass()).equalTo(key,value).findFirst();
    }

    public List<?> getAllByCondition(RealmModel realmModel,List<KeyValueParcelable> keyValueParcelableList){
        RealmQuery<RealmModel> query = (RealmQuery<RealmModel>) realm.where(realmModel.getClass());

        for(int i=0;i<keyValueParcelableList.size();i++){
            query.equalTo(keyValueParcelableList.get(i).getKey(),keyValueParcelableList.get(i).getValue());
        }

        RealmResults<?> results = query.findAll();

        return results;
    }

    public void deleteById(RealmModel realmModel,String key,String value){
        final RealmResults<?> realmResults = (RealmResults<?>) realm.where(realmModel.getClass()).equalTo(key,value).findAll();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realmResults.deleteAllFromRealm();
            }
        });

    }
    public void delete(final RealmResults<?> realmResults){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realmResults.deleteAllFromRealm();
            }
        });
    }
    public void deleteAll(final RealmModel realmModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(realmModel.getClass());
            }
        });
    }
}