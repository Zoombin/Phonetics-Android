package phonetics.android.tools;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by lsd on 15/7/29.
 */

public class MLayoutManager extends LinearLayoutManager {

    public MLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        int cont = state.getItemCount();
        int height = 0;
        int width = 0;
        for (int i = 0; i < cont; i++) {
            View view = recycler.getViewForPosition(i);
            if (view != null) {
                measureChild(view, widthSpec, heightSpec);

                height = view.getMeasuredHeight();
                int measuredHeight = view.getMeasuredWidth();
                width += measuredHeight;
            }
        }
        setMeasuredDimension(width, height);
    }
}

