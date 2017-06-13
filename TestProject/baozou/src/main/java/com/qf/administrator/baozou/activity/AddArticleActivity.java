package com.qf.administrator.baozou.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qf.administrator.baozou.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AddArticleActivity extends AppCompatActivity {

    @InjectView(R.id.article_back)
    ImageView articleBack;
    @InjectView(R.id.img_more)
    ImageView imgMore;
    @InjectView(R.id.tv_publish)
    TextView tvPublish;
    @InjectView(R.id.ed_article_title)
    EditText edArticleTitle;
    @InjectView(R.id.ed_content_title)
    EditText edContentTitle;
    @InjectView(R.id.ig_biaoqing)
    ImageView igBiaoqing;
    @InjectView(R.id.ig_image)
    ImageView igImage;
    @InjectView(R.id.ig_link)
    ImageView igLink;
    @InjectView(R.id.activity_add_article)
    LinearLayout activityAddArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.article_back, R.id.img_more, R.id.tv_publish, R.id.ig_biaoqing, R.id.ig_image, R.id.ig_link})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.article_back:
                finish();
                break;
            case R.id.img_more:
                break;
            case R.id.tv_publish:
                SystemClock.sleep(300);
                Toast.makeText(this,"提交成功，小编会尽快审核",Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.ig_biaoqing:
                break;
            case R.id.ig_image:
                break;
            case R.id.ig_link:
                break;
        }
    }
}
