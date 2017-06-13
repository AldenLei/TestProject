package com.qf.administrator.baozou;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class WellcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置 加载主布局之前显示的白版
        setTheme(R.style.appLauncher);
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wellcome);

        // 设置全屏
        // 移除标题栏
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WellcomeActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.set_left,R.anim.set_right);
                finish();
            }
        },4000);
    }


}
