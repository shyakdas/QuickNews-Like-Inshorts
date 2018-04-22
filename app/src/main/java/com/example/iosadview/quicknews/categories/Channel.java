package com.example.iosadview.quicknews.categories;

import com.example.iosadview.quicknews.model.BaseResponse;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

public interface Channel {

    interface View extends MvpLceView<BaseResponse> {

    }

    interface Presenter extends MvpPresenter<Channel.View> {

        void fetchBuisnessCategorisData();

        void fetchBitCoinData();

        void getTopHeadLine();

        void getAppleDataNews();

        void getJournalData();
    }
}
