package phonetics.android.entity;

import android.content.Context;

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

    public PhoneticsEntity entity;

    public void loadData(Context context) {
        entity = AssetsUtil.readAssets(context);
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
