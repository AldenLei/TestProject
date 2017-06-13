package com.qf.administrator.baozou.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.qf.administrator.baozou.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Alden on 2017/5/9.
 */

public class ShareDialog extends Dialog {
    @InjectView(R.id.rb_share_qq)
    RadioButton rbShareQq;
    @InjectView(R.id.rb_add_favourity)
    RadioButton rbAddFavourity;
    @InjectView(R.id.rb_copy_link)
    RadioButton rbCopyLink;
    @InjectView(R.id.rb_weixin)
    RadioButton rbWeixin;
    @InjectView(R.id.rb_friend_cir)
    RadioButton rbFriendCir;
    @InjectView(R.id.rb_qq_zone)
    RadioButton rbQqZone;
    @InjectView(R.id.rb_sina_weibo)
    RadioButton rbSinaWeibo;
    @InjectView(R.id.rb_more)
    RadioButton rbMore;

    private Context context;

    private OnMyDialogClickListener onmyListener;

    public interface OnMyDialogClickListener {
        public void onShareQQ();

        public void onAddFavourity();

        public void onCopyLink();
    }

    public ShareDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_my_layout);
        ButterKnife.inject(this);
        //按空白处能取消动画
        setCanceledOnTouchOutside(true);
        setRbImageSize();


    }

    private void setRbImageSize() {
        Drawable drawable1 = context.getResources().getDrawable(R.mipmap.shareweixin_selector);
        drawable1.setBounds(0, 0, 120, 120);
        rbWeixin.setCompoundDrawables(null, drawable1, null, null);
        Drawable drawable2 = context.getResources().getDrawable(R.mipmap.shareweixin_friends_selector);
        drawable2.setBounds(0, 0, 120, 120);
        rbFriendCir.setCompoundDrawables(null, drawable2, null, null);
        Drawable drawable3 = context.getResources().getDrawable(R.mipmap.shareqq_selector);
        drawable3.setBounds(0, 0, 120, 120);
        rbShareQq.setCompoundDrawables(null, drawable3, null, null);
        Drawable drawable4 = context.getResources().getDrawable(R.mipmap.shareqzone_selector);
        drawable4.setBounds(0, 0, 120, 120);
        rbQqZone.setCompoundDrawables(null, drawable4, null, null);
        Drawable drawable5 = context.getResources().getDrawable(R.mipmap.sharesinaweibo_selector);
        drawable5.setBounds(0, 0, 120, 120);
        rbSinaWeibo.setCompoundDrawables(null, drawable5, null, null);
        Drawable drawable6 = context.getResources().getDrawable(R.mipmap.btn_article_share_favourites_pre);
        drawable6.setBounds(0, 0, 120, 120);
        rbAddFavourity.setCompoundDrawables(null, drawable6, null, null);
        Drawable drawable7 = context.getResources().getDrawable(R.mipmap.shareslink_selector);
        drawable7.setBounds(0, 0, 120, 120);
        rbCopyLink.setCompoundDrawables(null, drawable7, null, null);
        Drawable drawable8 = context.getResources().getDrawable(R.mipmap.btn_article_share_more_nor);
        drawable8.setBounds(0, 0, 120, 120);
        rbMore.setCompoundDrawables(null, drawable8, null, null);
    }

    public void setOnMyDilogListener(OnMyDialogClickListener listener) {
        this.onmyListener = listener;
    }

    @OnClick({R.id.rb_share_qq, R.id.rb_add_favourity, R.id.rb_copy_link})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_share_qq:
                onmyListener.onShareQQ();
                break;
            case R.id.rb_add_favourity:
                onmyListener.onAddFavourity();
                break;
            case R.id.rb_copy_link:
                onmyListener.onCopyLink();
                break;
        }
    }
}
