package phonetics.android.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import phonetics.android.BaseActivity;
import phonetics.android.R;

/**
 * Created by lsd on 15/7/26.
 */
public class CompareActivity extends BaseActivity {
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
    }

    private void initView() {
        top_iv_front = (ImageView) findViewById(R.id.top_iv_front);
        top_iv_side = (ImageView) findViewById(R.id.top_iv_side);
        top_iv_img = (ImageView) findViewById(R.id.top_iv_img);
        top_bt_voice = (Button) findViewById(R.id.top_bt_voice);
        top_bt_front = (TextView) findViewById(R.id.top_bt_front);
        top_bt_side = (TextView) findViewById(R.id.top_bt_side);

        bottom_iv_front = (ImageView) findViewById(R.id.bottom_iv_front);
        bottom_iv_side = (ImageView) findViewById(R.id.bottom_iv_side);
        bottom_iv_img = (ImageView) findViewById(R.id.bottom_iv_img);
        bottom_bt_voice = (Button) findViewById(R.id.bottom_bt_voice);
        bottom_bt_front = (TextView) findViewById(R.id.bottom_bt_front);
        bottom_bt_side = (TextView) findViewById(R.id.bottom_bt_side);
    }
}
