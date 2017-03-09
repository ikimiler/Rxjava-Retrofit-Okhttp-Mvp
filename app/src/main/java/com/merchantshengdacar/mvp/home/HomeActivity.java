package com.merchantshengdacar.mvp.home;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.merchantshengdacar.R;
import com.merchantshengdacar.mvp.BaseActivity;
import com.merchantshengdacar.mvp.BasePresenter;
import com.merchantshengdacar.view.BottomNavigationViewEx;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivity extends BaseActivity<BasePresenter> {

    public static final String KEY_POSITION = "position";

    int[] ids = {R.id.home_order, R.id.home_statistics, R.id.home_zbar, R.id.home_code, R.id.home_setting};

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.viewpager)
    ViewPager mViewpager;
    @InjectView(R.id.bottomNavigation)
    BottomNavigationViewEx mBottomNavigation;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);

        mBottomNavigation.enableAnimation(false);
        mBottomNavigation.enableShiftingMode(false);
        mBottomNavigation.enableItemShiftingMode(false);
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                for (int i = 0; i < ids.length; i++) {
                    if (item.getItemId() == ids[i]) {
                        mViewpager.setCurrentItem(i, false);
                        break;
                    }
                }
                return true;
            }
        });

        mViewpager.setOffscreenPageLimit(5);
        mViewpager.setAdapter(new HomePagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public void setPresenter(@NonNull BasePresenter presenter) {

    }
}
