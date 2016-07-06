package com.cp.mylibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.io.InputStream;

/**
 * Created by Jerry on 2016/7/6.
 */
public class ImageUtils {


    /**
     *  pass
     *
     *  通过资源id 取得图片， bitmap
     * @param context
     * @param resourceID
     * @return
     */
    public static Bitmap getBitmapById(Context context, int resourceID) {
        InputStream is = context.getResources().openRawResource(resourceID);
        Bitmap mBitmap = BitmapFactory.decodeStream(is);
//         Paint mPaint = new Paint();
//            Canvas.drawBitmap(mBitmap, 40, 40, mPaint);
        return mBitmap;
    }


}
