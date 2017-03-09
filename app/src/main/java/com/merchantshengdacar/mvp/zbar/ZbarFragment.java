package com.merchantshengdacar.mvp.zbar;

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

public class ZbarFragment extends BaseFragment<ZbarContract.Presenter> implements
        ZbarContract.View {

    public static ZbarFragment newInstance(Bundle bundle) {
        ZbarFragment instance = new ZbarFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    protected void lzyLoadData() {
        Toast.makeText(mActivity, "lanjiazai  zbar", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        TextView textView = new TextView(mActivity);
        textView.setText("zbar");
        return textView;
    }

    @Override
    public void setPresenter(@NonNull ZbarContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
