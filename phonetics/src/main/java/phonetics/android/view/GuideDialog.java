package phonetics.android.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import phonetics.android.R;
import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.ui.DetailsActivity;
import phonetics.android.widget.CustomDialog;

/**
 * Created by lsd on 15/8/2.
 */
public class GuideDialog {
    public static void Step1(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step1_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.indicator_tap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CurrentPhonetics.instance().setCurrentVoice(CurrentPhonetics.instance().basicsVoiceList.get(0));
                context.startActivity(new Intent(context, DetailsActivity.class));
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }

    public static void Step2(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step2_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Step3(context);
                dialog.dismiss();
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }

    public static void Step3(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step3_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }
}
