package com.quicknews.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.iosadview.quicknews.R;
import com.google.android.material.navigation.NavigationView;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;
import com.quicknews.adapter.home.VerticalPagerAdapter;
import com.quicknews.fragment.CategoriesFragment;
import com.quicknews.home.Home;
import com.quicknews.home.HomePresenter;
import com.quicknews.utils.VerticalViewPager;
import com.quicknews.listener.ItemClickListener;
import com.quicknews.model.ArticleData;
import com.quicknews.model.BaseResponse;
import com.quicknews.utils.QuickNewsConstant;
import com.quicknews.utils.QuickNewsUtils;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends MvpLceActivity<ConstraintLayout, BaseResponse, Home.View, Home.Presenter>
        implements Home.View, ItemClickListener {

    private VerticalViewPager verticalViewPager;
    private VerticalPagerAdapter verticlePagerAdapter;
    private ArrayList<ArticleData> mTotoalList;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        if (mDrawerLayout != null) {
            NavigationView navigationView = findViewById(R.id.master_fragment_container);
            navigationView.setNavigationItemSelectedListener(item -> true);

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

        CategoriesFragment categoriesFragment = CategoriesFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.master_fragment_container, categoriesFragment, QuickNewsConstant.TAG_MASTER_FRAGMENT)
                .commit();

        mTotoalList = new ArrayList<>();
        verticalViewPager = findViewById(R.id.verticleViewPager);
        verticlePagerAdapter = new VerticalPagerAdapter(this, mTotoalList, this);
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

    public void addAll(List<ArticleData> categories) {
        this.mTotoalList.addAll(categories);
        verticlePagerAdapter.notifyDataSetChanged();
        verticalViewPager.setAdapter(verticlePagerAdapter);
    }

    @Override
    public void onClick(int position, @NonNull String url) {
        QuickNewsUtils.Companion.openUrlInCustomTab(this, url);
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
