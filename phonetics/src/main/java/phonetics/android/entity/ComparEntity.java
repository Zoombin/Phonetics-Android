package phonetics.android.entity;

import java.io.Serializable;

/**
 * Created by lsd on 15/8/2.
 */
public class ComparEntity implements Serializable {

    PhoneticsEntity.VoiceEty topEty;
    PhoneticsEntity.VoiceEty bottomEty;

    public PhoneticsEntity.VoiceEty getTopEty() {
        return topEty;
    }

    public void setTopEty(PhoneticsEntity.VoiceEty topEty) {
        this.topEty = topEty;
    }

    public PhoneticsEntity.VoiceEty getBottomEty() {
        return bottomEty;
    }

    public void setBottomEty(PhoneticsEntity.VoiceEty bottomEty) {
        this.bottomEty = bottomEty;
    }
}
