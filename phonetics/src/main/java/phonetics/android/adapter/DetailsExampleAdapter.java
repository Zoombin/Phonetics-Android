package phonetics.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import phonetics.android.R;

public class DetailsExampleAdapter extends BaseAdapter {
    private Context context;
    List<String> list;

    public DetailsExampleAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<String> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_details_example_item, null);
            viewHodler.iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);
            viewHodler.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }

        String  ety = (String) getItem(position);
        if (ety != null) {
            viewHodler.tv_content.setText(ety);
        }
        return convertView;
    }

    class ViewHodler {
        ImageView iv_pic;
        TextView tv_content;
    }
}
