package com.quicknews.home;

import android.content.Context;
import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.quicknews.network.APIUtils;
import com.quicknews.utils.QuickNewsConstant;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends MvpBasePresenter<Home.View> implements Home.Presenter {

    private Context mContext;

    public HomePresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void fetchBuisnessCategorisData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(QuickNewsConstant.COUNTRY, "us");
        hashMap.put(QuickNewsConstant.CATEGORY, "business");
        hashMap.put(QuickNewsConstant.API_KEY, "a2b79ad65ea54c8387a71724bd888b17");
        APIUtils.getAPIService().getNewsChannels(hashMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(channelResponse -> {
                    Log.e("TAG", "channelResponse==" + channelResponse.body().getStatus());
                    ifViewAttached(true, view -> view.setData(channelResponse.body()));
                }, throwable -> Log.e("TAG", "channelThrowable==" + throwable.getMessage()));
    }

    @Override
    public void fetchBitCoinData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("q", "bitcoin");
        hashMap.put("sortBy", "publishedAt");
        hashMap.put(QuickNewsConstant.API_KEY, "a2b79ad65ea54c8387a71724bd888b17");
        APIUtils.getAPIService().getEveryThing(hashMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitCoinResponse -> {
                    Log.e("TAG", "bitCoinResponse==" + bitCoinResponse.body().getStatus());
                    ifViewAttached(true, view -> view.setData(bitCoinResponse.body()));
                }, bitCoinThrowable -> Log.e("TAG", "channelThrowable==" + bitCoinThrowable.getMessage()));
    }

    @Override
    public void getTopHeadLine() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("sources", "techcrunch");
        hashMap.put(QuickNewsConstant.API_KEY, "a2b79ad65ea54c8387a71724bd888b17");
        APIUtils.getAPIService().getTopHeadLine(hashMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(topHeadlineResponse -> {
                    Log.e("TAG", "topHeadlineResponse==" + topHeadlineResponse.body().getStatus());
                    ifViewAttached(true, view -> view.setData(topHeadlineResponse.body()));
                }, topHeadlineThrowable -> Log.e("TAG", "topHeadlineThrowable==" + topHeadlineThrowable.getMessage()));
    }

    @Override
    public void getAppleDataNews() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("q", "apple");
        hashMap.put("from", "2018-04-18");
        hashMap.put("to", "2018-04-18");
        hashMap.put("sortBy", "popularity");
        hashMap.put(QuickNewsConstant.API_KEY, "a2b79ad65ea54c8387a71724bd888b17");
        APIUtils.getAPIService().getEveryThing(hashMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(appleNewsResponse -> {
                    Log.e("TAG", "appleNewsResponse==" + appleNewsResponse.body().getStatus());
                    ifViewAttached(true, view -> view.setData(appleNewsResponse.body()));
                }, appleNewsResponse -> Log.e("TAG", "appleNewsResponse==" + appleNewsResponse.getMessage()));
    }

    @Override
    public void getJournalData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("domains", "wsj.com");
        hashMap.put(QuickNewsConstant.API_KEY, "a2b79ad65ea54c8387a71724bd888b17");
        APIUtils.getAPIService().getEveryThing(hashMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(journeyDataResponse -> {
                    Log.e("TAG", "journeyDataResponse==" + journeyDataResponse.body().getStatus());
                    ifViewAttached(true, view -> view.setData(journeyDataResponse.body()));
                }, journeyDataThrowable -> Log.e("TAG", "journeyDataThrowable==" + journeyDataThrowable.getMessage()));
    }
}
