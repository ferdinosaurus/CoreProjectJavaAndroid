package com.example.coreproject.activity.realm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.coreproject.R;
import com.example.coreproject.adapter.ExampleRealmAdapter;
import com.example.coreproject.presenter.RealmPresenter;
import com.example.coreproject.realm.model.ExampleModel;
import com.example.coreproject.view.RealmView;

import java.util.List;

public class ExampleRealmActivity extends AppCompatActivity implements RealmView {

    RecyclerView rv_main;
    RealmPresenter realmPresenter;

    ExampleRealmAdapter exampleRealmAdapter;

    Button btn_exampleRealm_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_realm);
        init();
        setupListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupData();
    }

    private void setupData(){
        realmPresenter.getAll(new ExampleModel());
    }

    private void setupListener(){
        btn_exampleRealm_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExampleRealmActivity.this,ExampleRealmInsertUpdateActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        rv_main = findViewById(R.id.rv_exampleRealm);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ExampleRealmActivity.this,LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_main.setLayoutManager(linearLayoutManager);

        realmPresenter = new RealmPresenter(this,this);

        btn_exampleRealm_insert = findViewById(R.id.btn_exampleRealm_insert);
    }

    @Override
    public void getAll(List<?> objects) {
        List<ExampleModel> exampleModelList = (List<ExampleModel>) objects;
        exampleRealmAdapter = new ExampleRealmAdapter(ExampleRealmActivity.this,exampleModelList,realmPresenter);

        rv_main.setAdapter(exampleRealmAdapter);
    }

    @Override
    public void get(Object object) {

    }
}
