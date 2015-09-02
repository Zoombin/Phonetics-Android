package phonetics.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;

import phonetics.android.utils.MediaPlayerUtil;

/**
 * Created by LSD on 15/8/16.
 */
public class AudioStreamReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (intent.getAction().equals("android.intent.action.HEADSET_PLUG")) {
            if (intent.hasExtra("state")) {
                if (intent.getIntExtra("state", 0) == 0) {
                    //拔出耳机
                    audioManager.setSpeakerphoneOn(true);
                } else if (intent.getIntExtra("state", 0) == 1) {
                    ////插入耳机
                    audioManager.setWiredHeadsetOn(true);
                }
            }
        }
    }
}
