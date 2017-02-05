package com.example.speedlibrary;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zengzhaolin on 2016/12/20.
 */

public class StoreDataKit  {

    private SharedPreferences sharedCorrelationId ;

//    public String correlationId;
//
//    StoreDataKit() {}
//    private static final StoreDataKit single = new StoreDataKit();
//    //静态工厂方法
//    public static StoreDataKit getInstance() {
//        return single;
//    }


    public  void sharedPreferencesData(String object ,String data, Context a){

        sharedCorrelationId = a.getSharedPreferences("correlation",MODE_PRIVATE);
        //获取editor对象
        SharedPreferences.Editor editor = sharedCorrelationId.edit();
        //存储数据时选用对应类型的方法
        editor.putString(object,data);
        //提交保存数据
        editor.commit();

    }

    public String getSharedPreferencesData(String object , Context a){

        sharedCorrelationId = a.getSharedPreferences("correlation",MODE_PRIVATE);
        //获取editor对象
        SharedPreferences.Editor editor = sharedCorrelationId.edit();

        return sharedCorrelationId.getString(object,"");

    }

    public void deleteStoreData(Context a){

        sharedCorrelationId = a.getSharedPreferences("correlation",MODE_PRIVATE);
        //获取editor对象
        SharedPreferences.Editor editor = sharedCorrelationId.edit();

        sharedCorrelationId.edit().clear().commit();

    }





}
