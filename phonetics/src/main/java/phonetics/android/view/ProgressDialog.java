package phonetics.android.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import phonetics.android.R;
import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.ui.DetailsActivity;
import phonetics.android.widget.CustomDialog;

/**
 * Created by Administrator on 2015/8/6.
 */
public class ProgressDialog {
    CustomDialog dialog;

    public ProgressDialog(Context context) {
        dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_progress_dialog, null);
        dialog.setContentVw(view);
    }

    public void show() {
        if (!dialog.isShowing()){
            dialog.show();
        }
    }

    public void dismiss() {
        if(dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
