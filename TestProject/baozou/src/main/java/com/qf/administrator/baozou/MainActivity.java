package com.qf.administrator.baozou;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qf.administrator.baozou.activity.ReadActivity;
import com.qf.administrator.baozou.activity.Self_Activity;
import com.qf.administrator.baozou.entity.LoginInfoBean;
import com.qf.administrator.baozou.fragment.DawerIndexFragment;
import com.qf.administrator.baozou.activity.ModeActivity;
import com.qf.administrator.baozou.activity.OfflineDownActivity;
import com.qf.administrator.baozou.activity.SettingActivity;
import com.qf.administrator.baozou.utils.SharedPreferencesUtils;
import com.qf.administrator.baozou.views.CircleImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.frame_replace)
    FrameLayout frameReplace;
    @InjectView(R.id.draw_left)
    RelativeLayout drawLeft;
    @InjectView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @InjectView(R.id.circleview)
    CircleImageView circleview;
    @InjectView(R.id.tv_login)
    TextView tvLogin;
    @InjectView(R.id.rb_collect)
    RadioButton rbCollect;
    @InjectView(R.id.rb_coment)
    RadioButton rbComent;
    @InjectView(R.id.rb_read)
    RadioButton rbRead;
    @InjectView(R.id.rl_top)
    RelativeLayout rlTop;
    @InjectView(R.id.rb_home)
    RadioButton rbHome;
    @InjectView(R.id.rb_bang)
    RadioButton rbBang;
    @InjectView(R.id.rb_channal)
    RadioButton rbChannal;
    @InjectView(R.id.rb_search)
    RadioButton rbSearch;
    @InjectView(R.id.rb_setting)
    RadioButton rbSetting;
    @InjectView(R.id.rb_down)
    RadioButton rbDown;
    @InjectView(R.id.rd_group)
    RadioGroup rdGroup;
    @InjectView(R.id.tv_night)
    Button tvNight;
    private FragmentManager manager;

    private  DawerIndexFragment dawerIndexFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        //加载首页的Fragment
        dawerIndexFragment = new DawerIndexFragment();
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_replace, dawerIndexFragment).commit();

        initleftview();

    }

    //为首页Frament提供公共方法打开DrawerLayout
    public void openOrCloseDrawer() {
        if (!drawerlayout.isDrawerOpen(Gravity.LEFT)) {
            drawerlayout.openDrawer(Gravity.LEFT);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initleftview() {
        Drawable drawablesearch = getResources().getDrawable(R.drawable.ic_resident_search);
        drawablesearch.setBounds(0, 0, 80, 80);
        Drawable drawablehome = getResources().getDrawable(R.drawable.ic_explore_home_normal);
        drawablehome.setBounds(0, 0, 80, 80);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        Drawable drawablebang = getResources().getDrawable(R.drawable.ic_explore_subscribe_normal);
        drawablebang.setBounds(0, 0, 80, 80);
        Drawable drawablechannal = getResources().getDrawable(R.drawable.ic_explore_video_normal);
        drawablechannal.setBounds(0, 0, 80, 80);
        Drawable drawablesetting = getResources().getDrawable(R.drawable.ic_explore_tools_normal);
        drawablesetting.setBounds(0, 0, 80, 80);
        Drawable drawabledown = getResources().getDrawable(R.drawable.ic_explore_mything_normal);
        drawabledown.setBounds(0, 0, 80, 80);
        Drawable drawablenight = getResources().getDrawable(R.mipmap.icon_sidebar_night);
        drawablenight.setBounds(0, 0, 60, 60);

        Drawable drawableCollect = getResources().getDrawable(R.mipmap.icon_sidebar_favourite);
        drawableCollect.setBounds(0, 0, 60, 60);
        Drawable drawableComment = getResources().getDrawable(R.mipmap.icon_sidebar_comment);
        drawableComment.setBounds(0, 0, 60, 60);
        Drawable drawableRead = getResources().getDrawable(R.mipmap.icon_sidebar_history);
        drawableRead.setBounds(0, 0, 60, 60);
        rbCollect.setCompoundDrawables(drawableCollect,null,null,null);
        rbComent.setCompoundDrawables(drawableComment,null,null,null);
        rbRead.setCompoundDrawables(drawableRead,null,null,null);

        rbHome.setCompoundDrawables(drawablehome,null,null,null);
        rbBang.setCompoundDrawables(drawablebang,null,null,null);
        rbChannal.setCompoundDrawables(drawablechannal,null,null,null);
        rbSetting.setCompoundDrawables(drawablesetting,null,null,null);
        rbSearch.setCompoundDrawables(drawablesearch,null,null,null);
        rbDown.setCompoundDrawables(drawabledown,null,null,null);
        tvNight.setCompoundDrawables(drawablenight,null,null,null);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //changeTheme(false);
        //第一次打开加载主题
        if(!SharedPreferencesUtils.isDayTheme(this)){

            tvNight.setText("日间模式");
            circleview.setBorderColor(getResources().getColor(R.color.color_Light_text_gray));
            rlTop.setBackgroundColor(getResources().getColor(R.color.color_Light_text_gray));
            drawLeft.setBackgroundColor(getResources().getColor(R.color.color_Light_text_gray));
            tvLogin.setTextColor(getResources().getColor(R.color.background_white));
            tvNight.setTextColor(getResources().getColor(R.color.background_white));
            rbCollect.setTextColor(getResources().getColor(R.color.background_white));
            rbComent.setTextColor(getResources().getColor(R.color.background_white));
            rbRead.setTextColor(getResources().getColor(R.color.background_white));
            rbHome.setTextColor(getResources().getColor(R.color.background_white));
            rbSearch.setTextColor(getResources().getColor(R.color.background_white));
            rbSetting.setTextColor(getResources().getColor(R.color.background_white));
            rbChannal.setTextColor(getResources().getColor(R.color.background_white));
            rbDown.setTextColor(getResources().getColor(R.color.background_white));
            rbBang.setTextColor(getResources().getColor(R.color.background_white));
            dawerIndexFragment.changeTheme(false,this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.color_Light_text_gray));
            }
        }else{

            tvNight.setText("夜间模式");
            circleview.setBorderColor(getResources().getColor(R.color.backgroud_red));
            drawLeft.setBackgroundColor(getResources().getColor(R.color.background_white));
            rlTop.setBackgroundColor(getResources().getColor(R.color.backgroud_red));
            tvLogin.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            tvNight.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbCollect.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbComent.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbRead.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbHome.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbSearch.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbSetting.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbChannal.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbDown.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbBang.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            dawerIndexFragment.changeTheme(false,this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//改变状态栏颜色
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.backgroud_red));
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SharedPreferencesUtils.isLogin(this)) {
            Gson gson = new Gson();
            LoginInfoBean infoBean = gson.fromJson(SharedPreferencesUtils.getLoginInfo(this, "jsoninfo"), LoginInfoBean.class);
            Glide.with(this).load(infoBean.getIcon()).placeholder(R.mipmap.avatar_feedback).into(circleview);
            tvLogin.setText(infoBean.getNickname());
        }

    }

    @OnClick({R.id.circleview, R.id.tv_login, R.id.rb_collect, R.id.rb_coment, R.id.rb_read, R.id.rl_top, R.id.rb_home, R.id.rb_bang, R.id.rb_channal, R.id.rb_search, R.id.rb_setting, R.id.rb_down, R.id.rd_group, R.id.tv_night})
    public void onClick_two(View view) {
        switch (view.getId()) {
            case R.id.circleview:
                startActivity(new Intent(this, Self_Activity.class));
                break;
            case R.id.tv_login:
                startActivity(new Intent(this, Self_Activity.class));
                break;
            case R.id.rb_collect:
                //收藏

                startActivity(new Intent(this, ReadActivity.class));
                break;
            case R.id.rb_coment:
                //评论
                break;
            case R.id.rb_read:
                //阅读
                startActivity(new Intent(this, ReadActivity.class));
                break;
            case R.id.rl_top:
                break;
            case R.id.rb_home:
                //主页
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.rb_bang:
                break;
            case R.id.rb_channal:
                break;
            case R.id.rb_search:
                break;
            case R.id.rb_setting:
                startActivity(new Intent(this,SettingActivity.class));
                break;
            case R.id.rb_down:
                startActivity(new Intent(this,OfflineDownActivity.class));
                break;
            case R.id.rd_group:
                break;
            case R.id.tv_night:
                //startActivity(new Intent(this,ModeActivity.class));
                changeTheme(true);
                break;
        }
    }

    public void changeTheme(boolean isSaveStatus) {
        Log.e("TAG","click"+SharedPreferencesUtils.isDayTheme(this));
        if(SharedPreferencesUtils.isDayTheme(this)){//现在主题是白天，设置为晚上
            if(isSaveStatus){
                SharedPreferencesUtils.saveTheme(this,"isDayTheme","1");
            }
            tvNight.setText("日间模式");
            circleview.setBorderColor(getResources().getColor(R.color.color_Light_text_gray));
            rlTop.setBackgroundColor(getResources().getColor(R.color.color_Light_text_gray));
            drawLeft.setBackgroundColor(getResources().getColor(R.color.color_Light_text_gray));
            tvLogin.setTextColor(getResources().getColor(R.color.background_white));
            tvNight.setTextColor(getResources().getColor(R.color.background_white));
            rbCollect.setTextColor(getResources().getColor(R.color.background_white));
            rbComent.setTextColor(getResources().getColor(R.color.background_white));
            rbRead.setTextColor(getResources().getColor(R.color.background_white));
            rbHome.setTextColor(getResources().getColor(R.color.background_white));
            rbSearch.setTextColor(getResources().getColor(R.color.background_white));
            rbSetting.setTextColor(getResources().getColor(R.color.background_white));
            rbChannal.setTextColor(getResources().getColor(R.color.background_white));
            rbDown.setTextColor(getResources().getColor(R.color.background_white));
            rbBang.setTextColor(getResources().getColor(R.color.background_white));
            dawerIndexFragment.changeTheme(false,this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.color_Light_text_gray));
            }
        }else{//晚上设置为白天
            if(isSaveStatus){
                SharedPreferencesUtils.saveTheme(this,"isDayTheme","0");
            }
            tvNight.setText("夜间模式");
            circleview.setBorderColor(getResources().getColor(R.color.backgroud_red));
            drawLeft.setBackgroundColor(getResources().getColor(R.color.background_white));
            rlTop.setBackgroundColor(getResources().getColor(R.color.backgroud_red));
            tvLogin.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            tvNight.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbCollect.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbComent.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbRead.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbHome.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbSearch.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbSetting.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbChannal.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbDown.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            rbBang.setTextColor(getResources().getColor(R.color.color_Light_text_black));
            dawerIndexFragment.changeTheme(false,this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//改变状态栏颜色
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.backgroud_red));
            }

        }
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();

    }
}
