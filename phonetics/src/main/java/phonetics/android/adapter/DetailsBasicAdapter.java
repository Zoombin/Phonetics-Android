package phonetics.android.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import phonetics.android.R;
import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.entity.StepEntity;
import phonetics.android.ui.DetailsActivity;
import phonetics.android.utils.AnimationUtil;
import phonetics.android.utils.FileNameUtil;
import phonetics.android.utils.MediaPlayerUtil;
import phonetics.android.utils.PlayUtil;

public class DetailsBasicAdapter extends BaseAdapter {
    private Context context;
    List<StepEntity>  list;

    public DetailsBasicAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<StepEntity>  list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list == null ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return list == null ? 0 : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHodler viewHodler = null;
        if (convertView == null) {
            viewHodler = new ViewHodler();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_details_basic_item, null);
            viewHodler.iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);
            viewHodler.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }

        final StepEntity  ety = (StepEntity) getItem(position);
        if (ety != null) {
            if(position == 0){
                viewHodler.iv_pic.setImageResource(R.drawable.ic_step_1);
            }
            if(position == 1){
                viewHodler.iv_pic.setImageResource(R.drawable.ic_step_2);
            }
            if(position == 2){
                viewHodler.iv_pic.setImageResource(R.drawable.ic_step_3);
            }
            viewHodler.tv_content.setText(ety.getDescribes());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String stepVoices = ety.getVoices();

                    int[] data = PlayUtil.getStepVoicesData(stepVoices);

                    int start_time = data[0];
                    int delay_time = data[1];

                    //播放动画
                    String pics = ety.getPics();
                    String[] picArray = pics.split(",");
                    String resouce[] = null;
                    if (picArray != null && picArray.length > 0) {
                        resouce = new String[picArray.length];
                        if (DetailsActivity.faceSide == 0){
                            for (int i = 0;i<picArray.length;i++){
                                resouce[i] = FileNameUtil.replace(picArray[i]);
                            }
                            AnimationUtil.startAnimation(context, DetailsActivity.iv_front, resouce, delay_time);
                        }else{
                            for (int i = 0;i<picArray.length;i++){
                                resouce[i] = "c"+FileNameUtil.replace(picArray[i]);
                            }
                            AnimationUtil.startAnimation(context, DetailsActivity.iv_side, resouce, delay_time);
                        }

                        //播放音乐
                        MediaPlayerUtil.start(start_time,delay_time);

                    }
                }
            });
        }
        return convertView;
    }

    class ViewHodler {
        ImageView iv_pic;
        TextView tv_content;
    }
}
