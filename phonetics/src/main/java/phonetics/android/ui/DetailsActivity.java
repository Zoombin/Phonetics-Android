package phonetics.android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import phonetics.android.BaseActivity;
import phonetics.android.R;
import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.fragment.PageBasicFragment;
import phonetics.android.fragment.PageExampleFragment;
import phonetics.android.fragment.PageSimilarFragment;
import phonetics.android.fragment.PageDescribeFragment;

public class DetailsActivity extends BaseActivity implements OnClickListener {
    PhoneticsEntity.VoiceEty ety;
    TextView bt_front, bt_side;
    ImageView iv_img;
    Button bt_voice;

    ImageView iv_front, iv_side;
    ImageView iv_back;
    TextView tv_basic, tv_typejp, tv_example, tv_similar;

    int currentTabIndex;
    Fragment[] fragments;
    int index;

    PageBasicFragment baseFragment;
    PageDescribeFragment describeFragment;
    PageExampleFragment exampleFragment;
    PageSimilarFragment similarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initView();
        initFragment();
        initPageStatus();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setData();
    }

    private void initView() {
        (bt_front = (TextView) findViewById(R.id.bt_front)).setOnClickListener(this);
        (bt_side = (TextView) findViewById(R.id.bt_side)).setOnClickListener(this);
        bt_side.setSelected(true);

        (iv_img = (ImageView) findViewById(R.id.iv_img)).setOnClickListener(this);
        (bt_voice = (Button) findViewById(R.id.bt_voice)).setOnClickListener(this);
        bt_voice.setSelected(true);

        (iv_front = (ImageView) findViewById(R.id.iv_front)).setOnClickListener(this);
        iv_front.setVisibility(View.GONE);
        (iv_side = (ImageView) findViewById(R.id.iv_side)).setOnClickListener(this);
        (iv_back = (ImageView) findViewById(R.id.iv_back)).setOnClickListener(this);

        (tv_basic = (TextView) findViewById(R.id.tv_basic)).setOnClickListener(this);
        (tv_typejp = (TextView) findViewById(R.id.tv_typejp)).setOnClickListener(this);
        (tv_example = (TextView) findViewById(R.id.tv_example)).setOnClickListener(this);
        (tv_similar = (TextView) findViewById(R.id.tv_similar)).setOnClickListener(this);
    }

    /***
     * 初始化Fragment
     */
    private void initFragment() {
        fragments = new Fragment[4];
        fragments[0] = baseFragment = new PageBasicFragment();
        fragments[1] = describeFragment = new PageDescribeFragment();
        fragments[2] = exampleFragment = new PageExampleFragment();
        fragments[3] = similarFragment = new PageSimilarFragment();
    }

    /***
     * 设置基础与高级的显示
     */
    private void initPageStatus(){
        if (CurrentPhonetics.VoiceType.BASIC == CurrentPhonetics.instance().voiceType){
            //基础
            index = 0;
            currentTabIndex =0;
            tv_basic.setSelected(true);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragments[0]).show(fragments[0])
                    .commit();
        }else{
            //高级
            index = 1;
            currentTabIndex = 1;
            tv_basic.setVisibility(View.GONE);
            tv_similar.setVisibility(View.GONE);
            tv_typejp.setText(R.string.bt_txt_describe);
            tv_typejp.setSelected(true);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragments[1]).show(fragments[1])
                    .commit();
        }

    }

    private void setData() {
        ety = CurrentPhonetics.instance().curVoice;

        setImgData();
        setFaceData();
    }

    /**
     * 音标数据
     */
    private void setImgData() {
        ImageLoader.getInstance().displayImage("assets://symbol/" + ety.getImg() + ".png", iv_img);
    }

    /**
     * 脸型数据
     */
    private void setFaceData() {
        String pics = ety.getExamples_pics();
        String[] pic = pics.split(",");

        if (pic != null && pic.length > 0) {
            ImageLoader.getInstance().displayImage("assets://voicepic/" + pic[0] + ".jpg", iv_front);
            ImageLoader.getInstance().displayImage("assets://voicepic/" + "c" + pic[0] + ".jpg", iv_side);
        }
    }

    private void tabSelect(View v) {
        bt_front.setSelected(false);
        bt_side.setSelected(false);
        v.setSelected(true);

        iv_front.setVisibility(View.GONE);
        iv_side.setVisibility(View.GONE);

        PhoneticsEntity entity = CurrentPhonetics.instance().entity;
        switch (v.getId()) {
            case R.id.bt_front:
                iv_front.setVisibility(View.VISIBLE);
                break;
            case R.id.bt_side:
                iv_side.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void pageSelect(View v) {
        tv_basic.setSelected(false);
        tv_typejp.setSelected(false);
        tv_example.setSelected(false);
        tv_similar.setSelected(false);
        v.setSelected(true);

        switch (v.getId()) {
            case R.id.tv_basic:
                index = 0;
                pageChange();
                break;
            case R.id.tv_typejp:
                index = 1;
                pageChange();
                break;
            case R.id.tv_example:
                index = 2;
                pageChange();
                break;
            case R.id.tv_similar:
                index = 3;
                pageChange();
                break;
        }
    }

    private void pageChange() {
        if (currentTabIndex != index) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            transaction.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                transaction.add(R.id.fragment_container, fragments[index]);
            }
            transaction.show(fragments[index]).commit();
        }
        currentTabIndex = index;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_front:
            case R.id.bt_side:
                tabSelect(v);
                break;
            case R.id.tv_basic:
            case R.id.tv_typejp:
            case R.id.tv_example:
            case R.id.tv_similar:
                pageSelect(v);
                break;
            case R.id.iv_img:
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
