package com.example.coreproject.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.example.coreproject.parcelable.PopParcelable;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ConverterHelper {

    public static String format_number(Integer value) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String formattedString = formatter.format(value);
        return formattedString;
    }
    public static Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static List<PopParcelable> JsonArrayToPopParcelable(JSONArray jsonArray) {
        List<PopParcelable> popParcelableList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                popParcelableList.add(new PopParcelable(jsonObject.getString("POP_ID"), jsonObject.getString("POP_NAME")));
            } catch (Exception e) {
                Log.d("catch", e.toString());
            }
        }
        return popParcelableList;
    }
}
