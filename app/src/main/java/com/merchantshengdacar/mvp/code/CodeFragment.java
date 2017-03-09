package com.merchantshengdacar.mvp.code;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.merchantshengdacar.mvp.BaseFragment;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


/**
 * Created by kimi on 2017/3/6 0006.
 * Email: 24750@163.com
 */

public class CodeFragment extends BaseFragment<CodeContract.Presenter> implements CodeContract.View {


    public static CodeFragment newInstance(Bundle bundle) {
        CodeFragment instance = new CodeFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void setPresenter(CodeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    protected void lzyLoadData() {


    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        TextView textView = new TextView(mActivity);
        textView.setText("code");
        return textView;
    }


}
