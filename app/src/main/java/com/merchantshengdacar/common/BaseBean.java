package com.merchantshengdacar.common;

import com.merchantshengdacar.utils.ToastUtils;

import java.io.Serializable;

/**
 * Created by kimi on 2017/3/6 0006.
 * Email: 24750@163.com
 */

public class BaseBean<T> implements Serializable{

    public T data;
    public String resultCode;//	SUCCESS
    public String resultDesc;//	成功,系统处理正常

    public boolean isSuccess(){
        return "SUCCESS".equals(resultCode);
    }

    public void showErrorMsg(){
        ToastUtils.showMsg(resultDesc);
    }
}
