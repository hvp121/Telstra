package com.telstraasgn.activity;

import android.util.Log;

import com.telstraasgn.listener.GetDataApiService;
import com.telstraasgn.model.CountryData;
import com.telstraasgn.model.CountryDataList;
import com.telstraasgn.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryDataIntractorImpl implements CountryContract.GetCountryDataIntractor {

    ArrayList<CountryData> countryDataList1;

    /** Model Class API Calling*/
    @Override
    public void getCountryDataArrayList(final OnFinishedListener onFinishedListener) {


        /** Create handle for the RetrofitInstance interface*/
        GetDataApiService service = RetrofitInstance.getRetrofitInstance().create(GetDataApiService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<CountryDataList> call = service.getCountryData();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        /**Retrofit API Calling*/
        call.enqueue(new Callback<CountryDataList>() {
            @Override
            public void onResponse(Call<CountryDataList> call, Response<CountryDataList> response) {
                onFinishedListener.onFinished(response.body().getCountryDataArrayList(),response.body().getCountryName());
                Log.e("OUTPUT",response.body().getCountryDataArrayList()+"");

            }

            @Override
            public void onFailure(Call<CountryDataList> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }

}

