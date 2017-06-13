package com.qf.administrator.baozou.activity;

import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.anye.greendao.gen.DataBaseEntityDao;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.qf.administrator.baozou.MyApp;
import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.adapter.CommentAdapter;
import com.qf.administrator.baozou.callback.ResultCallBack;
import com.qf.administrator.baozou.constanse.Constances;
import com.qf.administrator.baozou.entity.CommentBean;
import com.qf.administrator.baozou.entity.DataBaseEntity;
import com.qf.administrator.baozou.utils.ImageUtils;
import com.qf.administrator.baozou.utils.SharedPreferencesUtils;
import com.qf.administrator.baozou.utils.TimeUtils;
import com.qf.administrator.baozou.views.CircleImageView;
import com.qf.administrator.baozou.views.ScrollWebView;
import com.qf.administrator.baozou.views.ShareDialog;
import com.zhy.http.okhttp.OkHttpUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.Call;

/**
 * 首页Item的详情页
 */
public class IndexItemActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @InjectView(R.id.img_item_back)
    ImageView imgItemBack;
    @InjectView(R.id.img_setting_more)
    ImageView imgSettingMore;
    @InjectView(R.id.img_star_love)
    ImageView imgStarLove;
    @InjectView(R.id.img_comment)
    ImageView imgComment;
    @InjectView(R.id.img_vote)
    ImageView imgVote;
    @InjectView(R.id.tv_comment_num)
    TextView tvCommentNum;
    @InjectView(R.id.tv_vote_num)
    TextView tvVoteNum;
    @InjectView(R.id.item_webview)
    ScrollWebView itemWebview;
    @InjectView(R.id.ll_content)
    LinearLayout llContent;//内容容器
    @InjectView(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @InjectView(R.id.rl_detail_head_bg)
    ImageView rlDetailHeadBg;
    @InjectView(R.id.cv_detail_icon)
    CircleImageView cvDetailIcon;
    @InjectView(R.id.tv_detail_name)
    TextView tvDetailName;
    @InjectView(R.id.tv_detail_time_ago)
    TextView tvDetailTimeAgo;
    @InjectView(R.id.ll_title_author)
    LinearLayout llTitleAuthor;
    @InjectView(R.id.tv_user_tougao_title)
    TextView tvUserTougaoTitle;
    @InjectView(R.id.tv_user_tougao_name)
    TextView tvUserTougaoName;
    @InjectView(R.id.ll_user_tougao_title_name)
    LinearLayout llUserTougaoTitleName;
    @InjectView(R.id.ll_comment)
    LinearLayout llComment;
    @InjectView(R.id.btn_see_all_comment)
    Button btnSeeAllComment;
    @InjectView(R.id.sr_view)
    ScrollView srView;
    @InjectView(R.id.btn_see_all_comment_web)
    Button btnSeeAllCommentWeb;
    @InjectView(R.id.fab_add_comment)
    CircleImageView fabAddComment;
    @InjectView(R.id.rv_detail_hot_comment)
    RecyclerView rvDetailHotComment;
    @InjectView(R.id.rv_detail_latest_comment)
    RecyclerView rvDetailLatestComment;
    @InjectView(R.id.ig_placeholder_loading)
    ImageView igPlaceholderLoading;
    @InjectView(R.id.rl_loading_anim)
    RelativeLayout rlLoadingAnim;
    @InjectView(R.id.rl_detail_head)
    RelativeLayout rlDetailHead;
    @InjectView(R.id.rb_friend_circle)
    RadioButton rbFriendCircle;
    @InjectView(R.id.rb_qq_zone)
    RadioButton rbQqZone;
    @InjectView(R.id.rb_sina_weibo)
    RadioButton rbSinaWeibo;
    @InjectView(R.id.rb_more)
    RadioButton rbMore;
    private String url;
    private int vote_count;
    private int comment_count;
    private int urlSourceFlag = -1;


    private List<HashMap<String, String>> listData = new ArrayList<>();
    private LinkedHashMap<String, String> map;
    private LinearLayout.LayoutParams params;
    private int p = 0;
    private int img = 0;
    private String strStyle;
    private String title;
    private String bgImage;
    private long timestamp;
    private String authorName;
    private String authorAvator;
    private String document_id;
    private DataBaseEntity dataBaseEntity;
    private boolean stopThread = false;

    private boolean hasVote = false;

    private List<CommentBean.DataBean> listCommentHot = new ArrayList<>();
    private List<CommentBean.DataBean> listCommentLatest = new ArrayList<>();
    private CommentAdapter hotCommentAdapter;
    private CommentAdapter latestCommentAdapter;

    private AnimationDrawable animDrawable;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_item);
        ButterKnife.inject(this);
        Intent intent = getIntent();
        dataBaseEntity = (DataBaseEntity)intent.getSerializableExtra("databean");
        url = intent.getStringExtra("url");
        document_id = intent.getIntExtra("id", 0) + "";
        vote_count = intent.getIntExtra("vote_count", 0);
        comment_count = intent.getIntExtra("comment_count", 0);
        urlSourceFlag = intent.getFlags();


        tvVoteNum.setText(vote_count + "");
        tvCommentNum.setText(comment_count + "");
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 25, 0, 25);


        initWebview();

        Log.e("flag", urlSourceFlag + "");
        if (url != null && urlSourceFlag == Constances.WEIXINURLFLAG) {//加载网页

            itemWebview.setOnScrollChangeListener(new ScrollWebView.OnScrollChangeListener() {
                @Override
                public void onPageEnd(int l, int t, int oldl, int oldt) {
                    btnSeeAllCommentWeb.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPageTop(int l, int t, int oldl, int oldt) {

                }

                @Override
                public void onScrollChanged(int l, int t, int oldl, int oldt) {
                    //Log.e("Scroll",l+"="+t+"="+oldl+"="+oldt);
                    //向上滑
                    if (oldt > t) {
                        btnSeeAllCommentWeb.setVisibility(View.GONE);

                    }

                }
            });
            itemWebview.loadUrl(url);

        } else if (urlSourceFlag == Constances.BAOZOUURLFLAG) {//解析网页

            Drawable drawable1 = this.getResources().getDrawable(R.mipmap.shareweixin_friends_selector);
            drawable1.setBounds(0, 0, 130, 130);
            rbFriendCircle.setCompoundDrawables(null, drawable1, null, null);
            Drawable drawable2 = this.getResources().getDrawable(R.mipmap.btn_article_qqzone_nor);
            drawable2.setBounds(0, 0, 130, 130);
            rbQqZone.setCompoundDrawables(null, drawable2, null, null);
            Drawable drawable3 = this.getResources().getDrawable(R.mipmap.btn_article_sinaweibo_nor);
            drawable3.setBounds(0, 0, 130, 130);
            rbSinaWeibo.setCompoundDrawables(null, drawable3, null, null);
            Drawable drawable4 = this.getResources().getDrawable(R.mipmap.btn_article_share_more_nor);
            drawable4.setBounds(0, 0, 130, 130);
            rbMore.setCompoundDrawables(null, drawable4, null, null);

            llTitleAuthor.setVisibility(View.VISIBLE);
            btnSeeAllCommentWeb.setVisibility(View.GONE);
            bgImage = intent.getStringExtra("bgImage");
            title = intent.getStringExtra("title");
            authorName = intent.getStringExtra("authorName");
            authorAvator = intent.getStringExtra("avatar");
            timestamp = intent.getIntExtra("timestamp", 0);
            tvDetailTitle.setText(title);

            Glide.with(this).load(bgImage).placeholder(R.mipmap.ic_launcher).into(rlDetailHeadBg);

            Glide.with(this).load(authorAvator).asBitmap().placeholder(R.mipmap.avatar_feedback).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    cvDetailIcon.setImageBitmap(resource);
                }
            });
            tvDetailName.setText(authorName);
            tvDetailTimeAgo.setText(TimeUtils.intervalTime(timestamp));
            llContent.setVisibility(View.VISIBLE);
            initAndLoadRecyAdapter();
            loadBaozouUrlContent();

        } else if (urlSourceFlag == Constances.USERTOUGAOFLAG) {//用户投稿
            title = intent.getStringExtra("title");
            authorName = intent.getStringExtra("authorName");
            timestamp = intent.getIntExtra("timestamp", 0);
            llUserTougaoTitleName.setVisibility(View.VISIBLE);
            llContent.setVisibility(View.VISIBLE);
            tvUserTougaoName.setText(TimeUtils.formatTimeStamp(timestamp) + " " + authorName);
            tvUserTougaoTitle.setText(title.trim());
            initAndLoadRecyAdapter();
            loadBaozouUrlContent();
        }


    }

    private void initWebview() {
        itemWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                itemWebview.setVisibility(View.VISIBLE);
                fabAddComment.setVisibility(View.VISIBLE);
                rlLoadingAnim.setVisibility(View.GONE);
                animDrawable.stop();
            }
        });
        itemWebview.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = itemWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportZoom(false);
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        animDrawable = (AnimationDrawable) igPlaceholderLoading.getDrawable();
        animDrawable.start();
    }

    /**
     * Jsoup解析网页内容，然后将内容爬取下来
     */
    private void loadBaozouUrlContent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(url).get();
                    Element element = document.body();

                    Elements eleTitle = document.select("div.row.article-page-picture.pr");
                    strStyle = eleTitle.select("[style]").attr("style");
                    Pattern pattern = Pattern.compile("(http|https)(\\w|\\W)+?(jpg|png)");
                    Matcher matcher = pattern.matcher(strStyle);
                    if (matcher.find()) {
                        strStyle = matcher.group();
                    }

                    final Element content = element.select("div.content.article-content").get(1);

                    Elements elements = content.getAllElements();
                    map = new LinkedHashMap<String, String>();
                    for (Element element1 : elements) {

                        String nodeName = element1.nodeName();
                        String text = null;
                        if (nodeName.equals("p")) {
                            text = element1.text();
                            map.put("p" + p, text);
                            p++;
                            listData.add(map);
                        } else if (nodeName.equals("br")) {
                            text = "br";
                        } else if (nodeName.equals("img")) {
                            if (!TextUtils.isEmpty(element1.attr("data-original"))) {
                                text = element1.attr("data-original");
                            } else {
                                text = element1.attr("src");
                            }
                            map.put("img" + img, text);
                            img++;
                            listData.add(map);

                        } else if (nodeName.equals("a")) {
                            text = element1.attr("href");
                            map.put("a", text);
                        }

                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("LIST", listData.size() + "");
                            Log.e("MAP", map.size() + "");
                            Iterator iter = map.entrySet().iterator();

                            while (iter.hasNext()) {
                                if (stopThread) {
                                    Log.e("stopThread", "true");
                                    return;
                                }
                                //Log.e("========", "=====");
                                Map.Entry entry = (Map.Entry) iter.next();
                                String key = (String) entry.getKey();
                                final String val = (String) entry.getValue();
                                //Log.e("KEY", key);
                                //Log.e("DATA", val);
                                if (key.contains("p")) {

                                    TextView textView = new TextView(IndexItemActivity.this);
                                    if ("举报".equals(val)) {
                                        textView.setText(Html.fromHtml("<a href=\"http://daily.ibaozou.com/report/new\">举报</a>"));
                                    } else {
                                        textView.setText(val);
                                    }

                                    textView.setTextSize(16);
                                    llContent.addView(textView);
                                } else if (key.contains("img")) {

                                    final ImageView imageView = new ImageView(IndexItemActivity.this);
                                    imageView.setPadding(0, 0, 0, 0);
                                    imageView.setLayoutParams(params);
                                    // imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                    if (val.contains(".gif")) {
                                        LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                                        param.width = 1080;//直接设置宽度为1080（自己用的手机屏幕宽度，未适配其他的）
                                        imageView.setLayoutParams(param);
                                        Glide.with(IndexItemActivity.this).load(val).asGif().placeholder(R.mipmap.ic_launcher).into(imageView);

                                    } else {
                                        Glide.with(IndexItemActivity.this).load(val).asBitmap().into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                                resource = ImageUtils.cropImage(IndexItemActivity
                                                        .this, resource);
                                                LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                                                param.width = resource.getWidth();
                                                //param.height = resource.getHeight();
                                                imageView.setLayoutParams(param);
                                                imageView.setImageBitmap(resource);
                                            }
                                        });
                                    }
                                    llContent.addView(imageView);
                                }
                            }
                            srView.setVisibility(View.VISIBLE);
                            fabAddComment.setVisibility(View.VISIBLE);
                            rlLoadingAnim.setVisibility(View.GONE);
                            animDrawable.stop();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    @OnClick({R.id.img_item_back, R.id.img_setting_more,
            R.id.img_star_love, R.id.img_comment, R.id.img_vote,
            R.id.btn_see_all_comment, R.id.btn_see_all_comment_web,
            R.id.fab_add_comment,R.id.rb_friend_circle, R.id.rb_qq_zone,
            R.id.rb_sina_weibo, R.id.rb_more})
    public void onClick(View view) {
        Intent intent = new Intent(this, CommentActivity.class);
        intent.putExtra("id", document_id);
        switch (view.getId()) {
            case R.id.img_item_back:
                finish();
                break;
            case R.id.img_setting_more:
                setMenu();
                break;
            case R.id.img_star_love:
                if(!SharedPreferencesUtils.isLogin(this)){
                    startActivity(new Intent(this, LoginActivity.class));
                    Toast.makeText(this,"请先登录再收藏",Toast.LENGTH_SHORT).show();
                }else{
                    //加入数据库
                    dataBaseEntity.setReadFlag("0");//是阅读还是收藏的Flag,阅读是1
                    MyApp.getInstances().getDaoSession().getDataBaseEntityDao().update(dataBaseEntity);
                    imgStarLove.setImageDrawable(getResources().getDrawable(R.mipmap.btn_nav_favourite_pre));
                }
                break;
            case R.id.img_comment:
                startActivity(intent);
                break;
            case R.id.img_vote:
                //登录->点赞
                if(!SharedPreferencesUtils.isLogin(this)){
                    startActivity(new Intent(this, LoginActivity.class));
                    Toast.makeText(this,"请先登录再点赞",Toast.LENGTH_SHORT).show();
                }else{
                    imgVote.setImageDrawable(getResources().getDrawable(R.mipmap.btn_actionbar_like_pre));
                    if(!hasVote) {
                        int voteNum = Integer.parseInt(tvVoteNum.getText().toString().trim()) + 1;
                        tvVoteNum.setText("" + voteNum);
                        hasVote = true;
                    }
                }
                break;
            case R.id.btn_see_all_comment:
                startActivity(intent);
                break;
            case R.id.btn_see_all_comment_web:
                startActivity(intent);
                break;

            case R.id.rb_friend_circle:
                break;
            case R.id.rb_qq_zone:
                break;
            case R.id.rb_sina_weibo:
                break;
            case R.id.fab_add_comment://浮动按钮和更多按钮功能一样
                showShareDialog();
                break;
            case R.id.rb_more:
                showShareDialog();
            break;
        }
    }

    private void showShareDialog() {
        ShareDialog shareDialog = new ShareDialog(this);
        shareDialog.setOnMyDilogListener(new ShareDialog.OnMyDialogClickListener() {
        @Override
        public void onShareQQ() {//分享QQ
            Log.e("TAG","response");
            //showShare();
            if (checkInstall("com.tencent.mobileqq")) {
                shareUrl("com.tencent.mobileqq",
                        "com.tencent.mobileqq.activity.JumpActivity",
                        url);
            } else {
                toInstallWebView("http://im.qq.com/mobileqq/");
            }
        }

        @Override
        public void onAddFavourity() {//添加收藏
            //加入数据库
            dataBaseEntity.setReadFlag("0");//是阅读还是收藏的Flag,阅读是1
            MyApp.getInstances().getDaoSession().getDataBaseEntityDao().update(dataBaseEntity);
            imgStarLove.setImageDrawable(getResources().getDrawable(R.mipmap.btn_nav_favourite_pre));
        }

        @Override
        public void onCopyLink() {//拷贝链接
            // 从API11开始android推荐使用android.content.ClipboardManager
            // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            // 将文本内容放到系统剪贴板里。
            cm.setText(url);
            Toast.makeText(IndexItemActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
        }
    });
        shareDialog.show();
    }

    private void setMenu() {
        PopupMenu popupMenu = new PopupMenu(this, imgSettingMore);
        if (true) {//日间还是夜间
            getMenuInflater().inflate(R.menu.more, popupMenu.getMenu());
        } else {
            getMenuInflater().inflate(R.menu.more_day, popupMenu.getMenu());
        }
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopThread = true;
    }


    @Override
    protected void onStart() {
        super.onStart();


        //解析评论
        if (urlSourceFlag != Constances.WEIXINURLFLAG) {
            String hotUrl = "http://dailyapi.ibaozou.com/api/v31/documents/" + document_id + "/comments/hot";
            String latestUrl = "http://dailyapi.ibaozou.com/api/v31/documents/" + document_id + "/comments/latest";
            // Log.e("Comment", hotUrl + "\n" + latestUrl);
            OkHttpUtils.get().url(hotUrl).build().execute(new ResultCallBack<CommentBean>() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(CommentBean response, int id) {
                    // Log.e("Comment", "==" + response.getData().size());
                    listCommentHot.addAll(response.getData());
                    hotCommentAdapter.notifyDataSetChanged();
                }
            });
            OkHttpUtils.get().url(latestUrl).build().execute(new ResultCallBack<CommentBean>() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(CommentBean response, int id) {
                    //  Log.e("Comment", "==" + response.getData().size());
                    listCommentLatest.addAll(response.getData());
                    latestCommentAdapter.notifyDataSetChanged();
                }
            });
        }

    }

    /**
     * 初始化评论的RecyclerView，设置adapter
     */
    public void initAndLoadRecyAdapter() {
        //不设置LinearLayoutManager
        LinearLayoutManager linearLayoutManagerHot = new LinearLayoutManager(this);
        linearLayoutManagerHot.setOrientation(OrientationHelper.VERTICAL);
        LinearLayoutManager linearLayoutManagerLartest = new LinearLayoutManager(this);
        linearLayoutManagerLartest.setOrientation(OrientationHelper.VERTICAL);
        rvDetailHotComment.setLayoutManager(linearLayoutManagerHot);
        rvDetailLatestComment.setLayoutManager(linearLayoutManagerLartest);

        //解决ScrollView嵌套RecyclerView后滑动很缓慢，不流畅，没有惯性
        rvDetailHotComment.setHasFixedSize(true);
        rvDetailHotComment.setNestedScrollingEnabled(false);

        rvDetailLatestComment.setHasFixedSize(true);
        rvDetailLatestComment.setNestedScrollingEnabled(false);


        hotCommentAdapter = new CommentAdapter(IndexItemActivity.this, listCommentHot);
        latestCommentAdapter = new CommentAdapter(IndexItemActivity.this, listCommentLatest);
        rvDetailHotComment.setAdapter(hotCommentAdapter);
        rvDetailLatestComment.setAdapter(latestCommentAdapter);
    }

    /**
     * FloatActionBar的动画
     *
     * @param view
     * @param isVisiable false隐藏  true出现
     */
    public void fabAnimation(View view, boolean isVisiable) {
        AnimationSet animatorSet = new AnimationSet(false);
        animatorSet.setDuration(300);
        ScaleAnimation goneASaleAnimation = new ScaleAnimation(1.0f, 0, 1.0f, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AlphaAnimation goneAlAni = new AlphaAnimation(1.0f, 0);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1.0f, 0, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AlphaAnimation alAni = new AlphaAnimation(0, 1.0f);
        if (isVisiable) {
            animatorSet.addAnimation(scaleAnimation);
            animatorSet.addAnimation(alAni);
        } else {
            animatorSet.addAnimation(goneASaleAnimation);
            animatorSet.addAnimation(goneAlAni);
        }
        view.setAnimation(animatorSet);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    //MOB分享
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
       // oks.setTitle("标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
       // oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
       // oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);

        // 启动分享GUI
        oks.show(this);
    }

    /**
     * 分享网页
     */
    public void shareUrl(String packageName,String className,String content){
        Intent intent =new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
//      if(null != className && null != packageName && !TextUtils.isEmpty(className) && !TextUtils.isEmpty(packageName)){
//
//      }else {
//          if(null != packageName && !TextUtils.isEmpty(packageName)){
//              intent.setPackage(packageName);
//          }
//      }
        if(stringCheck(className) && stringCheck(packageName)){
            ComponentName componentName = new ComponentName(packageName, className);
            intent.setComponent(componentName);
        }else if(stringCheck(packageName)){
            intent.setPackage(packageName);
        }

        intent.putExtra(Intent.EXTRA_TEXT, content);

        Intent chooserIntent = Intent.createChooser(intent, "分享到：");
        this.startActivity(chooserIntent);
    }
    public static boolean stringCheck(String str){
        if(null != str && !TextUtils.isEmpty(str)){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 是否安装分享app
     * @param packageName
     */
    public boolean checkInstall(String packageName){
        try {
            this.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "请先安装应用app", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 跳转官方安装网址
     */
    public void toInstallWebView(String url){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        this.startActivity(intent);
    }
}
