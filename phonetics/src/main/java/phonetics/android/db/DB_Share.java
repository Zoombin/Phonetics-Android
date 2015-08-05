package phonetics.android.db;

import android.content.Context;

public class DB_Share extends DB_Base {
    public DB_Share(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        setName("DB_Share");
        getSettings();
    }

    public boolean getShareResult() {
        return getSaveBoolean("ShareResult", false);
    }

    public void setShareResult(boolean result) {
        setSaveBoolean("ShareResult", result);
    }
}
