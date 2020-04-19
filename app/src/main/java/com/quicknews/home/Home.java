package com.quicknews.home;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.quicknews.model.BaseResponse;

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
