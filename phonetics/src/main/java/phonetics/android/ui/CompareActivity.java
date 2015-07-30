package phonetics.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import phonetics.android.BaseActivity;
import phonetics.android.R;
import phonetics.android.config.Config;
import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.utils.FileNameUtil;
import phonetics.android.utils.PlayUtil;

/**
 * Created by lsd on 15/7/26.
 */
public class CompareActivity extends BaseActivity implements View.OnClickListener {
    public int topVoice;// 0：男声   1：女声
    public int bottomVoice;// 0：男声   1：女声

    public int topFaceSide;//0：正面  1：侧面
    public int bottomFaceSide;//0：正面  1：侧面

    public int topRequstCode = 100;
    public int bottomRequstCode = 200;

    public PhoneticsEntity.VoiceEty topVioce;//顶部音标
    public PhoneticsEntity.VoiceEty bottomVioce;//底部音标

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
        topVoice = 0;

        bottom_bt_voice.setSelected(true);//底部男声
        bottomVoice = 0;

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


    private void startActivityForResult(int requstCode) {
        startActivityForResult(new Intent(mActivity, PickSymbolActivity.class), requstCode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_iv_img:
                startActivityForResult(topRequstCode);
                break;
            case R.id.bottom_iv_img:
                startActivityForResult(bottomRequstCode);
                break;
            case R.id.top_bt_voice:
                //顶部切换男女声音按钮
                if (v.isSelected()){
                    v.setSelected(false);
                    topVoice = 1;
                }else{
                    top_bt_voice.setSelected(true);
                    topVoice = 0;
                }
                break;
            case R.id.bottom_bt_voice:
                //底部切换男女声音
                if (v.isSelected()){
                    v.setSelected(false);
                    bottomVoice = 1;
                }else{
                    bottom_bt_voice.setSelected(true);
                    bottomVoice = 0;
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
                    int front_id = getResources().getIdentifier(bottomFirstPic,"drawable", "phonetics.android");
                    bottom_iv_front.setImageDrawable(getResources().getDrawable(front_id));
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
                    int front_id = getResources().getIdentifier("c"+bottomFirstPic,"drawable", "phonetics.android");
                    bottom_iv_side.setImageDrawable(getResources().getDrawable(front_id));
                }
                break;
            case R.id.top_iv_front:
                //顶部正面图片
                if (topVioce != null){
                    PlayUtil.playAnimation(mActivity, topFaceSide, top_iv_front, topVioce);
                    PlayUtil.playMedia(topVoice, topVioce);
                }
                break;
            case R.id.top_iv_side:
                //顶部侧面图片
                if (topVioce != null){
                    PlayUtil.playAnimation(mActivity, topFaceSide, top_iv_side, topVioce);
                    PlayUtil.playMedia(topVoice, topVioce);
                }
                break;
            case R.id.bottom_iv_front:
                //底部正面图片
                if (bottomVioce != null){
                    PlayUtil.playAnimation(mActivity, bottomFaceSide, bottom_iv_front, bottomVioce);
                    PlayUtil.playMedia(bottomVoice, bottomVioce);
                }
                break;
            case R.id.bottom_iv_side:
                //底部侧面图片
                if (bottomVioce != null){
                    PlayUtil.playAnimation(mActivity, bottomFaceSide, bottom_iv_side, bottomVioce);
                    PlayUtil.playMedia(bottomVoice, bottomVioce);
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_forward:
                finish();
                break;
            case R.id.iv_next:
                startActivity(new Intent(mActivity,CompareActivity.class));
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == topRequstCode) {
            //顶部选择回来
            topVioce = (PhoneticsEntity.VoiceEty) data.getSerializableExtra("ety");
            String pics = topVioce.getPics_front();
            String[] pic = pics.split(",");
            if (pic != null && pic.length > 0) {
                topFirstPic = FileNameUtil.replace(pic[0]);
                int front_id = getResources().getIdentifier("c"+topFirstPic,"drawable", "phonetics.android");
                top_iv_side.setImageDrawable(getResources().getDrawable(front_id));//

                ImageLoader.getInstance().displayImage(Config.SYMBOLPIC_BASE_PATH + topVioce.getImg() + Config.IMG_TYPE_PNG, top_iv_img);
            }
        }
        if (requestCode == bottomRequstCode) {
            //底部选择回来
            bottomVioce = (PhoneticsEntity.VoiceEty) data.getSerializableExtra("ety");
            String pics = bottomVioce.getPics_front();
            String[] pic = pics.split(",");
            if (pic != null && pic.length > 0) {
                bottomFirstPic = FileNameUtil.replace(pic[0]);
                int front_id = getResources().getIdentifier("c"+bottomFirstPic,"drawable", "phonetics.android");
                bottom_iv_side.setImageDrawable(getResources().getDrawable(front_id));

                ImageLoader.getInstance().displayImage(Config.SYMBOLPIC_BASE_PATH + bottomVioce.getImg() + Config.IMG_TYPE_PNG, bottom_iv_img);
            }
        }

    }
}
