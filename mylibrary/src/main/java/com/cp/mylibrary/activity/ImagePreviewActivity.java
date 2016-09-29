package com.cp.mylibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cp.mylibrary.R;
import com.cp.mylibrary.base.MyBaseActivity;
import com.cp.mylibrary.imagepreview.BasePagerAdapter;
import com.cp.mylibrary.imagepreview.GalleryViewPager;
import com.cp.mylibrary.imagepreview.UrlPagerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 图片预览界面
 * 
 * @author kymjs
 * @param
 * @param
 */
public class ImagePreviewActivity extends MyBaseActivity {




	public static final String BUNDLE_KEY_IMAGES = "bundle_key_images";
	private static final String BUNDLE_KEY_INDEX = "bundle_key_index";
	private String[] mImageUrls;

	private TextView mTvImgIndex;
	private ImageView mIvMore;
	private int mCurrentPostion = 0;
  private GalleryViewPager mViewPager;

	// //

	@Override
	public void setRootView() {

		setContentView(R.layout.activity_image_preview);
	}



	@Override
	public void initWidget() {
		// TODO Auto-generated method stub
		super.initWidget();

		mImageUrls = getIntent().getStringArrayExtra(BUNDLE_KEY_IMAGES);
		int index = getIntent().getIntExtra(BUNDLE_KEY_INDEX, 0);

		mTvImgIndex = (TextView) findViewById(R.id.tv_img_index);
		mIvMore = (ImageView) findViewById(R.id.iv_more);
		mIvMore.setOnClickListener(this);

		List<String> items = new ArrayList<String>();
		Collections.addAll(items, mImageUrls);

  UrlPagerAdapter pagerAdapter = new UrlPagerAdapter(this, items);

		pagerAdapter.setOnItemChangeListener(new BasePagerAdapter.OnItemChangeListener() {

			@Override
			public void onItemChange(int currentPosition) {
				mCurrentPostion = currentPosition;
				if (mImageUrls != null && mImageUrls.length > 1) {
					if (mTvImgIndex != null) {
						mTvImgIndex.setText((mCurrentPostion + 1) + "/"
								+ mImageUrls.length);
					}
				}
			}
		});

		mViewPager = (GalleryViewPager) findViewById(R.id.viewer);
		mViewPager.setOffscreenPageLimit(3);
		mViewPager.setAdapter(pagerAdapter);
		mViewPager.setCurrentItem(index);
		mViewPager.setOnItemClickListener(new GalleryViewPager.OnItemClickListener() {

			@Override
			public void onItemClicked(View view, int position) {
				// TODO Auto-generated method stub finish();
 finish();
			}
		});
 
	}

	public static void showImagePrivew(Context context, int index,
									   String[] images) {
		Intent intent = new Intent(context, ImagePreviewActivity.class);

		intent.putExtra(BUNDLE_KEY_IMAGES, images);
		intent.putExtra(BUNDLE_KEY_INDEX, index);
		context.startActivity(intent);
	}

}
