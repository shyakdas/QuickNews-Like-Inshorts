package com.example.iosadview.quicknews.home;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.iosadview.quicknews.Interface.PostItemClickListener;
import com.example.iosadview.quicknews.R;
import com.example.iosadview.quicknews.Utils.AppUtis;
import com.example.iosadview.quicknews.categories.CategoriesFragment;
import com.example.iosadview.quicknews.model.BaseResponse;
import com.google.android.material.navigation.NavigationView;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.iosadview.quicknews.Utils.Constant.TAG_MASTER_FRAGMENT;

public class MainActivity extends MvpLceActivity<ConstraintLayout, BaseResponse, Home.View, Home.Presenter>
        implements Home.View, PostItemClickListener {

    private VerticalViewPager verticalViewPager;
    private VerticlePagerAdapter verticlePagerAdapter;
    private ArrayList<BaseResponse.Article> mTotoalList;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        if (mDrawerLayout != null) {
            NavigationView navigationView = findViewById(R.id.master_fragment_container);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    return true;
                }
            });

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

        CategoriesFragment categoriesFragment = CategoriesFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.master_fragment_container, categoriesFragment, TAG_MASTER_FRAGMENT)
                .commit();

        mTotoalList = new ArrayList<>();
        verticalViewPager = findViewById(R.id.verticleViewPager);
        verticlePagerAdapter = new VerticlePagerAdapter(this, mTotoalList, this);
        loadData(false);
    }

    @NonNull
    @Override
    public Home.Presenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage() + "";
    }

    @Override
    public void setData(BaseResponse data) {
        Log.e("TAG", "setDataCheck==" + data.getArticles().get(2).getUrlToImage());
        addAll(data.getArticles());
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.fetchBuisnessCategorisData();
        presenter.fetchBitCoinData();
        presenter.getTopHeadLine();
        presenter.getAppleDataNews();
        presenter.getJournalData();
    }

    public void addAll(List<BaseResponse.Article> categories) {
        this.mTotoalList.addAll(categories);
        Log.e("TAG", "listSize==" + mTotoalList.size());
        verticlePagerAdapter.notifyDataSetChanged();
        verticalViewPager.setAdapter(verticlePagerAdapter);
    }

    @Override
    public void postClick(int position, String url) {
        Log.e("TAG", "checkingTheClick===" + position + "url====" + url);
        AppUtis.creatorCustomTabs(this, url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
