package com.merchantshengdacar.view.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.merchantshengdacar.R;
import com.merchantshengdacar.utils.DensityUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.merchantshengdacar.view.recycler.LoadMoreStatus.LOAD_RUNNING;

/**
 * Created by kimi on 2017/3/8 0008.
 * Email: 24750@163.com
 */

public class LoadMoreView extends LinearLayout {


    @InjectView(R.id.loadding_running)
    LinearLayout mLoaddingRunning;
    @InjectView(R.id.loadding_faild)
    LinearLayout mLoaddingFaild;
    @InjectView(R.id.loadding_empty)
    LinearLayout mLoaddingEmpty;

    public LoadMoreStatus currentStatus = LOAD_RUNNING;

    public LoadMoreView(Context context) {
        this(context, null);
    }

    public LoadMoreView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.loadmore_layout, this);
        ButterKnife.inject(this);
        setMinimumWidth(DensityUtil.getScreenWidth(context));
        setGravity(Gravity.CENTER);
    }

    public void setLoadMoreStatus(LoadMoreStatus status) {
        currentStatus = status;
        switch (status) {
            case LOAD_RUNNING:
                mLoaddingEmpty.setVisibility(View.GONE);
                mLoaddingFaild.setVisibility(View.GONE);
                mLoaddingRunning.setVisibility(View.VISIBLE);
                break;
            case LOAD_FAILD:
                mLoaddingEmpty.setVisibility(View.GONE);
                mLoaddingFaild.setVisibility(View.VISIBLE);
                mLoaddingRunning.setVisibility(View.GONE);
                break;
            case LOAD_EMPTY:
                mLoaddingEmpty.setVisibility(View.VISIBLE);
                mLoaddingFaild.setVisibility(View.GONE);
                mLoaddingRunning.setVisibility(View.GONE);
                break;
            case LOAD_GONE:
                mLoaddingEmpty.setVisibility(View.GONE);
                mLoaddingFaild.setVisibility(View.GONE);
                mLoaddingRunning.setVisibility(View.GONE);
                break;
        }
    }
}
