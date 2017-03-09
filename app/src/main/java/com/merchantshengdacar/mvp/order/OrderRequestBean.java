package com.merchantshengdacar.mvp.order;

import com.merchantshengdacar.common.Constant;
import com.merchantshengdacar.utils.SharePrefrencesUtil;

import java.io.Serializable;

/**
 * Created by kimi on 2017/3/7 0007.
 * Email: 24750@163.com
 */

public class OrderRequestBean implements Serializable {

    public String shopId = "3212";//SharePrefrencesUtil.getInstance().getString(Constant.KEY_SHHOP_ID);
    public int orderType=5;
    public String querydate="";
    public int page = 1;
    public String pageSize = "10";
}
