package com.example.coreproject.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.coreproject.parcelable.UserProfile;


public class SessionHelper {
    private static String TAG = SessionHelper.class.getSimpleName();


    SharedPreferences pref,auth;
    SharedPreferences.Editor editor,editorAuth;
    Context _context;
    public boolean MODE_DEVELOPMENT;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    public static final String PUSH_NOTIFICATION = "pushNotification";
    public static final String PUSH_FROM_BACKGROUND = "PUSH_FROM_BACKGROUND";
    public static final String SHARED_PREF = "ah_firebase";
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;
    public static final int MODE = Context.MODE_PRIVATE;

    private static final String PREF_NAME = "MobileSalesSGF";
    private static final String PREF_AUTH = "AuthorizationSGF";

    public static final String KEY_TOKEN ="token";
    public static final String KEY_IS_SENDING_REGID ="sening_regid";
    public static final String KEY_REGISTRATION_ID ="registration_id";
    public static final String KEY_PRESENT_LEAVE = "present_leave";

    public static final String KEY_WEEK_ID = "WeekID";

    // customer
    public static final String KEY_CUSTOMER_ID = "customer_id";

    public static final String KEY_UPDATE_APP ="update_version";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_IS_GRANTED = "isGranted";
    private static final String KEY_IS_CONFIRM = "isConfirm";
    private static final String KEY_IS_CLOCK_IN = "isClockIn";
    private static final String KEY_IS_CLOCK_IN_DATETIME = "isClockInDateTime";
    private static final String KEY_IS_PRESENT_LEAVE = "isPresentLeave";
    private static final String KEY_IS_PRESENT_GOHOME = "isPresentGohome";
    private static final String KEY_TRXID_CONSUMER = "isTRXConsumer";

    private static final String KEY_IS_DOWNLOAD = "isDownload";
    private static final String KEY_IS_LOADING = "isLoading";
    private static final String KEY_IS_CHECK_STOCK = "isCheckStock";
    private static final String KEY_IS_ORDER = "isOrder";
    private static final String KEY_IS_CASH = "isChas";
    private static final String KEY_OUTLET_CHECKIN = "checkIn";
    private static final String KEY_POSM_ID = "posmId";
    private static final String KEY_MSURVEY_ID = "marketSurveyId";

    private static final String KEY_LOAD_TRX_ID_DEPO  ="loadTrxId1";
    private static final String KEY_LOAD_TRX_ID_GROSIR ="loadTrxId2";
    private static final String KEY_TRX_CASHID ="trx_cashid";
    private static final String KEY_ORDER_ID ="orderId";
    private static final String KEY_ORDER_ID_NONOUTLET = "orderidNonoutlet";
    private static final String KEY_FOTO_SAMPLE ="foto_sample";
    private static final String KEY_FOTO_STRUCK ="foto_struck";
    private static final String KEY_FOTO_RECEIPT = "foto_receipt";
    private static final String KEY_VALUE_SOLD = "value_sold";
    private static final String KEY_VALUE_BUY = "value_buy";
    private static final String KEY_WAREHOUSE_ID = "werehouse_id";
    private static final String KEY_WAREHOUSE_NAME = "warehouse_name";
    private static final String KEY_WAREHOUSE_CLOCKIN = "warehouse_clockin";
    private static final String KEY_WAREHOUSE_LOGON = "warehouse_logon";
    private static final String KEY_WAREHOUSE_LOGON_NAME = "warehouse_logon_name";
    private static final String KEY_WAREHOUSE_CLOCKIN_NAME = "warehouse_clockin_name";
    private static final String KEY_TRX_CASHID_DISTIBUTOR = "trx_cash_distributor";
    private static final String KEY_USER_TYPE = "user_type";
    private static final String KEY_PASSWORD_TL = "password_tl";
    private static final String KEY_CHART_DATE_START = "chart_datestart";
    private static final String KEY_CHART_DATE_END = "chart_dateend";
    private static final String KEY_CHART_DATE_MONTHYEAR = "chart_date_monthyear";


    private static final String KEY_ENV_PROD= "environmentIsProd";

