package com.merchantshengdacar.mvp.order;

import com.merchantshengdacar.common.Constant;
import com.merchantshengdacar.net.RetrofitManager;

import java.util.HashMap;

import io.reactivex.Observer;

/**
 * Created by kimi on 2017/3/7 0007.
 * Email: 24750@163.com
 */

public class OrderTask implements OrderContract.Task {


    @Override
    public void queryOrder(OrderRequestBean bean, Observer observer) {

        HashMap<String, String> params = new HashMap<>();
//        params.put("service", "businessQueryOrder");
//        params.put("shopId", bean.shopId);
//        params.put("orderType", bean.orderType+"");
//        params.put("querydate", bean.querydate);
//        params.put("page", bean.page+"");
//        params.put("pageSize", bean.pageSize);


        params.put("App_name","测试父类apps");
        params.put("Os ","android");
        params.put("from","1");
        params.put("to","1");
        params.put("content","2");

        //http://172.23.13.41/goodsinfo
        //App_name = 测试父类apps  , Os = android , from = 1 , to = 1, content =2

        RetrofitManager.getInstance().post("goodsinfo", params, observer);
    }
}
