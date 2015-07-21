package phonetics.android;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

import phonetics.android.tools.StorageUtils;

/**
 * Created by LSD on 2015/7/20.
 */
public class BaseApplication extends Application {
    private static Application app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        initImageLoader(this);
    }

    public static Application instance() {
        return app;
    }

    /***
     * ImageLoader初始化实例
     *
     * @param context
     */
    public void initImageLoader(Context context) {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                //.showImageOnLoading(R.drawable.ic_launcher)
                //.showImageForEmptyUri(R.drawable.ic_launcher)
                //.showImageOnFail(R.drawable.ic_launcher)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)//OOM时不要使用
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//编码显示(OOM时采用 IN_SAMPLE_INT  EXACTLY)
                .build();

        File cacheDir = StorageUtils.getCacheDirectory(context);//自定义缓存目录
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(defaultOptions)
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 6 * 1000))//超时时间
                .build();

        ImageLoader.getInstance().init(config);
    }
}