    public SessionHelper(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        auth = _context.getSharedPreferences(PREF_AUTH,PRIVATE_MODE);

        editorAuth = auth.edit();
        editor = pref.edit();
    }


//==================================== ENVIRONTMENT =====================

    public void setModeProd(boolean prod){
        this.MODE_DEVELOPMENT = prod;
        editorAuth.putBoolean(KEY_ENV_PROD, prod);

        // commit changes
        editorAuth.commit();


        Log.d(TAG, "Set Environment !");

    }

    public boolean getEnvironment(){

        return auth.getBoolean(KEY_ENV_PROD, false);
    }



    public void setPermissionStatus(boolean isGranted){

        editor.putBoolean(KEY_IS_LOGGEDIN, isGranted);

        // commit changes
        editor.commit();

        Log.d(TAG, "Permission is Granted !");

    }
//=========================================== USER PROFILE =========================================

    public UserProfile getProfile(){

        UserProfile key = new UserProfile();
        UserProfile data = new UserProfile();

        data.setUser_id(auth.getString(key.getUser_id(),null));
        data.setFullname(auth.getString(key.getFullname(),null));
        data.setEnv(auth.getString(key.getEnv(),""));
        data.setType_id(auth.getString(key.getType_id(),null));

        return data;

    }

//=========================================== TOKEN ================================================
    public void setToken (String token){


        editorAuth.putString(KEY_TOKEN, "Bearer "+token);

        // commit changes
        editorAuth.commit();
    }

    public String getToken(){

        //String res = uuid.randomUUID().toString();
        String res = auth.getString(KEY_TOKEN,null);

        //Log.i("token",res);

        return res;

    }

    public boolean permissionIsGranted(){
        return pref.getBoolean(KEY_IS_GRANTED, false);
    }

    public void setUserLoginSession(UserProfile profile, String UserID){

        removeProfile();

        UserProfile profilekey = new UserProfile();

        editorAuth.putString(profilekey.getUser_id(), UserID);
        editorAuth.putString(profilekey.getFullname(), profile.getFullname());
        editorAuth.putString(profilekey.getType_id(), profile.getType_id());
        editorAuth.putString(profilekey.getEnv(), profile.getEnv());

        editorAuth.commit();


        setLogin(true);
    }


    public void removeProfile(){
        UserProfile salesmankey = new UserProfile();

        editorAuth.remove(salesmankey.getUser_id());
        editorAuth.remove(salesmankey.getFullname());
        editorAuth.remove(salesmankey.getType_id());
        editorAuth.remove(salesmankey.getEnv());
        editorAuth.commit();

    }


    public void setUserType(String a){

        editor.remove(KEY_USER_TYPE);

        editor.putString(KEY_USER_TYPE, a);

        // commit changes
        editor.commit();
    }

    public String getUserType(){

        return pref.getString(KEY_USER_TYPE,"");

    }
//========================================== PROFILE END ===========================================

    public void setRegistrationID(String a){

        editor.remove(KEY_REGISTRATION_ID);

        editor.putString(KEY_REGISTRATION_ID, a);

        // commit changes
        editor.commit();
    }

    public String getRegistrationID(){

        return pref.getString(KEY_REGISTRATION_ID,"");

    }

//========================================= ABSENSI ================================================


    public void setClockIn(boolean isClockIn) {

        editor.putBoolean(KEY_IS_CLOCK_IN, isClockIn);
        editor.putString(KEY_IS_CLOCK_IN_DATETIME, new GlobalHelper((Activity) _context).getDateTime());
        // commit changes
        editor.commit();

        Log.d(TAG, "Salescode has been clock in !");
    }

    public void setPresentLeave(boolean isPresenLeave) {

        editor.putBoolean(KEY_IS_PRESENT_LEAVE, isPresenLeave);

        // commit changes
        editor.commit();

        Log.d(TAG, "Salescode has been clock in !");
    }


    public void setPresentGohome(boolean isPresenGohome) {

        editor.putBoolean(KEY_IS_PRESENT_GOHOME, isPresenGohome);

        // commit changes
        editor.commit();

        Log.d(TAG, "Salescode has been clock in !");
    }


