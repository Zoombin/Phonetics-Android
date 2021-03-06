package phonetics.android.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import phonetics.android.BaseActivity;
import phonetics.android.BaseApplication;
import phonetics.android.R;
import phonetics.android.adapter.PhoneticsAdapter;
import phonetics.android.db.DB_Data;
import phonetics.android.db.DB_Share;
import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.receiver.AudioStreamReceiver;
import phonetics.android.utils.ClickUtil;
import phonetics.android.utils.AlertDialogUtil;
import phonetics.android.view.GuideDialog;
import phonetics.android.widget.CustomDialog;

public class MainActivity extends BaseActivity implements OnClickListener {
    final int SHARE_SUCCESS = 0;
    final int SHARE_FAIL = 1;
    final int SHARE_CANCEL = 2;


    TextView bt_left, bt_right;
    ImageView iv_menu;
    PhoneticsAdapter adapter;
    ListView listView;
    AudioStreamReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        if (CurrentPhonetics.instance().entity == null) {
            CurrentPhonetics.instance().loadData(mActivity);
        }

        setData();
        isFirstLogin();
        registerHeadsetPlugReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void registerHeadsetPlugReceiver() {
        receiver = new AudioStreamReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        registerReceiver(receiver, intentFilter);
    }

    private void initView() {
        (bt_left = (TextView) findViewById(R.id.bt_left)).setOnClickListener(this);
        bt_left.setSelected(true);

        (bt_right = (TextView) findViewById(R.id.bt_right)).setOnClickListener(this);
        (iv_menu = (ImageView) findViewById(R.id.iv_menu)).setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter = new PhoneticsAdapter(mActivity));
    }

    private void tabSelect(View v) {
        PhoneticsEntity entity = CurrentPhonetics.instance().entity;
        switch (v.getId()) {
            case R.id.bt_left:
                bt_right.setSelected(false);
                bt_left.setSelected(true);
                CurrentPhonetics.instance().voiceType = CurrentPhonetics.VoiceType.BASIC;//当前类型 基本
                adapter.setData(entity.getBasics());
                break;
            case R.id.bt_right:
                //限制一次
                if (!new DB_Share(mActivity).getShareResult()) {
                    showShare();
                    new DB_Share(mActivity).setShareResult(true);
                    return;
                }
                bt_left.setSelected(false);
                bt_right.setSelected(true);
                CurrentPhonetics.instance().voiceType = CurrentPhonetics.VoiceType.ADVANCE;//当前类型 高级
                adapter.setData(entity.getAdvanced());
                break;
        }
    }


    private void showMenuDialog() {
        final CustomDialog dialog = CustomDialog.create(mActivity, R.style.customDialogOne);
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_menu_dialog, null);
        TextView tv_click = (TextView) view.findViewById(R.id.tv_click);
        int count = ClickUtil.getCount(mActivity);
        if (count > 0) {
            tv_click.setVisibility(View.VISIBLE);
            tv_click.setText(count + "");
        } else {
            tv_click.setVisibility(View.GONE);
        }
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_colse).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_item_compare).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //去掉强制分享
                /*if (!new DB_Share(mActivity).getShareResult()) {
                    showShare();
                    dialog.dismiss();
                    return;
                }*/
                dialog.dismiss();
                if (ClickUtil.compareClick(mActivity)) {
                    startActivity(new Intent(mActivity, CompareActivity.class));
                }
            }
        });
        view.findViewById(R.id.tv_item_share).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_item_guide).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tabSelect(bt_left);
                new DB_Data(mActivity).setGuideMode(true);
                new GuideDialog().Step1(mActivity);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_item_call).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, WebViewActivity.class).putExtra("url", "http://url.cn/e1QPcQ"));
                dialog.dismiss();
            }
        });
        dialog.setContentVw(view);
        dialog.show();
    }


    private void setData() {
        PhoneticsEntity entity = CurrentPhonetics.instance().entity;
        adapter.setData(entity.getBasics());
    }

    public void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setTitle(getString(R.string.share_title));
        oks.setText(getString(R.string.share_content));
        //oks.setUrl(url);
        oks.setImagePath(getResources().getAssets() + "ic_logo");
        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                messageHandler.sendEmptyMessage(SHARE_SUCCESS);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Message msg = messageHandler.obtainMessage();
                msg.what = SHARE_FAIL;
                String expName = throwable.getClass().getSimpleName();
                if ("WechatClientNotExistException".equals(expName)
                        || "WechatTimelineNotSupportedException".equals(expName)
                        || "WechatFavoriteNotSupportedException".equals(expName)) {
                    msg.arg1 = 1;
                }
                messageHandler.sendMessage(msg);
            }

            @Override
            public void onCancel(Platform platform, int i) {
                messageHandler.sendEmptyMessage(SHARE_CANCEL);
            }
        });
        oks.show(this);
    }

    /***
     * 分享结果处理
     */
    Handler messageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHARE_SUCCESS:
                    //分享成功
                    Toast.makeText(mActivity, R.string.share_success, Toast.LENGTH_SHORT).show();
                    new DB_Share(mActivity).setShareResult(true);
                    break;
                case SHARE_CANCEL:
                    Toast.makeText(mActivity, R.string.share_cancel, Toast.LENGTH_SHORT).show();
                    break;
                case SHARE_FAIL:
                    if (msg.arg1 == 1) {
                        AlertDialogUtil.show(mActivity, getString(R.string.alter_title), getString(R.string.share_weichat_uninstalled));
                    } else {
                        Toast.makeText(mActivity, R.string.share_fail, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };


    /**
     * 引导
     */
    public void isFirstLogin() {
        if (new DB_Data(mActivity).isGuideMode()) {
            new GuideDialog().Step1(mActivity);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_left:
                tabSelect(v);
                break;
            case R.id.bt_right:
                tabSelect(v);
                break;
            case R.id.iv_menu:
                showMenuDialog();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        ShareSDK.stopSDK(this);
        BaseApplication.instance().onTerminate();
        super.onDestroy();
    }
}
