package com.qf.administrator.baozou;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public   class  BaseActivity extends AppCompatActivity {
    private SharedPreferences  preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //存数据
        //1.获取一个SharedPreferences对象   文件名    对文件操作的模式（一般都是用的私有模式）
        preferences = getSharedPreferences("text", MODE_PRIVATE);
        int textSize = preferences.getInt("textSize",0);

        if(textSize==2)
        {
            setTheme(R.style.bigTextSize);
        }
        else if(textSize==1)
        {
            setTheme(R.style.smallTextSize);
        }

        setContentView(R.layout.activity_base);

    }

}
