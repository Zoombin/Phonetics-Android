package phonetics.android.view;

import java.io.Serializable;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

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

    public ShareDialog(Context context) {
        this.context = context;

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

                break;
            default:
                break;
        }
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
        // 注意：在微信授权的时候，必须传递appSecret
        // wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
        String appId = "wx501bd7cea77cc83a";
        String appSecret = "89f629c822b71cabfe761f96265b4f71";
        // 添加微信平台
    }
}
