package phonetics.android.utils;

import android.util.Log;

/**
 * Created by lsd on 15/7/24.
 */
public class FileNameUtil {
    public static String replace(String name){
        String newName = name
                .replace("ɔɪ", "oui")
                .replace("ɔi", "our")
                .replace("oυ", "ou")
                .replace("aɪ", "ai")
                .replace("aυ", "au")
                .replace("tʃ", "tsh")
                .replace("dʒ", "gh")
                .replace("eɪ", "ei")
                .replace("ɔc", "ouc")
                .replace("ə", "er")
                .replace("ɚc", "erc")
                .replace("æ", "ian")
                .replace("ɪ", "i")
                .replace("ʊ", "eu")
                .replace("ʃ", "sh")
                .replace("υ", "ev")
                .replace("α", "a")
                .replace("ð", "th")
                .replace("θ", "zh")
                .replace("ʒ", "rh")
                .replace("ε", "ah")
                .replace("ʌ", "ar")
                .replace("ŋ", "ng")
                .replace("ɫ", "ii");

        return  newName;
    }

}
