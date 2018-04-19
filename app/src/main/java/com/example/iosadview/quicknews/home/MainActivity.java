package com.example.iosadview.quicknews.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.Log;

import com.example.iosadview.quicknews.R;
import com.example.iosadview.quicknews.model.BaseResponse;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MvpLceActivity<ConstraintLayout, BaseResponse, Home.View, Home.Presenter>
        implements Home.View {

    private VerticalViewPager verticalViewPager;
    private VerticlePagerAdapter verticlePagerAdapter;
    private ArrayList<BaseResponse.Article> mTotoalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTotoalList = new ArrayList<>();
        verticalViewPager = findViewById(R.id.verticleViewPager);
        verticlePagerAdapter = new VerticlePagerAdapter(this, mTotoalList);
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
}
