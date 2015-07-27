package phonetics.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import cn.sharesdk.framework.ShareSDK;
import phonetics.android.BaseActivity;
import phonetics.android.BaseApplication;
import phonetics.android.R;
import phonetics.android.adapter.PhoneticsAdapter;
import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.view.ShareDialog;
import phonetics.android.widget.CustomDialog;

public class MainActivity extends BaseActivity implements OnClickListener {
    TextView bt_left, bt_right;
    ImageView iv_menu;
    PhoneticsAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        if (CurrentPhonetics.instance().entity == null) {
            CurrentPhonetics.instance().loadData(mActivity);
        }

        setData();

        ShareSDK.initSDK(mActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        bt_right.setSelected(false);
        bt_left.setSelected(false);
        v.setSelected(true);

        PhoneticsEntity entity = CurrentPhonetics.instance().entity;
        switch (v.getId()) {
            case R.id.bt_left:
                CurrentPhonetics.instance().voiceType = CurrentPhonetics.VoiceType.BASIC;//当前类型 基本
                adapter.setData(entity.getBasics());
                break;
            case R.id.bt_right:
                CurrentPhonetics.instance().voiceType = CurrentPhonetics.VoiceType.ADVANCE;//当前类型 高级
                adapter.setData(entity.getAdvanced());
                break;
        }
    }

    private void showMenuDialog() {
        final CustomDialog dialog = CustomDialog.create(mActivity, R.style.customDialogOne);
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_menu_dialog, null);
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
                startActivity(new Intent(mActivity, CompareActivity.class));
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_item_share).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareDialog(mActivity);
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
        BaseApplication.instance().onTerminate();
        super.onDestroy();
    }
}
