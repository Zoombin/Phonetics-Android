package phonetics.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import phonetics.android.R;
import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.entity.StepEntity;
import phonetics.android.ui.DetailsActivity;
import phonetics.android.utils.AnimationUtil;
import phonetics.android.utils.FileNameUtil;
import phonetics.android.utils.MediaPlayerUtil;
import phonetics.android.utils.PlayUtil;

public class PickSymbolAdapter extends BaseExpandableListAdapter {
    private Context context;
    List<PhoneticsEntity.SymbolEty> list;

    public PickSymbolAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<PhoneticsEntity.SymbolEty> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }


    @Override
    public int getGroupCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (list != null) {
            List<PhoneticsEntity.VoiceEty> subList = list.get(groupPosition).getVoices();
            if (subList != null) {
                return subList.size();
            }
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list == null ? null : list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (list != null) {
            List<PhoneticsEntity.VoiceEty> subList = list.get(groupPosition).getVoices();
            if (subList != null) {
                return subList.get(childPosition);
            }
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return list == null ? 0 : groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        if (list != null) {
            List<PhoneticsEntity.VoiceEty> subList = list.get(groupPosition).getVoices();
            if (subList != null) {
                return childPosition;
            }
        }
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_pksymobl_item,null);
        TextView textView = (TextView) view.findViewById(R.id.tv_title);
        PhoneticsEntity.SymbolEty ety = (PhoneticsEntity.SymbolEty) getGroup(groupPosition);
        textView.setText(ety.getTitle());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_pksymobl_subitem,null);
        TextView textView = (TextView) view.findViewById(R.id.tv_name);
        PhoneticsEntity.SymbolEty ety = (PhoneticsEntity.SymbolEty) getGroup(groupPosition);
        if (ety != null){
           List<PhoneticsEntity.VoiceEty> subList = ety.getVoices();
            if (subList != null){
                textView.setText(subList.get(childPosition).getName());
            }
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
