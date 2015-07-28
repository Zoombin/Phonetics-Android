package phonetics.android.view;

import java.io.Serializable;
import java.util.HashMap;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.moments.WechatMoments;
import phonetics.android.R;

/**
 * 分享对话框
 *
 * @author daiye
 */
public class ShareDialog implements OnClickListener {
    private final static int WEIXIN_CIRCLE = 0;

    private Dialog dialog;
    private Context context;
    Handler handler;

    public ShareDialog(Context context) {
        this.context = context;
        handler = new Handler();

        addCustomPlatforms();

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.dialog_share, null);

        LinearLayout item_friends = (LinearLayout) view.findViewById(R.id.item_friends);
        item_friends.setOnClickListener(this);

        dialog = new Dialog(context, R.style.ShareDialogStyleBottom);
        dialog.setContentView(view);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_friends:
                directShare(WEIXIN_CIRCLE);
                break;
            default:
                break;
        }
    }

    /**
     * 直接分享，底层分享接口。如果分享的平台是新浪、腾讯微博、豆瓣、人人，则直接分享，无任何界面弹出； 其它平台分别启动客户端分享</br>
     */
    private void directShare(final int mPlatform) {
        dialog.dismiss();
        switch (mPlatform) {
            case WEIXIN_CIRCLE:
                // 接口说明：
                // 微信分享必须设置targetURL，需要为http链接格式
                // 微信朋友圈只能显示title，并且过长会被微信截取部分内容
                // 设置微信朋友圈分享内容
                showShare();
                break;
            default:
                break;
        }
    }

    private void showShare(){
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setTitle("测试分享的标题");
        sp.setTitleUrl("http://sharesdk.cn"); // 标题的超链接
        sp.setText("测试分享的文本");
        sp.setImageUrl("http://www.someserver.com/测试图片网络地址.jpg");
        sp.setSite("发布分享的网站名称");
        sp.setSiteUrl("发布分享网站的地址");

        Log.i("LSD","share WechatMoments");

        Platform qzone = ShareSDK.getPlatform (WechatMoments.NAME);
        qzone. setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.e("LSD","i = "+i);
                throwable.printStackTrace();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "分享失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancel(Platform platform, int i) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "分享取消", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }); // 设置分享事件回调
        // 执行图文分享
        qzone.share(sp);
    }


    /**
     * 添加所有的平台</br>
     */
    private void addCustomPlatforms() {
        // 添加微信平台
        addWXPlatform();
    }

    /**
     * @return
     * @功能描述 : 添加微信平台分享
     */
    private void addWXPlatform() {
        String appId = "wxf1ce482aa1ad2023";
        String appSecret = "1af038971efee027911b4c2e622eab74";
    }
}
