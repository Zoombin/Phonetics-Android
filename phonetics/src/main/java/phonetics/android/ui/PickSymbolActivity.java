package phonetics.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import phonetics.android.BaseActivity;
import phonetics.android.R;
import phonetics.android.adapter.PickSymbolAdapter;
import phonetics.android.db.DB_Data;
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

        //添加日本音
        List<PhoneticsEntity.VoiceEty>  voices = CurrentPhonetics.instance().basicsVoiceList;
        List<PhoneticsEntity.VoiceEty> jpVoice = new ArrayList<>();
        for (PhoneticsEntity.VoiceEty voice: voices){
            if ("ɑ".equals(voice.getName())){
                jpVoice.add(changeVoice("ア","symbol_JPA",voice));
            }
            if ("j".equals(voice.getName())){
                jpVoice.add(changeVoice("イ","symbol_JPJ",voice));
            }
            if ("ʊ".equals(voice.getName())){
                jpVoice.add(changeVoice("ウ","symbol_JPU1",voice));
            }
            if ("ɔː".equals(voice.getName())){
                jpVoice.add(changeVoice("オ","symbol_JPU2",voice));
            }
            if ("æ".equals(voice.getName())){
                jpVoice.add(changeVoice("エ","symbol_JPUAE",voice));
            }
        }

        PhoneticsEntity.SymbolEty symbolEty = new PhoneticsEntity.SymbolEty();
        symbolEty.setTitle("日本发音");
        symbolEty.setVoices(jpVoice);

        List<PhoneticsEntity.SymbolEty> basics= new DB_Data(mActivity).getPhonetics().getBasics();
        basics.add(symbolEty);

        adapter.setData(basics);
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


    private PhoneticsEntity.VoiceEty changeVoice(String nama,String pic,PhoneticsEntity.VoiceEty ety){
        PhoneticsEntity.VoiceEty newEty = new PhoneticsEntity.VoiceEty();
        newEty.setName(nama);
        newEty.setImg(pic);
        newEty.setPics_front(ety.getPics_front());
        newEty.setLong_female(ety.getLong_female());
        newEty.setLong_male(ety.getLong_male());
        newEty.setEtime_female(ety.getEtime_female());
        newEty.setEtime_male(ety.getEtime_male());
        newEty.setStime_female(ety.getStime_female());
        newEty.setStime_male(ety.getStime_male());

        return newEty;
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
