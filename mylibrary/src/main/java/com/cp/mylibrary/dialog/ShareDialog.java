package com.cp.mylibrary.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.cp.mylibrary.R;
import com.cp.mylibrary.app.Config;
import com.cp.mylibrary.interf.ShareListener;
import com.cp.mylibrary.utils.ImageLoaderUtils;
import com.cp.mylibrary.utils.LogCp;
import com.cp.mylibrary.utils.StringUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.UmengTool;
import com.umeng.socialize.bean.SHARE_MEDIA;


import com.umeng.socialize.media.UMImage;

import java.util.Map;


/**
 * 分享界面dialog
 *
 * @author kymjs
 */
public class ShareDialog extends CommonDialog implements
        View.OnClickListener {
    private Context context;

    private Activity mActivity;
    private String title;
    private String content;
    private String link;
    // 分享中显示 的图片
    private String share_img_url;
//    private UMImage umImage;


//    private ShareListener shareListenr;


    private ShareDialog(Context context, boolean flag, DialogInterface.OnCancelListener listener) {
        super(context, flag, listener);
        this.context = context;
    }

    public ShareDialog(Context context, Activity activity) {
        this(context);

        this.context = context;
        this.mActivity = activity;


    }


    @SuppressLint("InflateParams")
    private ShareDialog(Context context, int defStyle) {
        super(context, defStyle);
        this.context = context;
        View shareView = getLayoutInflater().inflate(
                R.layout.dialog_cotent_share, null);
//        shareView.findViewById(R.id.ly_share_qq).setOnClickListener(this);
//        shareView.findViewById(R.id.ly_share_copy_link)
//                .setOnClickListener(this);
//        shareView.findViewById(R.id.ly_share_more_option).setOnClickListener(
//                this);
        shareView.findViewById(R.id.ly_share_sina_weibo).setOnClickListener(
                this);
        shareView.findViewById(R.id.ly_share_weichat).setOnClickListener(this);
        shareView.findViewById(R.id.ly_share_weichat_circle)
                .setOnClickListener(this);
        setContent(shareView, 0);
    }

    public ShareDialog(Context context) {
        this(context, R.style.dialog_bottom);
    }


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setGravity(Gravity.BOTTOM);

        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        getWindow().setAttributes(p);
    }

    // 设置需要分享的内容
    public void setShareInfo(String title, String content, String link, String share_img_url  ) {
        this.title = title;
        this.content = content;
        this.link = link;
        this.share_img_url = share_img_url;

//        if (!StringUtils.isEmpty(share_img_url)) {
//
//            this.umImage = new UMImage(mActivity, share_img_url);
//        } else if (image!=null){
//            this.umImage = image;
//        }


    }

    @Override
    public void onClick(View v) {
//        if (!checkCanShare()) {
//            AppContext.showToast("内容加载中，请稍候...");
//            return;
//        }


        if (v.getId() == R.id.ly_share_weichat_circle) {
            //   shareListenr.onWeiChatCircle();
            shareToWeiChatCircle();
        }

        if (v.getId() == R.id.ly_share_weichat) {
            // shareListenr.onWeiChat();
            shareToWeiChat();
        }

        if (v.getId() == R.id.ly_share_sina_weibo) {

            //  shareListenr.onSinaWeiBo();
            shareToSinaWeibo();
        }

        if (v.getId() == R.id.ly_share_qq) {


        }


        this.dismiss();
    }


    @SuppressWarnings("deprecation")
    private void shareToWeiChatCircle() {

        LogCp.i(LogCp.CP, ShareDialog.class + " 来分享到weChat 朋友圈" + title + content + link + share_img_url);

//
         UMImage image = new UMImage(mActivity, share_img_url);

        new ShareAction(mActivity).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                .withText(content)
                .withTitle(title)
                .withTargetUrl(link)
                .withMedia(image)
                .setCallback(umShareListener)
                .share();


    }

    @SuppressWarnings("deprecation")
    private void shareToWeiChat() {

        LogCp.i(LogCp.CP, ShareDialog.class + " 来分享到weChat  " + title + content + link + share_img_url);

       UMImage image = new UMImage(mActivity, share_img_url);

        new ShareAction(mActivity).setPlatform(SHARE_MEDIA.WEIXIN)
                .withText(content)
                .withTitle(title)
                .withTargetUrl(link)
                .withMedia(image)
                .setCallback(umShareListener)
                .share();


    }

    private void shareToSinaWeibo() {


      UMImage image = new UMImage(mActivity, share_img_url);

        new ShareAction(mActivity).setPlatform(SHARE_MEDIA.SINA)
                .withText(content)
                .withTitle(title)
                .withTargetUrl(link)
                .withMedia(image)
                .setCallback(umShareListener)
                .share();


    }

//    private void shareToQQ() {
//        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler((Activity) this.context,
//                Constants.QQ_APPID, Constants.QQ_APPKEY);
//        qqSsoHandler.setTargetUrl(this.link);
//        qqSsoHandler.setTitle(this.title);
//        qqSsoHandler.addToSocialSDK();
//        mController.setShareContent(this.content);
//        mController.setShareImage(getShareImg());
//        mController.postShare(this.context, SHARE_MEDIA.QQ, null);
//    }


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);

            Toast.makeText(context, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(context, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(context, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };


    private void getSinaImg() {


    }

    private UMImage getShareImg() {
        LogCp.i(LogCp.CP, ShareDialog.class + " 分享的图片：  " + share_img_url);

        UMImage img;
        if (!StringUtils.isEmpty(share_img_url)) {
            img = new UMImage(context, share_img_url);
        } else {

            img = new UMImage(context, R.drawable.ic_launcher);
        }


        return img;
    }

    private boolean checkCanShare() {
        boolean canShare = true;
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(content) || StringUtils.isEmpty(link)) {
            canShare = false;
        }
        return canShare;
    }


}
