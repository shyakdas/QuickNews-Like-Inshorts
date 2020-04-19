package com.example.iosadview.quicknews.categories;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.iosadview.quicknews.Utils.Constant;
import com.example.iosadview.quicknews.model.BaseResponse;
import com.example.iosadview.quicknews.network.APIUtils;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ChannelPresenter extends MvpBasePresenter<Channel.View> implements Channel.Presenter {

    private Context mContext;

    public ChannelPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void fetchBuisnessCategorisData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Constant.COUNTRY, "us");
        hashMap.put(Constant.CATEGORY, "business");
        hashMap.put(Constant.API_KEY, "a2b79ad65ea54c8387a71724bd888b17");
        APIUtils.getAPIService().getNewsChannels(hashMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<BaseResponse>>() {
                    @Override
                    public void accept(final Response<BaseResponse> channelResponse) throws Exception {
                        Log.e("TAG", "channelResponse==" + channelResponse.body().getStatus());
                        ifViewAttached(true, new ViewAction<Channel.View>() {
                            @Override
                            public void run(@NonNull Channel.View view) {
                                view.setData(channelResponse.body());
                            }
                        });

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("TAG", "channelThrowable==" + throwable.getMessage());
                    }
                });
    }

    @Override
    public void fetchBitCoinData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("q", "bitcoin");
        hashMap.put("sortBy", "publishedAt");
        hashMap.put(Constant.API_KEY, "a2b79ad65ea54c8387a71724bd888b17");
        APIUtils.getAPIService().getEveryThing(hashMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<BaseResponse>>() {
                    @Override
                    public void accept(final Response<BaseResponse> bitCoinResponse) throws Exception {
                        Log.e("TAG", "bitCoinResponse==" + bitCoinResponse.body().getStatus());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable bitCoinThrowable) throws Exception {
                        Log.e("TAG", "channelThrowable==" + bitCoinThrowable.getMessage());
                    }
                });
    }

    @Override
    public void getTopHeadLine() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("sources", "techcrunch");
        hashMap.put(Constant.API_KEY, "a2b79ad65ea54c8387a71724bd888b17");
        APIUtils.getAPIService().getTopHeadLine(hashMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<BaseResponse>>() {
                    @Override
                    public void accept(final Response<BaseResponse> topHeadlineResponse) throws Exception {
                        Log.e("TAG", "topHeadlineResponse==" + topHeadlineResponse.body().getStatus());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable topHeadlineThrowable) throws Exception {
                        Log.e("TAG", "topHeadlineThrowable==" + topHeadlineThrowable.getMessage());

                    }
                });
    }

    @Override
    public void getAppleDataNews() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("q", "apple");
        hashMap.put("from", "2018-04-18");
        hashMap.put("to", "2018-04-18");
        hashMap.put("sortBy", "popularity");
        hashMap.put(Constant.API_KEY, "a2b79ad65ea54c8387a71724bd888b17");
        APIUtils.getAPIService().getEveryThing(hashMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<BaseResponse>>() {
                    @Override
                    public void accept(final Response<BaseResponse> appleNewsResponse) throws Exception {
                        Log.e("TAG", "appleNewsResponse==" + appleNewsResponse.body().getStatus());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable appleNewsResponse) throws Exception {
                        Log.e("TAG", "appleNewsResponse==" + appleNewsResponse.getMessage());
                    }
                });
    }

    @Override
    public void getJournalData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("domains", "wsj.com");
        hashMap.put(Constant.API_KEY, "a2b79ad65ea54c8387a71724bd888b17");
        APIUtils.getAPIService().getEveryThing(hashMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<BaseResponse>>() {
                    @Override
                    public void accept(final Response<BaseResponse> journeyDataResponse) throws Exception {
                        Log.e("TAG", "journeyDataResponse==" + journeyDataResponse.body().getStatus());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable journeyDataThrowable) throws Exception {
                        Log.e("TAG", "journeyDataThrowable==" + journeyDataThrowable.getMessage());
                    }
                });
    }
}
