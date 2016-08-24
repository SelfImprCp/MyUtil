package cn.myasapp.main.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cp.mylibrary.adapter.ViewPageFragmentAdapter;
import com.cp.mylibrary.base.BaseViewPagerFragment;
import com.cp.mylibrary.interf.OnTabReselectListener;
import cn.myasapp.main.R;
import cn.myasapp.main.fragment.TestViewPageFragment;
import cn.myasapp.main.fragment.TestViewPageFragment2;
import cn.myasapp.main.fragment.TestViewPageFragment3;


/**
 * Created by Jerry on 2016/6/21.
 * <p>
 * 开户
 */
public class TestFragment extends BaseViewPagerFragment implements
        OnTabReselectListener {




    @Override
    public void onTabReselect() {
        try {
            int currentIndex = mViewPager.getCurrentItem();
            Fragment currentFragment = getChildFragmentManager().getFragments()
                    .get(currentIndex);
            if (currentFragment != null
                    && currentFragment instanceof OnTabReselectListener) {
                OnTabReselectListener listener = (OnTabReselectListener) currentFragment;
                listener.onTabReselect();
            }
        } catch (NullPointerException e) {
        }
    }



    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {

        String[] title = getResources().getStringArray(R.array.kaihu_array);


        adapter.addTab(title[0], "info", TestViewPageFragment.class,
               null);

        adapter.addTab(title[1], "bank", TestViewPageFragment2.class,
                null);

        adapter.addTab(title[2], "finish", TestViewPageFragment3.class,
                null);


    }


    private Bundle getBundle(int newType) {
        Bundle bundle = new Bundle();
        //bundle.putInt(BaseListFragment.BUNDLE_KEY_CATALOG, newType);
        return bundle;
    }




}
