package cn.myasapp.main.ui;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cp.mylibrary.twocode.CreateTwoCodeUtil;
import com.cp.mylibrary.utils.ImageUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.R;

/**
 * Created by Jerry on 2016/7/6.
 */
public class TestCreateTwoCode extends BaseActivity {


    @BindView(id = R.id.create_two_codes_test_title)
    private TitleBarView create_two_codes_test_title;

    @BindView(id = R.id.create_two_codes_button, click = true)
    private Button create_two_codes_button;

    @BindView(id = R.id.create_two_codes_img)
    private ImageView create_two_codes_img;


    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_carete_two_code);
    }


    @Override
    protected void initView() {
        super.initView();

        create_two_codes_test_title.setTitleBackFinshActivity(this);
        create_two_codes_test_title.setTitleStr("生成二维码");


    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.create_two_codes_button:


                CreateTwoCodeUtil createTwoCodeUtil = new CreateTwoCodeUtil();

                String contents = " 生成个二维码，来试试，";
                Bitmap logo = ImageUtils.getBitmapById(this, R.drawable.ic_launcher);
                try {
                    Bitmap bm = createTwoCodeUtil.createCode(contents, logo, BarcodeFormat.QR_CODE);
                    create_two_codes_img.setImageBitmap(bm);

                } catch (WriterException e) {
                    e.printStackTrace();
                }

                break;
        }
    }
}
