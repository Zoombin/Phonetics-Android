package phonetics.android.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import phonetics.android.R;
import phonetics.android.entity.PhoneticsEntity;

public class VoiceHAdapter extends BaseAdapter {
    List<PhoneticsEntity.VoiceEty> list;

    public void addData(List<PhoneticsEntity.VoiceEty> list) {
        if (this.list == null) {
            this.list = new ArrayList<PhoneticsEntity.VoiceEty>();
        }
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public void setData(List<PhoneticsEntity.VoiceEty> list) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_hitem, null);
            viewHodler.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }

        PhoneticsEntity.VoiceEty ety = (PhoneticsEntity.VoiceEty) getItem(position);
        if (ety != null) {
            viewHodler.tv_name.setText(ety.getName());
        }
        return convertView;
    }

    class ViewHodler {
        TextView tv_name;
    }
}
