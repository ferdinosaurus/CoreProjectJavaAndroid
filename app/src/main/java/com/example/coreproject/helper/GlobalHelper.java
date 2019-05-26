package com.example.coreproject.helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.os.ConfigurationCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.coreproject.R;
import com.example.coreproject.parcelable.UserProfile;
import com.example.coreproject.retrofit.Urls;
import com.example.coreproject.utils.Constant;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class GlobalHelper {

    static int  imgType, xPoint, yPoint,yPoint2;
    private Context context;
    private Activity activity;
    RelativeLayout relativeLayout;
    SessionHelper sessionHelper;
    ProgressDialog pd;
    String version;
    public GlobalHelper() {
    }

    public GlobalHelper(Activity activity) {
        this.activity = activity;
        sessionHelper = new SessionHelper(activity);
        pd = new ProgressDialog(activity);
    }

    public GlobalHelper(Context context) {
        this.context = context;
        sessionHelper = new SessionHelper(context);
        pd = new ProgressDialog(context);
    }

//    public GlobalHelper(Context context) {
//        this.context = context;
//        sessionHelper = new SessionHelper(activity);
//        pd = new ProgressDialog(activity);
//    }



//    public void checkDevMode() {
//
//        relativeLayout = (RelativeLayout) activity.findViewById(R.id.modeDev);
//
//        if (sessionHelper.getProfile().getEnv().equals(Constant.PROD) || sessionHelper.getProfile().getEnv().equals("") ){
//            relativeLayout.setVisibility(View.GONE);
//
//        } else {
//            relativeLayout.setVisibility(View.VISIBLE);
//
//        }
//
//    }


    public void initEnvironment(){
        UserProfile userProfile;
        sessionHelper = new SessionHelper(activity);
        userProfile = sessionHelper.getProfile();
        if(userProfile.getEnv().equals(Constant.DEV)){
            sessionHelper.setModeProd(false);
        }else {
            sessionHelper.setModeProd(true);
        }
        Log.i("ENVIRONMENT",userProfile.getEnv()+"/"+new Urls().getAPI());


    }


    public String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this.activity, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current loction", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction ", "Canont get Address!");
        }
        return strAdd;
    }


    public String getDefLanguageCode(){
        Locale locale = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);

        return locale.getLanguage();

    }

    public Marker createMarker(GoogleMap googleMap, double latitude, double longitude, String title, String snippet, Bitmap iconResID) {
        //GoogleMap googleMap;
        return googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.fromBitmap(iconResID)));
    }


    public Bitmap createIconMarker(int iconResID, int width, int height) {


        BitmapDrawable bitmapdraw = (BitmapDrawable) activity.getResources().getDrawable(iconResID);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        return smallMarker;

    }

    public Circle createCircleMap(GoogleMap googleMap, Double lat, Double lang, int radius) {

        Circle circle = googleMap.addCircle(new CircleOptions()
                .center(new LatLng(lat, lang))
                .radius(radius) // in meters
                .strokeColor(Color.RED)
                .fillColor(0x5500ff00));

        return circle;
    }

    public int locationDistance(Double lat1, Double long1, Double lat2, Double long2) {


        float[] results = new float[1];
        Location.distanceBetween(lat1, long1,
                lat2, long2, results);

        return Math.round(results[0]);

    }


    public void createDialog(String title, String message,final boolean finishActivity) {
        AlertDialog alertDialog;

        alertDialog = new AlertDialog.Builder(activity).create();

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, activity.getResources().getString(R.string.app_name), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                if(finishActivity)
                    activity.finish();
                else
                    dialog.dismiss();
            }
        });

        alertDialog.setButton(Dialog.BUTTON_NEGATIVE, activity.getResources().getString(R.string.app_name), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed

                dialog.dismiss();
            }
        });
        alertDialog.show();

    }

    public void createDialog(String title, String message,String btn_positive, String btn_negative,final boolean finishActivity) {
        AlertDialog alertDialog;


        alertDialog = new AlertDialog.Builder(activity).create();

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, btn_positive, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                if(finishActivity)
                    activity.finish();
                else
                    dialog.dismiss();
            }
        });

        alertDialog.setButton(Dialog.BUTTON_NEGATIVE, btn_negative, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed

                dialog.dismiss();
            }
        });
        alertDialog.show();

    }

    public void createDialogClosed(String title, String message,String btn_positive) {
        AlertDialog alertDialog;


        alertDialog = new AlertDialog.Builder(activity).create();

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, btn_positive, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                dialog.dismiss();
            }
        });

        alertDialog.show();

    }

    public void createDialogIntent(String title, String message, String btn_positive, final Intent intent) {
        AlertDialog alertDialog;

        alertDialog = new AlertDialog.Builder(activity).create();

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, btn_positive, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed

                Intent i = intent;
                activity.startActivity(i);
                activity.finish();

            }
        });

        alertDialog.show();

        //return alertDialog;
    }

    @SuppressLint("MissingPermission")
    public String getIMEI(Activity activity) {
        TelephonyManager telephonyManager = (TelephonyManager) activity
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }



    public String getVersion(){
        String version = null;
        try {
            PackageInfo pInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }

    public void sessionExpired(){

        AlertDialog alertDialog;

        alertDialog = new AlertDialog.Builder(activity).create();

        alertDialog.setTitle(activity.getResources().getString(R.string.app_name));
        alertDialog.setMessage(activity.getResources().getString(R.string.app_name));
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, activity.getResources().getString(R.string.app_name), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed


                /*
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.finish();
                dialog.dismiss();
                */

            }
        });

        alertDialog.show();

    }


    public void sessionExpiredParam(String message){

        AlertDialog alertDialog;

        alertDialog = new AlertDialog.Builder(activity).create();

        alertDialog.setTitle(activity.getResources().getString(R.string.app_name));
        alertDialog.setMessage(message);
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, activity.getResources().getString(R.string.app_name), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed


                /*
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.finish();
                dialog.dismiss();
                */

            }
        });

        alertDialog.show();

    }


    public void setProgressBar(String title, String message) {

        pd.setTitle(title);
        pd.setMessage(message);
        pd.setCancelable(false);
        pd.show();
    }

    public void dissmissPDialog() {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }

    public void createToast(int stringId){

        Toast.makeText(activity,activity.getResources().getString(stringId),Toast.LENGTH_SHORT).show();

    }

    public void createToast(String msg){

        Toast.makeText(activity, msg,Toast.LENGTH_SHORT).show();

    }

    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss Z", Locale.getDefault());
        Date date = new Date();
        String s = dateFormat.format(date);
        String fDate = s.substring(0, s.length() - 2) + ":" + s.substring(s.length() - 2, s.length());

        return fDate;
    }

    public static String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String fDate = dateFormat.format(date);

        return fDate;
    }


    public static Integer getYearNow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy", Locale.getDefault());
        Date date = new Date();
        int yDate = Integer.parseInt(dateFormat.format(date));

        return yDate;
    }

    public static String getMonthYearNow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MMM-yyyy", Locale.getDefault());
        Date date = new Date();
