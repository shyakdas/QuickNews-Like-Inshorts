package com.example.iosadview.quicknews.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.iosadview.quicknews.R;
import com.example.iosadview.quicknews.model.BaseResponse;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;

public class MainActivity extends MvpLceActivity<ConstraintLayout, BaseResponse, Home.View, Home.Presenter>
        implements Home.View {

    private RecyclerView mChannelRecylerView;
    private NewsChannelAdapter newsChannelAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChannelRecylerView = findViewById(R.id.channelRC);
        mProgressBar = findViewById(R.id.loadingView);
        linearLayoutManager = new LinearLayoutManager(this);
        mChannelRecylerView.setLayoutManager(linearLayoutManager);
        mChannelRecylerView.setItemAnimator(new DefaultItemAnimator());
        newsChannelAdapter = new NewsChannelAdapter(this);
        mChannelRecylerView.setAdapter(newsChannelAdapter);
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
        newsChannelAdapter.addAll(data.getArticles());
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.fetchChannelData();
    }
}
