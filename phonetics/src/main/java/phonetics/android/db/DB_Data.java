package phonetics.android.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import phonetics.android.entity.ComparEntity;

public class DB_Data extends DB_Base {
    public DB_Data(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        setName("DB_DATA");
        getSettings();
    }

    public List<ComparEntity> getComparEntities() {
        List<ComparEntity> list = (List<ComparEntity>) getSaveMode("etys", null);
        return list;
    }

    public void setComparEntitie(ComparEntity ety) {
        List<ComparEntity> list = getComparEntities();
        if (list == null){
            list = new ArrayList<>();
        }
        list.add(ety);
        setSaveMode("etys", list);
    }

    public void changeComparEntitie(int position, ComparEntity ety) {
        List<ComparEntity> list = getComparEntities();
        list.set(position, ety);
        setSaveMode("etys", list);
    }

    public boolean isGuideMode(){
        return getSaveBoolean("isGuideMode",true);
    }

    public void setGuideMode(boolean first){
        setSaveBoolean("isGuideMode",first);
    }

}
