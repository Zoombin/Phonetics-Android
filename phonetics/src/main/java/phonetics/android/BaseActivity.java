package phonetics.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.utils.AssetsUtil;

public class BaseActivity extends FragmentActivity {
    public Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
    }
}
