package com.merchantshengdacar.mvp.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by kimi on 2017/3/6 0006.
 * Email: 24750@163.com
 */

public class HomePagerAdapter extends FragmentPagerAdapter {

    FragmentFactory factory = new FragmentFactory();

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return factory.getItem(position);
    }

    @Override
    public int getCount() {
        return 5;
    }
}
