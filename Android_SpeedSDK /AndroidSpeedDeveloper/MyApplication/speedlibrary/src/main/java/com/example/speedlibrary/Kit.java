package com.example.speedlibrary;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zengzhaolin on 2016/12/15.
 */


public class Kit  implements RequestBaseUrl {

    public String startSpeedService(Context self) throws ParseException {

        if (new DateJudge().dateJudge(self)==true) {

            String number = new RequestHelper().GET("http://120.196.166.113/bdproxy/?appid=tencent");
            try {
                JSONObject json = new JSONObject(number);
                String objectString = json.getString("result");

                if (objectString != null) {

                    String speedService = new RequestHelper().POST("http://120.197.27.91:9180/services/AACAPIV1/applyTecentGamesQoS", "{\"UserIdentifier\":{\"IP\":\"" + json.getString("privateip") + "\",\"MSISDN\":\"" + json.getString("result") + "\"},\"ServiceId\":\"1000000117\",\"ResourceFeatureProperties\":[{\"Type\":0,\"Priority\":1,\"FlowProperties\":[{\"Direction\":2,\"SourceIpAddress\":\"10.229.160.177\",\"DestinationIpAddress\":\"223.167.81.81\",\"SourcePort\":40666,\"DestinationPort\":80,\"Protocol\":\"UDP\",\"MaximumUpStreamSpeedRate\":102400,\"MaximumDownStreamSpeedRate\":102400}]}], \"Duration\":1800}", json.getString("privateip"));
                    Log.i("Info", "post请求的结果" + speedService);
                    if (speedService != "请求异常" || speedService != "网络访问失败") {

                        JSONObject speedJson = new JSONObject(speedService);
                        String speedServiceString = speedJson.getString("CorrelationId");

                        //储存id
                        StoreDataKit object = new StoreDataKit();
                        object.sharedPreferencesData("CorrelationId", speedServiceString, self);

                        //储存请求成功的时间
                        Date dates = new Date();
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String time = format.format(dates);
                        object.sharedPreferencesData("date", time, self);
                        Log.i("Info", "当前时间:" + object.getSharedPreferencesData("date", self));

                        return speedServiceString;
                    }
                }

            } catch (JSONException e) {

                e.printStackTrace();

            }

            return "";
        }
        return "未超时";

    }

    public String endSpeedService(Context self){

        StoreDataKit OJ = new StoreDataKit();
        String number =  new RequestHelper().DELETE("http://120.197.27.91:9180/services/AACAPIV1/removeTecentGamesQoS/"+OJ.getSharedPreferencesData("CorrelationId",self));
        OJ.deleteStoreData(self);
        return number;
    }

}
