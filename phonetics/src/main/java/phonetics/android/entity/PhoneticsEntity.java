package phonetics.android.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by LSD on 2015/7/17.
 */
public class PhoneticsEntity {
    private List<SymbolEty> advanced;
    private List<SymbolEty> basics;

    public List<SymbolEty> getAdvanced() {
        return advanced;
    }

    public void setAdvanced(List<SymbolEty> advanced) {
        this.advanced = advanced;
    }

    public List<SymbolEty> getBasics() {
        return basics;
    }

    public void setBasics(List<SymbolEty> basics) {
        this.basics = basics;
    }

    public static class SymbolEty {
        private String title;
        private String describe;
        private List<VoiceEty> voices;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public List<VoiceEty> getVoices() {
            return voices;
        }

        public void setVoices(List<VoiceEty> voices) {
            this.voices = voices;
        }
    }


    public static class VoiceEty implements Serializable{
        private String examples_yb_name;//举列的音标名称，这个是在界面上显示的，不同单词用&&隔开
        private String similar_yb_name;//相似的音标名称，这个是在界面上显示的，不同单词用&&隔开
        private String similar_slow_read;//相似单词的慢速读音,不同单词用&&隔开，同一个单词，是这样的女开始时间,持续时间,男开始时间,持续时间
        private String similar_read; //相似单词的正常速读音,不同单词用&&隔开，同一个单词，是这样的女开始时间,持续时间,男开始时间,持续时间
        private String examples_read; //相似单词的正常速读音,不同单词用&&隔开，同一个单词，是这样的女开始时间,持续时间,男开始时间,持续时间
        private String examples_slow_read;//举列单词的慢速读音,不同单词用&&隔开，同一个单词，是这样的女开始时间,持续时间,男开始时间,持续时间
        private String similar_yb;//相似单词的音标,不同单词用&&隔开，同一个单词用逗号隔开
        private String examples_yb;//举列单词的音标,不同单词用&&隔开，同一个单词用逗号隔开
        private String similar_count;//相似单词的数量
        private String similar;//相似单词，用逗号隔开
        private String examples_count;//举列单词的数量
        private String examples;//举列单词，用逗号隔开
        private String name;//名字
        private String step_types;//分步类型，如果有3个，就1，2，3
        private String step_describes;//分步的描述,用&&隔开，如果有多个步骤的话
        private String step_count;//分步的数量
        private String describe;//音标的描述
        private String pics_side;//音标侧面动画,用逗号隔开，每张侧面图片加上前缀c
        private String pics_front;//音标正面动画，用逗号隔开
        private String long_male;//音标读音长度(男)
        private String etime_male;//音标结束时间(男)
        private String stime_male;//音标开始时间(男)
        private String long_female; //音标读音长度(女)
        private String stime_female;//音标开始时间(女)
        private String etime_female;//音标结束时间(女)
        private String img;//音标的图片
        private String step_voices;//分步的读音，不同步骤用&&隔开，每步是这样的，女开始时间,持续时间,男开始时间,持续时间
        private String step_pics;//分步播放的图片，不同步骤用&&隔开，同一步的图片用逗号隔开。
        private String examples_pics;//举列单词的图片，不同单词用&&隔开，同一个单词用逗号隔开

        public String getExamples_yb_name() {
            return examples_yb_name;
        }

        public void setExamples_yb_name(String examples_yb_name) {
            this.examples_yb_name = examples_yb_name;
        }

        public String getSimilar_yb_name() {
            return similar_yb_name;
        }

        public void setSimilar_yb_name(String similar_yb_name) {
            this.similar_yb_name = similar_yb_name;
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

        public String getExamples_read() {
            return examples_read;
        }

        public void setExamples_read(String examples_read) {
            this.examples_read = examples_read;
        }

        public String getExamples_slow_read() {
            return examples_slow_read;
        }

        public void setExamples_slow_read(String examples_slow_read) {
            this.examples_slow_read = examples_slow_read;
        }

        public String getSimilar_yb() {
            return similar_yb;
        }

        public void setSimilar_yb(String similar_yb) {
            this.similar_yb = similar_yb;
        }

        public String getExamples_yb() {
            return examples_yb;
        }

        public void setExamples_yb(String examples_yb) {
            this.examples_yb = examples_yb;
        }

        public String getSimilar_count() {
            return similar_count;
        }

        public void setSimilar_count(String similar_count) {
            this.similar_count = similar_count;
        }

        public String getSimilar() {
            return similar;
        }

        public void setSimilar(String similar) {
            this.similar = similar;
        }

        public String getExamples_count() {
            return examples_count;
        }

        public void setExamples_count(String examples_count) {
            this.examples_count = examples_count;
        }

        public String getExamples() {
            return examples;
        }

        public void setExamples(String examples) {
            this.examples = examples;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStep_types() {
            return step_types;
        }

        public void setStep_types(String step_types) {
            this.step_types = step_types;
        }

        public String getStep_describes() {
            return step_describes;
        }

        public void setStep_describes(String step_describes) {
            this.step_describes = step_describes;
        }

        public String getStep_count() {
            return step_count;
        }

        public void setStep_count(String step_count) {
            this.step_count = step_count;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getPics_side() {
            return pics_side;
        }

        public void setPics_side(String pics_side) {
            this.pics_side = pics_side;
        }

        public String getPics_front() {
            return pics_front;
        }

        public void setPics_front(String pics_front) {
            this.pics_front = pics_front;
        }

        public String getLong_male() {
            return long_male;
        }

        public void setLong_male(String long_male) {
            this.long_male = long_male;
        }

        public String getEtime_male() {
            return etime_male;
        }

        public void setEtime_male(String etime_male) {
            this.etime_male = etime_male;
        }

        public String getStime_male() {
            return stime_male;
        }

        public void setStime_male(String stime_male) {
            this.stime_male = stime_male;
        }

        public String getLong_female() {
            return long_female;
        }

        public void setLong_female(String long_female) {
            this.long_female = long_female;
        }

        public String getStime_female() {
            return stime_female;
        }

        public void setStime_female(String stime_female) {
            this.stime_female = stime_female;
        }

        public String getEtime_female() {
            return etime_female;
        }

        public void setEtime_female(String etime_female) {
            this.etime_female = etime_female;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getStep_voices() {
            return step_voices;
        }

        public void setStep_voices(String step_voices) {
            this.step_voices = step_voices;
        }

        public String getStep_pics() {
            return step_pics;
        }

        public void setStep_pics(String step_pics) {
            this.step_pics = step_pics;
        }

        public String getExamples_pics() {
            return examples_pics;
        }

        public void setExamples_pics(String examples_pics) {
            this.examples_pics = examples_pics;
        }
    }
}
