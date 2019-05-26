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

import com.example.coreproject.R;
import com.example.coreproject.helper.DialogHelper;
import com.example.coreproject.presenter.RealmPresenter;
import com.example.coreproject.realm.RealmController;
import com.example.coreproject.realm.model.ExampleModel;
import com.example.coreproject.view.DialogView;

import java.util.List;

import io.realm.RealmResults;

public class ExampleRealmAdapter extends RecyclerView.Adapter<ExampleRealmAdapter.ItemHolder> {

    Activity activity;
    List<ExampleModel> exampleParcelableList;
    RealmPresenter realmPresenter;

    DialogHelper dialogHelper;
    RealmController realmController;

    public ExampleRealmAdapter(Activity activity, List<ExampleModel> exampleParcelableList, RealmPresenter realmPresenter) {
        this.activity = activity;
        this.exampleParcelableList = exampleParcelableList;
        this.realmPresenter = realmPresenter;

        dialogHelper = new DialogHelper(activity);
        realmController = new RealmController(activity);
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

        itemHolder.btn_itemExample_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDelete(exampleModel);
            }
        });
    }

    private void dialogDelete(final ExampleModel exampleModel){
        dialogHelper.createDialogButtonChoose("peringatan", "apakah anda yakin ingin menghapus data ?", new DialogView() {
            @Override
            public void buttonPositive() {
                realmController.deleteById(new ExampleModel(),ExampleModel.KEY_ID,exampleModel.getId());
            }

            @Override
            public void buttonNeutral() {

            }

            @Override
            public void buttonNegative() {
                dialogHelper.dismissDialog();
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
