package com.example.speedlibrary;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zengzhaolin on 2016/12/28.
 */

public class DateJudge {

    public boolean dateJudge (Context self) throws ParseException {

        if (new StoreDataKit().getSharedPreferencesData("date",self).length()>0) {

            if (dateData(self)==true){

                return true;}

            else {

                return false;

            }
        }
        return true;
    }

    private boolean dateData(Context self) throws ParseException {

        StoreDataKit data = new StoreDataKit();
        String time1 = data.getSharedPreferencesData("date",self);
        Date dates = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time2 = format.format(dates);

        Date T1 = format.parse(time1);
        Date T2 = format.parse(time2);
        long T3 = (T2.getTime()-T1.getTime())/1000;

        if (T3>1800){

            return true;
        }
        return false;
    }

}
