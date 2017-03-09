package com.merchantshengdacar.net;

import com.merchantshengdacar.common.BaseBean;
import com.merchantshengdacar.utils.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by kimi on 2017/3/6 0006.
 * Email: 24750@163.com
 */

public abstract class BaseObserver implements Observer<BaseBean>{

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseBean baseBean) {
        try{
            if(baseBean != null && baseBean.isSuccess()){
                onSuccess(baseBean);
            }else{
                baseBean.showErrorMsg();
            }
        }catch (Exception e){
            e.printStackTrace();
            ToastUtils.showMsg("发生错误，请重试!");
            onComplete();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onFail();
        onComplete();
    }

    @Override
    public void onComplete() {
    }

    public abstract void onSuccess(BaseBean baseBean);

    public abstract void onFail();

}
