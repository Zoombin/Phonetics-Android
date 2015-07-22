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

import java.util.ArrayList;
import java.util.List;

import phonetics.android.R;
import phonetics.android.entity.PhoneticsEntity;

public class DetailsBasicAdapter extends BaseAdapter {
    private Context context;
    String[] list;

    public DetailsBasicAdapter(Context context) {
        this.context = context;
    }

    public void setData(String[] list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list == null ? 0 : list.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list == null ? null : list[position];
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

        String  ety = (String) getItem(position);
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
            viewHodler.tv_content.setText(ety);
        }
        return convertView;
    }

    class ViewHodler {
        ImageView iv_pic;
        TextView tv_content;
    }
}
