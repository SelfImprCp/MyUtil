package cn.myasapp.main.ui;

import android.view.View;
import android.widget.TextView;

import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.utils.LogCp;
import com.cp.mylibrary.utils.RandomUtils;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;

/**
 * Created by Jerry on 2016/7/5.
 *
 *  Random Utils tests
 *
 */
public class TextRandomUtils extends  BaseActivity {


    @BindView(id = R.id.random_utils_test_title)
    private TitleBarView random_utils_test_title;




    @BindView(id = R.id.random_utils_test_getRandomNumbersAndLetters,click = true)
    private TextView random_utils_test_getRandomNumbersAndLetters;



    @Override
    public void setRootView() {
        super.setRootView();

     setContentView(R.layout.activity_random_utils);
    }


    @Override
    protected void initView() {
        super.initView();

        random_utils_test_title.setTitleBackFinshActivity(this);
        random_utils_test_title.setTitleStr("Random 测试");



    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId())
        {
            case R.id.random_utils_test_getRandomNumbersAndLetters:

               //
                //  String str = RandomUtils.getRandomNumbersAndLetters(4);

                //String str = RandomUtils.getRandomNumbers(4);

            //     String str = RandomUtils.  getRandomLetters(8);

               // String str = RandomUtils.  getRandom("abcdkefg",2);
                int str = RandomUtils.  getRandom(-14,2);

                LogCp.i(LogCp.CP,TextRandomUtils.class + "取得 的随机数：" +str);

                break;

        }
    }
}