    public void setOutletCheckIn(boolean isOutletCheckIN){

        editor.remove(KEY_OUTLET_CHECKIN);
        editor.commit();

        editor.putBoolean(KEY_OUTLET_CHECKIN,isOutletCheckIN);
        editor.commit();
        Log.d(TAG, "set Outlet CI  !"+isOutletCheckIN);
    }


    //========================================= ABSENSI END ========================================
    //========================================= CUSTOMER ===========================================

    public void setCustomerID(String a){

        editor.remove(KEY_CUSTOMER_ID);

        editor.putString(KEY_CUSTOMER_ID, a);


        // commit changes
        editor.commit();
    }


    public String getCustomerID(){

        return pref.getString(KEY_CUSTOMER_ID,"");
    }

//============================================= CUSTOMER END =======================================
//============================================= CONSUMER ===========================================

    public void setKeyTrxConsumer(String a){

        editor.remove(KEY_TRXID_CONSUMER);

        editor.putString(KEY_TRXID_CONSUMER, a);

        // commit changes
        editor.commit();
    }

    public String getKeyTrxConsumer(){

        return pref.getString(KEY_TRXID_CONSUMER,"");

    }
//============================================= CONSUMER END =======================================


    public void setWarehouseClockinName(String a){

        editor.remove(KEY_WAREHOUSE_CLOCKIN_NAME);

        editor.putString(KEY_WAREHOUSE_CLOCKIN_NAME, a);

        // commit changes
        editor.commit();
    }

    public String getWarehouseClockinName(){

        return pref.getString(KEY_WAREHOUSE_CLOCKIN_NAME,"");

    }


    public void setWarehouseLogOn(String a){

        editor.remove(KEY_WAREHOUSE_LOGON);

        editor.putString(KEY_WAREHOUSE_LOGON, a);

        // commit changes
        editor.commit();
    }

    public String getWarehouseLogOn(){

        return pref.getString(KEY_WAREHOUSE_LOGON,"");

    }


    public void setWarehouseLogOnName(String a){

        editor.remove(KEY_WAREHOUSE_LOGON_NAME);

        editor.putString(KEY_WAREHOUSE_LOGON_NAME, a);

        // commit changes
        editor.commit();
    }

    public String getWarehouseLogOnName(){

        return pref.getString(KEY_WAREHOUSE_LOGON_NAME,"");

    }
//================================ WAREHOUSE END ==============

//======================================= CHART ==========================

    public void setChartDateStart(String a){

        editor.remove(KEY_CHART_DATE_START);

        editor.putString(KEY_CHART_DATE_START, a);

        // commit changes
        editor.commit();
    }

    public String getChartDateStart(){

        return pref.getString(KEY_CHART_DATE_START,"");

    }


    public void setChartDateEnd(String a){

        editor.remove(KEY_CHART_DATE_END);

        editor.putString(KEY_CHART_DATE_END, a);

        // commit changes
        editor.commit();
    }

    public String getChartDateEnd(){

        return pref.getString(KEY_CHART_DATE_END,"");

    }


    public void setChartMonthYear(String a){

        editor.remove(KEY_CHART_DATE_MONTHYEAR);

        editor.putString(KEY_CHART_DATE_MONTHYEAR, a);

        // commit changes
        editor.commit();
    }

    public String getChartMonthYear(){

        return pref.getString(KEY_CHART_DATE_MONTHYEAR,"");

    }

//===================================== CHART END =====================

//    public void removeSalesman(){
//        Salesman salesmankey = new Salesman();
//
//        editorAuth.remove(salesmankey.getUser_id());
//        editorAuth.remove(salesmankey.getFirst_name());
//        editorAuth.remove(salesmankey.getLast_name());
//        editorAuth.remove(salesmankey.getAvatar());
//        editorAuth.remove(salesmankey.getEmail());
//        editorAuth.remove(salesmankey.getType_id());
//        editorAuth.remove(salesmankey.getServer_env());
//        editorAuth.commit();
//
//    }


