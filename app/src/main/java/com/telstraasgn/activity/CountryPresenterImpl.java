package com.telstraasgn.activity;

import com.telstraasgn.app.App;
import com.telstraasgn.model.CountryData;
import com.telstraasgn.utils.ConnectionManager;

import java.util.ArrayList;

public class CountryPresenterImpl implements CountryContract.presenter, CountryContract.GetCountryDataIntractor.OnFinishedListener {
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
    public void onRefresh() {

        if (ConnectionManager.getInstance(App.getInstance()).isConnectingToInternet())
            getCountryDataIntractor.getCountryDataArrayList(this);
        else
            mainView.connectionNotAvailable();
    }

    //Get data from Model Class
    @Override
    public void requestDataFromServer() {
        if (ConnectionManager.getInstance(App.getInstance()).isConnectingToInternet())
            getCountryDataIntractor.getCountryDataArrayList(this);
        else
            mainView.connectionNotAvailable();
    }

    //Return data to View Class
    @Override
    public void onFinished(ArrayList<CountryData> countryDataArrayList, String countryName) {
        if (mainView != null) {

            if (countryDataArrayList.size() > 0) {
                ArrayList<CountryData> cleanCountryDataArrayList = new ArrayList<>();
                for (int i = 0; i < countryDataArrayList.size(); i++) {
                    if (countryDataArrayList.get(i).getTitle() != null && countryDataArrayList.get(i).getDescription() != null
                            && countryDataArrayList.get(i).getImageHref() != null) {
                        cleanCountryDataArrayList.add(countryDataArrayList.get(i));
                    }
                }
                mainView.setDataToRecyclerView(cleanCountryDataArrayList);

                if (countryName != null) {
                    mainView.setActionBarTitle(countryName);
                } else
                    mainView.setActionBarTitle("");
            } else {
                mainView.emptyRecyclerView();
            }
        }

    }

    //Presenter data failed method
    @Override
    public void onFailure(Throwable t) {
        if (mainView != null) {
            mainView.onResponseFailure(t);
        }
    }


}
