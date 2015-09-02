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

    public void setCount(int count) {
        setSaveInt("count", count);
    }

    public int getCount() {
        return getSaveInt("count", 0);
    }


    public void setTime(long time) {
        setSaveLong("Time", time);
    }

    public long getTime() {
        return getSaveLong("Time", 0);
    }

    public void setDay(int day) {
        setSaveInt("Day", day);
    }

    public int getDay() {
        return getSaveInt("Day", 1);
    }

    public void setMonth(int month) {
        setSaveInt("Month", month);
    }

    public int getMonth() {
        return getSaveInt("Month", 1);
    }


    public void setBasicVoiceClick(int day) {
        setSaveInt("BasicVoiceClick", day);
    }

    public int getBasicVoiceClick() {
        return getSaveInt("BasicVoiceClick", 0);
    }

    public void setAdvancedVoiceClick(int day) {
        setSaveInt("AdvancedVoiceClick", day);
    }

    public int getAdvancedVoiceClick() {
        return getSaveInt("AdvancedVoiceClick", 0);
    }

    public void setCompareClick(int day) {
        setSaveInt("CompareClick", day);
    }

    public int getCompareClick() {
        return getSaveInt("CompareClick", 0);
    }

    public void setCompareTopSelectClick(int day) {
        setSaveInt("CompareTopSelectClick", day);
    }

    public int getCompareTopSelectClick() {
        return getSaveInt("CompareTopSelectClick", 0);
    }

    public void setCompareBottomSelectClick(int day) {
        setSaveInt("CompareBottomSelectClick", day);
    }

    public int getCompareBottomSelectClick() {
        return getSaveInt("CompareBottomSelectClick", 0);
    }

    public void setCompareBottomSelectClickCount(int count) {
        setSaveInt("CompareBottomSelectClickCount", count);
    }

    public int getCompareBottomSelectClickCount() {
        return getSaveInt("CompareBottomSelectClickCount", 1);
    }

    public void setCompareTopSelectClickCount(int count) {
        setSaveInt("CompareTopSelectClickCount", count);
    }

    public int getCompareTopSelectClickCount() {
        return getSaveInt("CompareTopSelectClickCount", 1);
    }



    public void setBasicVoiceClickCount(int count) {
        setSaveInt("BasicVoiceClickCount", count);
    }

    public int getBasicVoiceClickCount() {
        return getSaveInt("BasicVoiceClickCount", 1);
    }

    public void setAdvancedVoiceClickCount(int count) {
        setSaveInt("AdvancedVoiceClickCount", count);
    }

    public int getAdvancedVoiceClickCount() {
        return getSaveInt("AdvancedVoiceClickCount", 1);
    }

    public void setCompareClickCount(int count) {
        setSaveInt("CompareClickCount", count);
    }

    public int getCompareClickCount() {
        return getSaveInt("CompareClickCount", 1);
    }


}
