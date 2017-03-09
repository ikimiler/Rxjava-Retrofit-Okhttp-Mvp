package com.merchantshengdacar.mvp.order;

import com.alibaba.fastjson.JSON;
import com.merchantshengdacar.net.StringObserver;

/**
 * Created by kimi on 2017/3/7 0007.
 * Email: 24750@163.com
 */

public class OrderPresenter implements OrderContract.Presenter {

    OrderContract.View mView;
    OrderContract.Task mTask;

    public OrderPresenter(OrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mTask = new OrderTask();
    }

    @Override
    public void queryOrder(OrderRequestBean bean) {
        mView.showLoadding();
        mTask.queryOrder(bean, new StringObserver() {

            @Override
            protected void onSuccess(String json) throws Exception {
//                OrderBean orderBean = JSON.parseObject(json, OrderBean.class);
//                if(orderBean.isSuccess()){
//                    mView.querySuccess(orderBean.orders);
//                }else{
//                    orderBean.showErrorMsg();
//                }
            }

            @Override
            protected void onFaild(Throwable e) {
                mView.queryFaild();
            }

            @Override
            public void onComplete() {
                mView.hiddenLoadding();
            }
        });
    }

}
