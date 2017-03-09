package com.merchantshengdacar.mvp;

import android.support.annotation.NonNull;

/**
 * Created by kimi on 2017/3/6 0006.
 * Email: 24750@163.com
 */

public interface BaseView<T> {

    void setPresenter(@NonNull T presenter);

    void showLoadding();

    void hiddenLoadding();
}
