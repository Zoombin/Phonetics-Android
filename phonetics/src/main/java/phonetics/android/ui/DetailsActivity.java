package phonetics.android.ui;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;

import phonetics.android.BaseActivity;
import phonetics.android.R;
import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.fragment.PageBasicFragment;
import phonetics.android.fragment.PageExampleFragment;
import phonetics.android.fragment.PageSimilarFragment;
import phonetics.android.fragment.PageDescribeFragment;
import phonetics.android.utils.AnimationUtil;

public class DetailsActivity extends BaseActivity implements OnClickListener {
    PhoneticsEntity.VoiceEty ety;
    TextView bt_front, bt_side;
    ImageView iv_img;
    Button bt_voice;

    ImageView iv_front, iv_side;
    ImageView iv_back,iv_forward,iv_next;
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
        initButtonStatus();
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
        (iv_forward = (ImageView) findViewById(R.id.iv_forward)).setOnClickListener(this);
        (iv_next = (ImageView) findViewById(R.id.iv_next)).setOnClickListener(this);

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
     * 初始化底部按钮的状态
     */
    private void initButtonStatus(){
        if(!CurrentPhonetics.instance().canForward)
            iv_forward.setVisibility(View.INVISIBLE);
        else
            iv_forward.setVisibility(View.VISIBLE);
        if (!CurrentPhonetics.instance().canNext)
            iv_next.setVisibility(View.INVISIBLE);
        else
            iv_next.setVisibility(View.VISIBLE);
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
        ety = CurrentPhonetics.instance().getCurrentVoice();

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
            ImageLoader.getInstance().displayImage("assets://voicepic/" + "n0" + ".jpg", iv_front);
            ImageLoader.getInstance().displayImage("assets://voicepic/" + "c" + "e1" + ".jpg", iv_side);
        }
    }

    /***
     * 正面/侧面切换
     * @param v
     */
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

    /***
     * 选择fragment
     * @param v
     */
    private void pageSelect(View v) {
        tv_basic.setSelected(false);
        tv_typejp.setSelected(false);
        tv_example.setSelected(false);
        tv_similar.setSelected(false);
        v.setSelected(true);

        switch (v.getId()) {
            case R.id.tv_basic:
                index = 0;
                break;
            case R.id.tv_typejp:
                index = 1;
                break;
            case R.id.tv_example:
                index = 2;
                break;
            case R.id.tv_similar:
                index = 3;
                break;
        }
        pageChange();
    }

    /***
     * 刷新数据
     * @param v
     */
    private void reLoadData(View v){
        PhoneticsEntity.VoiceEty ety = null;
        switch (v.getId()) {
            case R.id.iv_forward:
                //上一个
                ety = CurrentPhonetics.instance().getForwardVoice();
                break;
            case R.id.iv_next:
                //下一个
                ety = CurrentPhonetics.instance().getNextVoice();
                break;
        }
        if(ety != null){
            pageSelect(tv_basic);

            setData();

            baseFragment.refrashData(ety);
            describeFragment.refrashData(ety);
            exampleFragment.refrashData(ety);
            similarFragment.refrashData(ety);

            initButtonStatus();
        }
    }


    /**
     * 图片点击 播放动画
     * @param v
     */
    private void faceClick(View v) {
        switch (v.getId()) {
            case R.id.iv_front:
                String[] resouce = new String[]{"n0","n1","n2","n3","n4","n5","n6"};
                AnimationUtil.startAnimation(mActivity,(ImageView)v,resouce,400);
                break;
            case R.id.iv_side:
                String[] resouce1 = new String[]{"ce1","ce2","ce3","ce4","ce5","ce6"};
                AnimationUtil.startAnimation(mActivity,(ImageView)v,resouce1,400);
                break;
        }
    }

    /***
     * fragment切换
     */
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
            case R.id.iv_front:
            case R.id.iv_side:
                faceClick(v);
                break;
            case R.id.iv_forward:
            case R.id.iv_next:
                reLoadData(v);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
