package com.merchantshengdacar.mvp.order;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.merchantshengdacar.R;
import com.merchantshengdacar.mvp.BaseFragment;
import com.merchantshengdacar.utils.DensityUtil;
import com.merchantshengdacar.utils.ToastUtils;
import com.merchantshengdacar.view.recycler.LoadMoreRecyclerOnScrollListener;
import com.merchantshengdacar.view.recycler.LoadMoreStatus;
import com.merchantshengdacar.view.recycler.OnHeaderAndFooterClickListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by kimi on 2017/3/6 0006.
 * Email: 24750@163.com
 */

public class OrderFragment extends BaseFragment<OrderContract.Presenter> implements OrderContract.View, SwipeRefreshLayout.OnRefreshListener, OnHeaderAndFooterClickListener {


    OrderRequestBean requestBean = new OrderRequestBean();
    @InjectView(R.id.recycler)
    RecyclerView mRecycler;
    @InjectView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;
    OrderAdapter mAdapter;

    public LoadMoreRecyclerOnScrollListener loadMoreRecyclerOnScrollListener;

    public static OrderFragment newInstance(Bundle bundle) {
        OrderFragment instance = new OrderFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    protected void initDatas() {
        mPresenter.queryOrder(requestBean);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        mPresenter = new OrderPresenter(this);
        loadMoreRecyclerOnScrollListener = new LoadMoreRecyclerOnScrollListener(this);
        View v = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.inject(this, v);

        mSwipeRefresh.setOnRefreshListener(this);
        return v;
    }

    @Override
    public void setPresenter(@NonNull OrderContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void querySuccess(List<OrderBean.DataBean> data) {
        loaddingEnable = true;
        loadMoreRecyclerOnScrollListener.isLoad = true;
        mSwipeRefresh.setRefreshing(false);
        mSwipeRefresh.setEnabled(true);
        if(mAdapter == null){
            mAdapter = new OrderAdapter(getContext(),data);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            mRecycler.setLayoutManager(linearLayoutManager);
            mAdapter.setOnItemClickListener(this);
            mRecycler.setAdapter(mAdapter);
            mRecycler.addOnScrollListener(loadMoreRecyclerOnScrollListener);
        }else{
            if(requestBean.page == 1){
                mAdapter.mDatas.clear();
            }
            mAdapter.mDatas.addAll(data);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void queryFaild() {
        loaddingEnable = true;
        loadMoreRecyclerOnScrollListener.isLoad = true;
        mSwipeRefresh.setRefreshing(false);
        mSwipeRefresh.setEnabled(true);
        ToastUtils.showMsg("出现错误，请重试!");
    }


    @Override
    public void onRefresh() {
        loadMoreRecyclerOnScrollListener.isLoad = false;
        loaddingEnable = false;
        requestBean.page = 1;
        mPresenter.queryOrder(requestBean);
    }

    @Override
    public void onHeaderClick(View v, int position) {
    }

    @Override
    public void onItemViewClick(View v , int position) {
    }

    @Override
    public void onLoadMore() {
        loaddingEnable = false;
        mSwipeRefresh.setEnabled(false);
        ++requestBean.page;
        mPresenter.queryOrder(requestBean);
    }
}
