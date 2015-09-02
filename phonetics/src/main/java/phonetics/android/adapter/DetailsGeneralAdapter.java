package phonetics.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import phonetics.android.Constants;
import phonetics.android.R;
import phonetics.android.entity.ExampleEntity;
import phonetics.android.entity.GeneralEntity;
import phonetics.android.utils.ClickUtil;

public class DetailsGeneralAdapter extends BaseAdapter {
    private Context context;
    List<GeneralEntity> list;
    List<Boolean> slows;
    OnItemClick click;

    public DetailsGeneralAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClick(OnItemClick click) {
        this.click = click;
    }

    public void setData(List<GeneralEntity> list) {
        this.list = list;
        if (list != null && list.size() > 0) {
            slows = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                slows.add(i, false);
            }
            slows.set(0, true);
        }
        this.notifyDataSetChanged();
    }

    public void changSlowsPic(int position) {
        if (list != null && list.size() > 0) {
            slows = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                slows.add(i, false);
            }
            slows.set(position, true);
        }
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHodler viewHodler = null;
        if (convertView == null) {
            viewHodler = new ViewHodler();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_details_example_item, null);
            viewHodler.content_layout = (LinearLayout) convertView.findViewById(R.id.content_layout);
            viewHodler.iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);
            viewHodler.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }


        GeneralEntity ety = (GeneralEntity) getItem(position);
        if (ety != null) {
            String name = ety.getName();
            String[] yb_name_array = ety.getYb_name_array();
            StringBuffer sBuffer = new StringBuffer();
            sBuffer.append(" /");
            for (String yb_name : yb_name_array) {
                sBuffer.append(yb_name + " ");
            }
            sBuffer.append("/");

            viewHodler.tv_content.setText(name + sBuffer);
        }
        boolean b = slows.get(position);
        if (b) {
            viewHodler.iv_pic.setVisibility(View.VISIBLE);
        } else {
            viewHodler.iv_pic.setVisibility(View.INVISIBLE);
        }
        viewHodler.content_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Constants.isPlaying){
                    //正在播放不进行其他操作
                    return;
                }
                //改变视图
                changSlowsPic(position);
                if (click != null) {
                    click.click(position, v);
                }
            }
        });
        viewHodler.iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Constants.isPlaying){
                    //正在播放不进行其他操作
                    return;
                }
                //慢放
                if (click != null) {
                    click.click(position, v);
                }
            }
        });

        return convertView;
    }

    class ViewHodler {
        LinearLayout content_layout;
        ImageView iv_pic;
        TextView tv_content;
    }

    /**
     * 处理点击回调
     */
    public interface OnItemClick {
        void click(int postion, View v);
    }
}
