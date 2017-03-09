package com.merchantshengdacar.net;

import com.merchantshengdacar.utils.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by kimi on 2017/3/8 0008.
 * Email: 24750@163.com
 */

public abstract class StringObserver implements Observer<String> {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String s) {
        try {
            onSuccess(s);
        }catch (Exception e){
            e.printStackTrace();
            ToastUtils.showMsg("发生错误，请重试!");
            onComplete();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onFaild(e);
        onComplete();
    }

    protected abstract void onSuccess(String json) throws Exception;

    protected abstract void onFaild(Throwable e);

}
