package com.merchantshengdacar.mvp;

import android.app.Application;

/**
 * Created by kimi on 2017/3/6 0006.
 * Email: 24750@163.com
 */

public class BaseApplication extends Application {

    private static BaseApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static BaseApplication getInstance(){
       return  mInstance;
    }
}
