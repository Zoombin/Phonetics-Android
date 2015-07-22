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
    public PhoneticsEntity.VoiceEty curVoice;
    public List<PhoneticsEntity.VoiceEty> basicsVoiceList;
    public List<PhoneticsEntity.VoiceEty> advancedVoiceList;
    private List<PhoneticsEntity.VoiceEty> curVoiceList;

    /***
     * 加载数据
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

    /***
     * 获取当前语音列表
     * @return
     */
    public List<PhoneticsEntity.VoiceEty> getCurrentVoiceList() {
        if (voiceType == VoiceType.BASIC) {
            return basicsVoiceList;
        } else {
            return advancedVoiceList;
        }
    }

    public void clean() {
        if (cClass != null) {
            cClass = null;
        }
        if (entity != null) {
            entity = null;
        }
    }
}