    public void setLogin(boolean isLoggedIn) {

        editorAuth.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // commit changes
        editorAuth.commit();

        Log.d(TAG, "Salescode has been login !");
    }




    public String getAbsenDate(){

        return  pref.getString(KEY_IS_CLOCK_IN_DATETIME,"");



    }



    public void setIsDownload(boolean a) {

        editor.putBoolean(KEY_IS_DOWNLOAD, a);

        // commit changes
        editor.commit();

        Log.d(TAG, "Salescode has been download!");
    }



    public void setCheckStock(boolean a) {

        editor.putBoolean(KEY_IS_CHECK_STOCK, a);

        // commit changes
        editor.commit();

        Log.d(TAG, "Salescode has been Check Stock!");
    }



    public void setSendingREGID(boolean a) {

        editor.putBoolean(KEY_IS_SENDING_REGID, a);

        // commit changes
        editor.commit();

        Log.d(TAG, "Sending Registration ID!");
    }


    public boolean isClockIn(){
        return pref.getBoolean(KEY_IS_CLOCK_IN, false);
    }

    public boolean isPresentLeave(){
        return pref.getBoolean(KEY_IS_PRESENT_LEAVE, false);
    }

    public boolean isPresentGohome(){
        return pref.getBoolean(KEY_IS_PRESENT_GOHOME, true);
    }

    public boolean isOutletChekcIN(){
        return pref.getBoolean(KEY_OUTLET_CHECKIN, false);
    }

    public boolean isDownload(){
        return pref.getBoolean(KEY_IS_DOWNLOAD, false);
    }

    public boolean isLoading(){
        return pref.getBoolean(KEY_IS_LOADING, false);
    }

    public boolean isSendingREGID(){
        return pref.getBoolean(KEY_IS_SENDING_REGID, false);
    }
    public boolean isCheckStock(){
        return pref.getBoolean(KEY_IS_CHECK_STOCK, false);
    }



    public boolean isUserConfirm(){
        return auth.getBoolean(KEY_IS_CONFIRM, false);
    }

    public boolean isLoggedIn(){
        return auth.getBoolean(KEY_IS_LOGGEDIN, false);
    }


    public boolean isUpdateApk(){

        Log.d(TAG, "APK status is " + String.valueOf(auth.getBoolean(KEY_UPDATE_APP, false)));

        return auth.getBoolean(KEY_UPDATE_APP, false);
    }



    public void setLoadTrxId(String a, Integer typeLoading){

        if (typeLoading == 1){
            editor.remove(KEY_LOAD_TRX_ID_DEPO);

            editor.putString(KEY_LOAD_TRX_ID_DEPO, a);
        }else{
            editor.remove(KEY_LOAD_TRX_ID_GROSIR);

            editor.putString(KEY_LOAD_TRX_ID_GROSIR, a);
        }

        // commit changes
        editor.commit();
    }

    public void removeLoadTrxId(String typeLoading){

        if (typeLoading.equals("1")){
            editor.remove(KEY_LOAD_TRX_ID_DEPO);

        }else{
            editor.remove(KEY_LOAD_TRX_ID_GROSIR);
        }

        // commit changes
        editor.commit();
    }


    public void removeTrxLoad(Integer typeLoading){

        if (typeLoading == 1){
            editor.remove(KEY_LOAD_TRX_ID_DEPO);
        }else{
            editor.remove(KEY_LOAD_TRX_ID_GROSIR);

        }




        // commit changes
        editor.commit();
    }


    public String getLoadTrxId(Integer typeLoading){

        if (typeLoading == 1){
            return pref.getString(KEY_LOAD_TRX_ID_DEPO,"");
        }else{
            return pref.getString(KEY_LOAD_TRX_ID_GROSIR,"");

        }




    }


    public void setWeekID(Integer a){

            editor.remove(KEY_WEEK_ID);

            editor.putInt(KEY_WEEK_ID, a);


        // commit changes
        editor.commit();
    }


