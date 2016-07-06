package com.cp.mylibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by Jerry on 2016/7/6.
 * <p/>
 * 图片加载的封装、
 * <p/>
 * 在Application 中进行初始化
 */
public class ImageLoaderUtils {


//    public static ImageLoaderUtils imageLoaderUtils;
//
//    /**
//     * 单一实例
//     */
//    public static ImageLoaderUtils getInstance() {
//
//        if (imageLoaderUtils == null) {
//            imageLoaderUtils = new ImageLoaderUtils();
//
//        }
//        return imageLoaderUtils;
//    }

    /**
     * stubImg ：设置图片下载期间显示的图片
     * emptyImg：设置图片Uri为空或是错误的时候显示的图片
     * failImg：设置图片加载或解码过程中发生错误显示的图片
     * cachePath：缓存的路径
     * <p/>
     * 配置ImageLoder
     */
    public static void configImageLoader(Context context, int stubImg, int emptyImg, int failImg, String cachePath) {
        // 初始化ImageLoader
        @SuppressWarnings("deprecation")
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showStubImage(stubImg)
                // 设置图片下载期间显示的图片
                .showImageForEmptyUri(emptyImg)
                // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(failImg)
                // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)
                // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)
                // 设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                // .displayer(new RoundedBitmapDisplayer(50)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        File cacheDir = StorageUtils.getOwnCacheDirectory(
                context, cachePath);

        LogCp.i(LogCp.CP, ImageLoaderUtils.class + "  存放图片 的路径 ，， " + cacheDir);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCache(new UnlimitedDiscCache(cacheDir))
                // 自定义缓存路径
                .imageDownloader(
                        new BaseImageDownloader(context,
                                10 * 1000, 30 * 1000))
                // .imageDownloader(new BaseImageDownloader(context, 5 * 1000,
                // 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }


}
