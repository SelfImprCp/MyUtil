package cn.myasapp.main.ui;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.cp.mylibrary.base.MyBaseActivity;
import com.cp.mylibrary.utils.NetWorkUtil;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.kymjs.kjframe.KJActivity;


/**
 * 应用Activity基类
 *
 * @author Administrator
 *         <p/>
 *         <p/>
 *         基类中各个方法的调用顺序：
 *         setRootView(); //用于调用setContent()；
 * @BindView //setRootView执行后将会执行注解绑定
 * initDataFromThread();（执行在异步，用于做耗时操作）
 * threadDataInited();（initDataFromThread() 执行完成后将会回调）
 * initData(); //用于初始化数据
 * initWidget(); //用于设置控件内容
 * registerBroadcast(); //用于注册广播与上下文菜单
 */
public class BaseActivity extends MyBaseActivity {


    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        super.onCreate(savedInstanceState);


//        initView();
//
//        initData();
    }





    @Override
    public void initWidget() {
        super.initWidget();

        initView();
    }

    @Override
    public void initData() {
        super.initData();

        initMyData();
    }

    /**
     * 子类复写，初始化UI
     */
     protected void initView() {


     }


    @Override
    public void setRootView() {

    }

    /**
     * 子类复写，初始化数据
     */
     public void initMyData() {
     }


    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }


}
