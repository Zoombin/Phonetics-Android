package phonetics.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import phonetics.android.BaseActivity;
import phonetics.android.Constants;
import phonetics.android.R;
import phonetics.android.config.Config;
import phonetics.android.db.DB_Data;
import phonetics.android.entity.ComparEntity;
import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.utils.ClickUtil;
import phonetics.android.utils.DensityUtil;
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

    public List<ComparEntity> comparEntities;
    int index = 0;
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
    RelativeLayout ll_headview1;
    RelativeLayout ll_headview2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_compare);
        initView();
        initHeaderViewHight();
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

        ll_headview1 = (RelativeLayout) findViewById(R.id.ll_headview1);
        ll_headview2 = (RelativeLayout) findViewById(R.id.ll_headview2);

        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_forward).setOnClickListener(this);
        findViewById(R.id.iv_next).setOnClickListener(this);
    }


    public  void initHeaderViewHight(){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)ll_headview1.getLayoutParams();
        params.height = Constants.PTImgHeight;
        ll_headview1.setLayoutParams(params);

        ll_headview2.setLayoutParams(params);
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
            topVoiceEty = comparEntities.get(0).getTopEty();
            bottomVoiceEty = comparEntities.get(0).getBottomEty();

            setData();
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
        if (topVoiceEty != null) {
            String pics = topVoiceEty.getPics_front();
            String[] pic = pics.split(",");
            if (pic != null && pic.length > 0) {
                topFirstPic = FileNameUtil.replace(pic[3]);
                int front_id = getResources().getIdentifier("c" + topFirstPic, "drawable", "phonetics.android");
                top_iv_side.setImageDrawable(getResources().getDrawable(front_id));//
                //ImageLoader.getInstance().displayImage(Config.VOCIEPIC_BASE_PATH + "c" + topFirstPic + Config.IMG_TYPE_JPG,top_iv_side);

                ImageLoader.getInstance().displayImage(Config.SYMBOLPIC_BASE_PATH + topVoiceEty.getImg() + Config.IMG_TYPE_PNG, top_iv_img);
            }
        } else {
            //设置空数据
            top_iv_side.setImageDrawable(null);
            top_iv_front.setImageDrawable(null);
            top_iv_img.setImageDrawable(null);
        }
    }

    /**
     * 底部数据
     */
    private void setBottomData() {
        if (bottomVoiceEty != null) {
            String pics = bottomVoiceEty.getPics_front();
            String[] pic = pics.split(",");
            if (pic != null && pic.length > 0) {
                bottomFirstPic = FileNameUtil.replace(pic[3]);
                //脸部数据
                int front_id = getResources().getIdentifier("c" + bottomFirstPic, "drawable", "phonetics.android");
                bottom_iv_side.setImageDrawable(getResources().getDrawable(front_id));
                //ImageLoader.getInstance().displayImage(Config.VOCIEPIC_BASE_PATH + "c" + bottomFirstPic + Config.IMG_TYPE_JPG,bottom_iv_side);

                //英标名称
                ImageLoader.getInstance().displayImage(Config.SYMBOLPIC_BASE_PATH + bottomVoiceEty.getImg() + Config.IMG_TYPE_PNG, bottom_iv_img);
            }
        } else {
            //设置空数据
            bottom_iv_side.setImageDrawable(null);
            bottom_iv_front.setImageDrawable(null);
            bottom_iv_img.setImageDrawable(null);
        }
    }

    /***
     *
     */
    private void cleanData() {
        setContentView(R.layout.activity_compare);
        initView();
        setViewStatus();

        topVoiceEty = null;
        bottomVoiceEty = null;
    }


    private void saveData() {
        if (comparEntities != null && comparEntities.size() > 0) {
            new DB_Data(mActivity).setComparEntities(comparEntities);
        }
    }


    private void startActivityForResult(int requstCode) {
        startActivityForResult(new Intent(mActivity, PickSymbolActivity.class), requstCode);
    }

    @Override
    public void onClick(View v) {
        if(Constants.isPlaying){
            //正在播放不进行其他操作
            return;
        }
        switch (v.getId()) {
            case R.id.top_iv_img:
                if (ClickUtil.compareTopSelectClick(mActivity)) {
                    startActivityForResult(topRequstCode);
                }
                break;
            case R.id.bottom_iv_img:
                if (ClickUtil.compareBottomSelectClick(mActivity)) {
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
                    int front_id = getResources().getIdentifier(topFirstPic, "drawable", "phonetics.android");
                    top_iv_front.setImageDrawable(getResources().getDrawable(front_id));//
                    //ImageLoader.getInstance().displayImage(Config.VOCIEPIC_BASE_PATH + topFirstPic + Config.IMG_TYPE_JPG,top_iv_front);
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
                    int front_id = getResources().getIdentifier("c" + topFirstPic, "drawable", "phonetics.android");
                    top_iv_side.setImageDrawable(getResources().getDrawable(front_id));//
                    //ImageLoader.getInstance().displayImage(Config.VOCIEPIC_BASE_PATH + "c" + topFirstPic + Config.IMG_TYPE_JPG,top_iv_side);
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
                    int front_id = getResources().getIdentifier(bottomFirstPic, "drawable", "phonetics.android");
                    bottom_iv_front.setImageDrawable(getResources().getDrawable(front_id));
                    //ImageLoader.getInstance().displayImage(Config.VOCIEPIC_BASE_PATH + bottomFirstPic + Config.IMG_TYPE_JPG,bottom_iv_front);
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
                    int front_id = getResources().getIdentifier("c" + bottomFirstPic, "drawable", "phonetics.android");
                    bottom_iv_side.setImageDrawable(getResources().getDrawable(front_id));
                    //ImageLoader.getInstance().displayImage(Config.VOCIEPIC_BASE_PATH + "c" + bottomFirstPic + Config.IMG_TYPE_JPG,bottom_iv_side);
                }
                break;
            case R.id.top_iv_front:
                //顶部正面图片
                if (topVoiceEty != null) {
                    PlayUtil.playAnimation(mActivity, topFaceSide, top_iv_front, topVoiceEty, true, new PlayUtil.LoadListener() {
                        @Override
                        public void complete() {
                            if (needplayMedia(topVoiceEty)) {
                                PlayUtil.playMedia(topVoiceType, topVoiceEty);
                            }
                        }
                    });
                }
                break;
            case R.id.top_iv_side:
                //顶部侧面图片
                if (topVoiceEty != null) {
                    PlayUtil.playAnimation(mActivity, topFaceSide, top_iv_side, topVoiceEty, true, new PlayUtil.LoadListener() {
                        @Override
                        public void complete() {
                            if (needplayMedia(topVoiceEty)) {
                                PlayUtil.playMedia(topVoiceType, topVoiceEty);
                            }
                        }
                    });
                }
                break;
            case R.id.bottom_iv_front:
                //底部正面图片
                if (bottomVoiceEty != null) {
                    PlayUtil.playAnimation(mActivity, bottomFaceSide, bottom_iv_front, bottomVoiceEty, true, new PlayUtil.LoadListener() {
                        @Override
                        public void complete() {
                            if (needplayMedia(bottomVoiceEty)) {
                                PlayUtil.playMedia(bottomVoiceType, bottomVoiceEty);
                            }
                        }
                    });
                }
                break;
            case R.id.bottom_iv_side:
                //底部侧面图片
                if (bottomVoiceEty != null) {
                    PlayUtil.playAnimation(mActivity, bottomFaceSide, bottom_iv_side, bottomVoiceEty, true, new PlayUtil.LoadListener() {
                        @Override
                        public void complete() {
                            if (needplayMedia(bottomVoiceEty)) {
                                PlayUtil.playMedia(bottomVoiceType, bottomVoiceEty);
                            }
                        }
                    });
                }
                break;
            case R.id.iv_back:
                back();
                break;
            case R.id.iv_forward:
                if (ClickUtil.basicClick(mActivity)) {
                    if (comparEntities != null && comparEntities.size() > 0) {
                        if (index > 0) {
                            index = index - 1;
                            topVoiceEty = comparEntities.get(index).getTopEty();
                            bottomVoiceEty = comparEntities.get(index).getBottomEty();
                            setData();
                        }
                    }
                }
                break;
            case R.id.iv_next:
                if (ClickUtil.basicClick(mActivity)) {
                    if (comparEntities != null && comparEntities.size() > 0) {
                        if (index < comparEntities.size() - 1) {
                            index = index + 1;
                            topVoiceEty = comparEntities.get(index).getTopEty();
                            bottomVoiceEty = comparEntities.get(index).getBottomEty();
                            setData();
                        } else {
                            if (comparEntities.get(index).getTopEty() != null && comparEntities.get(index).getBottomEty() != null) {
                                index = index + 1;
                                comparEntities.add(new ComparEntity());
                                cleanData();
                            }
                        }
                    }
                }
                break;
        }

    }

    /**
     * 日本音不用播放音乐
     *
     * @param voiceEty
     * @return
     */
    private boolean needplayMedia(PhoneticsEntity.VoiceEty voiceEty) {
        String name = voiceEty.getName();
        if (("ア".equals(name)) || ("イ".equals(name)) || ("ウ".equals(name)) || ("オ".equals(name)) || ("エ".equals(name))) {
            return false;
        } else {
            return true;
        }
    }

    private void back() {
        saveData();
        finish();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == topRequstCode) {
            //顶部选择回来
            topVoiceEty = (PhoneticsEntity.VoiceEty) data.getSerializableExtra("ety");
            if (comparEntities != null && comparEntities.size() > 0) {
                comparEntities.get(index).setTopEty(topVoiceEty);
            } else {
                //第一次
                ComparEntity comparEntity = new ComparEntity();
                comparEntity.setTopEty(topVoiceEty);
                comparEntities = new ArrayList<>();
                comparEntities.add(comparEntity);
            }
            setTopData();
        }
        if (requestCode == bottomRequstCode) {
            //底部选择回来
            bottomVoiceEty = (PhoneticsEntity.VoiceEty) data.getSerializableExtra("ety");
            if (comparEntities != null && comparEntities.size() > 0) {
                comparEntities.get(index).setBottomEty(bottomVoiceEty);
            } else {
                //第一次
                ComparEntity comparEntity = new ComparEntity();
                comparEntity.setBottomEty(bottomVoiceEty);
                comparEntities = new ArrayList<>();
                comparEntities.add(comparEntity);
            }
            setBottomData();
        }

    }
}
