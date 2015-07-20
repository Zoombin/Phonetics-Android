package phonetics.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import phonetics.android.BaseActivity;
import phonetics.android.R;
import phonetics.android.entity.CurrentPhonetics;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        CurrentPhonetics.instance().loadData(mActivity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(mActivity, MainActivity.class));
                finish();
            }
        }, 600);
    }
}
