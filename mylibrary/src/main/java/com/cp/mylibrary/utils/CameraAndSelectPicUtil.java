package com.cp.mylibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.cp.mylibrary.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;




/**
 * 拍照或选择图片 上传的工具类
 *
 *  使用方法详见 TestCreateTwoCode
 * 
 * @author Administrator
 * 
 */
public class CameraAndSelectPicUtil {

	
	// 遮罩
	private View myview_user;
	 /**
	  * 在每个布局文件中添加 效果为pop弹出后，背景变暗
	  *     <View
    android:id="@+id/myview_user"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:visibility="gone"
    android:background="#AF000000"/>
	  */

	
	private Context mContext;
	
	private Activity mActivity;

	private final static String FILE_SAVEPATH = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ SDCardUtils.SDPATH;
	private Uri origUri;
	private Uri cropUri;
	private File protraitFile;
	// private Bitmap protraitBitmap;
	private String protraitPath;
	private String theLarge;

	private final static int CROP = 200;

	 /**
	  *  
	  * @param context
	  * @param activity
	  * @param view 设置遮罩
	  */
	public CameraAndSelectPicUtil(Context context, Activity activity, View view) {
		this.mContext = context;
		this.myview_user = view;
		this.mActivity = activity;
	}

	
	/**
	 *  传入的View 为点击触发些pop的view
	 * @param parent
	 * @return
	 */
	public PopupWindow getPopupWindow(View parent) {
		PopupWindows popWin = new PopupWindows(mContext, parent);
		return popWin;
	}
   
	 /**
	  * 取得 上传的图片的文件
	  * 
	  */
	 public File getUpFile()
	 {
		 protraitFile = new File(protraitPath);
		 return protraitFile;
	 }

	/**
	 *  取得 上传图片的路径
	 */
	 public String getUpFilePath()
	 {
		 return  protraitPath;
	 }
	
	 
	  /**
	   *取得  指定上传的图片的尺寸,如果要原图，宽高为0，
	   * @param w
	   * @param h
	   * @return
	   */
	public Bitmap getUpBitmapSetSize(int w, int h) {

		if (!StringUtils.isEmpty(protraitPath) && protraitFile.exists()) {
			Bitmap protraitBitmap = ImageUtils.loadImgThumbnail(protraitPath,
					w, w);

			return protraitBitmap;
		} else
			return null;

	}

