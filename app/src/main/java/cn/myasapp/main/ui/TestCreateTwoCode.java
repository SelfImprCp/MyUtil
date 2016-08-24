package cn.myasapp.main.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cp.mylibrary.activity.ImagePreviewActivity;
import com.cp.mylibrary.custom.BadgeView;
import com.cp.mylibrary.custom.CircleImageView;
import com.cp.mylibrary.custom.TimerTextView;
import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.twocode.CreateTwoCodeUtil;
import com.cp.mylibrary.utils.CameraAndSelectPicUtil;
import com.cp.mylibrary.utils.ImageUtils;
import com.cp.mylibrary.utils.ShowToastUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.kymjs.kjframe.ui.BindView;

import java.io.File;
import java.io.IOException;

import cn.myasapp.main.R;

/**
 * Created by Jerry on 2016/7/6.
 */
public class TestCreateTwoCode extends BaseActivity {


    @BindView(id = R.id.create_two_codes_test_title)
    private TitleBarView create_two_codes_test_title;

    @BindView(id = R.id.create_two_codes_button, click = true)
    private Button create_two_codes_button;


    @BindView(id = R.id.select_photo_button, click = true)
    private Button select_photo_button;


    @BindView(id = R.id.create_two_codes_img,click = true)
    private ImageView create_two_codes_img;

    @BindView(id = R.id.select_photo_img)
    private ImageView select_photo_img;



    @BindView(id = R.id.circle_imageview)
    private CircleImageView circle_imageview;

    @BindView(id = R.id.timer_text_view)
    private TimerTextView timer_text_view;

    // 遮罩
    @BindView(id = R.id.myview_user)
    private View myview_user;


    private CameraAndSelectPicUtil cameraAndSelectPicUtil;

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


        ImageLoader.getInstance().displayImage("http://avatar.csdn.net/3/D/6/1_u013193363.jpg",circle_imageview);



        timer_text_view.setTimes(50);
        if (!timer_text_view.isRun()) {
            timer_text_view.start();
        }

        // new 一个出来，
        cameraAndSelectPicUtil = new CameraAndSelectPicUtil(TestCreateTwoCode.this,
                TestCreateTwoCode.this, myview_user);




        // BadgeView 的用法
        BadgeView bv = new BadgeView(this, create_two_codes_img);
        bv.setText("12");
        bv.setTextColor(getResources().getColor(R.color.swiperefresh_color1));
        bv.setTextSize(12);
        bv.setPadding(8,8,8,8);

        bv.setBadgePosition(BadgeView.POSITION_TOP_RIGHT); //默认值
        bv.show();


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

            case R.id.select_photo_button:

                cameraAndSelectPicUtil.getPopupWindow(select_photo_img);


                break;

            case R.id.create_two_codes_img:

 String str [] = {"http://img5.imgtn.bdimg.com/it/u=614953024,3186504313&fm=21&gp=0.jpg"
 ,"http://pic4.nipic.com/20090727/1843533_144804089_2.jpg"
 ,"http://pic17.nipic.com/20111118/8868045_214622414000_2.jpg"
 ,"http://pic31.nipic.com/20130624/8821914_104949466000_2.jpg"
 ,"http://img5.imgtn.bdimg.com/it/u=3139005874,2415212363&fm=21&gp=0.jpg"};

                 // 浏览大图
            ImagePreviewActivity.showImagePrivew(this, 0, str);

 break;

        }
    }


    /**
     *  选择图片 ，返回 后的处理
     */

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnIntent) {

        if (resultCode != Activity.RESULT_OK)
            return;

        switch (requestCode) {
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA:
                cameraAndSelectPicUtil.startActionCrop(null);// 拍照后裁剪
                break;
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP:
                cameraAndSelectPicUtil.startActionCrop(imageReturnIntent.getData());// 选图后裁剪
                break;

            case ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD:


                upImage();

                break;
        }

    }

    /**
     * 拿到图片
     */
    private void upImage() {

        // 获取头像缩略图

       // if (cameraAndSelectPicUtil.getUpBitmapSetSize(200, 200) != null) {


            File fileImg =  cameraAndSelectPicUtil.getUpFile();
            Bitmap bitmap =  ImageUtils.getBitmapByFile(fileImg);


             if(null!=bitmap)
             {
                 select_photo_img.setImageBitmap(bitmap);
             }else
             {
                 ShowToastUtil.showToast(this,"图像不存在，上传失败");


             }

      //  } else {
        //}

    }





}
