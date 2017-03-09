package com.merchantshengdacar.mvp.order;

import com.merchantshengdacar.common.BaseBean;
import com.merchantshengdacar.mvp.BasePresenter;
import com.merchantshengdacar.mvp.BaseView;
import com.merchantshengdacar.net.BaseObserver;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by kimi on 2017/3/6 0006.
 * Email: 24750@163.com
 */

public interface OrderContract {

    interface View extends BaseView<Presenter> {

        void querySuccess(List<OrderBean.DataBean> data);

        void queryFaild();

    }

    interface Presenter extends BasePresenter {

        void queryOrder(OrderRequestBean bean);

    }

    interface Task {

        void queryOrder(OrderRequestBean bean, Observer observer);
    }


}
