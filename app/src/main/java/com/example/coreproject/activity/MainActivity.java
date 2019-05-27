package com.example.coreproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.coreproject.R;
import com.example.coreproject.activity.realm.ExampleRealmActivity;
import com.example.coreproject.activity.sqlite.SqliteActivity;

public class MainActivity extends AppCompatActivity{
    Button btn_main_realm,btn_main_sqlite,btn_main_retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setupListener();
    }

    private void init(){
        btn_main_realm = findViewById(R.id.btn_main_realm);
        btn_main_sqlite = findViewById(R.id.btn_main_sqlite);
        btn_main_retrofit = findViewById(R.id.btn_main_retrofit);
    }

    private void setupListener(){
        btn_main_realm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExampleRealmActivity.class);
                startActivity(intent);
            }
        });

        btn_main_sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SqliteActivity.class);
                startActivity(intent);
            }
        });

    }
}