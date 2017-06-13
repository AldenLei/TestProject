package com.qf.administrator.baozou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.activity.Pindao_Detail_Activity;
import com.qf.administrator.baozou.adapter.Pindao_Adapter;
import com.qf.administrator.baozou.callback.ResultCallBack;
import com.qf.administrator.baozou.entity.Pindao_itemBean;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import okhttp3.Call;

/**
 * Created by FHP on 2016/11/15.
 */
public class PinDao_Fg extends Fragment{
    private PullToRefreshListView ptr_listview;
    private static String URL="http://dailyapi.ibaozou.com/api/v31/channels/index?page=",URL_LAST="&per_page=10&";
    private static int page=1;
    private Pindao_Adapter adapter;
    private List<Pindao_itemBean.DataBean> data;
    private boolean tag=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getContext()).inflate(R.layout.pindao_fg, null);

        ptr_listview = ((PullToRefreshListView) view.findViewById(R.id.ptr_lv));

        tag=false;
        OkHttpUtils.get().url(URL+page+URL_LAST).build()
                .execute(new ResultCallBack<Pindao_itemBean>() {



                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Pindao_itemBean response, int id) {
                        data = response.getData();
                        adapter=new Pindao_Adapter(data,getContext());

                        ptr_listview.setAdapter(adapter);
                        tag=true;
                        ptr_listview.setOnScrollListener(new AbsListView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(AbsListView view, int scrollState) {

                            }

                            @Override
                            public void onScroll(final AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                                int position = view.getLastVisiblePosition();
                                if (tag==true){
                                    if (position==(view.getCount()-1)){
                                        Log.i("info","到最后了！！！");
                                        page=page+1;
                                        OkHttpUtils.get().url(URL+page+URL_LAST).build()
                                                .execute(new ResultCallBack<Pindao_itemBean>() {


                                                    @Override
                                                    public void onError(Call call, Exception e, int id) {

                                                    }

                                                    @Override
                                                    public void onResponse(Pindao_itemBean response, int id) {
                                                        if (data!=null){
                                                            data.addAll(response.getData());
                                                            adapter.notifyDataSetChanged();
                                                        }

                                                    }
                                                });
                                    }

                                }

                            }
                        });
                    }
                });




        ptr_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int id1 = data.get(position).getId();
                int uid = id1-1;
                Intent intent = new Intent(getContext(), Pindao_Detail_Activity.class);

                intent.putExtra("id",uid);

                getContext().startActivity(intent);


            }
        });
        return view;
    }
}
