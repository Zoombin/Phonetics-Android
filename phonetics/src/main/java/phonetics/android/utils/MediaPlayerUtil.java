package phonetics.android.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import phonetics.android.BaseApplication;
import phonetics.android.Constants;
import phonetics.android.R;

/**
 * Created by lsd on 15/7/25.
 */
public class MediaPlayerUtil {
    static Context appContext;
    static MediaPlayer player;

    public static void create(Context context) {
        appContext = context;
        player = MediaPlayer.create(appContext, R.raw.sy3);
    }

    public static MediaPlayer getPlayer() {
        return player;
    }

    public static void start(int startTime, final int delayTime) {
        if (player == null) {
            Toast.makeText(appContext, "正在获取语音数据，请稍后再试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (startTime > 0) {
            player.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                @Override
                public void onSeekComplete(MediaPlayer mp) {
                    Constants.isPlaying = true;
                    mp.start();
                    delay(delayTime);
                }
            });
            try {
                player.seekTo(startTime);
            } catch (Exception e) {
                e.printStackTrace();
                player = null;
                Toast.makeText(appContext, "正在获取语音数据，请稍后再试", Toast.LENGTH_SHORT).show();
                create(BaseApplication.instance());
            }
        } else {
            Constants.isPlaying = true;
            player.start();
            delay(delayTime);
        }
    }


    private static void delay(int time) {
        TimerUtil tUtil = new TimerUtil(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                player.pause();
                setIsNotPlaying();
            }
        });
        tUtil.startTimer(time);
    }

    private static void setIsNotPlaying(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Constants.isPlaying = false;
            }
        },200);
    }


    public static void release() {
        player.release();
    }

}