	public class PopupWindows extends PopupWindow {
		public void dismiss() {
			// 在pop消失之前，给咱们加的view设置背景渐变出场动画（Android3.0以上的开发环境，这里建议使用属性动画，那样很柔和，视觉效果更棒！）
			myview_user.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.anim_bookshelf_folder_editer_exit));
			myview_user.setVisibility(View.GONE);
			super.dismiss();
		}

		public PopupWindows(Context mContext, View parent) {
			View view = View
					.inflate(mContext, R.layout.item_pop_creame_picture, null);
			view.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.fade_ins));
			LinearLayout ll_popup = (LinearLayout) view
					.findViewById(R.id.ll_popup);
			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.push_bottom_in_2));

			Button bt1 = (Button) view
					.findViewById(R.id.item_popupwindows_camera);
			Button bt2 = (Button) view
					.findViewById(R.id.item_popupwindows_Photo);
			Button bt3 = (Button) view
					.findViewById(R.id.item_popupwindows_cancel);
			bt1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// 拍照，
					// photo();
					startTakePhoto();
					dismiss();
				}
			});
			bt2.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// 相框选择
					// ImageGridAdapter.IMAG_ALL = 1;

					// UIHelper.showSelectPic(UserActivity.this);
					startImagePick();
					dismiss();
				}
			});
			bt3.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					dismiss();
				}
			});

			// 设置Pop入场动画效果
			setAnimationStyle(R.style.dialogstyle);

			// 在显示pop之前，给咱们加的view设置背景渐变入场动画（Android3.0以上的开发环境，这里建议使用属性动画，那样很柔和，视觉效果更棒！）
			myview_user.setVisibility(View.VISIBLE);
			myview_user.startAnimation(AnimationUtils.loadAnimation(mActivity,
					R.anim.anim_bookshelf_folder_editer_enter));

			// 在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
			setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
			setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
			// 点击空白处时，隐藏掉pop窗口
			setFocusable(true);

			backgroundAlpha(1f);

			// 添加pop窗口关闭事件
			setOnDismissListener(new poponDismissListener());

			setWidth(LayoutParams.FILL_PARENT);
			setHeight(LayoutParams.FILL_PARENT);
			setBackgroundDrawable(new BitmapDrawable());
			setOutsideTouchable(true);
			setContentView(view);
			showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			update();

		}
	}

	/**
	 * 设置添加屏幕的背景透明度
	 * 
	 * @param bgAlpha
	 */
	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		mActivity.getWindow().setAttributes(lp);
	}

	/*
	 * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
	 * 
	 * @author cg
	 */
	class poponDismissListener implements PopupWindow.OnDismissListener {

		@Override
		public void onDismiss() {
			// TODO Auto-generated method stub
			// Log.v("List_noteTypeActivity:", "我是关闭事件");
			backgroundAlpha(1f);
		}

	}

	/**
	 * 选择拍照
	 */
	private void startTakePhoto() {
		Intent intent;
		// 判断是否挂载了SD卡
		String savePath = "";
		String storageState = Environment.getExternalStorageState();
		if (storageState.equals(Environment.MEDIA_MOUNTED)) {
			savePath = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + SDCardUtils.SDPATH;
			File savedir = new File(savePath);
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
		}

		// 没有挂载SD卡，无法保存文件
		if (StringUtils.isEmpty(savePath)) {
			ShowToastUtil.showToast(mContext,"无法保存照片，请检查SD卡是否挂载");
			return;
		}

		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());
		String fileName = "mofoxuser_" + timeStamp + ".jpg";// 照片命名
		File out = new File(savePath, fileName);
		Uri uri = Uri.fromFile(out);
		origUri = uri;

		theLarge = savePath + fileName;// 该照片的绝对路径

		intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		mActivity.startActivityForResult(intent,
				ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA);
	}

	/**
	 * 选择图片裁剪
	 * 
	 * @param
	 */
	private void startImagePick() {
		Intent intent;
		if (Build.VERSION.SDK_INT < 19) {
			intent = new Intent();
			intent.setAction(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*");
			mActivity.startActivityForResult(
					Intent.createChooser(intent, "选择图片"),
					ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP);
		} else {
			intent = new Intent(Intent.ACTION_PICK,
					Images.Media.EXTERNAL_CONTENT_URI);
			intent.setType("image/*");
			mActivity.startActivityForResult(
					Intent.createChooser(intent, "选择图片"),
					ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP);
		}
	}

	/**
	 * 拍照后裁剪
	 * 
	 * @param data
	 *            原始图片
	 * @param
	 *
	 */
	public void startActionCrop(Uri data) {
		if (data == null)
			data = origUri;

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(data, "image/*");
		intent.putExtra("output", getUploadTempFile(data));
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);// 裁剪框比例
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", CROP);// 输出图片大小
		intent.putExtra("outputY", CROP);
		intent.putExtra("scale", true);// 去黑边
		intent.putExtra("scaleUpIfNeeded", true);// 去黑边
		mActivity.startActivityForResult(intent,
				ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD);
	}

	// 裁剪头像的绝对路径
	private Uri getUploadTempFile(Uri uri) {
		String storageState = Environment.getExternalStorageState();
		if (storageState.equals(Environment.MEDIA_MOUNTED)) {
			File savedir = new File(FILE_SAVEPATH);
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
		} else {
			ShowToastUtil.showToast(mContext,"无法保存上传的头像，请检查SD卡是否挂载");
			return null;
		}
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());
		String thePath = ImageUtils.getAbsolutePathFromNoStandardUri(uri);

		// 如果是标准Uri
		if (StringUtils.isEmpty(thePath)) {
			thePath = ImageUtils.getAbsoluteImagePath(mActivity, uri);
		}
		String ext = FileUtil.getFileFormat(thePath);
		ext = StringUtils.isEmpty(ext) ? "jpg" : ext;
		// 照片命名
		String cropFileName = "mofox_crop_" + timeStamp + "." + ext;
		// 裁剪头像的绝对路径
		protraitPath = FILE_SAVEPATH + cropFileName;
		protraitFile = new File(protraitPath);

		cropUri = Uri.fromFile(protraitFile);
		return this.cropUri;
	}

}
