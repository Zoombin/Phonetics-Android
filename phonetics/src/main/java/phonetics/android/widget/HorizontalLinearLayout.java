package phonetics.android.widget;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.nostra13.universalimageloader.core.ImageLoader;

import phonetics.android.R;
import phonetics.android.config.Config;
import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.ui.DetailsActivity;
import phonetics.android.utils.PlayUtil;

public class HorizontalLinearLayout extends LinearLayout {
    Context context;
    private List<PhoneticsEntity.VoiceEty> list;

    public HorizontalLinearLayout(Context context) {
        this(context, null);
    }

    public HorizontalLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    public void setData(List<PhoneticsEntity.VoiceEty> list) {
        this.list = list;

        removeAllViews();

        for (int i = 0; i < list.size(); i++) {
            PhoneticsEntity.VoiceEty ety = list.get(i);
            View view = getView(context, ety);
            addView(view);
        }
    }


    private View getView(final Context context, final PhoneticsEntity.VoiceEty ety) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recyclerview_hitem, null);
        RelativeLayout item_layout = (RelativeLayout) view.findViewById(R.id.item_layout);
        ImageView iv_img = (ImageView) view.findViewById(R.id.iv_img);
        if (ety != null) {
            ImageLoader.getInstance().displayImage(Config.SYMBOLPIC_BASE_PATH + ety.getImg() + Config.IMG_TYPE_PNG, iv_img);
            item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (DetailsActivity.faceSide == 0) {
                        PlayUtil.playAnimation(context, DetailsActivity.faceSide, DetailsActivity.iv_front, ety);
                        PlayUtil.playMedia(DetailsActivity.voice, ety);
                    }
                    if (DetailsActivity.faceSide == 1) {
                        PlayUtil.playAnimation(context, DetailsActivity.faceSide, DetailsActivity.iv_side, ety);
                        PlayUtil.playMedia(DetailsActivity.voice, ety);
                    }
                }
            });
        }
        return view;
    }
}
