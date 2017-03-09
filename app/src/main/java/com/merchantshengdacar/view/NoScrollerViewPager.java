package com.merchantshengdacar.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by kimi on 2017/3/6 0006.
 * Email: 24750@163.com
 */

public class NoScrollerViewPager extends ViewPager {

    public NoScrollerViewPager(Context context) {
        this(context,null);
    }

    public NoScrollerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }
}
