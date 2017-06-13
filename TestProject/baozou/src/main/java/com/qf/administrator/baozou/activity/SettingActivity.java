package com.qf.administrator.baozou.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qf.administrator.baozou.BaseActivity;
import com.qf.administrator.baozou.MainActivity;
import com.qf.administrator.baozou.MyApp;
import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.utils.SharedPreferencesUtils;
import com.zcw.togglebutton.ToggleButton;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class SettingActivity extends BaseActivity {
    ToggleButton tog_offlineDown;
    ToggleButton tog_articleBigText;
    ToggleButton tog_newMsg;
    @InjectView(R.id.btn_back_login)
    Button btnBackLogin;
    @InjectView(R.id.tv_cache_num)
    TextView tvCacheNum;
    private SharedPreferences preferences;
    @InjectView(R.id.setting_back)
    ImageView settingBack;
    @InjectView(R.id.tog_offlineDown)
    ToggleButton togOfflineDown;
    @InjectView(R.id.smallPicMode)
    RelativeLayout smallPicMode;
    @InjectView(R.id.tog_articleBigText)
    ToggleButton togArticleBigText;
    @InjectView(R.id.tog_newMsg)
    ToggleButton togNewMsg;
    @InjectView(R.id.cacheClear)
    RelativeLayout cacheClear;
    @InjectView(R.id.cacheDirText)
    TextView cacheDirText;
    @InjectView(R.id.cacheDir)
    RelativeLayout cacheDir;
    @InjectView(R.id.help)
    RelativeLayout help;
    @InjectView(R.id.share)
    RelativeLayout share;
    @InjectView(R.id.appRecommend)
    RelativeLayout appRecommend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);
        ButterKnife.inject(this);
        if (SharedPreferencesUtils.isLogin(this)) {
            btnBackLogin.setVisibility(View.VISIBLE);
        }
        tog_offlineDown = (ToggleButton) findViewById(R.id.tog_offlineDown);
        tog_articleBigText = (ToggleButton) findViewById(R.id.tog_articleBigText);
        tog_newMsg = (ToggleButton) findViewById(R.id.tog_newMsg);
        /*//切换开关
        toggleBtn.toggle();
        //切换无动画
        toggleBtn.toggle(false);
            toggleBtn.setToggleOn();
        toggleBtn.setToggleOff();
        //无动画切换
        toggleBtn.setToggleOn(false);
        toggleBtn.setToggleOff(false);

        //禁用动画
        toggleBtn.setAnimate(false);*/
        //开关切换事件
        //存数据
        //1.获取一个SharedPreferences对象   文件名    对文件操作的模式（一般都是用的私有模式）
        preferences = getSharedPreferences("text", MODE_PRIVATE);
        int textSize = preferences.getInt("textSize", 0);

        if (textSize == 2) {
            tog_articleBigText.setToggleOn();
        } else if (textSize == 1) {
            tog_articleBigText.setToggleOff();
        }

        tog_offlineDown.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (on) {
                    Toast.makeText(SettingActivity.this, "离线模式已开启", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SettingActivity.this, "离线模式已关闭", Toast.LENGTH_LONG).show();
                }
            }
        });
        tog_articleBigText.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (on) {

                    Toast.makeText(SettingActivity.this, "大字体已开启", Toast.LENGTH_LONG).show();

                    //2.通过SharedPreferences对象，获取到一个编辑对象
                    SharedPreferences.Editor edit = preferences.edit();
                    //3.通过编辑对象，存储要保存的数据
                    edit.putInt("textSize", 2);
                    //4.提交数据
                    edit.commit();

                } else {
                    Toast.makeText(SettingActivity.this, "大字体已关闭", Toast.LENGTH_LONG).show();
                    //2.通过SharedPreferences对象，获取到一个编辑对象
                    SharedPreferences.Editor edit = preferences.edit();
                    //3.通过编辑对象，存储要保存的数据
                    edit.putInt("textSize", 1);
                    //4.提交数据
                    edit.commit();
                }
            }
        });
        tog_newMsg.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (on) {
                    Toast.makeText(SettingActivity.this, "新通知已开启", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SettingActivity.this, "新通知已关闭", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @OnClick({R.id.setting_back, R.id.smallPicMode, R.id.cacheClear,
            R.id.cacheDir, R.id.help, R.id.share, R.id.btn_back_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.smallPicMode:
                break;
            case R.id.cacheClear:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("清除缓存？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvCacheNum.setText("0.0M");
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            case R.id.cacheDir:
                showCustomDialog();
                break;
            case R.id.help:
                startActivity(new Intent(SettingActivity.this, HelpActivity.class));
                break;
            case R.id.share://分享

                break;
            case R.id.btn_back_login:
                ShareSDK.initSDK(this);
                final Platform qq = ShareSDK.getPlatform(QQ.NAME);
                if (qq.isAuthValid()) {
                    AlertDialog.Builder builderCathe = new AlertDialog.Builder(this);
                    builderCathe.setTitle("退出登录");
                    builderCathe.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builderCathe.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            qq.removeAccount(true);
                            Log.e("TAG", "取消授权");
                            SharedPreferencesUtils.saveLoginInfo(SettingActivity.this, "isLogin", "0");
                            startActivity(new Intent(SettingActivity.this, MainActivity.class));
                        }
                    });
                    builderCathe.show();

                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    public void showCustomDialog() {
        final AlertDialog customDialog;
        EditText et_dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());

        long blockSize;
        long totalBlocks;
        long availableBlocks;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = stat.getBlockSizeLong();
            totalBlocks = stat.getBlockCountLong();
            availableBlocks = stat.getAvailableBlocksLong();
        } else {
            blockSize = stat.getBlockSize();
            totalBlocks = stat.getBlockCount();
            availableBlocks = stat.getAvailableBlocks();
        }

        String sd_avail = formatSize(availableBlocks * blockSize);
        String totalBlocks_avail = formatSize(totalBlocks * blockSize);


        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        builder.setView(dialogView);
        customDialog = builder.create();
        customDialog.show();

        TextView tv = (TextView) dialogView.findViewById(R.id.tv_sdcontainer);
        tv.setText(sd_avail + "/" + totalBlocks_avail);
        TextView btnSureButton = (TextView) dialogView.findViewById(R.id.btnSure);
        TextView btnCancleButton = (TextView) dialogView.findViewById(R.id.btnCancel);

        btnCancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
        btnSureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
    }

    private String formatSize(long size) {
        return Formatter.formatFileSize(this, size);
    }

}
