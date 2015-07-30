package phonetics.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import phonetics.android.BaseActivity;
import phonetics.android.R;
import phonetics.android.adapter.PickSymbolAdapter;
import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.entity.PhoneticsEntity;

/**
 * Created by lsd on 15/7/26.
 */
public class PickSymbolActivity extends BaseActivity implements View.OnClickListener {
    ExpandableListView listView;
    PickSymbolAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picksymbol);

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);

        listView = (ExpandableListView) findViewById(R.id.listView);
        listView.setAdapter(adapter = new PickSymbolAdapter(mActivity));
        adapter.setData(CurrentPhonetics.instance().entity.getBasics());
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                PhoneticsEntity.VoiceEty ety = (PhoneticsEntity.VoiceEty) adapter.getChild(groupPosition, childPosition);
                Intent intent = getIntent();
                intent.putExtra("ety", ety);
                setResult(RESULT_OK, intent);
                finish();
                return false;
            }
        });
        for (int i=0;i<adapter.getGroupCount();i++){
            listView.expandGroup(i);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
