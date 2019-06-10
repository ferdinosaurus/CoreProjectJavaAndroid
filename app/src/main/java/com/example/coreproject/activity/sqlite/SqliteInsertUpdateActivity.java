package com.example.coreproject.activity.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coreproject.R;
import com.example.coreproject.presenter.SqlitePresenter;
import com.example.coreproject.sqlite.parcelable.ExampleParcelable;
import com.example.coreproject.view.LocalView;

import java.util.List;

public class SqliteInsertUpdateActivity extends AppCompatActivity implements LocalView {

    EditText et_sqliteInsertUpdate_nama,et_sqliteInsertUpdate_email,et_sqliteInsertUpdate_phone,et_sqliteInsertUpdate_alamat;
    Button btn_sqliteInsertUpdate_submit;
    SqlitePresenter sqlitePresenter;
    long id;
    String task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_insert_update);
        init();
        checkTask();
        setupListener();
    }

    private void init(){
        et_sqliteInsertUpdate_nama = findViewById(R.id.et_sqliteInsertUpdate_nama);
        et_sqliteInsertUpdate_email = findViewById(R.id.et_sqliteInsertUpdate_email);
        et_sqliteInsertUpdate_phone = findViewById(R.id.et_sqliteInsertUpdate_phone);
        et_sqliteInsertUpdate_alamat = findViewById(R.id.et_sqliteInsertUpdate_alamat);
        btn_sqliteInsertUpdate_submit = findViewById(R.id.btn_sqliteInsertUpdate_submit);

        sqlitePresenter = new SqlitePresenter(this,this);
        id = getIntent().getLongExtra("id",0);
        task = getIntent().getStringExtra("task");
    }

    private void checkTask(){
        if(task.equals("update")){
            sqlitePresenter.getByID(id);
        }
    }

    private void setupListener(){
        btn_sqliteInsertUpdate_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(task.equals("insert")){
                    insert();
                }else if(task.equals("update")){
                    update();
                }else{
                    Toast.makeText(SqliteInsertUpdateActivity.this, "task not found", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    private void insert(){
        sqlitePresenter.insertExample(et_sqliteInsertUpdate_nama.getText().toString(),
                et_sqliteInsertUpdate_email.getText().toString(),
                et_sqliteInsertUpdate_phone.getText().toString(),
                et_sqliteInsertUpdate_alamat.getText().toString());
    }

    private void update(){
        sqlitePresenter.update(id,et_sqliteInsertUpdate_nama.getText().toString(),
                et_sqliteInsertUpdate_email.getText().toString(),
                et_sqliteInsertUpdate_phone.getText().toString(),
                et_sqliteInsertUpdate_alamat.getText().toString());
    }

    @Override
    public void getAll(List<?> objects) {

    }

    @Override
    public void get(Object object) {
        ExampleParcelable exampleParcelable = (ExampleParcelable) object;

        et_sqliteInsertUpdate_nama.setText(exampleParcelable.getName());
        et_sqliteInsertUpdate_email.setText(exampleParcelable.getEmail());
        et_sqliteInsertUpdate_phone.setText(exampleParcelable.getPhone());
        et_sqliteInsertUpdate_alamat.setText(exampleParcelable.getAlamat());
    }
}
