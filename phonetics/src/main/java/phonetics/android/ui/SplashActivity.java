package phonetics.android.ui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import phonetics.android.BaseActivity;
import phonetics.android.R;
import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.utils.MediaPlayerUtil;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(mActivity, MainActivity.class));
                finish();
            }
        }, 800);
    }
}
