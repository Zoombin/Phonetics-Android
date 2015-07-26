package phonetics.android.entity;

import java.util.List;

/**
 * Created by lsd on 15/7/25.
 */
public class GeneralEntity {
    String name;
    String yb;
    String[] yb_array;
    String yb_name;
    String[] yb_name_array;
    String slow_read;
    String read;
    String advanced_pic;
    List<PhoneticsEntity.VoiceEty> vList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYb() {
        return yb;
    }

    public void setYb(String yb) {
        this.yb = yb;
    }

    public String[] getYb_array() {
        return yb_array;
    }

    public void setYb_array(String[] yb_array) {
        this.yb_array = yb_array;
    }

    public String getYb_name() {
        return yb_name;
    }

    public void setYb_name(String yb_name) {
        this.yb_name = yb_name;
    }

    public String[] getYb_name_array() {
        return yb_name_array;
    }

    public void setYb_name_array(String[] yb_name_array) {
        this.yb_name_array = yb_name_array;
    }

    public String getSlow_read() {
        return slow_read;
    }

    public void setSlow_read(String slow_read) {
        this.slow_read = slow_read;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public List<PhoneticsEntity.VoiceEty> getvList() {
        return vList;
    }

    public void setvList(List<PhoneticsEntity.VoiceEty> vList) {
        this.vList = vList;
    }

    public String getAdvanced_pic() {
        return advanced_pic;
    }

    public void setAdvanced_pic(String advanced_pic) {
        this.advanced_pic = advanced_pic;
    }
}