//        String yDate = Integer.parseInt(dateFormat.format(date));
        String yDate = String.valueOf(dateFormat.format(date));

        return yDate;
    }

    public static Date to_date(String dtStart) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");

        Date date = null;
        try {
            date = dateFormat.parse(dtStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return date;

    }

    public static String DateYesterday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Create a calendar object with today date. Calendar is in java.util pakage.
        Calendar calendar = Calendar.getInstance();

        // Move calendar to yesterday
        calendar.add(Calendar.DATE, -1);

        // Get current date of calendar which point to the yesterday now
        Date yesterday = calendar.getTime();

        return dateFormat.format(yesterday).toString();
    }


    public static String DateNow() {
        DateFormat dateFormat = new SimpleDateFormat("MMM-yyyy");

        // Create a calendar object with today date. Calendar is in java.util pakage.
        Calendar calendar = Calendar.getInstance();

        // Move calendar to yesterday
        calendar.add(Calendar.DATE, 0);

        // Get current date of calendar which point to the yesterday now
        Date yesterday = calendar.getTime();

        return dateFormat.format(yesterday).toString();
    }

    public static String DateLast6() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Create a calendar object with today date. Calendar is in java.util pakage.
        Calendar calendar = Calendar.getInstance();

        // Move calendar to yesterday
        calendar.add(Calendar.DATE, -7);

        // Get current date of calendar which point to the yesterday now
        Date last_six = calendar.getTime();

        return dateFormat.format(last_six).toString();
    }

    public static String strDateToChar(String dtStart) {

        SimpleDateFormat datein = new SimpleDateFormat(
                "yyyy-MM-dd");

        SimpleDateFormat dateout = new SimpleDateFormat(
                "dd-MM-yyyy");
        String strDate=null;
        Date date = null;
        try {
            date = datein.parse(dtStart);
            strDate = dateout.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return strDate;

    }

    public static Date dateToChar(String dtStart) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy");

        Date date = null;
        try {
            date = dateFormat.parse(dtStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;

    }

    public void updateApplication(){
        AlertDialog alertDialog;

        alertDialog = new AlertDialog.Builder(activity).create();

        alertDialog.setTitle(activity.getResources().getString(R.string.app_name));
        alertDialog.setMessage(activity.getResources().getString(R.string.app_name));
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, activity.getResources().getString(R.string.app_name), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed


                openPlayStore(activity);
                dialog.dismiss();
                activity.finish();
            }
        });

        alertDialog.show();


    }
    public static void openPlayStore(Context context) {
        // you can also use BuildConfig.APPLICATION_ID

        String appId = context.getPackageName();
        Intent rateIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("market://details?id=" + appId));
        boolean marketFound = false;

        // find all applications able to handle our rateIntent
        final List<ResolveInfo> otherApps = context.getPackageManager()
                .queryIntentActivities(rateIntent, 0);

        for (ResolveInfo otherApp: otherApps) {
            // look for Google Play application
            if (otherApp.activityInfo.applicationInfo.packageName
                    .equals("com.android.vending")) {

                ActivityInfo otherAppActivity = otherApp.activityInfo;
                ComponentName componentName = new ComponentName(
                        otherAppActivity.applicationInfo.packageName,
                        otherAppActivity.name
                );
                // make sure it does NOT open in the stack of your activity
                rateIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // task reparenting if needed
                rateIntent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                // if the Google Play was already open in a search result
                //  this make sure it still go to the app page you requested
                rateIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // this make sure only the Google Play app is allowed to
                // intercept the intent
                rateIntent.setComponent(componentName);
                context.startActivity(rateIntent);
                marketFound = true;
                break;

            }
        }

        // if GP not present on device, open web browser
        if (!marketFound) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id="+appId));
            context.startActivity(webIntent);
        }
    }


    public static String baseConverterWatermark(String path,String customer,String salesman) {
        String s=customer.toUpperCase()+" - "+salesman;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        Bitmap scaledBitmap = getResizedBitmap(bitmap );
        if (imgType == 0) {
            yPoint = 270;
            yPoint2 = 302;
        }else{
            yPoint = 430;
            yPoint2 = 462;
        }
        Point point = new Point(20, yPoint);
        Point point2 = new Point(20, yPoint2);
        Bitmap newBitmapLine1 = mark(scaledBitmap, s, point, Color.BLACK, 100, 16, true, Typeface.DEFAULT_BOLD);

        Bitmap newBitmap = markLine2(newBitmapLine1, getDateTime(), point2, Color.BLACK, 100, 16, true, Typeface.DEFAULT_BOLD);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 40, outputStream);

        byte[] bytes = outputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public static Bitmap getResizedBitmap(Bitmap bm) {


        int width = bm.getWidth();
        int height = bm.getHeight();
        int newWidth, newHeight;

        if (width > height) {
            newWidth = 480;
            newHeight = 320;
            imgType=0; // landscape
        } else {
            newWidth = 320;
            newHeight = 480;
            imgType=1; // potrait
        }

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

//=================WATERMARK===================//
    public static Bitmap mark(Bitmap src, String watermark, Point location, int color, int alpha,
                              int size, boolean underline, Typeface typeface) {
        int w = src.getWidth();
        int h = src.getHeight();
        int margin=5;
        Paint.FontMetrics fm = new Paint.FontMetrics();
        Bitmap result = Bitmap.createBitmap(w, h, src.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, null);
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAlpha(alpha);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(size);
        paint.setAntiAlias(true);
        paint.setUnderlineText(underline);
        paint.setTypeface(typeface);
        paint.getFontMetrics(fm);
        canvas.drawRect(20 - margin, yPoint + fm.top - margin,
                20 + paint.measureText(watermark) + margin,
                yPoint + fm.bottom+ margin, paint);
        paint.setColor(Color.WHITE);
        canvas.drawText(watermark, location.x, location.y, paint);

        return result;
    }

    public static Bitmap markLine2(Bitmap src, String watermark, Point location, int color, int alpha,
                                   int size, boolean underline, Typeface typeface) {
        int w = src.getWidth();
        int h = src.getHeight();
        int margin=5;
        Paint.FontMetrics fm = new Paint.FontMetrics();
        Bitmap result = Bitmap.createBitmap(w, h, src.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, null);
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAlpha(alpha);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(size);
        paint.setAntiAlias(true);
        paint.setUnderlineText(underline);
        paint.setTypeface(typeface);
        paint.getFontMetrics(fm);
        canvas.drawRect(20 - margin, yPoint2 + fm.top - margin,
                20 + paint.measureText(watermark) + margin,
                yPoint2 + fm.bottom+ margin, paint);
        paint.setColor(Color.WHITE);
        canvas.drawText(watermark, location.x, location.y, paint);

        return result;
    }

//=====================AUTO DATE======================//
    public static String autodatess() {
        SimpleDateFormat simple = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();
        return simple.format(date);
    }

    public static String ParsInt(String Sparam){

        String dot = Sparam.replace(".","");
        String coma = dot.replace(",","");
        String doubledot = coma.replace(":","");
        String doubleComa = doubledot.replace(";","");
        String dash = doubleComa.replace("-","");
        String parse = dash.replace("/","");

        return parse;
    }

    public static String ParsString(String Sparam){

        String dot = Sparam.replace(".","");
        String coma = dot.replace(",","");
        String doubledot = coma.replace(":","");
        String doubleComa = doubledot.replace(";","");
        String doubleQuote = doubleComa.replace("\"","");
        String dash = doubleQuote.replace("-","");
        String kutip = dash.replace("'", "");
        String parse = kutip.replace("/","");

        return parse;
    }


    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }






    public int checkStringToInt(@Nullable String data){
        int i = 0;

        if (data.isEmpty() || data.equals("")|| data == null ||data.length()==0)
            i = 0;
        else
            i = Integer.parseInt(data);

        return i;

    }

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static String format_number(float value){


        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String formattedString = formatter.format(value);

        return formattedString;
    }

    public static String format_number(int value){


        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String formattedString = formatter.format(value);

        return formattedString;
    }


    public static String format_number(long value) {


        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String formattedString = formatter.format(value);

        return formattedString;

    }

    public static void deleteFileFromMediaStore(String mCurrentPhotoPath) {

        new File(mCurrentPhotoPath).delete();


    }

    public String version_app() {

        try {
            PackageInfo pInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            version = pInfo.versionName;
//            txtVersion.setText("Version : "+version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }

    public String genTrxIdLoadingMaterial(Integer typeLoading){

        UserProfile userProfile;
        sessionHelper = new SessionHelper(activity);
        userProfile = sessionHelper.getProfile();


        String result = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyMMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String yymmdd = dateFormat.format(date);

        Random rand = new Random();

        int  n = rand.nextInt(50) + 1;

        String uuid = UUID.randomUUID().toString();
        if (typeLoading == 1)
            result = "LMD/"+userProfile.getUser_id().toUpperCase()+"/"+yymmdd+n;
        else
            result = "LMG/"+userProfile.getUser_id().toUpperCase()+"/"+yymmdd+n;

        return result;
    }


    public String genTrxIDOrder(){

        UserProfile userProfile;
        sessionHelper = new SessionHelper(activity);
        userProfile = sessionHelper.getProfile();


        String result = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyMMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String yymmdd = dateFormat.format(date);

        Random rand = new Random();

        int  n = rand.nextInt(50) + 1;

        String uuid = UUID.randomUUID().toString();

        result = "AVIS/OR/"+userProfile.getUser_id().toUpperCase()+"/"+yymmdd+n;

        return result;
    }

    public String genTrxIDOrderNonOutlet(){

        UserProfile userProfile;
        sessionHelper = new SessionHelper(activity);
        userProfile = sessionHelper.getProfile();


        String result = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyMMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String yymmdd = dateFormat.format(date);

        Random rand = new Random();

        int  n = rand.nextInt(50) + 1;

        String uuid = UUID.randomUUID().toString();

        result = "AVIS/ORN/"+userProfile.getUser_id().toUpperCase()+"/"+yymmdd+n;

        return result;
    }

    public String genTrxPOSM(String userid){


        String result = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyMMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String yymmdd = dateFormat.format(date);

        Random rand = new Random();

        int  n = rand.nextInt(50) + 1;

        String uuid = UUID.randomUUID().toString();

        result = "POSM/"+userid.toUpperCase()+"/"+yymmdd+n;

        return result;
    }

    public String genTrxMarketSurvey(String userid){


        String result = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyMMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String yymmdd = dateFormat.format(date);

        Random rand = new Random();

        int  n = rand.nextInt(50) + 1;

        String uuid = UUID.randomUUID().toString();

        result = "MS/"+userid.toUpperCase()+"/"+yymmdd+n;

        return result;
    }

    public static String genNooId(String salesId){

        String result = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyMMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String yymmdd = dateFormat.format(date);

        Random rand = new Random();

        int  n = rand.nextInt(50) + 1;

        String uuid = UUID.randomUUID().toString();

        result = salesId+yymmdd+n;

        return result;
    }

    public String genTrxCashID(){

        UserProfile userProfile;
        sessionHelper = new SessionHelper(activity);
        userProfile = sessionHelper.getProfile();


        String result = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyMMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String yymmdd = dateFormat.format(date);

        Random rand = new Random();

        int  n = rand.nextInt(50) + 1;

        String uuid = UUID.randomUUID().toString();

        result = "AVIS/CASH/"+userProfile.getUser_id().toUpperCase()+"/"+yymmdd+n;

        return result;
    }


    public String genTrxCashDistributor(){

        UserProfile userProfile;
        sessionHelper = new SessionHelper(activity);
        userProfile = sessionHelper.getProfile();


        String result = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyMMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String yymmdd = dateFormat.format(date);

        Random rand = new Random();

        int  n = rand.nextInt(50) + 1;

        String uuid = UUID.randomUUID().toString();

        result = "AVIS/CASH/"+userProfile.getUser_id().toUpperCase()+"/"+yymmdd+n;

        return result;
    }

    public static String cleanString(String input){
       return input.replaceAll("[^\\p{Alpha}\\p{Digit}]+","");
    }



    public static long getdiffDateMonth(Integer s1, Integer s2){
        Calendar mycal = new GregorianCalendar(s2, s1, 1);

// Get the number of days in that month
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

        return daysInMonth;
    }

    public static long getDiffDate(String s1, String s2){

//        Date d1 = dateToChar(s1);
//        Date d2 = dateToChar(s2);

        Date dates = null;
        Date daten = null;


        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");

        try {
            dates = dateFormat.parse(s1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            daten = dateFormat.parse(s2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // HH converts hour in 24 hours format (0-23), day calculation
        // must match with your date format
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long diffDays=0;
        try {

            //in milliseconds
            long diff = daten.getTime() - dates.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");



        } catch (Exception e) {
            e.printStackTrace();
        }

        return diffDays;

    }

    public static String nameOfDay(){

        Calendar sCalendar = Calendar.getInstance();
        String dayLongName = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        Log.e("dayLongName",dayLongName);

        return dayLongName;
    }

    public static String nameOfDay(int year, int month, int dayOfMonth){


        Calendar cal = new GregorianCalendar(year, month, dayOfMonth);
        String dayLongName = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        Log.e("dayLongName",String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(dayOfMonth)+"="+dayLongName);
        return dayLongName;
    }

    public String getSecurityCode(){
        String res="";

        res= GlobalHelper.MD5(sessionHelper.getWarehouseLogOn()+ GlobalHelper.getDate());

        return res;
    }
}
