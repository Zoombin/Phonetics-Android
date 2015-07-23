package phonetics.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import phonetics.android.BaseFragment;
import phonetics.android.R;
import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.entity.PhoneticsEntity;

/**
 * Created by lsd on 15/7/22.
 */
public class PageDescribeFragment extends BaseFragment {
    TextView tv_describe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_describe, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        setData(CurrentPhonetics.instance().getCurrentVoice());
    }

    private void initView() {
        tv_describe = (TextView) mActivity.findViewById(R.id.tv_describe);
    }

    public void refrashData(PhoneticsEntity.VoiceEty ety) {
        if (tv_describe != null) {
            setData(ety);
        }
    }

    private void setData(PhoneticsEntity.VoiceEty ety) {
        if (ety != null) {
            tv_describe.setText(ety.getDescribe());
        }
    }
}
