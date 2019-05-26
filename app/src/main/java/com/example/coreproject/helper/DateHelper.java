package com.example.coreproject.helper;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateHelper {

    public static String nameOfDay(int year, int month, int dayOfMonth){


        Calendar cal = new GregorianCalendar(year, month, dayOfMonth);
        String dayLongName = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        Log.e("dayLongName",String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(dayOfMonth)+"="+dayLongName);
        return dayLongName;
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
}
