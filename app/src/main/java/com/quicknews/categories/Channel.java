package com.quicknews.categories;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.quicknews.model.BaseResponse;

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
