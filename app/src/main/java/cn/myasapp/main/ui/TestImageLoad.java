package cn.myasapp.main.ui;

import android.widget.ImageView;

import com.cp.mylibrary.custom.TitleBarView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;

/**
 * Created by Jerry on 2016/7/6.
 */
public class TestImageLoad  extends  BaseActivity{


    @BindView( id = R.id.image_load_test_title)

    private TitleBarView image_load_test_title;

    @BindView( id = R.id.image_load_test_image)

    private ImageView image_load_test_image;


    @Override
    public void setRootView() {
        super.setRootView();

     setContentView(R.layout.activity_image_load);
    }

    @Override
    protected void initView() {
        super.initView();


        image_load_test_title.setTitleStr("测试 imageload");
        image_load_test_title.setTitleBackFinshActivity(this);

         String imageUrl = "http://b.hiphotos.baidu.com/baike/pic/item/ae51f3deb48f8c54cd34cafb3a292df5e1fe7f7a.jpg";
        ImageLoader.getInstance().displayImage(imageUrl,image_load_test_image);

    }
}
