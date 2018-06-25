package com.vqsxb.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vc.play.mylibrary.JCVideoPlayer;
import com.vc.play.mylibrary.JCVideoPlayerStandard;
import com.vqsxb.R;
import com.vqsxb.adapter.PersonalCenterFragmentAdapter;
import com.vqsxb.fragment.appdetail.GameCommentFragment;
import com.vqsxb.fragment.appdetail.GameDetailsFragment;
import com.vqsxb.utils.OtherUtil;
import com.vqsxb.widget.MyRatingBar;
import com.vqsxb.widget.VqsViewPager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.vqsxb.httputils.ApiService.xBService;
// 游戏详情页
public class DetailsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.vqs_detail_title)
    TextView vqsDetailTitle;
    @BindView(R.id.app_details_complaints)
    ImageView appDetailsComplaints;
    @BindView(R.id.app_details_share)
    ImageView appDetailsShare;
    @BindView(R.id.lineTop)
    RelativeLayout lineTop;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;
    @BindView(R.id.info_gamename)
    TextView infoGamename;
    @BindView(R.id.rank_manager_star)
    MyRatingBar rankManagerStar;
    @BindView(R.id.rank_manager_score)
    TextView rankManagerScore;
    @BindView(R.id.info_paly_num)
    TextView infoPalyNum;
    @BindView(R.id.info_game_type)
    TextView infoGameType;
    @BindView(R.id.app_details_appicon)
    ImageView appDetailsAppicon;
    @BindView(R.id.app_details_score_tv)
    Button appDetailsScoreTv;
    @BindView(R.id.app_details_score_tv2)
    Button appDetailsScoreTv2;
    @BindView(R.id.lineHead)
    LinearLayout lineHead;
    @BindView(R.id.collapse_layout)
    CollapsingToolbarLayout collapseLayout;
    @BindView(R.id.user_detail_tab)
    TabLayout userDetailTab;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.user_detail_viewpager)
    VqsViewPager userDetailViewpager;
    @BindView(R.id.floatingActionButton)
    ImageButton floatingActionButton;
    @BindView(R.id.tv_favor)
    Button tvFavor;
    @BindView(R.id.down_details_button)
    Button downDetailsButton;
    @BindView(R.id.cardView)
    CardView cardView;

    private JSONObject gameinfo;
    private List<String> mTabTitle;
    private PersonalCenterFragmentAdapter mPageAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_details;
    }

    @Override
    protected void initViews() {

        userDetailViewpager.setCanScroll(true);

        vqsDetailTitle.setOnClickListener(this);

        Intent intent = getIntent();
        String appID = intent.getStringExtra("appID");
        Call<String> call = xBService.APPDetails(appID);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body().toString();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String error = jsonObject.getString("error");
                    if ("0".equals(error)) {
                        gameinfo = jsonObject.getJSONObject("gameinfo");

                        initFragment(s);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void initFragment(String result) {

        try {
            vqsDetailTitle.setText(gameinfo.getString("title"));
            Glide.with(DetailsActivity.this).load(gameinfo.getString("icon")).asBitmap().into(appDetailsAppicon);
            infoGamename.setText(gameinfo.getString("title"));
            rankManagerScore.setText(gameinfo.getString("score") + "分");
            infoPalyNum.setText(gameinfo.getString("downCount") + "人在玩");
            rankManagerStar.setVisibility(View.VISIBLE);
            rankManagerStar.setStar(gameinfo.getString("score"));
            String youxishipin = gameinfo.getString("youxishipin");
            if (OtherUtil.isEmpty(youxishipin)) {
                videoplayer.setVisibility(View.GONE);
            }else {
                videoplayer.setVisibility(View.VISIBLE);
                // 设置url
                videoplayer.setUp(youxishipin, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
                // 设置显示图片
                Glide.with(DetailsActivity.this).load(gameinfo.getString("shipin_pic")).asBitmap().into(videoplayer.thumbImageView);
                // 将按钮隐藏
                videoplayer.backButton.setVisibility(View.GONE);
                videoplayer.tinyBackImageView.setVisibility(View.GONE);
                videoplayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                // 设置监听事件
                videoplayer.setIOnClickStarListener(new JCVideoPlayer.IOnClickStarListener() {
                    @Override
                    public void onStartPlayer(int i) {
                        videoplayer.startVideo();
                    }
                    @Override
                    public void onImgclik() {
                    }
                });
            }

            // 初始化fragment
            userDetailTab.setupWithViewPager(userDetailViewpager);
            List<Fragment> fragments = new ArrayList<>();
            mTabTitle = new ArrayList<String>();
            mTabTitle.add("详情");
            fragments.add(GameDetailsFragment.newInstance(result));
            fragments.add(GameCommentFragment.newInstance(gameinfo.getString("appID")));
            mTabTitle.add("评论(" + gameinfo.getString("commentTotal") + ")");
            mPageAdapter = new PersonalCenterFragmentAdapter(getSupportFragmentManager(), fragments, mTabTitle);
            userDetailViewpager.setAdapter(mPageAdapter);
            appBarLayoutListen(); //设置appBarLayout
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void appBarLayoutListen() {
        //设置appbarlayout
        appbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                try {
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    collapseLayout.setTitle(gameinfo.getString("title"));
                    collapseLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
                } else {
                    collapseLayout.setTitle("");
                }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vqs_detail_title:
                finish();
            break;
        }
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

}
