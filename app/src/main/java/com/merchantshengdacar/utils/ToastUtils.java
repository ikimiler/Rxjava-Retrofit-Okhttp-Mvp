package com.merchantshengdacar.utils;

import android.widget.Toast;

import com.merchantshengdacar.mvp.BaseApplication;

/**
 * Created by kimi on 2017/3/6 0006.
 * Email: 24750@163.com
 */

public class ToastUtils {

    public static Toast mToast;

    public static void showMsg(String text){

        if(mToast == null){
            mToast = Toast.makeText(BaseApplication.getInstance(), text, Toast.LENGTH_SHORT);
            mToast.show();
        }else{
            mToast.setText(text);
            mToast.show();
        }
    }
}
