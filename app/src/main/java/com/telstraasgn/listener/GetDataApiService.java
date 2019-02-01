package com.telstraasgn.listener;

import com.telstraasgn.model.CountryData;
import com.telstraasgn.model.CountryDataList;
import com.telstraasgn.utils.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetDataApiService {
    //Retrofit API Service
    @GET(Constants.API_NAME)
    Call<CountryDataList> getCountryData();

}
