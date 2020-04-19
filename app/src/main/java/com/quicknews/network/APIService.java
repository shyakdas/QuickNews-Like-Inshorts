package com.quicknews.network;

import com.quicknews.model.BaseResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface APIService {

    @GET("/v2/top-headlines")
    Observable<Response<BaseResponse>> getNewsChannels(@QueryMap HashMap<String, String> hashMap);

    @GET("/v2/everything")
    Observable<Response<BaseResponse>> getEveryThing(@QueryMap HashMap<String, String> hashMap);

    @GET("/v2/top-headlines")
    Observable<Response<BaseResponse>> getTopHeadLine(@QueryMap HashMap<String, String> hashMap);

}
