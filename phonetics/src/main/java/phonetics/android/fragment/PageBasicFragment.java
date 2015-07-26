package phonetics.android.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import phonetics.android.BaseFragment;
import phonetics.android.R;
import phonetics.android.adapter.DetailsBasicAdapter;
import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.entity.StepEntity;

/**
 * Created by lsd on 15/7/22.
 */
public class PageBasicFragment extends BaseFragment{
    ListView listView;
    DetailsBasicAdapter adapter;

    PhoneticsEntity.VoiceEty thisEty;

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
            thisEty = ety;
            String step_count = ety.getStep_count();
            int count = 0;
            try {
                count = Integer.parseInt(step_count);
            }catch (Exception e){}
            if (count >0){
                String[] describeArray = ety.getStep_describes().split("&&");
                String[] typesArray = ety.getStep_types().split(",");
                String[] voicesArray = ety.getStep_voices().split("&&");
                String[] picsArray = ety.getStep_pics().split("&&");
                List<StepEntity> list = new ArrayList<>();
                for (int i=0;i<count ;i++){
                    StepEntity stepEntity = new StepEntity();
                    stepEntity.setDescribes(describeArray[i]);
                    stepEntity.setPics(picsArray[i]);
                    stepEntity.setTypes(typesArray[i]);
                    stepEntity.setVoices(voicesArray[i]);
                    list.add(stepEntity);
                }
                adapter.setData(list);
            }
        }
    }
}
