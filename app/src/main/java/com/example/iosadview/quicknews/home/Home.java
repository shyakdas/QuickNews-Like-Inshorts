package com.example.iosadview.quicknews.home;

import com.example.iosadview.quicknews.model.BaseResponse;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

public interface Home {

    interface View extends MvpLceView<BaseResponse> {

    }

    interface Presenter extends MvpPresenter<Home.View> {

        void fetchBuisnessCategorisData();

        void fetchBitCoinData();

        void getTopHeadLine();

        void getAppleDataNews();

        void getJournalData();
    }
}
