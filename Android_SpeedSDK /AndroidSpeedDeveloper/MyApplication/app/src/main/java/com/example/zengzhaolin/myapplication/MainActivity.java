package com.example.zengzhaolin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.ParseException;

public class MainActivity extends AppCompatActivity {

//    private SharedPreferences sharedCorrelationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(){
            public void run(){
                Kit kit = new Kit();
                try {
                    kit.startSpeedService(MainActivity.this);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                kit.endSpeedService(MainActivity.this);
                try {
                    Log.i("Info","哈哈哈"+kit.startSpeedService(MainActivity.this));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            };
        }.start();

        StoreDataKit asd = new StoreDataKit();
        asd.sharedPreferencesData("1","我是大神",this);
        Log.i("Info","啊实打实大"+asd.getSharedPreferencesData("1",this));

    }





}
