package com.qf.administrator.baozou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.qf.administrator.baozou.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class BaozouManhuaActivity extends AppCompatActivity {

    @InjectView(R.id.ig_self_back)
    ImageView igSelfBack;
    @InjectView(R.id.btn_baozou_prom)
    Button btnBaozouProm;
    @InjectView(R.id.ed_acount)
    EditText edAcount;
    @InjectView(R.id.ed_psw)
    EditText edPsw;
    @InjectView(R.id.btn_bz_login)
    Button btnBzLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baozou_manhua);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.ig_self_back, R.id.btn_bz_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ig_self_back:
                finish();
                break;
            case R.id.btn_bz_login:
                if(TextUtils.isEmpty(edAcount.getText())){
                    doBeforeLogin("请输入用户名");
                    return;

                }
                if(TextUtils.isEmpty(edPsw.getText())){
                    doBeforeLogin("请输入密码");
                    return;
                }
                //登录操作

                break;
        }
    }

    private void doBeforeLogin(String prom) {
        btnBaozouProm.setVisibility(View.VISIBLE);
        btnBaozouProm.setText(prom);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                btnBaozouProm.post(new Runnable() {
                    @Override
                    public void run() {
                       btnBaozouProm.setVisibility(View.GONE);
                    }
                });
            }
        },2000);
    }
}
