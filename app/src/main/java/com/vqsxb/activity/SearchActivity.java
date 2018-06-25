package com.vqsxb.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vqsxb.R;
import com.vqsxb.fragment.search.SearchHintFragment;
import com.vqsxb.fragment.search.SearchHotFragment;
import com.vqsxb.fragment.search.SearchListFragment;
import com.vqsxb.fragment.search.SearchThirdFragment;
import com.vqsxb.utils.KeybordUtil;
import com.vqsxb.utils.OtherUtil;
import com.vqsxb.widget.VqsViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
// 搜索
public class SearchActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.search_title_back)
    ImageView mSearchTitleBack;
    @BindView(R.id.search_title_et)
    EditText mSearchTitleEt;
    @BindView(R.id.search_title_searchbtn)
    ImageView mSearchTitleSearchbtn;
    @BindView(R.id.vqs_search_titlelin)
    LinearLayout mVqsSearchTitlelin;
    @BindView(R.id.search_viewpager)
    VqsViewPager mSearchViewpager;
    @BindView(R.id.search_hint_recyclerview)
    RecyclerView mSearchHintRecyclerview;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    // 热门搜索
    private SearchHotFragment hotFragment;
    // 搜索结果
    private SearchListFragment listFragment;
    // 其他搜索方式
    private SearchThirdFragment thirdFragment;
    // 搜索提示
    private SearchHintFragment hintFragment;
   // private SearchHintAdapter hintAdapter;
    // 所有的
    private SearchPagerAdaper adapter;
    // 热搜关键词
    private String hotkey = "";
    private List<Fragment> fragments = new ArrayList<>();
    // private List<SearchHintInfo> hintlist = new ArrayList<>();

    // 是百度还是豌豆荚
    public static final int TYPE_BAIDU = 1000;
    public static final int TYPE_WDJ = 1001;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        mSearchTitleSearchbtn.setOnClickListener(this);
        mSearchTitleBack.setOnClickListener(this);

        initViewPager();
        initEditText();
    }

    private void initEditText() {
        mSearchTitleEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    search(mSearchTitleEt.getText().toString());
                }
                return true;
            }
        });

        mSearchTitleEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (OtherUtil.isNotEmpty(charSequence)) {
                    String s = charSequence.toString();
                    // 搜索提示
                    if (!s.equals(hotkey)){
                        searchHint(s);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    // 加载所有的viewpager
    private void initViewPager() {
        hotFragment = new SearchHotFragment(this);
        listFragment = new SearchListFragment(this);
        thirdFragment = new SearchThirdFragment(this);
        hintFragment = new SearchHintFragment(this);

        fragments.add(hotFragment);
        fragments.add(hintFragment);
        fragments.add(listFragment);
        fragments.add(thirdFragment);
        adapter = new SearchPagerAdaper(getSupportFragmentManager());
        mSearchViewpager.setAdapter(adapter);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_title_searchbtn:
                search(mSearchTitleEt.getText().toString());
                break;

            case R.id.search_title_back:
                finish();
                break;
        }
    }

    // 搜索结果
    public void search(String key) {
        if (OtherUtil.isNotEmpty(key)) {
            mSearchHintRecyclerview.setVisibility(View.INVISIBLE);
            KeybordUtil.closeKeybord(mSearchTitleEt, this);
            mSearchViewpager.setCurrentItem(2);
            listFragment.toSearch(key);
        }
    }

    // 搜索提示
    public void searchHint(String key){
        mSearchViewpager.setCurrentItem(1);
        hintFragment.toSearch(key);
    }

    // 第三方搜索
    public void toThirdSearch(String key,int type){
        thirdFragment.search(key,type);
        mSearchViewpager.setCurrentItem(3);
    }

    // 热搜
    public void setHotkey(String key){
        hotkey = key;
        mSearchTitleEt.setText(key);
    }

    class SearchPagerAdaper extends FragmentPagerAdapter {

        public SearchPagerAdaper(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        int current = mSearchViewpager.getCurrentItem();
        /*if (search_hint_recyclerview.getVisibility() == View.VISIBLE) {
            search_hint_recyclerview.setVisibility(View.INVISIBLE);
        } else */
        if (mSearchViewpager.getCurrentItem() > 0) {
            mSearchViewpager.setCurrentItem(current - 2);
        } else {
            finish();
        }
    }
}
