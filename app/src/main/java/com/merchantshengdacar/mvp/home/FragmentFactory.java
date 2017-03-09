package com.merchantshengdacar.mvp.home;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.merchantshengdacar.mvp.code.CodeFragment;
import com.merchantshengdacar.mvp.order.OrderFragment;
import com.merchantshengdacar.mvp.setting.SettingFragment;
import com.merchantshengdacar.mvp.statistics.StatisticsFragment;
import com.merchantshengdacar.mvp.zbar.ZbarFragment;

/**
 * Created by kimi on 2017/3/6 0006.
 * Email: 24750@163.com
 */

public class FragmentFactory {

    public SparseArray<Fragment>  fragments = new SparseArray<Fragment>();


    public Fragment getItem(int position){
        Fragment fragment = fragments.get(position);
        if(fragment != null)
            return fragment;

        if(position == 0){
            fragment = OrderFragment.newInstance(null);
        }else if(position == 1){
            fragment = StatisticsFragment.newInstance(null);
        }else if(position == 2){
            fragment = ZbarFragment.newInstance(null);
        }else if(position == 3){
            fragment = CodeFragment.newInstance(null);
        }else if(position == 4){
            fragment = SettingFragment.newInstance(null);
        }

        fragments.put(position,fragment);
        return fragment;
    }

}
