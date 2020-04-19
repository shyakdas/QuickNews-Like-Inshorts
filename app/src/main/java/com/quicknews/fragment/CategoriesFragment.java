package com.quicknews.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iosadview.quicknews.R;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;
import com.quicknews.adapter.category.ChannelAdapter;
import com.quicknews.categories.Channel;
import com.quicknews.categories.ChannelPresenter;
import com.quicknews.model.ArticleData;
import com.quicknews.model.BaseResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends MvpLceFragment<RelativeLayout, BaseResponse, Channel.View, Channel.Presenter>
        implements Channel.View {

    private RecyclerView mRecylerview;
    private ChannelAdapter channelAdapter;
    private RecyclerView.LayoutManager linearLayoutManager;
    private ArrayList<ArticleData> mList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        mRecylerview = view.findViewById(R.id.channel_list);
        mList = new ArrayList<>();
        loadData(false);
        return view;
    }

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecylerview.setLayoutManager(linearLayoutManager);
        mRecylerview.setItemAnimator(new DefaultItemAnimator());
        channelAdapter = new ChannelAdapter(getActivity(), mList);
        mRecylerview.setHasFixedSize(true);
        loadData(false);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage() + "";
    }

    @Override
    public Channel.Presenter createPresenter() {
        return new ChannelPresenter(getContext());
    }

    @Override
    public void setData(BaseResponse data) {
        addAll(data.getArticles());
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.fetchBuisnessCategorisData();
    }

    public void addAll(List<ArticleData> categories) {
        this.mList.addAll(categories);
        Log.e("TAG", "listSize==" + mList.size());
        channelAdapter.notifyDataSetChanged();
        mRecylerview.setAdapter(channelAdapter);
    }
}
