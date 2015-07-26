package phonetics.android.entity;

import java.util.List;

/**
 * Created by lsd on 15/7/25.
 */
public class SimilarEntity {
    String similar;
    String similar_yb;
    String[] similar_yb_array;
    String similar_yb_name;
    String[] similar_yb_name_array;
    String similar_slow_read;
    String similar_read;
    List<PhoneticsEntity.VoiceEty> vList;


    public String getSimilar() {
        return similar;
    }

    public void setSimilar(String similar) {
        this.similar = similar;
    }

    public String getSimilar_yb() {
        return similar_yb;
    }

    public void setSimilar_yb(String similar_yb) {
        this.similar_yb = similar_yb;
    }

    public String[] getSimilar_yb_array() {
        return similar_yb_array;
    }

    public void setSimilar_yb_array(String[] similar_yb_array) {
        this.similar_yb_array = similar_yb_array;
    }

    public String getSimilar_yb_name() {
        return similar_yb_name;
    }

    public void setSimilar_yb_name(String similar_yb_name) {
        this.similar_yb_name = similar_yb_name;
    }

    public String[] getSimilar_yb_name_array() {
        return similar_yb_name_array;
    }

    public void setSimilar_yb_name_array(String[] similar_yb_name_array) {
        this.similar_yb_name_array = similar_yb_name_array;
    }

    public String getSimilar_slow_read() {
        return similar_slow_read;
    }

    public void setSimilar_slow_read(String similar_slow_read) {
        this.similar_slow_read = similar_slow_read;
    }

    public String getSimilar_read() {
        return similar_read;
    }

    public void setSimilar_read(String similar_read) {
        this.similar_read = similar_read;
    }

    public List<PhoneticsEntity.VoiceEty> getvList() {
        return vList;
    }

    public void setvList(List<PhoneticsEntity.VoiceEty> vList) {
        this.vList = vList;
    }
}
