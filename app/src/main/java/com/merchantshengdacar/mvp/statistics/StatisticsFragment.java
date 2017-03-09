package com.merchantshengdacar.mvp.statistics;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.merchantshengdacar.mvp.BaseFragment;


/**
 * Created by kimi on 2017/3/6 0006.
 * Email: 24750@163.com
 */

public class StatisticsFragment extends BaseFragment<StatisticsContract.Presenter> implements
        StatisticsContract.View {

    public static StatisticsFragment newInstance(Bundle bundle) {
        StatisticsFragment instance = new StatisticsFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    protected void lzyLoadData() {
        Toast.makeText(mActivity, "lanjiazai  statistics", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        TextView textView = new TextView(mActivity);
        textView.setText("statistics");
        return textView;
    }

    @Override
    public void setPresenter(@NonNull StatisticsContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
