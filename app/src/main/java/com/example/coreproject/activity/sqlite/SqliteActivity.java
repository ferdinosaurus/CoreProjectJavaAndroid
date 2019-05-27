package com.example.coreproject.activity.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.coreproject.R;
import com.example.coreproject.activity.realm.ExampleRealmActivity;
import com.example.coreproject.adapter.ExampleSqliteAdapter;
import com.example.coreproject.sqlite.dao.ExampleDao;
import com.example.coreproject.sqlite.parcelable.ExampleParcelable;

import java.util.List;

public class SqliteActivity extends AppCompatActivity {

    Button btn_sqlite_insert;
    RecyclerView rv_sqlite;

    ExampleSqliteAdapter exampleSqliteAdapter;
    ExampleDao exampleDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        init();
        setupListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupData();
    }

    private void init(){
        btn_sqlite_insert = findViewById(R.id.btn_sqlite_insert);

        rv_sqlite = findViewById(R.id.rv_sqlite);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SqliteActivity.this,LinearLayoutManager.VERTICAL,false);
        rv_sqlite.setLayoutManager(linearLayoutManager);

        exampleDao = new ExampleDao(this);
    }
    private void setupListener(){
        btn_sqlite_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SqliteActivity.this,SqliteInsertUpdateActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupData(){
        List<ExampleParcelable> exampleParcelableList = exampleDao.getAll();
        exampleSqliteAdapter = new ExampleSqliteAdapter(this,exampleParcelableList);
        rv_sqlite.setAdapter(exampleSqliteAdapter);
    }
}
