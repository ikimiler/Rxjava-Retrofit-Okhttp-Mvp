package com.merchantshengdacar.mvp.login;

import com.merchantshengdacar.mvp.BasePresenter;
import com.merchantshengdacar.mvp.BaseView;
import com.merchantshengdacar.net.BaseObserver;

import io.reactivex.Observer;

/**
 * Created by kimi on 2017/3/7 0007.
 * Email: 24750@163.com
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void loginSuccess();

        void loginFaild();

    }

    interface Presenter extends BasePresenter {

        void login(String username, String password);

    }

    interface Task {

        void login(String username, String password, Observer observer);

    }

}
