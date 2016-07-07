package com.cp.mylibrary.banner;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.cp.mylibrary.utils.ImageUtils;
import com.cp.mylibrary.utils.LogCp;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * ImageView创建工厂
 */
public class ViewFactory {

	/**
	 * 获取ImageView视图的同时加载显示url
	 * 
	 * @param
	 * 
	 * @return
	 */

	public static ImageView getImageView(final Context context, String url, int layoutID, final int round) {
		final ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(
				layoutID, null);

		ImageLoader.getInstance().displayImage(url, imageView,
				new ImageLoadingListener() {

					@Override
					public void onLoadingStarted(String imageUri, View view) {
						// TODO Auto-generated method stub
						//imageView.setImageResource(R.drawable.banner_default);
					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
												FailReason failReason) {
						//	imageView.setImageResource(R.drawable.banner_default);


					}

					@Override
					public void onLoadingComplete(String imageUri, View view,
												  Bitmap loadedImage) {
					 if(round>0)
					 {
						imageView.setImageBitmap(   ImageUtils.getRoundedCornerBitmap(loadedImage, round));
					 }
					}

					@Override
					public void onLoadingCancelled(String imageUri, View view) {
						//	imageView.setImageResource(R.drawable.banner_default);

					}
				});
		LogCp.i(LogCp.CP, ViewFactory.class + "  显示 图片 的url 》" + url);
		return imageView;
	}
}
