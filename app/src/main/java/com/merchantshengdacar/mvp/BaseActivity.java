package com.merchantshengdacar.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.merchantshengdacar.common.LoaddingDialog;
import com.merchantshengdacar.utils.NetLog;

/**
 * Created by kimi on 2017/3/6 0006.
 * Email: 24750@163.com
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView<T> {

    protected LoaddingDialog mProgressDialog;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetLog.d("", this.getClass().getSimpleName());
        mProgressDialog = new LoaddingDialog();
        initViews();
        initDatas();
    }

    /**
     * 初始化数据
     */
    protected void initDatas() {
    }

    ;

    /**
     * 加载view
     */
    protected abstract void initViews();

    @Override
    public void showLoadding() {
        if (mProgressDialog != null && getSupportFragmentManager().findFragmentByTag(mProgressDialog.getClass().getSimpleName()) == null) {
            mProgressDialog.show(getSupportFragmentManager(), mProgressDialog.getClass().getSimpleName());
        }
    }

    @Override
    public void hiddenLoadding() {
        if (mProgressDialog != null && getSupportFragmentManager().findFragmentByTag(mProgressDialog.getClass().getSimpleName()) != null) {
            mProgressDialog.dismiss();
        }
    }
}
