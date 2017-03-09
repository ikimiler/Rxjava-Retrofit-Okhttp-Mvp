package com.merchantshengdacar.mvp.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.merchantshengdacar.common.BaseBean;
import com.merchantshengdacar.net.BaseObserver;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by kimi on 2017/3/7 0007.
 * Email: 24750@163.com
 */

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View mView;
    LoginContract.Task mTask;

    public LoginPresenter(LoginContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mTask = new LoginTask();
    }

    @Override
    public void login(String username, String password) {
        mView.showLoadding();
        mTask.login(username, password, new Observer<String>(){

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
