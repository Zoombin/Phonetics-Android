package phonetics.android.utils;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by lsd on 15/8/5.
 */
public class AlertDialogUtil {
    public static void show(Context context, String title, String message) {
        new AlertDialog.Builder(context).setTitle(title).setMessage(message).setNegativeButton("确定", null).show();
    }
}
