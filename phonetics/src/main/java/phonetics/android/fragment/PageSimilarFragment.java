package phonetics.android.fragment;

import android.os.Bundle;
import android.os.Handler;
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
import phonetics.android.utils.AnimationUtil;
import phonetics.android.utils.FileNameUtil;
import phonetics.android.utils.MediaPlayerUtil;
import phonetics.android.utils.PlayUtil;

/**
 * Created by lsd on 15/7/22.
 */
public class PageSimilarFragment extends BaseFragment implements DetailsGeneralAdapter.OnItemClick {
    TextView tv_similar_name;
    RecyclerView similar_recyclerview;
    ListView similar_listview;

    DetailsGeneralAdapter adapter;
    DetailsRecyclerViewHAdapter rAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_similar, container, false);
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
        similar_listview.setAdapter(adapter = new DetailsGeneralAdapter(mActivity));
        adapter.setOnItemClick(this);
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
            //计算数据
            List<GeneralEntity> list = new ArrayList<>();
            int count = Integer.parseInt(ety.getSimilar_count());
            if (count > 1) {
                String[] exs = ety.getSimilar().split(",");
                String[] reads = ety.getSimilar_read().split("&&");
                String[] slow_reads = ety.getSimilar_slow_read().split("&&");
                String[] ybs = ety.getSimilar_yb().split("&&");
                String[] yb_names = ety.getSimilar_yb_name().split("&&");

                for (int i = 0; i < count; i++) {
                    GeneralEntity entity = PlayUtil.getGeneralEntity(exs[i], reads[i], slow_reads[i], ybs[i], yb_names[i]);
                    list.add(entity);
                }
            } else if (count == 1) {
                GeneralEntity entity = PlayUtil.getGeneralEntity(ety.getSimilar(), ety.getSimilar_read(), ety.getSimilar_slow_read(), ety.getSimilar_yb(), ety.getSimilar_yb_name());
                list.add(entity);
            }


            if (list.size() > 0) {
                GeneralEntity gEty = list.get(0);
                //名称
                tv_similar_name.setText(gEty.getName());

                //步骤列表
                adapter.setData(list);

                //关联音标
                List<PhoneticsEntity.VoiceEty> vList = gEty.getvList();
                if (vList.size() > 0) {
                    similar_recyclerview.setVisibility(View.VISIBLE);
                    rAdapter.setData(vList);
                } else {
                    similar_recyclerview.setVisibility(View.GONE);
                }
            }
        }
    }

    private void setRecyclerViewLayoutPamas(){
       final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) similar_recyclerview.getLayoutParams();
        View view = adapter.getView(0, null, null);
        int hight = view.getHeight();
        int width = 0;
        for (int i = 0;i<adapter.getCount();i++){
            width+= view.getWidth();
        }
        params.width = width;
        params.height = hight;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                similar_recyclerview.setLayoutParams(params);
            }
        },200);
    }


    //处理点击回调
    @Override
    public void click(int postion, View v) {
        GeneralEntity gEty = (GeneralEntity) adapter.getItem(postion);
        tv_similar_name.setText(gEty.getName());//重新设置名称
        rAdapter.setData(gEty.getvList());//重新设置关联音标

        switch (v.getId()) {
            case R.id.content_layout:
                //条目点击
                PlayUtil.playMergeMedia(mActivity,0, gEty);
                break;
            case R.id.iv_pic:
                //慢放点击
                PlayUtil.playMergeMedia(mActivity, 1, gEty);
                break;
        }
    }
}


