package phonetics.android.utils;

import android.content.Context;

import java.util.Calendar;

import phonetics.android.db.DB_Click;

/**
 * Created by lsd on 15/8/2.
 */
public class AdClickUtil {

    public static void adClick(Context context) {
        int count = new DB_Click(context).getClickCount();

        long time = new DB_Click(context).getClickTime();
        long curTime = System.currentTimeMillis();
        if ((curTime - time) > 14 * 60 * 1000) {
            count = count + 1;
            new DB_Click(context).setClickCount(count);
            new DB_Click(context).setClickTime(curTime);
        }

    }

    public static int getCount(Context context) {
        return new DB_Click(context).getClickCount();
    }

    public static void clean(Context context) {
        Calendar calendar = Calendar.getInstance();
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);

        int day = new DB_Click(context).getClickDay();
        if (curDay < day) {
            new DB_Click(context).setClickCount(0);
        }
    }
}
