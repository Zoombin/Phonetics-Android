package phonetics.android;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.Map;

import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.utils.AssetsUtil;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PhoneticsEntity entity = AssetsUtil.readAssets(MainActivity.this);

        List<PhoneticsEntity.SymbolEty> list = entity.getAdvanced();
        Log.i("LSD", "---------------* = advanced");
        for (PhoneticsEntity.SymbolEty ety : list) {
            Log.i("LSD", ety.getTitle());
            Log.i("LSD", ety.getDescribe());
            List<PhoneticsEntity.VoiceEty> voiceEtys = ety.getVoices();
            for (PhoneticsEntity.VoiceEty vEty : voiceEtys) {
                Log.i("LSD", "******* = VoiceEty");
                Log.i("LSD", vEty.getName());
                Log.i("LSD", vEty.getDescribe());
            }
        }
        List<PhoneticsEntity.SymbolEty> list1 = entity.getAdvanced();
        Log.i("LSD", "---------------* = basics");
        for (PhoneticsEntity.SymbolEty ety : list) {
            Log.i("LSD", ety.getTitle());
            Log.i("LSD", ety.getDescribe());
            List<PhoneticsEntity.VoiceEty> voiceEtys = ety.getVoices();
            for (PhoneticsEntity.VoiceEty vEty : voiceEtys) {
                Log.i("LSD", "******* = VoiceEty");
                Log.i("LSD", vEty.getName());
                Log.i("LSD", vEty.getDescribe());
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
