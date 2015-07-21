package phonetics.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import phonetics.android.BaseFragment;
import phonetics.android.R;

/**
 * Created by lsd on 15/7/22.
 */
public class PageBaseFragment extends BaseFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base,null);
        initView(view);
        return view;
    }

    private void initView(View view){

    }
}
