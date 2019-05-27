package com.example.coreproject.activity.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coreproject.R;
import com.example.coreproject.helper.NullEmptyChecker;
import com.example.coreproject.presenter.RealmPresenter;
import com.example.coreproject.realm.model.ExampleModel;
import com.example.coreproject.view.RealmView;

import java.util.List;

public class ExampleRealmInsertUpdateActivity extends AppCompatActivity implements RealmView {

    protected EditText et_realmInsertUpdate_nama, et_realmInsertUpdate_email, et_realmInsertUpdate_phone, et_realmInsertUpdate_alamat;
    protected Button btn_realmInsertUpdate_submit;
    private RealmPresenter realmPresenter;

    private String taskIntent,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_realm_insert_update);
        init();
        setupListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupData();
    }

    private void init() {
        et_realmInsertUpdate_nama = findViewById(R.id.et_realmInsertUpdate_nama);
        et_realmInsertUpdate_email = findViewById(R.id.et_realmInsertUpdate_email);
        et_realmInsertUpdate_phone = findViewById(R.id.et_realmInsertUpdate_phone);
        et_realmInsertUpdate_alamat = findViewById(R.id.et_realmInsertUpdate_alamat);
        btn_realmInsertUpdate_submit = findViewById(R.id.btn_realmInsertUpdate_submit);
        realmPresenter = new RealmPresenter(this, this);

        taskIntent = getIntent().getStringExtra("task");

        id = getIntent().getStringExtra("id");
    }


    private void setupData() {
        if (NullEmptyChecker.isNotNullOrNotEmpty(taskIntent)) {
            if (taskIntent.equals("update")) {
                realmPresenter.getById(new ExampleModel(), ExampleModel.KEY_ID, id);
            }
        }
    }

    private void setupListener() {
        btn_realmInsertUpdate_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taskIntent.equals("insert")) {
                    realmPresenter.insertExampleModel(et_realmInsertUpdate_nama.getText().toString(),
                            et_realmInsertUpdate_email.getText().toString(),
                            et_realmInsertUpdate_phone.getText().toString(),
                            et_realmInsertUpdate_alamat.getText().toString());
                    finish();
                } else {
                    realmPresenter.updateExampleModelByID(id,
                            et_realmInsertUpdate_nama.getText().toString(),
                            et_realmInsertUpdate_email.getText().toString(),
                            et_realmInsertUpdate_phone.getText().toString(),
                            et_realmInsertUpdate_alamat.getText().toString());
                    finish();
                }

            }
        });
    }

    @Override
    public void getAll(List<?> objects) {

    }

    @Override
    public void get(Object object) {
        ExampleModel exampleModel = (ExampleModel) object;

        et_realmInsertUpdate_nama.setText(exampleModel.getNama());
        et_realmInsertUpdate_email.setText(exampleModel.getEmail());
        et_realmInsertUpdate_phone.setText(exampleModel.getPhone());
        et_realmInsertUpdate_alamat.setText(exampleModel.getAlamat());
    }
}
