package com.merchantshengdacar.mvp.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.merchantshengdacar.R;
import com.merchantshengdacar.utils.ToastUtils;
import com.merchantshengdacar.view.recycler.HeaderAndFooterAdapter;

import java.util.List;

/**
 * Created by kimi on 2017/3/8 0008.
 * Email: 24750@163.com
 */

public class OrderAdapter extends HeaderAndFooterAdapter<OrderAdapter.ViewHolder,List<OrderBean.DataBean>> {


    public OrderAdapter(Context cxt, List<OrderBean.DataBean> data) {
        super(cxt, data);
    }

    @Override
    protected int getCount() {
        if(mDatas != null && mDatas.size() > 0 ){
            return mDatas.size();
        }
        return 0;
    }

    @Override
    protected void setViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder h = (ViewHolder) holder;
        h.setDatas(mDatas.get(position));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item_,parent,false));
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView typeName,orderNumber,userName,orderTime;

        public ViewHolder(View v) {
            super(v);
            typeName = (TextView) v.findViewById(R.id.typeName);
            orderNumber = (TextView) v.findViewById(R.id.orderNumber);
            userName = (TextView) v.findViewById(R.id.userName);
            orderTime = (TextView) v.findViewById(R.id.orderTime);

        }

        public void setDatas(final OrderBean.DataBean bean) {
            typeName.setText(bean.activityName);
            orderNumber.setText(bean.orderId);
            userName.setText(bean.userName);
            orderTime.setText(bean.date);
            typeName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showMsg("xxxxxx");
                }
            });
        }
    }
}
