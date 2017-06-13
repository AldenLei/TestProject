package com.qf.administrator.baozou.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.adapter.Pindao_Detail_Adapter;
import com.qf.administrator.baozou.callback.ResultCallBack;
import com.qf.administrator.baozou.entity.Pindao_Detail_Bean;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import okhttp3.Call;

public class Pindao_Detail_Activity extends AppCompatActivity {
    private static String URL_PRE="http://dailyapi.ibaozou.com/api/v31/channels/";
    private Pindao_Detail_Adapter adapter;
    private List<Pindao_Detail_Bean.DataBean> data;
    private PullToRefreshListView ptr_lv;
    private TextView tv_title;
    private Pindao_Detail_Bean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pindao__detail_);

        ptr_lv = ((PullToRefreshListView) findViewById(R.id.ptr_lv));
        tv_title = (TextView) findViewById(R.id.title);

        Intent intent = getIntent();

        int id = intent.getIntExtra("id",3);

        Log.i("info",id+"==============");
        OkHttpUtils.get().url(URL_PRE+id).build()
                .execute(new ResultCallBack<Pindao_Detail_Bean>() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Pindao_Detail_Bean response, int id) {

                        bean = response;
                        tv_title.setText(response.getName());
                        data = response.getData();
                        adapter = new Pindao_Detail_Adapter(data,getApplicationContext());
                        ptr_lv.setAdapter(adapter);
                    }

                });

        ptr_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String share_url = bean.getData().get(position - 1).getShare_url();
                Intent intent1 = new Intent(getApplicationContext(),PinDao_ContentActivity.class);

                intent1.putExtra("url",share_url);

                startActivity(intent1);
            }
        });
    }
}
