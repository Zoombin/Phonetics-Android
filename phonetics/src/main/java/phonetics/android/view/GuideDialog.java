package phonetics.android.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import phonetics.android.R;
import phonetics.android.db.DB_Data;
import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.ui.DetailsActivity;
import phonetics.android.widget.CustomDialog;

/**
 * Created by lsd on 15/8/2.
 */
public class GuideDialog {
    private CallBack callBack;

    public GuideDialog() {
    }

    public GuideDialog(CallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * 首页英标
     *
     * @param context
     */
    public void Step1(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step1_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close(context);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.indicator_tap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentPhonetics.instance().setCurrentVoice(CurrentPhonetics.instance().basicsVoiceList.get(1));
                context.startActivity(new Intent(context, DetailsActivity.class));
                dialog.dismiss();
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                close(context);
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }

    /**
     * 简单介绍
     *
     * @param context
     */
    public void Step2(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step2_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close(context);
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
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                close(context);
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }

    /**
     * 头像介绍
     *
     * @param context
     */
    public void Step3(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step3_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close(context);
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
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                close(context);
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }

    /**
     * 点击举例
     *
     * @param context
     */
    public void Step4(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step4_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close(context);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.indicator_tap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Step5(context);
                doCallBack(4);
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                close(context);
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }

    /**
     * *
     * 点击嘴型
     *
     * @param context
     */
    public void Step5(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step5_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close(context);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.indicator_tap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Step6(context);
                doCallBack(5);
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                close(context);
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }

    /**
     * 点击正面/侧面
     *
     * @param context
     */
    public void Step6(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step6_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close(context);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.indicator_tap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Step7(context);
                doCallBack(6);
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                close(context);
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }

    /**
     * 点击步骤发音
     *
     * @param context
     */
    public void Step7(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step7_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close(context);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.indicator_tap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Step8(context);
                doCallBack(7);
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                close(context);
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }

    /**
     * 点击单词发音
     *
     * @param context
     */
    public void Step8(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step8_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close(context);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.indicator_tap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Step9(context);
                doCallBack(8);
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                close(context);
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }

    /**
     * *
     * 点击慢放
     *
     * @param context
     */
    public void Step9(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step9_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close(context);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.indicator_tap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Step10(context);
                doCallBack(9);
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                close(context);
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }

    /**
     * 返回
     *
     * @param context
     */
    public void Step10(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step10_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close(context);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Step11(context);
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                close(context);
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }


    /**
     * 结束
     *
     * @param context
     */
    public void Step11(final Context context) {
        final CustomDialog dialog = CustomDialog.create(context, R.style.customDialogOne);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_step11_dialog, null);
        view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close(context);
                dialog.dismiss();
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                close(context);
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }


    private void close(Context context) {
        new DB_Data(context).setGuideMode(false);
        if (callBack != null) {
            ((Activity) context).finish();
        }
    }

    private void doCallBack(int step) {
        if (callBack != null) {
            callBack.onStep(step);
        }
    }


    public interface CallBack {
        void onStep(int step);
    }

}
