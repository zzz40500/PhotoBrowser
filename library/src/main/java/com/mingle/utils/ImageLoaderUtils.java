package com.mingle.utils;

import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


public class ImageLoaderUtils {


    /**
     * 初始化ImageLoader
     *
     * @param context
     */
    public static void initImageLoader(Context context) {

        // String filePath=context.getCacheDir().getPath();

       if(! ImageLoader.getInstance().isInited()) {

           ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                   context).threadPriority(Thread.NORM_PRIORITY - 2)
                   .denyCacheImageMultipleSizesInMemory()
                   .discCacheFileNameGenerator(new Md5FileNameGenerator())
                   .tasksProcessingOrder(QueueProcessingType.LIFO)
                   .writeDebugLogs() // Remove for release app
                   .build();
           // Initialize ImageLoaderUtils with configuration.
           ImageLoader.getInstance().init(config);
       }
    }
}
