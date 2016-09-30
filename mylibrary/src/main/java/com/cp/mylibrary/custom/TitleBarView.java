package com.cp.mylibrary.custom;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cp.mylibrary.R;
import com.cp.mylibrary.utils.StringUtils;

import org.w3c.dom.Text;


/**
 * 标题
 * Created by cp on 2016/5/9.
 */


public class TitleBarView extends RelativeLayout {

    private TextView titleBarTitle, titlebar_tv_menu2,titlebar_text_title_two_1,titlebar_text_title_two_2,titlebar_tv_menu_text;
    private ImageView titlebar_img_back;
    private ImageView titlebar_img_menu;




    public TitleBarView(Context context) {
        super(context);

        initView(context);



    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(final Context context) {

        View view = View.inflate(context, R.layout.main_titlebar, this);
        initTitleView(view);
    }

    private void initTitleView(View view) {
        titleBarTitle = (TextView) view.findViewById(R.id.titlebar_text_title);

        titlebar_tv_menu2 = (TextView) view.findViewById(R.id.titlebar_tv_menu2);

        titlebar_text_title_two_1 = (TextView)view.findViewById(R.id.titlebar_text_title_two_1);
        titlebar_text_title_two_2 = (TextView)view.findViewById(R.id.titlebar_text_title_two_2);

        titlebar_tv_menu_text = (TextView)view.findViewById(R.id.titlebar_tv_menu_text);




        titlebar_img_menu = (ImageView) view.findViewById(R.id.titlebar_img_menu);
        titlebar_img_back = (ImageView) view.findViewById(R.id.titlebar_img_back);


    }



    public void setTitleBarImageMenuText(String str,OnClickListener onClickListener) {
        if (!StringUtils.isEmpty(str))

        {
            titlebar_tv_menu_text.setText(str);
            titlebar_tv_menu_text.setVisibility(View.VISIBLE);
            titlebar_tv_menu_text.setOnClickListener(onClickListener);
        }

    }

    public void setTitleTwo1Str(String str) {
        if (!StringUtils.isEmpty(str))

        {
            titlebar_text_title_two_1.setText(str);
            titlebar_text_title_two_1.setVisibility(View.VISIBLE);
        }

    }

    public void setTitleTwo2Str(String str) {
        if (!StringUtils.isEmpty(str))

        {
            titlebar_text_title_two_2.setText(str);
            titlebar_text_title_two_2.setVisibility(View.VISIBLE);
        }

    }


    /**
     * @param str
     */
    public void setTitleStr(String str) {
        if (!StringUtils.isEmpty(str))

        {
            titleBarTitle.setText(str);
            titleBarTitle.setVisibility(View.VISIBLE);
        }

    }

    /**
     * @param str
     */
    public void setMenuTextStr(String str) {
        if (!StringUtils.isEmpty(str)) {
            titlebar_tv_menu2.setVisibility(View.VISIBLE);
            titlebar_tv_menu2.setText(str);
        }

    }

    /**
     * @param onClickListener
     */
    public void setMenuTextOnClick(OnClickListener onClickListener) {
        titlebar_tv_menu2.setOnClickListener(onClickListener);
    }

    /**
     * @param resouId
     */
    public void setTitleBarMenuImg(int resouId) {
        titlebar_img_menu.setVisibility(View.VISIBLE);
        titlebar_img_menu.setImageResource(resouId);
    }

    /**
     *
     * @return
     */
     public ImageView getTitleBarMenuImg()
     {
          return  titlebar_img_menu;
     }


    /**
     * @param onClickListener
     */
    public void setTitlebarMenuImgOnClick(OnClickListener onClickListener) {

        titlebar_img_menu.setOnClickListener(onClickListener);
    }

    /**
     * 给返回设置监听
     * @param onClickListener
     */
    public void setTitleBackClick(OnClickListener onClickListener) {

        titlebar_img_back.setOnClickListener(onClickListener);
    }


    /**
     *  设置返回 直接关掉当前使...活动
     * @param activity
     */
    public void setTitleBackFinshActivity(final Activity activity) {

        titlebar_img_back.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }



    /**
     *
     */
    public void  setTitleBackIsShow(int show)
    {
        switch (show)
        {
            case  View.VISIBLE:
                titlebar_img_back.setVisibility(View.VISIBLE);
                break;


            case  View.GONE:
                titlebar_img_back.setVisibility(View.GONE);
                break;

        }

    }


}
