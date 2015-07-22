package phonetics.android.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import phonetics.android.BaseFragment;
import phonetics.android.R;
import phonetics.android.adapter.DetailsExampleAdapter;
import phonetics.android.adapter.DetailsRecyclerViewHAdapter;
import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.entity.PhoneticsEntity;

/**
 * Created by lsd on 15/7/22.
 */
public class PageExampleFragment extends BaseFragment {
    TextView tv_example_name;
    RecyclerView example_recyclerview;
    ListView example_listview;

    DetailsExampleAdapter adapter;
    DetailsRecyclerViewHAdapter rAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_example, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        setData(CurrentPhonetics.instance().curVoice);
    }

    private void initView() {
        tv_example_name = (TextView) mActivity.findViewById(R.id.tv_example_name);

        //RecyclerView
        example_recyclerview = (RecyclerView) mActivity.findViewById(R.id.example_recyclerview);
        example_recyclerview.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        example_recyclerview.setAdapter(rAdapter = new DetailsRecyclerViewHAdapter(mActivity));

        //ListView
        example_listview = (ListView) mActivity.findViewById(R.id.example_listview);
        example_listview.setAdapter(adapter = new DetailsExampleAdapter(mActivity));
    }

    public void refrashData(PhoneticsEntity.VoiceEty ety) {
        if (tv_example_name != null) {
            setData(ety);
        }
    }

    /**
     * 设置数据
     *
     * @param ety
     */
    private void setData(PhoneticsEntity.VoiceEty ety) {
        if (ety != null) {
            //名称
            tv_example_name.setText(ety.getExamples());

            //例子
            String Examples_yb_name = ety.getExamples_yb_name();
            List<PhoneticsEntity.VoiceEty> vList = new ArrayList<PhoneticsEntity.VoiceEty>();
            String[] Examples_yb_name_array = Examples_yb_name.split(",");
            if (Examples_yb_name_array != null && Examples_yb_name_array.length > 0) {
                List<PhoneticsEntity.VoiceEty> list =  CurrentPhonetics.instance().getCurrentVoiceList();
                for (String string : Examples_yb_name_array){
                    for (PhoneticsEntity.VoiceEty vEty : list){
                        if (string.equals(vEty.getName())){
                            vList.add(vEty);
                            break;
                        }
                    }
                }
                rAdapter.setData(vList);
            }

            //步骤
            int exampleCount = Integer.parseInt(ety.getExamples_count());
            String Examples_yb = ety.getExamples_yb();
            String Examples = ety.getExamples();

            List<String> list = new ArrayList<String>();
            String[] Examples_yb_array = null;
            String[] Example_array = null;
            if (exampleCount > 1) {
                Examples_yb_array = Examples_yb.split("&&");
                Example_array = Examples.split("&&");
                if (Examples_yb_array != null && Example_array != null) {
                    for (int i = 0; i < exampleCount; i++) {
                        String yb = Examples_yb_array[i].replace(",", " ");
                        list.add(Example_array + " /" + yb + "/");
                    }
                }
            } else {
                String yb = Examples_yb.replace(",", " ");
                list.add(Examples + " /" + yb + "/");
            }
            adapter.setData(list);
        }
    }
}
