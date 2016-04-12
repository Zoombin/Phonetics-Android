package phonetics.android.utils;

import android.content.Context;

import java.util.Calendar;

import phonetics.android.R;
import phonetics.android.db.DB_Click;

/**
 * Created by lsd on 15/8/2.
 */
public class ClickUtil {
    private static long lastClickTime = 0;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }

        lastClickTime = time;
        return false;
    }


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

    /***
     * 基本
     * @param context
     * @return
     */
    public static boolean basicClick(Context context){
        return true;
        //去掉次数限制
        /*int day = new DB_Click(context).getBasicVoiceClick();
        Calendar calendar = Calendar.getInstance();
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        if(day != curDay){
            new DB_Click(context).setBasicVoiceClick(curDay);
            new DB_Click(context).setBasicVoiceClickCount(1);
            return true;
        }else{
            int count = new DB_Click(context).getBasicVoiceClickCount();
            if (count>10){
                showAlter(context);
                return false;
            }else{
                new DB_Click(context).setBasicVoiceClickCount(count + 1);
                return true;
            }
        }*/
    }

    /***
     * 高级
     * @param context
     * @return
     */
    public static boolean advancedClick(Context context){
        return true;
        //去掉次数限制
        /*int day = new DB_Click(context).getAdvancedVoiceClick();
        Calendar calendar = Calendar.getInstance();
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        if(day != curDay){
            new DB_Click(context).setAdvancedVoiceClick(curDay);
            new DB_Click(context).setAdvancedVoiceClickCount(1);
            return true;
        }else{
            int count = new DB_Click(context).getAdvancedVoiceClickCount();
            if (count>10){
                showAlter(context);
                return false;
            }else{
                new DB_Click(context).setAdvancedVoiceClickCount(count + 1);
                return true;
            }
        }*/
    }


    /***
     * 进入对比
     * @param context
     * @return
     */
    public static boolean compareClick(Context context){
        return true;
        //去掉次数限制
        /*int day = new DB_Click(context).getCompareClick();
        Calendar calendar = Calendar.getInstance();
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        if(day != curDay){
            new DB_Click(context).setCompareClick(curDay);
            new DB_Click(context).setCompareClickCount(1);
            return true;
        }else{
            int count = new DB_Click(context).getCompareClickCount();
            if (count>10){
                showAlter(context);
                return false;
            }else{
                new DB_Click(context).setCompareClickCount(count + 1);
                return true;
            }
        }*/
    }

    /***
     * 对比上面选择
     * @param context
     * @return
     */
    public static boolean compareTopSelectClick(Context context){
        return true;
        //去掉次数限制
        /*int day = new DB_Click(context).getCompareTopSelectClick();
        Calendar calendar = Calendar.getInstance();
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        if(day != curDay){
            new DB_Click(context).setCompareTopSelectClick(curDay);
            new DB_Click(context).setCompareTopSelectClickCount(1);
            return true;
        }else{
            int count = new DB_Click(context).getCompareTopSelectClickCount();
            if (count>10){
                showAlter(context);
                return false;
            }else{
                new DB_Click(context).setCompareTopSelectClickCount(count + 1);
                return true;
            }
        }*/
    }

    /***
     * 对比下面选择
     * @param context
     * @return
     */
    public static boolean compareBottomSelectClick(Context context){
        return true;
        //去掉次数限制
        /*int day = new DB_Click(context).getCompareBottomSelectClick();
        Calendar calendar = Calendar.getInstance();
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        if(day != curDay){
            new DB_Click(context).setCompareBottomSelectClick(curDay);
            new DB_Click(context).setCompareBottomSelectClickCount(1);
            return true;
        }else{
            int count = new DB_Click(context).getCompareBottomSelectClickCount();
            if (count>10){
                showAlter(context);
                return false;
            }else{
                new DB_Click(context).setCompareBottomSelectClickCount(count + 1);
                return true;
            }
        }*/
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

    public static  void showAlter(Context context){
        AlertDialogUtil.show(context,context.getString(R.string.alter_title),context.getString(R.string.alter_beta_content));
    }


}
