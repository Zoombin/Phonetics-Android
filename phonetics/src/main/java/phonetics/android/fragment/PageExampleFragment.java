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
import phonetics.android.adapter.DetailsRecyclerViewHAdapter;
import phonetics.android.adapter.DetailsGeneralAdapter;
import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.entity.GeneralEntity;
import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.utils.PlayUtil;

/**
 * Created by lsd on 15/7/22.
 */
public class PageExampleFragment extends BaseFragment implements DetailsGeneralAdapter.OnItemClick {
    TextView tv_example_name;
    RecyclerView example_recyclerview;
    ListView example_listview;

    DetailsGeneralAdapter adapter;
    DetailsRecyclerViewHAdapter rAdapter;
    PhoneticsEntity.VoiceEty thisEty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_example, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        setData(CurrentPhonetics.instance().getCurrentVoice());
    }

    private void initView() {
        tv_example_name = (TextView) mActivity.findViewById(R.id.tv_example_name);

        //RecyclerView
        example_recyclerview = (RecyclerView) mActivity.findViewById(R.id.example_recyclerview);
        example_recyclerview.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        example_recyclerview.setAdapter(rAdapter = new DetailsRecyclerViewHAdapter(mActivity));

        //ListView
        example_listview = (ListView) mActivity.findViewById(R.id.example_listview);
        example_listview.setAdapter(adapter = new DetailsGeneralAdapter(mActivity));
        adapter.setOnItemClick(this);
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
            //计算数据
            List<GeneralEntity> list = new ArrayList<>();
            int count = Integer.parseInt(ety.getExamples_count());
            if (count > 1) {
                String[] exs = ety.getExamples().split(",");
                String[] reads = ety.getExamples_read().split("&&");
                String[] slow_reads = ety.getExamples_slow_read().split("&&");
                String[] ybs = ety.getExamples_yb().split("&&");
                String[] yb_names = ety.getExamples_yb_name().split("&&");

                for (int i = 0; i < count; i++) {
                    GeneralEntity entity = PlayUtil.getGeneralEntity(exs[i], reads[i], slow_reads[i], ybs[i], yb_names[i]);
                    //advanced
                    if (CurrentPhonetics.instance().voiceType == CurrentPhonetics.VoiceType.ADVANCE){
                        entity.setAdvanced_pic(ety.getExamples_pics());
                    }
                    list.add(entity);
                }
            } else {
                GeneralEntity entity = PlayUtil.getGeneralEntity(ety.getExamples(), ety.getExamples_read(), ety.getExamples_slow_read(), ety.getExamples_yb(), ety.getExamples_yb_name());
                //advanced
                if (CurrentPhonetics.instance().voiceType == CurrentPhonetics.VoiceType.ADVANCE){
                    entity.setAdvanced_pic(ety.getExamples_pics());
                }
                list.add(entity);
            }


            if (list.size() > 0) {
                GeneralEntity eEty = list.get(0);
                //名称
                tv_example_name.setText(eEty.getName());

                //步骤列表
                adapter.setData(list);

                //关联音标
                List<PhoneticsEntity.VoiceEty> vList = eEty.getvList();
                if (vList.size() > 0) {
                    example_recyclerview.setVisibility(View.VISIBLE);
                    rAdapter.setData(vList);
                } else {
                    example_recyclerview.setVisibility(View.GONE);
                }
            }
        }
    }


    //处理点击回调
    @Override
    public void click(int postion, View v) {
        GeneralEntity eEty = (GeneralEntity) adapter.getItem(postion);
        tv_example_name.setText(eEty.getName());//重新设置名称
        rAdapter.setData(eEty.getvList());//重新设置关联音标

        switch (v.getId()) {
            case R.id.content_layout:
                //条目点击
                PlayUtil.playMergeMedia(mActivity,0,eEty);
                break;
            case R.id.iv_pic:
                //慢放点击
                PlayUtil.playMergeMedia(mActivity,1,eEty);
                break;
        }
    }
}
