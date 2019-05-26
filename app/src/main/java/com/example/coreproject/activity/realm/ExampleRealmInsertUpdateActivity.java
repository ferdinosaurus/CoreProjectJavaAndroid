package com.example.coreproject.activity.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coreproject.R;
import com.example.coreproject.presenter.RealmPresenter;
import com.example.coreproject.view.RealmView;

import java.util.List;

public class ExampleRealmInsertUpdateActivity extends AppCompatActivity implements RealmView {

    protected EditText et_realmInsertUpdate_nama,et_realmInsertUpdate_email,et_realmInsertUpdate_phone,et_realmInsertUpdate_alamat;
    protected Button btn_realmInsertUpdate_submit;
    private RealmPresenter realmPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_realm_insert_update);
        init();
        setupListener();
    }

    private void init(){
        et_realmInsertUpdate_nama = findViewById(R.id.et_realmInsertUpdate_nama);
        et_realmInsertUpdate_email = findViewById(R.id.et_realmInsertUpdate_email);
        et_realmInsertUpdate_phone = findViewById(R.id.et_realmInsertUpdate_phone);
        et_realmInsertUpdate_alamat = findViewById(R.id.et_realmInsertUpdate_alamat);
        btn_realmInsertUpdate_submit = findViewById(R.id.btn_realmInsertUpdate_submit);
        realmPresenter = new RealmPresenter(this,this);
    }

    private void setupListener(){
        btn_realmInsertUpdate_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realmPresenter.insertExampleModel(et_realmInsertUpdate_nama.getText().toString(),
                        et_realmInsertUpdate_email.getText().toString(),
                        et_realmInsertUpdate_phone.getText().toString(),
                        et_realmInsertUpdate_alamat.getText().toString());
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
