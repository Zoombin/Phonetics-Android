package phonetics.android.utils;

import android.content.Context;

import java.util.Calendar;

import phonetics.android.db.DB_Click;

/**
 * Created by lsd on 15/8/2.
 */
public class AdClickUtil {

    public static void adClick(Context context) {
        int count = new DB_Click(context).getCount();

        int day = new DB_Click(context).getDay();
        Calendar calendar = Calendar.getInstance();
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (curDay != day) {
            //一天只计数一次
            count = count + 1;
            new DB_Click(context).setCount(count);
            new DB_Click(context).setDay(curDay);
        }

    }

    public static int getCount(Context context) {
        return new DB_Click(context).getCount();
    }

    /**
     * 第二个月清空
     *
     * @param context
     */
    public static void clean(Context context) {
        Calendar calendar = Calendar.getInstance();
        int curMonth = calendar.get(Calendar.MONTH);

        int month = new DB_Click(context).getMonth();
        if (month != curMonth) {
            new DB_Click(context).setCount(0);
            new DB_Click(context).setMonth(curMonth);
        }
    }


}
