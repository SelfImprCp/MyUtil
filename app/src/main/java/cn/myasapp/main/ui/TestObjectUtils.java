package cn.myasapp.main.ui;

import android.view.View;
import android.widget.TextView;

import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.utils.NetWorkUtil;
import com.cp.mylibrary.utils.ObjectUtil;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;
import cn.myasapp.main.bean.UserBean;

/**
 * Created by Jerry on 2016/7/5.
 * <p>
 * ObjectUtil 测试 类
 */
public class TestObjectUtils extends BaseActivity {


    @BindView(id = R.id.object_utils_test_title)
    private TitleBarView object_utils_test_title;


    @BindView(id = R.id.object_utils_test_isEquals, click = true)
    private TextView object_utils_test_isEquals;


    @BindView(id = R.id.object_utils_test_saveObject, click = true)
    private TextView object_utils_test_saveObject;


    @BindView(id = R.id.object_utils_test_readObject, click = true)
    private TextView object_utils_test_readObject;


    @BindView(id = R.id.object_utils_test_isExistDataCache, click = true)
    private TextView object_utils_test_isExistDataCache;


    private String saveFile = "object";


    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_object_utils_test);
    }


    @Override
    protected void initView() {
        super.initView();
        object_utils_test_title.setTitleBackFinshActivity(this);
        object_utils_test_title.setTitleStr("  ObjectUtil  测试");


    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {

            case R.id.object_utils_test_isEquals:
                UserBean userBean2 = new UserBean();
                userBean2.setName("小张");
                userBean2.setAge(10);


                UserBean userBean = new UserBean();
                userBean.setName("小张");
                userBean.setAge(10);

                ShowToastUtil.showToast(this, "测试 两个对像是不是相等 " + ObjectUtil.isEquals(userBean, userBean2))
                ;


                break;


            case R.id.object_utils_test_saveObject:

                UserBean userBean3 = new UserBean();
                userBean3.setName("小张");
                userBean3.setAge(10);
                ShowToastUtil.showToast(this, "对像是否保存成功" + ObjectUtil.saveObject(this, userBean3, saveFile));

                break;


            case R.id.object_utils_test_readObject:
                UserBean userBean4 = (UserBean) ObjectUtil.readObject(this, saveFile);
                ShowToastUtil.showToast(this, " 读出为的对像   ：" + userBean4.getName());


                break;


            case R.id.object_utils_test_isExistDataCache:

                ShowToastUtil.showToast(this, " 保存的文件是否存在" + ObjectUtil.isExistDataCache(this, saveFile));
                break;


        }
    }
}
