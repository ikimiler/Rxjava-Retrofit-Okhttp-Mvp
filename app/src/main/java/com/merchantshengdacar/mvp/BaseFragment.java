package com.merchantshengdacar.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.merchantshengdacar.common.LoaddingDialog;

/**
 * Created by kimi on 2017/3/6 0006.
 * Email: 24750@163.com
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView<T> {

    public static final String KEY_HIDDEN = "isHidden";
    public boolean isFirst = true;
    protected View mView;
    protected Activity mActivity;
    protected T mPresenter;
    protected LoaddingDialog mProgressDialog;
    protected boolean loaddingEnable = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isHidden = savedInstanceState.getBoolean(KEY_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {
            mProgressDialog = new LoaddingDialog();
            mView = initView(inflater, container);
        } else {
            if (mView.getParent() != null) {
                ViewParent parent = mView.getParent();
                if (parent instanceof ViewGroup) {
                    ViewGroup vp = (ViewGroup) parent;
                    vp.removeView(mView);
                }
            }
        }
        return mView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();
    }


    protected void initDatas() {
    }

    ;

    /**
     * 初始化view
     *
     * @param inflater
     * @param container
     * @return
     */
    @NonNull
    protected abstract View initView(LayoutInflater inflater, ViewGroup container);


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isFirst) {
            isFirst = false;
            lzyLoadData();
        }
    }

    /**
     * 懒加载数据
     */
    protected void lzyLoadData() {

    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(KEY_HIDDEN, isHidden());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showLoadding() {
        if (loaddingEnable && mProgressDialog != null && getChildFragmentManager().findFragmentByTag(mProgressDialog.getClass().getSimpleName()) == null) {
            mProgressDialog.show(getChildFragmentManager(), mProgressDialog.getClass().getSimpleName());
        }
    }

    @Override
    public void hiddenLoadding() {
        if (loaddingEnable && mProgressDialog != null && getChildFragmentManager().findFragmentByTag(mProgressDialog.getClass().getSimpleName()) != null) {
            mProgressDialog.dismiss();
        }
    }
}
