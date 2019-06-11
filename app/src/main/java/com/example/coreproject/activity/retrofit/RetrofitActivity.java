package com.example.coreproject.activity.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.coreproject.R;

public class RetrofitActivity extends AppCompatActivity {

    Button btn_retrofit_insert;
    RecyclerView rv_retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        init();
        setupListener();
    }

    private void init(){
        btn_retrofit_insert = findViewById(R.id.btn_retrofit_insert);
        rv_retrofit = findViewById(R.id.rv_retrofit);
    }

    private void setupListener(){
        btn_retrofit_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RetrofitActivity.this, "click button insert", Toast.LENGTH_SHORT).show();
            }
        });
    }
}