package phonetics.android.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import phonetics.android.BaseFragment;
import phonetics.android.R;
import phonetics.android.adapter.DetailsBasicAdapter;
import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.entity.PhoneticsEntity;

/**
 * Created by lsd on 15/7/22.
 */
public class PageBasicFragment extends BaseFragment {
    ListView listView;
    DetailsBasicAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basic, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        setData(CurrentPhonetics.instance().getCurrentVoice());
    }

    private void initView() {
        listView = (ListView) mActivity.findViewById(R.id.listview);
        listView.setAdapter(adapter = new DetailsBasicAdapter(mActivity));
    }

    public void refrashData(PhoneticsEntity.VoiceEty ety) {
        if (listView != null) {
            setData(ety);
        }
    }

    private void setData(PhoneticsEntity.VoiceEty ety) {
        if (ety != null) {
            String describes = ety.getStep_describes();
            String[] describe = describes.split("&&");
            if (describe != null && describe.length > 0) {
                adapter.setData(describe);
            }
        }
    }
}
