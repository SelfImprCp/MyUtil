package cn.myasapp.main.fragment;

import com.cp.mylibrary.adapter.ListBaseAdapter;
import com.cp.mylibrary.base.BaseListFragment;
import com.cp.mylibrary.interf.OnTabReselectListener;
import com.cp.mylibrary.utils.GsonUtil;

import java.util.List;

import cn.myasapp.main.adapter.MainFocusAdapter;
import cn.myasapp.main.api.TestApi;
import cn.myasapp.main.bean.MainFocus;
import cn.myasapp.main.res.MainFocusListRes;


/**
 * 开户个人信息界面
 * Created by Jerry on 2016/6/21.
 */
public class TestViewPageFragment extends BaseListFragment<MainFocus>
implements OnTabReselectListener {

    public MainFocusAdapter focusAdapter;
    @Override
    protected ListBaseAdapter<MainFocus> getListAdapter() {
        focusAdapter = new MainFocusAdapter(getActivity() );
        return focusAdapter;

    }


    @Override
    protected List<MainFocus> parseList(String is) throws Exception {
        MainFocusListRes res = GsonUtil.jsonStrToBean(is,
                MainFocusListRes.class);

        return res.getResult();
    }

    @Override
    protected void sendRequestData() {
        TestApi.getMainFocusList("dynamiclist",mCurrentPage + "",    "76", mHandler);
    }

    @Override
    public void onTabReselect() {

    }

    @Override
    protected String getCacheKeyPrefix() {
        return  " str ";
    }
}
