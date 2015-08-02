package phonetics.android.db;

import android.content.Context;

import java.util.List;

import phonetics.android.entity.ComparEntity;

public class DB_Click extends DB_Base {
    public DB_Click(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        setName("DB_Click");
        getSettings();
    }

    public void setClickCount(int count) {
        setSaveString("clickCount", count + "");
    }

    public int getClickCount() {
        String s = getSaveString("clickCount", "0");
        return Integer.parseInt(s);
    }


    public void setClickTime(long time) {
        setSaveLong("ClickTime", time);
    }

    public long getClickTime() {
        return getSaveLong("ClickTime", 0);
    }

    public void setClickDay(int day) {
        setSaveInt("ClickDay", day);
    }

    public int getClickDay() {
        return getSaveInt("ClickDay", 1);
    }


}
