package com.telstraasgn.listener;

import com.telstraasgn.model.CountryDataList;
import com.telstraasgn.app.Constants;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataApiService {
    //Retrofit API Service
    @GET(Constants.API_NAME)
    Call<CountryDataList> getCountryData();

}
