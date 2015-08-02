package phonetics.android.entity;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import phonetics.android.utils.AssetsUtil;

/**
 * Created by LSD on 2015/7/20.
 */
public class CurrentPhonetics {
    private static CurrentPhonetics cClass;

    private CurrentPhonetics() {
    }

    synchronized public static CurrentPhonetics instance() {
        if (cClass == null) {
            synchronized (CurrentPhonetics.class) {
                if (cClass == null) {
                    cClass = new CurrentPhonetics();
                }
            }
        }
        return cClass;
    }

    /**
     * 语音类型
     */
    public enum VoiceType {
        BASIC, ADVANCE;
    }

    public VoiceType voiceType = VoiceType.BASIC;
    public PhoneticsEntity entity;
    private PhoneticsEntity.VoiceEty curVoice;
    public int curIndex = 0;
    public boolean canNext = true;
    public boolean canForward = false;
    public List<PhoneticsEntity.VoiceEty> basicsVoiceList;
    public List<PhoneticsEntity.VoiceEty> advancedVoiceList;
    private List<PhoneticsEntity.VoiceEty> curVoiceList;

    /**
     * 加载数据
     *
     * @param context
     */
    public void loadData(Context context) {
        entity = AssetsUtil.readAssets(context);

        if (entity != null) {
            List<PhoneticsEntity.SymbolEty> basicSymbols = entity.getBasics();
            basicsVoiceList = new ArrayList<>();
            for (PhoneticsEntity.SymbolEty ety : basicSymbols) {
                basicsVoiceList.addAll(ety.getVoices());
            }

            List<PhoneticsEntity.SymbolEty> advancedSymbols = entity.getAdvanced();
            advancedVoiceList = new ArrayList<>();
            for (PhoneticsEntity.SymbolEty ety : advancedSymbols) {
                advancedVoiceList.addAll(ety.getVoices());
            }
        }
    }

    /**
     * 获取当前语音列表
     *
     * @return
     */
    public List<PhoneticsEntity.VoiceEty> getCurrentVoiceList() {
        if (voiceType == VoiceType.BASIC) {
            return basicsVoiceList;
        } else {
            return advancedVoiceList;
        }
    }

    /**
     * 操作当前的语言
     *
     * @param voice
     */
    public void setCurrentVoice(PhoneticsEntity.VoiceEty voice) {
        this.curVoice = voice;
        setCurrentIndex();
    }

    public PhoneticsEntity.VoiceEty getCurrentVoice() {
        return curVoice;
    }

    //下一个
    public PhoneticsEntity.VoiceEty getNextVoice() {
        if (voiceType == VoiceType.BASIC) {
            if (curIndex < basicsVoiceList.size() - 1) {
                curIndex = curIndex + 1;
                setCanForward();
                setCanNextB();
                return curVoice = basicsVoiceList.get(curIndex);
            }
        } else {
            if (curIndex < advancedVoiceList.size() - 1) {
                curIndex = curIndex + 1;
                setCanForward();
                setCanNextA();
                return curVoice = advancedVoiceList.get(curIndex);
            }
        }
        return null;
    }

    //上一个
    public PhoneticsEntity.VoiceEty getForwardVoice() {
        if (voiceType == VoiceType.BASIC) {
            if (curIndex > 1) {
                curIndex = curIndex - 1;
                setCanForward();
                setCanNextB();
                return curVoice = basicsVoiceList.get(curIndex);
            }
        } else {
            if (curIndex > 1) {
                curIndex = curIndex - 1;
                setCanForward();
                setCanNextA();
                return curVoice = advancedVoiceList.get(curIndex);
            }
        }
        return null;
    }

    /**
     * 当前语音的位置
     */
    public void setCurrentIndex() {
        if (voiceType == VoiceType.BASIC) {
            if (curVoice != null && basicsVoiceList != null) {
                for (int i = 0; i < basicsVoiceList.size(); i++) {
                    PhoneticsEntity.VoiceEty ety = basicsVoiceList.get(i);
                    if (curVoice.getName().equals(ety.getName())) {
                        curIndex = i;
                        setCanNextB();
                        break;
                    }
                }
            }
        } else {
            if (curVoice != null && advancedVoiceList != null) {
                for (int i = 0; i < advancedVoiceList.size(); i++) {
                    PhoneticsEntity.VoiceEty ety = advancedVoiceList.get(i);
                    if (curVoice.getName().equals(ety.getName())) {
                        curIndex = i;
                        setCanNextA();
                        break;
                    }
                }
            }
        }
        setCanForward();
    }

    private void setCanNextB(){
        //计算是否可以下一个
        if (curIndex < basicsVoiceList.size()-1){
            canNext = true;
        }else {
            canNext = false;
        }
    }
    private void setCanNextA(){
        //计算是否可以下一个
        if (curIndex < advancedVoiceList.size()-1){
            canNext = true;
        }else {
            canNext = false;
        }
    }

    private void setCanForward(){
        if (curIndex > 0){
            canForward = true;
        }else {
            canForward = false;
        }
    }

    public void clean() {
        if (cClass != null) {
            cClass = null;
        }
    }
}
