package phonetics.android.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import phonetics.android.R;

/**
 * Created by lsd on 15/7/25.
 */
public class MediaPlayerUtil {
    static MediaPlayer player;

    public static void create(Context context) {
        player = MediaPlayer.create(context, R.raw.sy3);
        /*player = new MediaPlayer();
        try {
            player.setDataSource(Environment.getExternalStorageDirectory()+"/Phies/sy3.mp3");
            player.prepare();
        }catch (Exception e){}*/
    }

    public static void start(int startTime, final int delayTime) {
        if (startTime > 0) {
            player.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                @Override
                public void onSeekComplete(MediaPlayer mp) {
                    mp.start();
                    delay(delayTime);
                }
            });
            player.seekTo(startTime);
        } else {
            player.start();
            delay(delayTime);
        }
    }


    private static void delay(int time) {
        TimerUtil tUtil = new TimerUtil(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                player.pause();
            }
        });
        tUtil.startTimer(time);
    }


    public static void release() {
        player.release();
    }

}
