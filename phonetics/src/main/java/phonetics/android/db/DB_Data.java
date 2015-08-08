package phonetics.android.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import phonetics.android.entity.ComparEntity;
import phonetics.android.entity.PhoneticsEntity;

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

    public void setComparEntities(List<ComparEntity> list) {
        setSaveMode("etys", list);
    }


    public boolean isGuideMode(){
        return getSaveBoolean("isGuideMode",true);
    }

    public void setGuideMode(boolean first){
        setSaveBoolean("isGuideMode",first);
    }


    public void setPhonetics(PhoneticsEntity entity){
        setSaveMode("Phonetics", entity);
    }

    public PhoneticsEntity getPhonetics(){
        return (PhoneticsEntity) getSaveMode("Phonetics",null);
    }

}
