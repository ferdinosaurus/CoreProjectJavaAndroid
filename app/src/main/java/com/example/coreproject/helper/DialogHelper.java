package com.example.coreproject.helper;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.coreproject.view.DialogView;

public class DialogHelper {

    Activity activity;
    AlertDialog alertDialog;
    public DialogHelper(Activity activity) {
        this.activity = activity;

    }

    public void createDialogWithoutButton(String title, String message) {
        alertDialog = new AlertDialog.Builder(activity).create();

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.show();

    }

    public void createDialogButtonClose(String title,String message){
        alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismissDialog();
            }
        });
        alertDialog.show();
    }

    public void createDialogButtonChoose(String title, String message, final DialogView dialogView){
        alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogView.buttonPositive();
            }
        });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogView.buttonNegative();
            }
        });

        alertDialog.show();
    }

    public void dismissDialog(){
        if(alertDialog.isShowing()){
            alertDialog.dismiss();
        }
    }

}
