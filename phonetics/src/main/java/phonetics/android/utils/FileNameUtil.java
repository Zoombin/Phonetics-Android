package phonetics.android.utils;

import android.util.Log;

/**
 * Created by lsd on 15/7/24.
 */
public class FileNameUtil {
    public static String replace(String name){
        String newName = name
                .replace("æ", "ian")
                .replace("aɪ", "ai")
                .replace("aυ", "au")
                .replace("υ", "ev")
                .replace("ð", "th")
                .replace("dʒ", "gh")
                .replace("eɪ", "ei")
                .replace("ə", "er")
                .replace("ɚc", "erc")
                .replace("ɪ", "i")
                .replace("ɔɪ", "oui")
                .replace("ɔi", "our")
                .replace("oυ", "ou")
                .replace("ʊ", "u")
                .replace("ʃ", "sh")
                .replace("tʃ", "tsh")
                .replace("α", "a")
                .replace("ɔc", "ouc")
                .replace("θ", "zh")
                .replace("ʒ", "rh")
                .replace("ε", "ah")
                .replace("ʌ", "ar")
                .replace("ŋ", "ng");
        return  newName;
    }

}
