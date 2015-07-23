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
public class PageSimilarFragment extends BaseFragment {
    TextView tv_similar_name;
    RecyclerView similar_recyclerview;
    ListView similar_listview;

    DetailsExampleAdapter adapter;
    DetailsRecyclerViewHAdapter rAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_similar,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        setData(CurrentPhonetics.instance().getCurrentVoice());
    }

    private void initView() {
        tv_similar_name = (TextView) mActivity.findViewById(R.id.tv_similar_name);

        //RecyclerView
        similar_recyclerview = (RecyclerView) mActivity.findViewById(R.id.similar_recyclerview);
        similar_recyclerview.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        similar_recyclerview.setAdapter(rAdapter = new DetailsRecyclerViewHAdapter(mActivity));

        //ListView
        similar_listview = (ListView) mActivity.findViewById(R.id.similar_listview);
        similar_listview.setAdapter(adapter = new DetailsExampleAdapter(mActivity));
    }

    public void refrashData(PhoneticsEntity.VoiceEty ety) {
        if (tv_similar_name != null) {
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
            tv_similar_name.setText(ety.getSimilar().replace(","," "));

            //例子
            String Similar_yb_name = ety.getSimilar_yb_name();
            List<PhoneticsEntity.VoiceEty> vList = new ArrayList<>();
            String[] Similar_yb_name_array = Similar_yb_name.split(",");
            if (Similar_yb_name_array != null && Similar_yb_name_array.length > 0) {
                List<PhoneticsEntity.VoiceEty> list =  CurrentPhonetics.instance().getCurrentVoiceList();
                for (String string : Similar_yb_name_array){
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
            int SimilarCount = Integer.parseInt(ety.getSimilar_count());
            String Similar_yb = ety.getSimilar_yb();
            String Similars = ety.getSimilar();

            List<String> list = new ArrayList<String>();
            String[] Similar_yb_array = null;
            String[] Similar_array = null;
            if (SimilarCount > 1) {
                Similar_yb_array = Similar_yb.split("&&");
                Similar_array = Similars.split(",");
                if (Similar_yb_array != null && Similar_array != null) {
                    for (int i = 0; i < SimilarCount; i++) {
                        String yb = Similar_yb_array[i].replace(",", " ");
                        list.add(Similar_array[i] + " /" + yb + "/");
                    }
                }
            } else {
                String yb = Similar_yb.replace(",", " ");
                list.add(Similars + " /" + yb + "/");
            }
            adapter.setData(list);
        }
    }
}
