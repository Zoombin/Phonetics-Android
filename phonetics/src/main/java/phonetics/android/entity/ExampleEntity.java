package phonetics.android.entity;

import java.util.List;

/**
 * Created by lsd on 15/7/25.
 */
public class ExampleEntity {
    String example;
    String examples_yb;
    String[] examples_yb_array;
    String examples_yb_name;
    String[] examples_yb_name_array;
    String examples_slow_read;
    String examples_read;
    List<PhoneticsEntity.VoiceEty> vList;


    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getExamples_yb() {
        return examples_yb;
    }

    public void setExamples_yb(String examples_yb) {
        this.examples_yb = examples_yb;
    }

    public String getExamples_yb_name() {
        return examples_yb_name;
    }

    public void setExamples_yb_name(String examples_yb_name) {
        this.examples_yb_name = examples_yb_name;
    }

    public String getExamples_slow_read() {
        return examples_slow_read;
    }

    public void setExamples_slow_read(String examples_slow_read) {
        this.examples_slow_read = examples_slow_read;
    }

    public String getExamples_read() {
        return examples_read;
    }

    public void setExamples_read(String examples_read) {
        this.examples_read = examples_read;
    }

    public List<PhoneticsEntity.VoiceEty> getvList() {
        return vList;
    }

    public void setvList(List<PhoneticsEntity.VoiceEty> vList) {
        this.vList = vList;
    }

    public String[] getExamples_yb_array() {
        return examples_yb_array;
    }

    public void setExamples_yb_array(String[] examples_yb_array) {
        this.examples_yb_array = examples_yb_array;
    }

    public String[] getExamples_yb_name_array() {
        return examples_yb_name_array;
    }

    public void setExamples_yb_name_array(String[] examples_yb_name_array) {
        this.examples_yb_name_array = examples_yb_name_array;
    }
}
