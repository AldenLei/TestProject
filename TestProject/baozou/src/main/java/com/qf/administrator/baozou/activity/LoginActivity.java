package com.qf.administrator.baozou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.ig_self_back)
    ImageView igSelfBack;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.ig_login_sina)
    ImageView igLoginSina;
    @InjectView(R.id.ig_login_wechat)
    ImageView igLoginWechat;
    @InjectView(R.id.ig_login_qq)
    ImageView igLoginQq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        ShareSDK.initSDK(this);
    }

    @OnClick({R.id.ig_self_back, R.id.btn_login, R.id.ig_login_sina, R.id.ig_login_wechat, R.id.ig_login_qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ig_self_back:
                finish();
                break;
            case R.id.btn_login:
                startActivity(new Intent(this,BaozouManhuaActivity.class));
                break;
            case R.id.ig_login_sina:
                //sina登录略
                break;
            case R.id.ig_login_wechat:
                //微信登录略
                break;
            case R.id.ig_login_qq:
                //登录
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                        if(platform.getDb().exportData()!=null) {
                            Log.e("TAG",platform.getDb().exportData());//得到info的json
                            SharedPreferencesUtils.saveLoginInfo(LoginActivity.this,"jsoninfo",platform.getDb().exportData());
                            SharedPreferencesUtils.saveLoginInfo(LoginActivity.this,"isLogin","1");//是否登录，1为登录，0未登录
                            Toast.makeText(LoginActivity.this,"授权成功",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                }); // 设置分享事件回调

                //qq.authorize();//单独授权
                qq.showUser(null);//授权并获取用户信息
                break;
        }
    }
}
