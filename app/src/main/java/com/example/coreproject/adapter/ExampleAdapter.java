package com.example.coreproject.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.coreproject.R;
import com.example.coreproject.parcelable.ExampleParcelable;
import com.example.coreproject.presenter.RealmPresenter;
import com.example.coreproject.realm.model.ExampleModel;

import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ItemHolder> {

    Activity activity;
    List<ExampleModel> exampleParcelableList;
    RealmPresenter realmPresenter;

    public ExampleAdapter(Activity activity, List<ExampleModel> exampleParcelableList, RealmPresenter realmPresenter) {
        this.activity = activity;
        this.exampleParcelableList = exampleParcelableList;
        this.realmPresenter = realmPresenter;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_example, viewGroup, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        final ExampleModel exampleModel = exampleParcelableList.get(i);

        itemHolder.tv_itemexample_id.setText(exampleModel.getId());
        itemHolder.tv_itemexample_name.setText(exampleModel.getNama());


    }

    @Override
    public int getItemCount() {
        return exampleParcelableList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        CardView cv_itemexample;
        TextView tv_itemexample_id,tv_itemexample_name;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            cv_itemexample = itemView.findViewById(R.id.cv_itemexample);
            tv_itemexample_id = itemView.findViewById(R.id.tv_itemexample_id);
            tv_itemexample_name = itemView.findViewById(R.id.tv_itemexample_name);
        }
    }
}
