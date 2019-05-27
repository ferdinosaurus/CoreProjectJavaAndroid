package com.example.coreproject.activity.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coreproject.R;
import com.example.coreproject.helper.NullEmptyChecker;
import com.example.coreproject.presenter.RealmPresenter;
import com.example.coreproject.realm.model.ExampleModel;
import com.example.coreproject.view.RealmView;

import java.util.List;

public class ExampleRealmDetailActivity extends AppCompatActivity implements RealmView {

    TextView tv_realmDetail_id,tv_realmDetail_nama,tv_realmDetail_email,tv_realmDetail_phone,tv_realmDetail_alamat;
    Button btn_realmDetail_delete;

    RealmPresenter realmPresenter;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_realm_detail);
        init();
        setupListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupData();
    }

    private void init(){
        tv_realmDetail_id = findViewById(R.id.tv_realmDetail_id);
        tv_realmDetail_nama = findViewById(R.id.tv_realmDetail_nama);
        tv_realmDetail_email = findViewById(R.id.tv_realmDetail_email);
        tv_realmDetail_phone = findViewById(R.id.tv_realmDetail_phone);
        tv_realmDetail_alamat = findViewById(R.id.tv_realmDetail_alamat);
        btn_realmDetail_delete = findViewById(R.id.btn_realmDetail_delete);

        realmPresenter = new RealmPresenter(this,this);

        id = getIntent().getStringExtra("id");
    }

    private void setupListener(){
        btn_realmDetail_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ExampleRealmDetailActivity.this, "delete click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupData(){
        if(NullEmptyChecker.isNotNullOrNotEmpty(id)){
            realmPresenter.getById(new ExampleModel(),ExampleModel.KEY_ID,id);
        }
    }

    @Override
    public void getAll(List<?> objects) {

    }

    @Override
    public void get(Object object) {
        ExampleModel exampleModel = (ExampleModel) object;

        tv_realmDetail_id.setText(exampleModel.getId());
        tv_realmDetail_nama.setText(exampleModel.getNama());
        tv_realmDetail_email.setText(exampleModel.getEmail());
        tv_realmDetail_phone.setText(exampleModel.getPhone());
        tv_realmDetail_alamat.setText(exampleModel.getAlamat());
    }
}
