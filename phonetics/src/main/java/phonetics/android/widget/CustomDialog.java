package phonetics.android.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;

import phonetics.android.R;

public class CustomDialog extends Dialog {
    private static CustomDialog customDialog = null;

    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }

    public static CustomDialog create(Context context, int theme) {
        customDialog = new CustomDialog(context, theme);
        customDialog.setCanceledOnTouchOutside(true);
        return customDialog;
    }

    public static CustomDialog create(Context context) {
        customDialog = new CustomDialog(context, R.style.customDialog);
        customDialog.setCanceledOnTouchOutside(true);
        return customDialog;
    }

    public enum CGravity {
        TOP(), BOTTOM(), CENTER();

        private CGravity() {
        }
    }

    /**
     * setGravity
     *
     * @param cGraity
     */
    public CustomDialog setGravity(CGravity cGraity) {
        switch (cGraity) {
            case TOP:
                customDialog.getWindow().getAttributes().gravity = Gravity.TOP;
                break;
            case BOTTOM:
                customDialog.getWindow().getAttributes().gravity = Gravity.BOTTOM;
                break;
            case CENTER:
                customDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
                break;
            default:
                customDialog.getWindow().getAttributes().gravity = Gravity.TOP;
                break;
        }
        return customDialog;
    }

    /**
     * @param id
     * @return
     */
    public CustomDialog setContentVw(int id) {
        customDialog.setContentView(id);
        return customDialog;
    }

    public CustomDialog setContentVw(View view) {
        customDialog.setContentView(view);
        return customDialog;
    }


    /**
     * Animation
     *
     * @return
     */
    public CustomDialog loadAnimation() {
        customDialog.getWindow().setWindowAnimations(R.style.dialogAnim);
        return customDialog;
    }

    public CustomDialog loadAnimation(int style) {
        customDialog.getWindow().setWindowAnimations(style);
        return customDialog;
    }
}
