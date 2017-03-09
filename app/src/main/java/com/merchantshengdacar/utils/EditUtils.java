package com.merchantshengdacar.utils;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by kimi on 2017/3/7 0007.
 * Email: 24750@163.com
 */

public class EditUtils {

    public static String getEditTextContent(EditText edt){
        return edt.getEditableText().toString().trim();
    }


    /**
     * 判断是否为空
     * @param edts
     * @return
     */
    public static boolean isEmpty(EditText ... edts){
        for (EditText edt: edts) {
            if(TextUtils.isEmpty(edt.getEditableText().toString().trim())){
                return true;
            }
        }
        return false;
    }
}
