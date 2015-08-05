package phonetics.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import phonetics.android.Constants;
import phonetics.android.config.Config;

/**
 * Created by lsd on 15/7/23.
 */
public class AnimationUtil {

    /**
     * @param imageView
     * @param resouce   资源
     * @param time      毫秒
     */
    public static void startAnimation(Context context, ImageView imageView, String[] resouce, int time) {
        if (Constants.isPlaying){
            return;
        }
        ImageLoader loader = ImageLoader.getInstance();
        if (imageView != null && resouce != null) {
            int count = resouce.length;
            int durtion = time / count;
            AnimationDrawable animationDrawable = new AnimationDrawable();
            for (int i = 0; i < count; i++) {
                int resource_ID = context.getResources().getIdentifier(resouce[i], "drawable", "phonetics.android");
                if(resource_ID != 0){
                    Drawable drawable = context.getResources().getDrawable(resource_ID);
                    animationDrawable.addFrame(drawable,durtion);
                }
                //loader.getMemoryCache().get(Config.VOCIEPIC_BASE_PATH + resouce[i] + Config.IMG_TYPE_JPG);
                //animationDrawable.addFrame(new BitmapDrawable(context.getResources(), loader.loadImageSync(Config.VOCIEPIC_BASE_PATH + resouce[i] + Config.IMG_TYPE_JPG)), durtion);
            }
            imageView.setImageDrawable(animationDrawable);
            animationDrawable.setOneShot(true);
            animationDrawable.start();
        }
    }
}
