package phonetics.android.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;


import phonetics.android.R;
import phonetics.android.entity.PhoneticsEntity;

public class PhoneticsAdapter extends BaseAdapter {
    private Context context;
    List<PhoneticsEntity.SymbolEty> list;

    public PhoneticsAdapter(Context context){
        this.context = context;
    }

    public void addData(List<PhoneticsEntity.SymbolEty> list) {
        if (this.list == null) {
            this.list = new ArrayList<PhoneticsEntity.SymbolEty>();
        }
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public void setData(List<PhoneticsEntity.SymbolEty> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_phonetics_item, null);
            viewHodler.iv_name = (TextView)convertView.findViewById(R.id.iv_name);
            viewHodler.iv_description = (TextView)convertView.findViewById(R.id.iv_description);
            viewHodler.recyclerview = (RecyclerView) convertView.findViewById(R.id.recyclerview);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }

        PhoneticsEntity.SymbolEty ety = (PhoneticsEntity.SymbolEty) getItem(position);
        if (ety != null) {
            viewHodler.iv_name.setText(ety.getTitle());
            viewHodler.iv_description.setText(ety.getDescribe());

            viewHodler.recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            RecyclerViewHAdapter adapter = new RecyclerViewHAdapter(context);
            viewHodler.recyclerview.setAdapter(adapter);
            adapter.setData(ety.getVoices());
        }
        return convertView;
    }

    class ViewHodler {
        TextView iv_name;
        TextView iv_description;
        RecyclerView recyclerview;
    }
}
