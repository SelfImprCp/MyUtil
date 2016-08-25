package com.cp.mylibrary.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.cp.mylibrary.R;
import com.cp.mylibrary.app.Config;
import com.cp.mylibrary.interf.ShareListener;
import com.cp.mylibrary.utils.ImageLoaderUtils;
import com.cp.mylibrary.utils.LogCp;
import com.cp.mylibrary.utils.StringUtils;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.ShareType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.utils.OauthHelper;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;


/**
 * 分享界面dialog
 *
 * @author kymjs
 */
public class ShareDialog extends CommonDialog implements
        View.OnClickListener {
    private Context context;
    private String title;
    private String content;
    private String link;
    // 分享中显示 的图片
    private String share_img_url;

    final UMSocialService mController = UMServiceFactory
            .getUMSocialService("com.umeng.share");
    private ShareListener shareListenr;


    private ShareDialog(Context context, boolean flag, DialogInterface.OnCancelListener listener) {
        super(context, flag, listener);
        this.context = context;
    }

    public ShareDialog(Context context, ShareListener listener) {
        this(context);

        this.context = context;
        this.shareListenr = listener;


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
    public void setShareInfo(String title, String content, String link, String share_img_url) {
        this.title = title;
        this.content = content;
        this.link = link;
        this.share_img_url = share_img_url;
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
        // 支持微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(this.context,
                Config.WEICHAT_APPID,Config.WEICHAT_SECRET);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
        // 设置微信朋友圈分享内容
        CircleShareContent circleMedia = new CircleShareContent();
        circleMedia.setShareContent(this.content);
        // 设置朋友圈title
        circleMedia.setTitle(this.title);
        circleMedia.setShareImage(getShareImg());
        circleMedia.setTargetUrl(this.link);
        mController.setShareMedia(circleMedia);
        mController.postShare(this.context, SHARE_MEDIA.WEIXIN_CIRCLE, null);
    }

    @SuppressWarnings("deprecation")
    private void shareToWeiChat() {
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(this.context,
                Config.WEICHAT_APPID,Config.WEICHAT_SECRET);
        wxHandler.addToSocialSDK();
        // 设置微信好友分享内容
        WeiXinShareContent weixinContent = new WeiXinShareContent();
        // 设置分享文字
        weixinContent.setShareContent(this.content);
        // 设置title
        weixinContent.setTitle(this.title);
        // 设置分享内容跳转URL
        weixinContent.setTargetUrl(this.link);
        // 设置分享图片
        weixinContent.setShareImage(getShareImg());
        mController.setShareMedia(weixinContent);
        mController.postShare(this.context, SHARE_MEDIA.WEIXIN, null);
    }

    private void shareToSinaWeibo() {
        // 设置新浪微博SSO handler
        SinaSsoHandler sinaSsoHandler = new SinaSsoHandler();
        sinaSsoHandler.setTargetUrl(this.link);
        mController.setShareType(ShareType.SHAKE);
        mController.setShareContent(this.content + " " + this.link);
        mController.setShareImage(getShareImg());
        mController.getConfig().setSsoHandler(sinaSsoHandler);

        if (OauthHelper.isAuthenticated(this.context, SHARE_MEDIA.SINA)) {
            mController.directShare(this.context, SHARE_MEDIA.SINA, null);
        } else {
            mController.doOauthVerify(this.context, SHARE_MEDIA.SINA,
                    new SocializeListeners.UMAuthListener() {

                        @Override
                        public void onStart(SHARE_MEDIA arg0) {
                        }

                        @Override
                        public void onError(SocializeException arg0,
                                            SHARE_MEDIA arg1) {
                        }

                        @Override
                        public void onComplete(Bundle arg0, SHARE_MEDIA arg1) {
                            mController.directShare(ShareDialog.this.context, SHARE_MEDIA.SINA,
                                    null);
                        }

                        @Override
                        public void onCancel(SHARE_MEDIA arg0) {
                        }
                    });
        }
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


    private void getSinaImg()
    {


    }

    private UMImage getShareImg( ) {
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

    public UMSocialService getController() {
        return mController;
    }
}
