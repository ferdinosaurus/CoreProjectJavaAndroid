package com.example.coreproject.activity.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coreproject.R;
import com.example.coreproject.presenter.SqlitePresenter;
import com.example.coreproject.sqlite.dao.ExampleDao;
import com.example.coreproject.sqlite.parcelable.ExampleParcelable;
import com.example.coreproject.view.LocalView;

import java.util.List;

public class SqliteInsertUpdateActivity extends AppCompatActivity implements LocalView {

    EditText et_sqliteInsertUpdate_nama,et_sqliteInsertUpdate_email,et_sqliteInsertUpdate_phone,et_sqliteInsertUpdate_alamat;
    Button btn_sqliteInsertUpdate_submit;

    //ExampleDao exampleDao;

    SqlitePresenter sqlitePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_insert_update);
        init();
        setupListener();
    }

    private void init(){
        et_sqliteInsertUpdate_nama = findViewById(R.id.et_sqliteInsertUpdate_nama);
        et_sqliteInsertUpdate_email = findViewById(R.id.et_sqliteInsertUpdate_email);
        et_sqliteInsertUpdate_phone = findViewById(R.id.et_sqliteInsertUpdate_phone);
        et_sqliteInsertUpdate_alamat = findViewById(R.id.et_sqliteInsertUpdate_alamat);
        btn_sqliteInsertUpdate_submit = findViewById(R.id.btn_sqliteInsertUpdate_submit);

        //exampleDao = new ExampleDao(this);
        sqlitePresenter = new SqlitePresenter(this,this);
    }

    private void setupListener(){
        btn_sqliteInsertUpdate_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                sqlitePresenter.insertExample(et_sqliteInsertUpdate_nama.getText().toString(),
                        et_sqliteInsertUpdate_email.getText().toString(),
                        et_sqliteInsertUpdate_phone.getText().toString(),
                        et_sqliteInsertUpdate_alamat.getText().toString());
                finish();
            }
        });
    }

    @Override
    public void getAll(List<?> objects) {

    }

    @Override
    public void get(Object object) {

    }
}
