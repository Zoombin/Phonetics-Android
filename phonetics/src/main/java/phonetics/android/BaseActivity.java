package phonetics.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.baidu.mobstat.StatService;

import java.util.List;

import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.utils.AssetsUtil;

public class BaseActivity extends FragmentActivity {
    public Activity mActivity;
    public int SCREEN_Width;
    public int SCREEN_Height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        SCREEN_Width = dm.widthPixels;
        SCREEN_Height = dm.heightPixels;
    }

    @Override
    protected void onResume() {
        StatService.onResume(mActivity);
        super.onResume();
    }

    @Override
    protected void onPause() {
        StatService.onPause (mActivity);
        super.onPause();
    }
}
