/*
 Copyright (c) 2012 Roman Truba

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial
 portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
 THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.cp.mylibrary.imagepreview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.cp.mylibrary.R;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class UrlTouchImageView extends RelativeLayout {
    protected ProgressBar mProgressBar;
    protected TouchImageView mImageView;

    protected Context mContext;

    public UrlTouchImageView(Context ctx)
    {
        super(ctx);
        mContext = ctx;
        init();

    }
    public UrlTouchImageView(Context ctx, AttributeSet attrs)
    {
        super(ctx, attrs);
        mContext = ctx;
        init();
    }
    public TouchImageView getImageView() { return mImageView; }

    @SuppressWarnings("deprecation")
    protected void init() {
        mImageView = new TouchImageView(mContext);
        LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        mImageView.setLayoutParams(params);
        this.addView(mImageView);
        mImageView.setVisibility(GONE);

        mProgressBar = new ProgressBar(mContext, null, android.R.attr.progressBarStyleHorizontal);
        params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.setMargins(30, 0, 30, 0);
        mProgressBar.setLayoutParams(params);
        mProgressBar.setIndeterminate(false);
        mProgressBar.setMax(100);
        this.addView(mProgressBar);
    }

    public void setUrl(String imageUrl)
    {
        new ImageLoadTask().execute(imageUrl);
    }
    
    public void setScaleType(ScaleType scaleType) {
        mImageView.setScaleType(scaleType);
    }
    
    
     
    
  //No caching load
    public class ImageLoadTask extends AsyncTask<String, Integer, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            Bitmap bm = null;
            try {
            	
            	
            	
            	
            	
            	
            	
                URL aURL = new URL(url);
                URLConnection conn = aURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                int totalLen = conn.getContentLength();
                InputStreamWrapper bis = new InputStreamWrapper(is, 8192, totalLen);
                bis.setProgressListener(new InputStreamWrapper.InputStreamProgressListener()
                {                    
                    @Override
                    public void onProgress(float progressValue, long bytesLoaded,
                            long bytesTotal)
                    {
                        publishProgress((int)(progressValue * 100));
                    }
                });
                BitmapFactory.Options options=new BitmapFactory.Options();
                options.inTempStorage = new byte[100*1024];
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inPurgeable = true;
                options.inSampleSize = 2;//压缩
                options.inInputShareable = true;
                bm = BitmapFactory.decodeStream(bis, null, options);
                bis.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bm;
        }
        
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) 
            {
                mImageView.setScaleType(ScaleType.CENTER);
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.no_photo);
                mImageView.setImageBitmap(bitmap);
            }
            else 
            {
                mImageView.setScaleType(ScaleType.MATRIX);
                mImageView.setImageBitmap(bitmap);
            }
            mImageView.setVisibility(VISIBLE);
            mProgressBar.setVisibility(GONE);
            
            
//            if(!bitmap.isRecycled()) {  
//        	bitmap.recycle();  
        	bitmap = null; //设置null 这个对象才会gc的时候回收  
     // }  
            
            
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            mProgressBar.setProgress(values[0]);
        }
    }
    
    
    //No caching load
//    public class ImageLoadTask extends AsyncTask<String, Integer, Bitmap>
//    {
//        @Override
//        protected Bitmap doInBackground(String... strings) {
//            String url = strings[0];
//            Bitmap bm = null;
//            try {
//                URL aURL = new URL(url);
//                URLConnection conn = aURL.openConnection();
//                conn.connect();
//                InputStream is = conn.getInputStream();
//                int totalLen = conn.getContentLength();
//                InputStreamWrapper bis = new InputStreamWrapper(is, 8192, totalLen);
//                bis.setProgressListener(new InputStreamProgressListener()
//				{					
//					@Override
//					public void onProgress(float progressValue, long bytesLoaded,
//							long bytesTotal)
//					{
//						publishProgress((int)(progressValue * 100));
//					}
//				});
//                
//                
//                
//                BitmapFactory.Options options = new BitmapFactory.Options();  
//                options.inJustDecodeBounds = true;  
//                
//    
//                
//                options.inSampleSize = 2;
//                		
//                		//calculateInSampleSize(options, 200, 200);  
//                options.inJustDecodeBounds = false;  
//                
//                
//                
//                
//                
//                
//                
//              //  bm = BitmapFactory.decodeStream(bis);
//                bm =  BitmapFactory.decodeStream(bis, null, options);
//                
//                bis.close();
//                is.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return bm;
//        }
//        
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//        	if (bitmap == null) 
//        	{
//        		mImageView.setScaleType(ScaleType.CENTER);
//        		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.no_photo);
//        		mImageView.setImageBitmap(bitmap);
//        	}
//        	else 
//        	{
//        		mImageView.setScaleType(ScaleType.MATRIX);
//	            mImageView.setImageBitmap(bitmap);
//        	}
//        //	bitmap.recycle();  
//        	mImageView.setVisibility(VISIBLE);
//            mProgressBar.setVisibility(GONE);
//        	
////             if(!bitmap.isRecycled()) {  
////             	bitmap.recycle();  
////            	bitmap = null; //设置null 这个对象才会gc的时候回收  
////           }  
//            
//            
//         
//        }
//
//		@Override
//		protected void onProgressUpdate(Integer... values)
//		{
//			mProgressBar.setProgress(values[0]);
//		}
//		
//		
//    }
//     
    
    
   //这个函数会对图片的大小进行判断，并得到合适的缩放比例，比如2即1/2,3即1/3   
  public  static int computeSampleSize(BitmapFactory.Options options, int target)
    {
        int w = options.outWidth;
        int h = options.outHeight;
        int candidateW = w / target;
        int candidateH = h / target;
        int candidate = Math.max(candidateW, candidateH);
        if (candidate == 0)
            return 1;
        if (candidate > 1)
        {
            if ((w > target) && (w / candidate) < target)
                candidate -= 1;
        }
        if (candidate > 1)
        {
            if ((h > target) && (h / candidate) < target)
                candidate -= 1;
        }
        //if (VERBOSE)
              return candidate;
    }
    
    /** 
     * 计算图片采样率 
     * @param options 
     * @param reqWidth 
     * @param reqHeight 
     * @return 
     */  
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image  
        final int height = options.outHeight;  
        final int width = options.outWidth;  
        int inSampleSize = 1;  
  
        if (height > reqHeight || width > reqWidth) {  
  
            final int halfHeight = height / 2;  
            final int halfWidth = width / 2;  
  
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {  
                inSampleSize *= 2;  
            }  
  
            long totalPixels = width / inSampleSize * height / inSampleSize ;  
  
            final long totalReqPixelsCap = reqWidth * reqHeight * 2;  
  
            while (totalPixels > totalReqPixelsCap) {  
                inSampleSize *= 2;  
                totalPixels /= 2;  
            }  
        }  
        return inSampleSize;  
    }
}
