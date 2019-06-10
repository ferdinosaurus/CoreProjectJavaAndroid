package com.example.coreproject.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coreproject.R;
import com.example.coreproject.presenter.SqlitePresenter;
import com.example.coreproject.sqlite.dao.ExampleDao;
import com.example.coreproject.sqlite.parcelable.ExampleParcelable;

import java.util.List;

public class ExampleSqliteAdapter extends RecyclerView.Adapter<ExampleSqliteAdapter.ItemHolder>  {

    Activity activity;
    List<ExampleParcelable> exampleParcelableList;
    ExampleDao exampleDao;

    SqlitePresenter sqlitePresenter;

    public ExampleSqliteAdapter(Activity activity, List<ExampleParcelable> exampleParcelableList, SqlitePresenter sqlitePresenter) {
        this.activity = activity;
        this.exampleParcelableList = exampleParcelableList;
        this.sqlitePresenter = sqlitePresenter;
        exampleDao = new ExampleDao(activity);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_example, viewGroup, false);
        return new ExampleSqliteAdapter.ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        final ExampleParcelable exampleParcelable = exampleParcelableList.get(i);

        itemHolder.tv_itemexample_id.setText("id : "+exampleParcelable.getId());
        itemHolder.tv_itemexample_name.setText(exampleParcelable.getName());

        itemHolder.cv_itemexample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "id : "+exampleParcelable.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        itemHolder.btn_itemExample_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "id delete : "+exampleParcelable.getId(), Toast.LENGTH_SHORT).show();
                //exampleDao.delete(exampleParcelable.getId());
            }
        });

        itemHolder.btn_itemExample_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "id update : "+exampleParcelable.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return exampleParcelableList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        CardView cv_itemexample;
        TextView tv_itemexample_id,tv_itemexample_name;
        Button btn_itemExample_update,btn_itemExample_delete;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            cv_itemexample = itemView.findViewById(R.id.cv_itemexample);
            tv_itemexample_id = itemView.findViewById(R.id.tv_itemexample_id);
            tv_itemexample_name = itemView.findViewById(R.id.tv_itemexample_name);
            btn_itemExample_update = itemView.findViewById(R.id.btn_itemExample_update);
            btn_itemExample_delete = itemView.findViewById(R.id.btn_itemExample_delete);
        }
    }
}