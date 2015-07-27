package phonetics.android.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.entity.PhoneticsEntity.SymbolEty;

/**
 * Created by LSD on 2015/7/17.
 */
public class AssetsUtil {
    /**
     * 读取配置文件
     *
     * @param context
     * @return
     */
    public static PhoneticsEntity readAssets(Context context) {
        PhoneticsEntity entity = null;
        try {
           InputStream inputStream =  context.getAssets().open("voiceinfo.json");
            byte[] buffer = new byte[1024];
            StringBuffer sBuffer = new StringBuffer();
            int len =0;
            while (-1 != (len = inputStream.read(buffer))){
                String string = new String(buffer,0,len,"UTF-8");
                sBuffer.append(string);
            }
            Gson gson = new Gson();
            entity = gson.fromJson(sBuffer.toString().trim(),PhoneticsEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("LSD",e.getMessage());
        }
        return entity;
    }
}
