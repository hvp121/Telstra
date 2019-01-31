package com.telstraasgn.activity;

import com.telstraasgn.model.CountryData;

import java.util.ArrayList;

public class CountryPresenterImpl implements CountryContract.presenter, CountryContract.GetCountryDataIntractor.OnFinishedListener{
    private CountryContract.MainView mainView;
    private CountryContract.GetCountryDataIntractor getCountryDataIntractor;

    public CountryPresenterImpl(CountryContract.MainView mainView, CountryContract.GetCountryDataIntractor getCountryDataIntractor) {
        this.mainView = mainView;
        this.getCountryDataIntractor = getCountryDataIntractor;
    }
    //Maincontract Override method
    @Override
    public void onDestroy() {

        mainView = null;

    }

    //Refresh Data Method
    @Override
    public void onRefreshButtonClick() {

        if(mainView != null){
            mainView.showProgress();
        }
        getCountryDataIntractor.getCountryDataArrayList(this);

    }
    //Get data from Model Class
    @Override
    public void requestDataFromServer() {
        getCountryDataIntractor.getCountryDataArrayList(this);
    }

    //Return data to View Class
    @Override
    public void onFinished(ArrayList<CountryData> countryDataArrayList, String countryName) {
        if(mainView != null){
            mainView.setDataToRecyclerView(countryDataArrayList);
            mainView.hideProgress();
            mainView.setActionBarTitle(countryName);
        }
    }
    //Presenter data failed method
    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}
