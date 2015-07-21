package phonetics.android.adapter;

import java.util.List;
import java.util.Objects;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import phonetics.android.R;
import phonetics.android.entity.PhoneticsEntity;

class RecyclerViewHAdapter extends RecyclerView.Adapter<RecyclerViewHAdapter.MViewHolder> {
    private List<PhoneticsEntity.VoiceEty> list;

    public void setData(List<PhoneticsEntity.VoiceEty> list){
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MViewHolder holder = new MViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_hitem, parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, final int position) {
        PhoneticsEntity.VoiceEty ety = (PhoneticsEntity.VoiceEty) getItem(position);
        if (ety != null) {
            ImageLoader.getInstance().displayImage("assets://symbol/" + ety.getImg()+".png", holder.iv_img);
            holder.item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public Object getItem(int position) {
        return list == null ? null : list.get(position);
    }

    class MViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout item_layout;
        ImageView iv_img;

        public MViewHolder(View view) {
            super(view);
            item_layout = (RelativeLayout) view.findViewById(R.id.item_layout);
            iv_img = (ImageView) view.findViewById(R.id.iv_img);
        }
    }
}