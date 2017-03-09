package com.merchantshengdacar.mvp.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.widget.CompoundButton;

import com.merchantshengdacar.R;
import com.merchantshengdacar.common.Constant;
import com.merchantshengdacar.mvp.BaseActivity;
import com.merchantshengdacar.mvp.home.HomeActivity;
import com.merchantshengdacar.utils.EditUtils;
import com.merchantshengdacar.utils.SharePrefrencesUtil;
import com.merchantshengdacar.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


/**
 * Created by kimi on 2017/3/6 0006.
 * Email: 24750@163.com
 */

public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {

    @InjectView(R.id.username)
    AppCompatEditText mUsername;
    @InjectView(R.id.password)
    AppCompatEditText mPassword;
    @InjectView(R.id.checkbox_remember)
    AppCompatCheckBox mCheckboxRemember;
    @InjectView(R.id.checkbox_autoLogin)
    AppCompatCheckBox mCheckboxAutoLogin;
    @InjectView(R.id.btn_login)
    AppCompatButton mBtnLogin;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        new LoginPresenter(this);

        mCheckboxRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharePrefrencesUtil.getInstance().putBoolean(Constant.KEY_LOGIN_REMEBER, isChecked);
            }
        });
        mCheckboxAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharePrefrencesUtil.getInstance().putBoolean(Constant.KEY_LOGIN_AUTO, isChecked);
            }
        });

    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFaild() {
        ToastUtils.showMsg(getString(R.string.login_faild));
    }


    @OnClick(R.id.btn_login)
    public void onClick() {

        if (EditUtils.isEmpty(mUsername, mPassword)) {
            ToastUtils.showMsg(getString(R.string.login_str_emp));
        } else {
            String username = EditUtils.getEditTextContent(mUsername);
            String password = EditUtils.getEditTextContent(mPassword);
            mPresenter.login(username, password);
        }
    }
}
