package com.qf.administrator.baozou.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.adapter.MyIndexFragmentAdapter;
import com.qf.administrator.baozou.fragment.CommentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CommentActivity extends AppCompatActivity {

    @InjectView(R.id.img_opendraw)
    ImageView imgOpendraw;
    @InjectView(R.id.conment_tablayou)
    TabLayout commentTablayou;
    @InjectView(R.id.conment_viewpager)
    ViewPager commentViewpager;
    @InjectView(R.id.ed_input_comment)
    EditText edInputComment;
    @InjectView(R.id.ig_send_comment)
    ImageView igSendComment;
    private String documentId, hotUrl, latestUrl, parentName;
    private List<Fragment> list = new ArrayList<>();
    private MyIndexFragmentAdapter adapter;//TabLayout与ViewPager联动

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.inject(this);
        documentId = getIntent().getStringExtra("id");
        parentName = getIntent().getStringExtra("name");
        Log.e("TAG", "==" + documentId + "==" + parentName);
        hotUrl = "http://dailyapi.ibaozou.com/api/v31/documents/" + documentId + "/comments/hot";
        latestUrl = "http://dailyapi.ibaozou.com/api/v31/documents/" + documentId + "/comments/latest";

        Bundle bundle = new Bundle();
        bundle.putString("id", latestUrl);
        CommentFragment fragmentLaytest = new CommentFragment();
        fragmentLaytest.setArguments(bundle);
        list.add(fragmentLaytest);
        Bundle bundle2 = new Bundle();
        bundle2.putString("id", hotUrl);
        CommentFragment fragmentHot = new CommentFragment();
        fragmentHot.setArguments(bundle2);
        list.add(fragmentHot);
        commentTablayou.addTab(commentTablayou.newTab(), 0, true);
        commentTablayou.addTab(commentTablayou.newTab(), 1, false);
        adapter = new MyIndexFragmentAdapter(getSupportFragmentManager(), list);
        //联动
        commentViewpager.setAdapter(adapter);
        commentTablayou.setupWithViewPager(commentViewpager);
        commentTablayou.getTabAt(0).setText("最新");
        commentTablayou.getTabAt(1).setText("热门");
        edInputComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("TAG tetx","1");
                if(!TextUtils.isEmpty(s.toString())||s.toString().length()!=0){
                    igSendComment.setImageDrawable(getResources().getDrawable(R.mipmap.btn_wordsend_pre));
                }else{
                    igSendComment.setImageDrawable(getResources().getDrawable(R.mipmap.btn_wordsend));
                }
            }
        });
    }


    @OnClick({R.id.img_opendraw, R.id.ig_send_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_opendraw:
                finish();
                break;
            case R.id.ig_send_comment:
                edInputComment.setText("");
                Toast.makeText(this,"发表成功",Toast.LENGTH_SHORT).show();
                igSendComment.setImageDrawable(getResources().getDrawable(R.mipmap.btn_wordsend));
                break;
        }
    }
}
