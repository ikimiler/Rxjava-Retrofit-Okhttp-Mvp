package com.merchantshengdacar.mvp.login;

import com.merchantshengdacar.common.BaseBean;
import com.merchantshengdacar.common.Constant;
import com.merchantshengdacar.net.BaseObserver;
import com.merchantshengdacar.net.RetrofitManager;

import java.util.HashMap;

import io.reactivex.Observer;

/**
 * Created by kimi on 2017/3/7 0007.
 * Email: 24750@163.com
 */

public class LoginTask implements LoginContract.Task {


    @Override
    public void login(String username, String password, Observer observer) {

        HashMap<String, String> params = new HashMap<>();
        params.put("service","businessUserLogin");
        params.put("businessUserName",username);
        params.put("businessPassword",password);
        RetrofitManager.getInstance().post(Constant.LOGIN_URL, params,observer);
    }
}
