package phonetics.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import phonetics.android.R;
import phonetics.android.utils.AnimationUtil;

/**
 * Created by Administrator on 2015/8/5.
 */
public class MImageView extends ImageView {
    public MImageView(Context context) {
        super(context);
        init(context);
    }

    public MImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Animation animation  =  AnimationUtils.loadAnimation(context, R.anim.alpha_anim);
        this.startAnimation(animation);
    }
}
