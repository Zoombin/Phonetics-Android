package phonetics.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

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
        ImageLoader loader = ImageLoader.getInstance();
        if (imageView != null && resouce != null) {
            int count = resouce.length;
            int durtion = time / count;
            AnimationDrawable animationDrawable = new AnimationDrawable();
            for (int i = 0; i < count; i++) {
                animationDrawable.addFrame(new BitmapDrawable(context.getResources(), loader.loadImageSync("assets://voicepic/" + resouce[i] + ".jpg")), durtion);
            }
            imageView.setImageDrawable(animationDrawable);
            animationDrawable.setOneShot(true);
            animationDrawable.start();
        }
    }
}
