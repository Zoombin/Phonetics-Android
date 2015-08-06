package phonetics.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import phonetics.android.BaseActivity;
import phonetics.android.R;
import phonetics.android.config.Config;
import phonetics.android.db.DB_Data;
import phonetics.android.entity.ComparEntity;
import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.utils.ClickUtil;
import phonetics.android.utils.FileNameUtil;
import phonetics.android.utils.PlayUtil;

/**
 * Created by lsd on 15/7/26.
 */
public class CompareActivity extends BaseActivity implements View.OnClickListener {
    public int topVoiceType;// 0：男声   1：女声
    public int bottomVoiceType;// 0：男声   1：女声

    public int topFaceSide;//0：正面  1：侧面
    public int bottomFaceSide;//0：正面  1：侧面

    public int topRequstCode = 100;
    public int bottomRequstCode = 200;

    boolean selectBack = false;
    boolean isNew = false;
    public int etyIndex = 0;
    public List<ComparEntity> comparEntities;
    public ComparEntity comparEntity;
    public PhoneticsEntity.VoiceEty topVoiceEty;//顶部音标
    public PhoneticsEntity.VoiceEty bottomVoiceEty;//底部音标

    public String topFirstPic;//顶部默认显示的第一张图片
    public String bottomFirstPic;//底部部默认显示的第一张图片

    ImageView top_iv_front;
    ImageView top_iv_side;
    ImageView top_iv_img;
    Button top_bt_voice;
    TextView top_bt_front;
    TextView top_bt_side;

