package com.example.coreproject.retrofit;


import android.util.Log;

import com.example.coreproject.application.CoreApplication;
import com.example.coreproject.helper.SessionHelper;
import com.example.coreproject.utils.Constant;


public class Urls {


    //public static String BASE_URL_DEV  = "http://mobilesales.japfacomfeed.co.id/";
//    public static String BASE_URL_DEV       = "https://portalsgf.japfacomfeed.co.id/salestrackingdev/index.php/";
    public static String BASE_URL_QA_V2     = "https://api.japfacomfeed.co.id/sgfqa/postrackapi/index.php/v2/";
    public static String BASE_URL_PROD      = "https://api.japfacomfeed.co.id/sgf/postrackapi/index.php/v2/";
    //public static String BASE_URL_PROD    = "https://api.japfacomfeed.co.id/sgf/salestracking/index.php/";

    public void Urls(){

    }


    public static String getAPI(){
//
        String BASE_URL_DEV     = "https://portalsgf.japfacomfeed.co.id/salestrackingdev/index.php/";
        String BASE_URL_QA_V2   = "https://api.japfacomfeed.co.id/sgfqa/postrackapi/index.php/v2/";
        String BASE_URL_PROD    = "https://api.japfacomfeed.co.id/sgf/postrackapi/index.php/v2/";
        String API = "";


        SessionHelper sessionHelper = new SessionHelper(CoreApplication.getAppContext());


        Log.e("MODE ",":"+ sessionHelper.getProfile().getEnv());

        if (sessionHelper.getProfile().getEnv().equals(Constant.PROD) || sessionHelper.getProfile().getEnv().equals("")  ){
            API = BASE_URL_PROD;
            Log.e("getAPI 1","PROD:"+API);

        }
        else if (sessionHelper.getProfile().getEnv().equals(Constant.QA)){
            API = BASE_URL_QA_V2;
            Log.e("getAPI 2","QA:"+API);
        }
        else if (sessionHelper.getProfile().getEnv().equals(Constant.DEV) ){
            API = BASE_URL_DEV;
            Log.e("getAPI 2","DEV:"+API);
        }
        else{
            API = BASE_URL_DEV;
            Log.e("getAPI 3","DEV:"+API);
        }
        return API;

    }



}