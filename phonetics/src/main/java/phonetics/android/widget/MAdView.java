package phonetics.android.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;

/**
 * Created by lsd on 15/8/5.
 */
public class MAdView extends AdView {
    OnClickListener listener;

    public MAdView(Context context) {
        super(context);
    }

    public MAdView(Context context, AdSize adSize) {
        super(context, adSize);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                listener.onClick();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }

    public interface OnClickListener{
        void onClick();
    }

}
