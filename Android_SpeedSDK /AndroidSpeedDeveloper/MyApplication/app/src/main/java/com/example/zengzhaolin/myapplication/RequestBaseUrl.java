package com.example.zengzhaolin.myapplication;

import android.content.Context;

import java.text.ParseException;

/**
 * Created by zengzhaolin on 2016/12/19.
 */

public interface RequestBaseUrl {

    public String startSpeedService(Context self) throws ParseException;

    public String endSpeedService(Context self);

}