    public Integer getWeekID(){

            return pref.getInt(KEY_WEEK_ID,0);
    }

    public void setMarketSurveyId(String a){

        editor.remove(KEY_MSURVEY_ID);

        editor.putString(KEY_MSURVEY_ID, a);


        // commit changes
        editor.commit();
    }

    public String getMartketSurveyId(){

        return pref.getString(KEY_MSURVEY_ID,"");
    }


    public void setKeyTrxCashID(String a){

        editor.remove(KEY_TRX_CASHID);

        editor.putString(KEY_TRX_CASHID, a);

        // commit changes
        editor.commit();
    }

    public String getKeyTrxCashID(){

        return pref.getString(KEY_TRX_CASHID,"");

    }


    public void setKeyTrxCashDistributor(String a){

        editor.remove(KEY_TRX_CASHID_DISTIBUTOR);

        editor.putString(KEY_TRX_CASHID_DISTIBUTOR, a);

        // commit changes
        editor.commit();
    }

    public String getKeyTrxCashDistributor(){

        return pref.getString(KEY_TRX_CASHID_DISTIBUTOR,"");

    }


    public void setKeyOrderId(String a){

        editor.remove(KEY_ORDER_ID);

        editor.putString(KEY_ORDER_ID, a);

        // commit changes
        editor.commit();
    }

    public String getKeyOrderId(){

        return pref.getString(KEY_ORDER_ID,"");

    }


    public void setKeyOrderIdNonOulet(String a){

        editor.remove(KEY_ORDER_ID_NONOUTLET);

        editor.putString(KEY_ORDER_ID_NONOUTLET, a);

        // commit changes
        editor.commit();
    }

    public String getKeyOrderIdNonOutlet(){

        return pref.getString(KEY_ORDER_ID_NONOUTLET,"");

    }

    public void setFotoSample(String a){

        editor.remove(KEY_FOTO_SAMPLE);

        editor.putString(KEY_FOTO_SAMPLE, a);

        // commit changes
        editor.commit();
    }

    public String getFotoSample(){

        return pref.getString(KEY_FOTO_SAMPLE,"");

    }


    public void setFotoStruck(String a){

        editor.remove(KEY_FOTO_STRUCK);

        editor.putString(KEY_FOTO_STRUCK, a);

        // commit changes
        editor.commit();
    }

    public String getFotoStruck(){

        return pref.getString(KEY_FOTO_STRUCK,"");

    }

    public void setFotoReceipt(String a){

        editor.remove(KEY_FOTO_RECEIPT);

        editor.putString(KEY_FOTO_RECEIPT, a);

        // commit changes
        editor.commit();
    }

    public String getFotoReceipt(){

        return pref.getString(KEY_FOTO_RECEIPT,"");

    }

    public void setValueSold(Integer a){

        editor.remove(KEY_VALUE_SOLD);

        editor.putInt(KEY_VALUE_SOLD, a);

        // commit changes
        editor.commit();
    }

    public Integer getValueSold(){

//        return pref.getString(KEY_FOTO_SAMPLE,"");
        return pref.getInt(KEY_VALUE_SOLD, 0);

    }


    public void setValueBuy(Integer a){

        editor.remove(KEY_VALUE_BUY);

        editor.putInt(KEY_VALUE_BUY, a);

        // commit changes
        editor.commit();
    }

    public Integer getValueBuy(){

//        return pref.getString(KEY_FOTO_SAMPLE,"");
        return pref.getInt(KEY_VALUE_BUY, 0);

    }

    public void removeCheckIn(){
        editor.remove(KEY_OUTLET_CHECKIN);
        editor.commit();
    }


    public void setPasswordTL(String a){

        editor.remove(KEY_PASSWORD_TL);

        editor.putString(KEY_PASSWORD_TL, a);


        // commit changes
        editor.commit();
    }


    public String getPasswordTL(){

        return pref.getString(KEY_PASSWORD_TL,"");
    }

    public void clear(){

        editorAuth.clear();
        editor.clear();
        editor.commit();
        editorAuth.commit();

    }

    public void logout(){

        clear();
    }
}
