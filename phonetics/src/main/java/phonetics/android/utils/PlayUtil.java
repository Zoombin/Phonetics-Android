package phonetics.android.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import phonetics.android.entity.CurrentPhonetics;
import phonetics.android.entity.GeneralEntity;
import phonetics.android.entity.PhoneticsEntity;
import phonetics.android.ui.DetailsActivity;

/**
 * Created by lsd on 15/7/25.
 * <p/>
 * 处理数据 合并一些相同的方法
 */
public class PlayUtil {

    /**
     * 播放动画
     *
     * @param context
     * @param faceSide
     * @param imageView
     * @param ety
     */

    public static void playAnimation(Context context, int faceSide, ImageView imageView, PhoneticsEntity.VoiceEty ety) {
        playAnimation(context, faceSide, imageView, ety, false);
    }
    public static void playAnimation(Context context, int faceSide, ImageView imageView, PhoneticsEntity.VoiceEty ety,boolean isCut) {
        String pics = null;
        if (CurrentPhonetics.instance().voiceType == CurrentPhonetics.VoiceType.ADVANCE) {
            //高级
            pics = ety.getExamples_pics();
        } else {
            //基础
            pics = ety.getPics_front();
        }

        int startIndex = 0;
        if (isCut){
            startIndex = 3;
        }

        String[] picArray = pics.split(",");
        String[] resouce = null;
        if (picArray != null && picArray.length > 0) {
            resouce = new String[picArray.length-startIndex];
        }
        int long_time = 0;
        try {
            double tem = Double.parseDouble(ety.getLong_male());
            long_time = (int) (tem * 1000);
        } catch (Exception e) {
        }
        if (faceSide == 0) {
            //播放动画
            int j =0;
            for (int i = startIndex; i < picArray.length; i++) {
                resouce[j] = FileNameUtil.replace(picArray[i]);
                j++;
            }
            AnimationUtil.startAnimation(context, imageView, resouce, long_time);
        }
        if (faceSide == 1) {
            //播放动画
            int j=0;
            for (int i = startIndex; i < picArray.length; i++) {
                resouce[j] = "c" + FileNameUtil.replace(picArray[i]);
                j++;
            }
            AnimationUtil.startAnimation(context, imageView, resouce, long_time);
        }
    }

    /**
     * 播放音频
     */
    public static void playMedia(int voice, PhoneticsEntity.VoiceEty ety) {
        int stime = 0;
        int long_time = 0;

        if (voice == 0) {
            //男声
            String stime_male = ety.getStime_male();
            String long_male = ety.getLong_male();
            try {
                String tem = stime_male.replace(":", ",").replace(".", ",");
                String[] temArray = tem.split(",");
                if (temArray != null) {
                    if (temArray.length == 3) {
                        stime = Integer.parseInt(temArray[0]) * 60 * 1000 + Integer.parseInt(temArray[1]) * 1000 + Integer.parseInt(temArray[2]);
                    }
                    if (temArray.length == 2) {
                        stime = Integer.parseInt(temArray[0]) * 1000 + Integer.parseInt(temArray[1]);
                    }
                }
            } catch (Exception e) {
            }

            try {
                double tem = Double.parseDouble(long_male);
                long_time = (int) (tem * 1000);
            } catch (Exception e) {
            }
        }
        if (voice == 1) {
            //女声
            String stime_fmale = ety.getStime_female();
            String long_fmale = ety.getLong_female();
            try {
                String tem = stime_fmale.replace(":", ",").replace(".", ",");
                String[] temArray = tem.split(",");
                if (temArray != null) {
                    if (temArray.length == 3) {
                        stime = Integer.parseInt(temArray[0]) * 60 * 1000 + Integer.parseInt(temArray[1]) * 1000 + Integer.parseInt(temArray[2]);
                    }
                    if (temArray.length == 2) {
                        stime = Integer.parseInt(temArray[0]) * 1000 + Integer.parseInt(temArray[1]);
                    }
                }
            } catch (Exception e) {
            }

            try {
                double tem = Double.parseDouble(long_fmale);
                long_time = (int) (tem * 1000);
            } catch (Exception e) {
            }
        }

        Log.i("LSD","stime = "+stime);
        Log.i("LSD","long_time = "+long_time);


        if (long_time > 0) {
            MediaPlayerUtil.start(stime, long_time);
        }
    }

