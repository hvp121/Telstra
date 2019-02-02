package com.telstraasgn.activity;

import com.telstraasgn.model.CountryData;

import java.util.ArrayList;

public interface CountryContract {

    /**
     * Call when user interact with the view and other when view OnDestroy()
     * */
    interface presenter{

        void onDestroy();

        void onRefresh();

        void requestDataFromServer();

    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the setDataToRecyclerView and onResponseFailure is fetched from the GetNoticeInteractorImpl class
     **/
    interface MainView {

        void setDataToRecyclerView(ArrayList<CountryData> noticeArrayList);

        void emptyRecyclerView();

        void onResponseFailure(Throwable throwable);

        void setActionBarTitle(String countryName);

        void connectionNotAvailable();
    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetCountryDataIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<CountryData> noticeArrayList, String countryName);
            void onFailure(Throwable t);
        }

        void getCountryDataArrayList(OnFinishedListener onFinishedListener);
    }
}
