package com.example.iosadview.quicknews.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.iosadview.quicknews.Utils.Contant;
import com.example.iosadview.quicknews.model.BaseResponse;
import com.example.iosadview.quicknews.network.APIUtils;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class HomePresenter extends MvpBasePresenter<Home.View> implements Home.Presenter {

    private Context mContext;

    public HomePresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void fetchChannelData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Contant.COUNTRY, "us");
        hashMap.put(Contant.CATEGORY, "business");
        hashMap.put(Contant.API_KEY, "a2b79ad65ea54c8387a71724bd888b17");
        APIUtils.getAPIService().getNewsChannels(hashMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<BaseResponse>>() {
                    @Override
                    public void accept(final Response<BaseResponse> channelResponse) throws Exception {
                        Log.e("TAG", "channelResponse==" + channelResponse.body().getStatus());
                        ifViewAttached(true, new ViewAction<Home.View>() {
                            @Override
                            public void run(@NonNull Home.View view) {
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
}