    /**
     * 处理 播放的开始时间和持续时间
     *
     * @param stepVoices
     * @return
     */
    public static int[] getStepVoicesData(String stepVoices) {
        //0:45.245,0.223,0:04.286,0.157
        //17:58.247,0.630, 9:35.248,0.435",
        String[] temp = stepVoices.trim().split(",");
        int start_time = 0;
        int delay_time = 0;
        if (temp != null && temp.length == 4) {
            String stime_fmale = temp[0];//女生
            String long_fmale = temp[1];
            String stime_male = temp[2];//男声
            String long_male = temp[3];

            if (DetailsActivity.voice == 0) {
                //男发声
                try {
                    String time = stime_male.replace(":", ",").replace(".", ",");
                    String[] temArray = time.split(",");
                    if (temArray != null) {
                        if (temArray.length == 3) {
                            start_time = Integer.parseInt(temArray[0]) * 60 * 1000 + Integer.parseInt(temArray[1]) * 1000 + Integer.parseInt(temArray[2]);
                        }
                        if (temArray.length == 2) {
                            start_time = Integer.parseInt(temArray[0]) * 1000 + Integer.parseInt(temArray[1]);
                        }
                    }
                } catch (Exception e) {
                }

                try {
                    double delay = Double.parseDouble(long_male);
                    delay_time = (int) (delay * 1000);
                } catch (Exception e) {
                }
            }
            if (DetailsActivity.voice == 1) {
                //女发声
                try {
                    String time = stime_fmale.replace(":", ",").replace(".", ",");
                    String[] temArray = time.split(",");
                    if (temArray != null) {
                        if (temArray.length == 3) {
                            start_time = Integer.parseInt(temArray[0]) * 60 * 1000 + Integer.parseInt(temArray[1]) * 1000 + Integer.parseInt(temArray[2]);
                        }
                        if (temArray.length == 2) {
                            start_time = Integer.parseInt(temArray[0]) * 1000 + Integer.parseInt(temArray[1]);
                        }
                    }
                } catch (Exception e) {
                }

                try {
                    double delay = Double.parseDouble(long_fmale);
                    delay_time = (int) (delay * 1000);
                } catch (Exception e) {
                }
            }
        }

        int[] data = new int[2];
        data[0] = start_time;
        data[1] = delay_time;
        return data;
    }


    /***
     *  获取播放动画的图片资源
     *
     * @param pics
     * @return
     */
    public String[] get(String pics) {
        String[] picArray = pics.split(",");
        String[] resouce = null;
        if (picArray != null && picArray.length > 0) {
            resouce = new String[picArray.length];
            if (DetailsActivity.faceSide == 0) {
                //播放动画
                for (int i = 0; i < picArray.length; i++) {
                    resouce[i] = FileNameUtil.replace(picArray[i]);
                }
            }
            if (DetailsActivity.faceSide == 1) {
                //播放动画
                for (int i = 0; i < picArray.length; i++) {
                    resouce[i] = "c" + FileNameUtil.replace(picArray[i]);
                }
            }
        }
        return resouce;
    }


    /**
     * 拼接通用的实体
     *
     * @param name
     * @param read
     * @param slow_read
     * @param yb
     * @param yb_name
     * @return
     */
    public static GeneralEntity getGeneralEntity(String name, String read, String slow_read, String yb, String yb_name) {
        GeneralEntity entity = new GeneralEntity();
        entity.setName(name);
        entity.setRead(read);
        entity.setSlow_read(slow_read);

        entity.setYb(yb);
        String[] yb_array = yb.split(",");
        entity.setYb_array(yb_array);

        entity.setYb_name(yb_name);
        String[] yb_name_array = yb_name.split(",");
        entity.setYb_name_array(yb_name_array);

        //关联的音标
        List<PhoneticsEntity.VoiceEty> vList = new ArrayList<PhoneticsEntity.VoiceEty>();
        List<PhoneticsEntity.VoiceEty> allVList = CurrentPhonetics.instance().getCurrentVoiceList();
        for (String string : yb_name_array) {
            for (PhoneticsEntity.VoiceEty vEty : allVList) {
                if (string.equals(vEty.getName())) {
                    vList.add(vEty);
                    break;
                }
            }
        }
        entity.setvList(vList);
        return entity;
    }


    /**
     * 合并播放
     *
     * @param type 0:正常  1：慢速
     * @param eEty
     */
    public static void playMergeMedia(Context context, int type, GeneralEntity eEty) {
        List<PhoneticsEntity.VoiceEty> list = eEty.getvList();
        StringBuffer pics = new StringBuffer();
        if (CurrentPhonetics.instance().voiceType == CurrentPhonetics.VoiceType.ADVANCE) {
            //高级
            pics.append(eEty.getAdvanced_pic() + ",");
        } else {
            //基础
            for (PhoneticsEntity.VoiceEty ety : list) {
                pics.append(ety.getPics_front() + ",");
            }
        }
        String picsStr = "";
        if (pics != null && pics.length() > 1) {
            picsStr = pics.toString().substring(0, pics.length() - 1);
        }
        String[] picArray = picsStr.split(",");
        String[] resouce = null;
        if (picArray != null && picArray.length > 0) {
            resouce = new String[picArray.length];
        }

        int[] data = null;
        if (0 == type) {
            //正常
            data = PlayUtil.getStepVoicesData(eEty.getRead());
        } else if (1 == type) {
            //慢速
            data = PlayUtil.getStepVoicesData(eEty.getSlow_read());
        }
        int start_time = data[0];
        int delay_time = data[1];

        /////动画
        if (DetailsActivity.faceSide == 0) {
            //播放动画
            for (int i = 0; i < picArray.length; i++) {
                resouce[i] = FileNameUtil.replace(picArray[i]);
            }
            AnimationUtil.startAnimation(context, DetailsActivity.iv_front, resouce, delay_time);
        }
        if (DetailsActivity.faceSide == 1) {
            //播放动画
            for (int i = 0; i < picArray.length; i++) {
                resouce[i] = "c" + FileNameUtil.replace(picArray[i]);
            }
            AnimationUtil.startAnimation(context, DetailsActivity.iv_side, resouce, delay_time);
        }
        //////音乐
        MediaPlayerUtil.start(start_time, delay_time);
    }
}
