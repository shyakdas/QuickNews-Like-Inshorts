package com.example.iosadview.quicknews.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.Log;

import com.example.iosadview.quicknews.R;
import com.example.iosadview.quicknews.model.BaseResponse;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;

public class MainActivity extends MvpLceActivity<ConstraintLayout, BaseResponse, Home.View, Home.Presenter>
        implements Home.View {

    private VerticalViewPager verticalViewPager;
    private VerticlePagerAdapter verticlePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verticalViewPager = findViewById(R.id.verticleViewPager);
        verticlePagerAdapter = new VerticlePagerAdapter(this);
        verticalViewPager.setAdapter(verticlePagerAdapter);
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
        Log.e("TAG", "check===" + data.getArticles().get(1).getUrlToImage());
        verticlePagerAdapter.addAll(data.getArticles());
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.fetchChannelData();
    }
}
