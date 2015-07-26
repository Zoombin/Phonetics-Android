package phonetics.android.utils;

import android.os.Handler;

/**
 * Created by lsd on 15/7/25.
 */
public class TimerUtil {
    private Handler handler;


    public TimerUtil(Handler handler) {
        // TODO Auto-generated constructor stub
        this.handler = handler;
    }

    public void startTimer(int time) {
        handler.postDelayed(task, time);
    }

    public void stopTimer() {
        handler.removeCallbacks(task);
    }

    private Runnable task = new Runnable() {
        public void run() {
            stopTimer();
            handler.sendEmptyMessage(0);
        }
    };
}
