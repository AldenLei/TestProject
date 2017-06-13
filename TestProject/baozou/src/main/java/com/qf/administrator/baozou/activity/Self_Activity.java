package com.qf.administrator.baozou.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.entity.LoginInfoBean;
import com.qf.administrator.baozou.utils.SharedPreferencesUtils;
import com.qf.administrator.baozou.views.CircleImageView;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 个人界面
 */
public class Self_Activity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @InjectView(R.id.ig_self_menu)
    ImageView igSelfMenu;
    @InjectView(R.id.ig_self_back)
    ImageView igSelfBack;
    @InjectView(R.id.img_head)
    CircleImageView imgHead;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.shouCang_num)
    TextView shouCangNum;
    @InjectView(R.id.ll_shoucang)
    LinearLayout llShoucang;
    @InjectView(R.id.pingLun_num)
    TextView pingLunNum;
    @InjectView(R.id.ll_pinglun)
    LinearLayout llPinglun;
    @InjectView(R.id.yueDu_num)
    TextView yueDuNum;
    @InjectView(R.id.ll_yuedu)
    LinearLayout llYuedu;
    @InjectView(R.id.tougao_num)
    TextView tougaoNum;
    @InjectView(R.id.ll_tougao)
    LinearLayout llTougao;
    @InjectView(R.id.tv_mission_pro)
    TextView tvMissionPro;
    @InjectView(R.id.sign_Coin_num)
    TextView signCoinNum;
    @InjectView(R.id.tv_self_isDone)
    TextView tvSelfIsDone;
    @InjectView(R.id.sign_Coin_num2)
    TextView signCoinNum2;
    @InjectView(R.id.isDone_article)
    TextView isDoneArticle;
    @InjectView(R.id.sign_Coin_num3)
    TextView signCoinNum3;
    @InjectView(R.id.isDone_comment)
    TextView isDoneComment;
    @InjectView(R.id.sign_Coin_num4)
    TextView signCoinNum4;
    @InjectView(R.id.isDone_like)
    TextView isDoneLike;
    @InjectView(R.id.sign_Coin_num11)
    TextView signCoinNum11;
    @InjectView(R.id.isDone_like_comment)
    TextView isDoneLikeComment;
    @InjectView(R.id.share_Coin_num)
    TextView shareCoinNum;
    @InjectView(R.id.isDone_share)
    TextView isDoneShare;
    @InjectView(R.id.ig_self_off)
    ImageView igSelfOff;
    @InjectView(R.id.ig_self_on)
    ImageView igSelfOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_);
        ButterKnife.inject(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPreferencesUtils.isLogin(this)){
            Gson gson = new Gson();
            LoginInfoBean infoBean =gson.fromJson(SharedPreferencesUtils.getLoginInfo(this,"jsoninfo"), LoginInfoBean.class);
            Glide.with(this).load(infoBean.getIcon()).placeholder(R.mipmap.avatar_feedback).into(imgHead);
            tvName.setText(infoBean.getNickname());
        }
    }


    @OnClick({R.id.ig_self_menu, R.id.ig_self_back, R.id.img_head, R.id.tv_name, R.id.ll_shoucang, R.id.ll_pinglun, R.id.ll_yuedu, R.id.ll_tougao, R.id.tv_mission_pro, R.id.ig_self_off, R.id.ig_self_on})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ig_self_menu:
                PopupMenu popupMenu = new PopupMenu(this,igSelfMenu);
                if(true) {//日间还是夜间
                    getMenuInflater().inflate(R.menu.more, popupMenu.getMenu());
                }else{
                    getMenuInflater().inflate(R.menu.more_day, popupMenu.getMenu());
                }
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(Self_Activity.this);
                break;
            case R.id.ig_self_back:
                finish();
                break;
            case R.id.img_head:
                if(!SharedPreferencesUtils.isLogin(this)) {//未登录
                    startActivity(new Intent(this, LoginActivity.class));
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    //builder.setIcon(R.drawable.ic_launcher);
                    builder.setTitle("修改头像");
                    //    指定下拉列表的显示数据
                    final String[] cities = {"拍照", "从相册选择"};
                    //    设置一个下拉的列表选择项
                    builder.setItems(cities, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            switch (which){
                                case 0:
                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 启动系统相机
                                    startActivityForResult(intent, 1);
                                    break;
                                case 1:
                                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    // 设定结果返回
                                    startActivityForResult(i, 2);
                                    break;
                            }
                        }
                    });
                    builder.show();
                }
                break;
            case R.id.tv_name:
                if(!SharedPreferencesUtils.isLogin(this)) {
                    startActivity(new Intent(this, LoginActivity.class));
                }else{

                }
                break;
            case R.id.ll_shoucang:
                break;
            case R.id.ll_pinglun:
                break;
            case R.id.ll_yuedu:
                break;
            case R.id.ll_tougao:
                break;
            case R.id.tv_mission_pro:
                break;
            case R.id.ig_self_off:
                break;
            case R.id.ig_self_on:
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_refresh:
                break;
            case R.id.menu_night_theme:
                break;
            case R.id.menu_day_theme:
                break;
            case R.id.menu_setting:
                break;
        }
        return false;
    }
}
