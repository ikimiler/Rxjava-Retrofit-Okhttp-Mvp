package com.merchantshengdacar.view.recycler;

import android.view.View;

/**
 * Created by kimi on 2017/3/8 0008.
 * Email: 24750@163.com
 */

public interface OnHeaderAndFooterClickListener {

    void onHeaderClick(View v, int position);

    void onItemViewClick(View v,int position);

    void onLoadMore();

}
