package phonetics.android.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import phonetics.android.ui.DetailsActivity;
import phonetics.android.utils.PlayUtil;
import phonetics.android.widget.HorizontalLinearLayout;

/**
 * Created by lsd on 15/7/22.
 */
public class PageExampleFragment extends BaseFragment implements DetailsGeneralAdapter.OnItemClick {
    TextView tv_example_name;
    HorizontalLinearLayout example_h_layout;
    ListView example_listview;

    DetailsGeneralAdapter adapter;

    //RecyclerView example_recyclerview;
    //DetailsRecyclerViewHAdapter rAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_example, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        setViewStatu();
        setData(CurrentPhonetics.instance().getCurrentVoice());
    }

    private void initView() {
        tv_example_name = (TextView) mActivity.findViewById(R.id.tv_example_name);

        //RecyclerView
        //example_recyclerview = (RecyclerView) mActivity.findViewById(R.id.example_recyclerview);
        //example_recyclerview.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        //example_recyclerview.setAdapter(rAdapter = new DetailsRecyclerViewHAdapter(mActivity));
        example_h_layout = (HorizontalLinearLayout) mActivity.findViewById(R.id.example_h_layout);

        //ListView
        example_listview = (ListView) mActivity.findViewById(R.id.example_listview);
        example_listview.setAdapter(adapter = new DetailsGeneralAdapter(mActivity));
        adapter.setOnItemClick(this);
    }

    private void setViewStatu(){
        if (CurrentPhonetics.instance().voiceType == CurrentPhonetics.VoiceType.ADVANCE){
            tv_example_name.setVisibility(View.GONE);
        }else {
            tv_example_name.setVisibility(View.VISIBLE);
        }
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
            } else if (count == 1) {
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
                    //example_recyclerview.setVisibility(View.VISIBLE);
                    //rAdapter.setData(vList);
                    example_h_layout.setVisibility(View.VISIBLE);
                    example_h_layout.setData(vList);
                } else {
                    //example_recyclerview.setVisibility(View.GONE);
                    example_h_layout.setVisibility(View.GONE);
                }
            }
        }
    }


    //处理点击回调
    @Override
    public void click(int postion, View v) {
        GeneralEntity eEty = (GeneralEntity) adapter.getItem(postion);
        tv_example_name.setText(eEty.getName());//重新设置名称
        //rAdapter.setData(eEty.getvList());//重新设置关联音标
        example_h_layout.setData(eEty.getvList());

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


    public void guideForItem(){
        click(0, R.id.content_layout);
    }

    public void guideForSlow(){
        click(0,R.id.iv_pic);
    }

    public void guideForExample(){
        List<PhoneticsEntity.VoiceEty> list = example_h_layout.getData();
        if (list != null && list.size()>0){
            PhoneticsEntity.VoiceEty ety = list.get(0);
            if (DetailsActivity.faceSide == 0) {
                PlayUtil.playAnimation(mActivity, DetailsActivity.faceSide, DetailsActivity.iv_front, ety);
                PlayUtil.playMedia(DetailsActivity.voice, ety);
            }
            if (DetailsActivity.faceSide == 1) {
                PlayUtil.playAnimation(mActivity, DetailsActivity.faceSide, DetailsActivity.iv_side, ety);
                PlayUtil.playMedia(DetailsActivity.voice, ety);
            }
        }
    }

    /***
     *
     * 特殊引导的时候用
     *
     * @param postion
     * @param id
     */
    public void click(int postion, int id) {
        GeneralEntity eEty = (GeneralEntity) adapter.getItem(postion);
        tv_example_name.setText(eEty.getName());//重新设置名称
        //rAdapter.setData(eEty.getvList());//重新设置关联音标
        example_h_layout.setData(eEty.getvList());

        switch (id) {
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
