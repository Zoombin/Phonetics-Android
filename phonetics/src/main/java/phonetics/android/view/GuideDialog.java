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
    /***
     * 首页英标
     * @param context
     */
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
                dialog.dismiss();
                CurrentPhonetics.instance().setCurrentVoice(CurrentPhonetics.instance().basicsVoiceList.get(0));
                context.startActivity(new Intent(context, DetailsActivity.class));
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }

    /***
     * 简单介绍
     * @param context
     */
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

    /***
     * 头像介绍
     * @param context
     */
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
                dialog.dismiss();
                Step4(context);
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }

    /***
     * 点击举例
     * @param context
     */
    public static void Step4(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step4_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.indicator_tap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Step5(context);
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }

    /****
     * 点击嘴型
     * @param context
     */
    public static void Step5(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step5_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.indicator_tap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Step6(context);
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }

    /***
     * 点击正面/侧面
     * @param context
     */
    public static void Step6(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step6_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.indicator_tap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }

    /***
     * 点击步骤发音
     * @param context
     */
    public static void Step7(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step6_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.indicator_tap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }
}
