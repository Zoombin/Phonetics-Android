package phonetics.android.ui;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import phonetics.android.BaseActivity;
import phonetics.android.R;
import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.utils.AssetsUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(CurrentPhonetics.instance().entity == null){
            CurrentPhonetics.instance().loadData(mActivity);
        }

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView(){

    }

}
