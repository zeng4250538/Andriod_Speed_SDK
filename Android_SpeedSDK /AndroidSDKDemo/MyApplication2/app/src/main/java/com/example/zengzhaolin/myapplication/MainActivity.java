package com.example.zengzhaolin.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.speedlibrary.Kit;

import java.text.ParseException;

public class MainActivity extends AppCompatActivity {

    private String content=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button)findViewById(R.id.button);
        final Handler handler = new Handler();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView asd = (TextView)findViewById(R.id.textView2);
                new Thread(){

                    public void run(){

                        Kit kit = new Kit();
                        try {
                          content = kit.startSpeedService(MainActivity.this);

                            handler.post(runnableUi);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }

                }.start();

            }
        });


        Button button2 = (Button)findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView asd = (TextView)findViewById(R.id.textView2);
                new Thread(){

                    public void run(){

                        Kit kit = new Kit();
                        content = kit.endSpeedService(MainActivity.this);

                        handler.post(runnableUi);

                    }

                }.start();

            }
        });

    }



    // 构建Runnable对象，在runnable中更新界面
    Runnable  runnableUi=new  Runnable(){
        @Override
        public void run() {
            //更新界面
            TextView asd = (TextView)findViewById(R.id.textView2);
            asd.setText("the Content is:"+content);
        }

    };
}
