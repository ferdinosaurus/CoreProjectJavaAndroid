package com.example.coreproject.activity.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coreproject.R;
import com.example.coreproject.presenter.SqlitePresenter;
import com.example.coreproject.sqlite.parcelable.ExampleParcelable;
import com.example.coreproject.view.LocalView;

import java.util.List;

public class SqliteDetailActivity extends AppCompatActivity implements LocalView {

    TextView tv_sqliteDetail_id,tv_sqliteDetail_nama,tv_sqliteDetail_email,tv_sqliteDetail_phone,tv_sqliteDetail_alamat;
    Button btn_sqliteDetail_update,btn_sqliteDetail_delete;

    SqlitePresenter sqlitePresenter;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_detail);
        init();
        setupListener();
    }


    private void init(){
        tv_sqliteDetail_id = findViewById(R.id.tv_sqliteDetail_id);
        tv_sqliteDetail_nama = findViewById(R.id.tv_sqliteDetail_nama);
        tv_sqliteDetail_email = findViewById(R.id.tv_sqliteDetail_email);
        tv_sqliteDetail_phone = findViewById(R.id.tv_sqliteDetail_phone);
        tv_sqliteDetail_alamat = findViewById(R.id.tv_sqliteDetail_alamat);

        btn_sqliteDetail_update = findViewById(R.id.btn_sqliteDetail_update);
        btn_sqliteDetail_delete = findViewById(R.id.btn_sqliteDetail_delete);

        sqlitePresenter = new SqlitePresenter(this,this);
    }

    private void setupListener(){
        btn_sqliteDetail_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SqliteDetailActivity.this,SqliteInsertUpdateActivity.class);
                intent.putExtra("task","update");
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        btn_sqliteDetail_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SqliteDetailActivity.this, "click delete", Toast.LENGTH_SHORT).show();
                sqlitePresenter.delete(id);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        id = getIntent().getLongExtra("id",0);
        sqlitePresenter.getByID(id);

    }

    @Override
    public void getAll(List<?> objects) {

    }

    @Override
    public void get(Object object) {
        ExampleParcelable exampleParcelable = (ExampleParcelable) object;
        tv_sqliteDetail_id.setText(String.valueOf(exampleParcelable.getId()));
        tv_sqliteDetail_nama.setText(exampleParcelable.getName());
        tv_sqliteDetail_email.setText(exampleParcelable.getEmail());
        tv_sqliteDetail_phone.setText(exampleParcelable.getPhone());
        tv_sqliteDetail_alamat.setText(exampleParcelable.getAlamat());
    }
}