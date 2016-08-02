package phonetics.android;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.provider.Settings;

import com.baidu.mobstat.SendStrategyEnum;
import com.baidu.mobstat.StatService;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

import cn.sharesdk.framework.ShareSDK;
import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.tools.StorageUtils;
import phonetics.android.utils.ClickUtil;
import phonetics.android.utils.DensityUtil;
import phonetics.android.utils.MediaPlayerUtil;

/**
 * Created by LSD on 2015/7/20.
 */
public class BaseApplication extends Application {
    private static Application app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        CurrentPhonetics.instance().loadData(this);//加载配置文件
        ClickUtil.clean(this);//是否需要清除数据

        //百度统计
        if(Constants.DEBUG){
            // 测试时，可以使用1秒钟session过期，这样不断的间隔1S启动退出会产生大量日志。
            StatService.setSessionTimeOut(30);
        }
        //设置启动时日志发送延时的秒数
        StatService.setLogSenderDelayed(200);
        //日志发送策略
        StatService.setSendLogStrategy(this, SendStrategyEnum.SET_TIME_INTERVAL, 1, false);
        StatService.setDebugOn(Constants.DEBUG);

        //ShareSDK初始化
        ShareSDK.initSDK(this);

        //初始化图片加载
        initImageLoader(this);

        new Thread(){
            @Override
            public void run() {
                MediaPlayerUtil.create(app);//准备音乐文件
                setSystemAudioManager();//设置系统音频参数
            }
        }.start();

        initStaticValues();
    }

    public static Application instance() {
        return app;
    }

    public void initStaticValues(){
        int screenHeight = DensityUtil.screenHeightInPx(app);
        Constants.PTImgHeight = (int)(screenHeight * 1.2/3);
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

    private void setSystemAudioManager(){
        AudioManager audioManager = (AudioManager) app.getSystemService(Context.AUDIO_SERVICE);
        Settings.System.putInt(app.getContentResolver(), Settings.System.SOUND_EFFECTS_ENABLED, 0);
        audioManager.unloadSoundEffects();//关闭按键音
        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 100, 0);//tempVolume:音量绝对值
        MediaPlayerUtil.getPlayer().setAudioStreamType(AudioManager.STREAM_MUSIC);
        if (audioManager.isWiredHeadsetOn()){
            //插上耳机
            audioManager.setWiredHeadsetOn(true);
        }else{
            audioManager.setSpeakerphoneOn(true);
        }
    }

    @Override
    public void onTerminate() {
        MediaPlayerUtil.release();//释放音乐文件
        CurrentPhonetics.instance().clean();//清除配置数据
        ImageLoader.getInstance().clearMemoryCache();//清除图片缓存

        super.onTerminate();
    }
}
