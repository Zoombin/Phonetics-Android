package phonetics.android.utils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;


import phonetics.android.Constants;
import phonetics.android.config.Config;
import phonetics.android.view.ProgressDialog;

/**
 * Created by lsd on 15/7/23.
 */
public class AnimationUtil {
    static ImageView displayImage;
    static ProgressDialog dialog;
    static PlayUtil.LoadListener lis;

    static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            dialog.dismiss();
            if (lis != null){
                lis.complete();
            }
            AnimationDrawable animationDrawable = (AnimationDrawable) msg.obj;
            displayImage.setImageDrawable(animationDrawable);
            animationDrawable.setOneShot(true);
            animationDrawable.start();
        }
    };

    /**
     * @param imageView
     * @param resouce   资源
     * @param time      毫秒
     */
    public static void startAnimation(final Context context, ImageView imageView, final String[] resouce, int time/*,PlayUtil.LoadListener listener*/) {
        displayImage = imageView;
        //lis = listener;
        if (Constants.isPlaying) {
            return;
        }
        dialog = new ProgressDialog(context);
        final ImageLoader loader = ImageLoader.getInstance();
        if (imageView != null && resouce != null) {
            final int count = resouce.length;
            final int durtion = time / count;
            new Thread() {
                @Override
                public void run() {
                    AnimationDrawable animationDrawable = new AnimationDrawable();
                    for (int i = 0; i < count; i++) {
                        animationDrawable.addFrame(new BitmapDrawable(context.getResources(), loader.loadImageSync(Config.VOCIEPIC_BASE_PATH + resouce[i] + Config.IMG_TYPE_JPG)), durtion);
                    }
                    Message msg = handler.obtainMessage();
                    msg.obj = animationDrawable;
                    handler.sendMessage(msg);
                }
            }.start();
        }
    }

}
