package phonetics.android.utils;

import android.content.Context;

import java.util.Calendar;

import phonetics.android.R;
import phonetics.android.db.DB_Click;

/**
 * Created by lsd on 15/8/2.
 */
public class ClickUtil {

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
        int day = new DB_Click(context).getBasicVoiceClick();
        Calendar calendar = Calendar.getInstance();
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        if(day != curDay){
            new DB_Click(context).setBasicVoiceClick(curDay);
            return true;
        }else{
            showAlter(context);
            return false;
        }
    }

    /***
     * 高级
     * @param context
     * @return
     */
    public static boolean advancedClick(Context context){
        int day = new DB_Click(context).getAdvancedVoiceClick();
        Calendar calendar = Calendar.getInstance();
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        if(day != curDay){
            new DB_Click(context).setAdvancedVoiceClick(curDay);
            return true;
        }else{
            showAlter(context);
            return false;
        }
    }


    /***
     * 进入对比
     * @param context
     * @return
     */
    public static boolean compareClick(Context context){
        int day = new DB_Click(context).getCompareClick();
        Calendar calendar = Calendar.getInstance();
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        if(day != curDay){
            new DB_Click(context).setCompareClick(curDay);
            return true;
        }else{
            showAlter(context);
            return false;
        }
    }

    /***
     * 对比上面选择
     * @param context
     * @return
     */
    public static boolean compareTopSelectClick(Context context){
        int day = new DB_Click(context).getCompareTopSelectClick();
        Calendar calendar = Calendar.getInstance();
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        if(day != curDay){
            new DB_Click(context).setCompareTopSelectClick(curDay);
            return true;
        }else{
            showAlter(context);
            return false;
        }
    }

    /***
     * 对比下面选择
     * @param context
     * @return
     */
    public static boolean compareBottomSelectClick(Context context){
        int day = new DB_Click(context).getCompareBottomSelectClick();
        Calendar calendar = Calendar.getInstance();
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        if(day != curDay){
            new DB_Click(context).setCompareBottomSelectClick(curDay);
            return true;
        }else{
            showAlter(context);
            return false;
        }
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
