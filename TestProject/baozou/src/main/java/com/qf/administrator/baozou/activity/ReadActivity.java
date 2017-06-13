package com.qf.administrator.baozou.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anye.greendao.gen.DataBaseEntityDao;
import com.qf.administrator.baozou.MainActivity;
import com.qf.administrator.baozou.MyApp;
import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.adapter.ReadAdapter;
import com.qf.administrator.baozou.entity.DataBaseEntity;
import com.qf.administrator.baozou.utils.SharedPreferencesUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ReadActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.img_back_head)
    ImageView imgBackHead;
    @InjectView(R.id.img_more_read)
    ImageView imgMoreRead;
    @InjectView(R.id.img_delete_read)
    ImageView imgDeleteRead;
    @InjectView(R.id.rv_read_record)
    RecyclerView rvReadRecord;
    @InjectView(R.id.sl_read_record)
    SwipeRefreshLayout slReadRecord;
    @InjectView(R.id.tv_hint)
    TextView tvHint;

    private List<DataBaseEntity> dataList;
    private ReadAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        ButterKnife.inject(this);
        if(!SharedPreferencesUtils.isLogin(this)){
            slReadRecord.setVisibility(View.GONE);
            tvHint.setVisibility(View.VISIBLE);
            return;
        }
        dataList = MyApp.getInstances().getDaoSession().getDataBaseEntityDao().loadAll();
        Log.e("size",dataList.size()+"");
        //dataList = MyApp.getInstances().getDaoSession().getDataBaseEntityDao().queryBuilder().where(DataBaseEntityDao.Properties.ReadFlag.eq("0")).list();
        slReadRecord.setColorSchemeColors(Color.RED);
        slReadRecord.setOnRefreshListener(this);
        //设置LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvReadRecord.setLayoutManager(linearLayoutManager);
        adapter = new ReadAdapter(dataList,this);
        rvReadRecord.setAdapter(adapter);
    }

    @OnClick({R.id.img_back_head, R.id.img_more_read, R.id.img_delete_read})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back_head:
                finish();
                break;
            case R.id.img_more_read:
                break;
            case R.id.img_delete_read:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("清除阅读记录？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       /* List<DataBaseEntity> entityList = MyApp.getInstances().getDaoSession().getDataBaseEntityDao().queryBuilder().where(DataBaseEntityDao.Properties.ReadFlag.eq("1")).list();
                        for (DataBaseEntity d:entityList) {
                            MyApp.getInstances().getDaoSession().getDataBaseEntityDao().delete(d);
                        }*/
                        MyApp.getInstances().getDaoSession().getDataBaseEntityDao().deleteAll();
                        dialog.dismiss();
                        recreate();
                    }
                });
                builder.show();

                break;
        }
    }

    @Override
    public void onRefresh() {
        slReadRecord.setRefreshing(false);
    }
}