    ImageView bottom_iv_front;
    ImageView bottom_iv_side;
    ImageView bottom_iv_img;
    Button bottom_bt_voice;
    TextView bottom_bt_front;
    TextView bottom_bt_side;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_compare);
        initView();
        setViewStatus();
        loadData();
    }

    private void initView() {
        (top_iv_front = (ImageView) findViewById(R.id.top_iv_front)).setOnClickListener(this);
        (top_iv_side = (ImageView) findViewById(R.id.top_iv_side)).setOnClickListener(this);
        (top_iv_img = (ImageView) findViewById(R.id.top_iv_img)).setOnClickListener(this);
        (top_bt_voice = (Button) findViewById(R.id.top_bt_voice)).setOnClickListener(this);
        (top_bt_front = (TextView) findViewById(R.id.top_bt_front)).setOnClickListener(this);
        (top_bt_side = (TextView) findViewById(R.id.top_bt_side)).setOnClickListener(this);

        (bottom_iv_front = (ImageView) findViewById(R.id.bottom_iv_front)).setOnClickListener(this);
        (bottom_iv_side = (ImageView) findViewById(R.id.bottom_iv_side)).setOnClickListener(this);
        (bottom_iv_img = (ImageView) findViewById(R.id.bottom_iv_img)).setOnClickListener(this);
        (bottom_bt_voice = (Button) findViewById(R.id.bottom_bt_voice)).setOnClickListener(this);
        (bottom_bt_front = (TextView) findViewById(R.id.bottom_bt_front)).setOnClickListener(this);
        (bottom_bt_side = (TextView) findViewById(R.id.bottom_bt_side)).setOnClickListener(this);

        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_forward).setOnClickListener(this);
        findViewById(R.id.iv_next).setOnClickListener(this);
    }

    private void setViewStatus() {
        top_bt_voice.setSelected(true);//顶部男声
        topVoiceType = 0;

        bottom_bt_voice.setSelected(true);//底部男声
        bottomVoiceType = 0;

        top_bt_front.setSelected(false);
        top_bt_side.setSelected(true);
        top_iv_front.setVisibility(View.GONE);
        top_iv_side.setVisibility(View.VISIBLE);//顶部显示侧面
        topFaceSide = 1;


        bottom_bt_front.setSelected(false);
        bottom_bt_side.setSelected(true);
        bottom_iv_front.setVisibility(View.GONE);
        bottom_iv_side.setVisibility(View.VISIBLE);//底部显示侧面
        bottomFaceSide = 1;
    }

    private void loadData() {
        comparEntities = new DB_Data(mActivity).getComparEntities();
        if (comparEntities != null && comparEntities.size() > 0) {
            etyIndex = 0;
            comparEntity = comparEntities.get(etyIndex);
            topVoiceEty = comparEntity.getTopEty();
            bottomVoiceEty = comparEntity.getBottomEty();

            setData();
        } else {
            isNew = true;
            comparEntities = new ArrayList<>();
            comparEntity = new ComparEntity();
        }
    }


    private void setData() {
        setTopData();
        setBottomData();
    }


    /**
     * 顶部的数据
     */
    private void setTopData() {
        String pics = topVoiceEty.getPics_front();
        String[] pic = pics.split(",");
        if (pic != null && pic.length > 0) {
            topFirstPic = FileNameUtil.replace(pic[0]);
            //int front_id = getResources().getIdentifier("c" + topFirstPic, "drawable", "phonetics.android");
            //top_iv_side.setImageDrawable(getResources().getDrawable(front_id));//
            ImageLoader.getInstance().displayImage(Config.VOCIEPIC_BASE_PATH + "c" + topFirstPic + Config.IMG_TYPE_JPG,top_iv_side);

            ImageLoader.getInstance().displayImage(Config.SYMBOLPIC_BASE_PATH + topVoiceEty.getImg() + Config.IMG_TYPE_PNG, top_iv_img);
        }
    }

    /**
     * 底部数据
     */
    private void setBottomData() {
        String pics = bottomVoiceEty.getPics_front();
        String[] pic = pics.split(",");
        if (pic != null && pic.length > 0) {
            bottomFirstPic = FileNameUtil.replace(pic[0]);
            //脸部数据
            //int front_id = getResources().getIdentifier("c" + bottomFirstPic, "drawable", "phonetics.android");
            //bottom_iv_side.setImageDrawable(getResources().getDrawable(front_id));
            ImageLoader.getInstance().displayImage(Config.VOCIEPIC_BASE_PATH + "c" + bottomFirstPic + Config.IMG_TYPE_JPG,bottom_iv_side);

            //英标名称
            ImageLoader.getInstance().displayImage(Config.SYMBOLPIC_BASE_PATH + bottomVoiceEty.getImg() + Config.IMG_TYPE_PNG, bottom_iv_img);
        }
    }

    /***
     *
     */
    private void cleanData() {
        setContentView(R.layout.activity_compare);
        initView();
        setViewStatus();

        isNew = true;
        topVoiceEty = null;
        bottomVoiceEty = null;
    }


    private void saveData(){
        if (isNew){
            if (topVoiceEty != null && bottomVoiceEty != null) {
                comparEntity.setBottomEty(bottomVoiceEty);
                comparEntity.setTopEty(topVoiceEty);
                comparEntities.add(comparEntity);
                new DB_Data(mActivity).setComparEntitie(comparEntity);
            }
        }else{
            if (selectBack){
                comparEntity.setBottomEty(bottomVoiceEty);
                comparEntity.setTopEty(topVoiceEty);
                if(etyIndex >0){
                    comparEntities.set(etyIndex,comparEntity);
                    new DB_Data(mActivity).changeComparEntitie(etyIndex, comparEntity);
                }else{
                    comparEntities.add(comparEntity);
                    new DB_Data(mActivity).setComparEntitie(comparEntity);
                }
            }
        }

        isNew = false;
        selectBack = false;
    }


    private void startActivityForResult(int requstCode) {
        startActivityForResult(new Intent(mActivity, PickSymbolActivity.class), requstCode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_iv_img:
                if(ClickUtil.compareTopSelectClick(mActivity)){
                    startActivityForResult(topRequstCode);
                }
                break;
            case R.id.bottom_iv_img:
                if(ClickUtil.compareBottomSelectClick(mActivity)){
                    startActivityForResult(bottomRequstCode);
                }
                break;
            case R.id.top_bt_voice:
                //顶部切换男女声音按钮
                if (v.isSelected()) {
                    v.setSelected(false);
                    topVoiceType = 1;
                } else {
                    top_bt_voice.setSelected(true);
                    topVoiceType = 0;
                }
                break;
            case R.id.bottom_bt_voice:
                //底部切换男女声音
                if (v.isSelected()) {
                    v.setSelected(false);
                    bottomVoiceType = 1;
                } else {
                    bottom_bt_voice.setSelected(true);
                    bottomVoiceType = 0;
                }
                break;
            case R.id.top_bt_front:
                //顶部正面按钮
                topFaceSide = 0;
                v.setSelected(true);
                top_bt_side.setSelected(false);

                top_iv_front.setVisibility(View.VISIBLE);//顶部显示正面
                top_iv_side.setVisibility(View.GONE);

                if (!TextUtils.isEmpty(topFirstPic)) {
                    //int front_id = getResources().getIdentifier(topFirstPic, "drawable", "phonetics.android");
                    //top_iv_front.setImageDrawable(getResources().getDrawable(front_id));//
                    ImageLoader.getInstance().displayImage(Config.VOCIEPIC_BASE_PATH + topFirstPic + Config.IMG_TYPE_JPG,top_iv_front);
                }
                break;
            case R.id.top_bt_side:
                //顶部侧面按钮
                topFaceSide = 1;
                v.setSelected(true);
                top_bt_front.setSelected(false);

                top_iv_front.setVisibility(View.GONE);
                top_iv_side.setVisibility(View.VISIBLE);//顶部显示侧面

                if (!TextUtils.isEmpty(topFirstPic)) {
                    //int front_id = getResources().getIdentifier("c" + topFirstPic, "drawable", "phonetics.android");
                    //top_iv_side.setImageDrawable(getResources().getDrawable(front_id));//
                    ImageLoader.getInstance().displayImage(Config.VOCIEPIC_BASE_PATH + "c" + topFirstPic + Config.IMG_TYPE_JPG,top_iv_side);
                }
                break;
            case R.id.bottom_bt_front:
                //底部正面按钮
                bottomFaceSide = 0;
                v.setSelected(true);
                bottom_bt_side.setSelected(false);

                bottom_iv_front.setVisibility(View.VISIBLE);//底部显示正面
                bottom_iv_side.setVisibility(View.GONE);

                if (!TextUtils.isEmpty(bottomFirstPic)) {
                    //int front_id = getResources().getIdentifier(bottomFirstPic, "drawable", "phonetics.android");
                    //bottom_iv_front.setImageDrawable(getResources().getDrawable(front_id));
                    ImageLoader.getInstance().displayImage(Config.VOCIEPIC_BASE_PATH + bottomFirstPic + Config.IMG_TYPE_JPG,bottom_iv_front);
                }
                break;
            case R.id.bottom_bt_side:
                //d底部侧面按钮
                bottomFaceSide = 1;
                v.setSelected(true);
                bottom_bt_front.setSelected(false);

                bottom_iv_front.setVisibility(View.GONE);
                bottom_iv_side.setVisibility(View.VISIBLE);//底部显示侧面

                if (!TextUtils.isEmpty(bottomFirstPic)) {
                    //int front_id = getResources().getIdentifier("c" + bottomFirstPic, "drawable", "phonetics.android");
                    //bottom_iv_side.setImageDrawable(getResources().getDrawable(front_id));
                    ImageLoader.getInstance().displayImage(Config.VOCIEPIC_BASE_PATH + "c" + bottomFirstPic + Config.IMG_TYPE_JPG,bottom_iv_side);
                }
                break;
            case R.id.top_iv_front:
                //顶部正面图片
                if (topVoiceEty != null) {
                    PlayUtil.playAnimation(mActivity, topFaceSide, top_iv_front, topVoiceEty,true);
                    if (needplayMedia(topVoiceEty)){
                        PlayUtil.playMedia(topVoiceType, topVoiceEty);
                    }
                }
                break;
            case R.id.top_iv_side:
                //顶部侧面图片
                if (topVoiceEty != null) {
                    PlayUtil.playAnimation(mActivity, topFaceSide, top_iv_side, topVoiceEty,true);
                    if (needplayMedia(topVoiceEty)){
                        PlayUtil.playMedia(topVoiceType, topVoiceEty);
                    }
                }
                break;
            case R.id.bottom_iv_front:
                //底部正面图片
                if (bottomVoiceEty != null) {
                    PlayUtil.playAnimation(mActivity, bottomFaceSide, bottom_iv_front, bottomVoiceEty,true);
                    if (needplayMedia(bottomVoiceEty)){
                        PlayUtil.playMedia(bottomVoiceType, bottomVoiceEty);
                    }
                }
                break;
            case R.id.bottom_iv_side:
                //底部侧面图片
                if (bottomVoiceEty != null) {
                    PlayUtil.playAnimation(mActivity, bottomFaceSide, bottom_iv_side, bottomVoiceEty,true);
                    if (needplayMedia(bottomVoiceEty)){
                        PlayUtil.playMedia(bottomVoiceType, bottomVoiceEty);
                    }
                }
                break;
            case R.id.iv_back:
                saveData();
                finish();
                break;
            case R.id.iv_forward:
                //if (ClickUtil.basicClick(mActivity)){
                    saveData();
                    if (comparEntities != null && comparEntities.size() > 0) {
                        if (etyIndex > 0) {
                            etyIndex = etyIndex - 1;
                        }
                        comparEntity = comparEntities.get(etyIndex);
                        topVoiceEty = comparEntity.getTopEty();
                        bottomVoiceEty = comparEntity.getBottomEty();
                    }
                    setData();
                //}
                break;
            case R.id.iv_next:
               // if (ClickUtil.basicClick(mActivity)){
                    saveData();
                    if (comparEntities != null && comparEntities.size() > 0) {
                        if (etyIndex < comparEntities.size()- 1){
                            etyIndex = etyIndex + 1;

                            comparEntity = comparEntities.get(etyIndex);
                            topVoiceEty = comparEntity.getTopEty();
                            bottomVoiceEty = comparEntity.getBottomEty();

                            setData();
                        }else{
                            cleanData();
                        }
                    } else {
                        cleanData();
                    }
                //}
                break;
        }

    }

    /***
     * 日本音不用播放音乐
     *
     * @param voiceEty
     * @return
     */
    private boolean needplayMedia(PhoneticsEntity.VoiceEty voiceEty){
        String name = voiceEty.getName();
        if(("ア".equals(name))|| ("イ".equals(name)) || ("ウ".equals(name))|| ("オ".equals(name)) || ("エ".equals(name))){
            return false;
        }else{
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        selectBack = true;
        if (requestCode == topRequstCode) {
            //顶部选择回来
            topVoiceEty = (PhoneticsEntity.VoiceEty) data.getSerializableExtra("ety");
            comparEntity.setTopEty(topVoiceEty);
            setTopData();
        }
        if (requestCode == bottomRequstCode) {
            //底部选择回来
            bottomVoiceEty = (PhoneticsEntity.VoiceEty) data.getSerializableExtra("ety");
            comparEntity.setBottomEty(bottomVoiceEty);
            setBottomData();
        }

    }
}
