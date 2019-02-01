package com.telstraasgn.model;

import com.google.gson.annotations.SerializedName;
import com.telstraasgn.utils.Constants;

import java.util.ArrayList;

//Retrofit Model Class
public class CountryDataList {
    @SerializedName(Constants.API_SERIALIZE_ROWS)
    private ArrayList<CountryData> countryDataList;

    public ArrayList<CountryData> getCountryDataArrayList() {
        return countryDataList;
    }

    @SerializedName(Constants.API_SERIALIZE_TITLE)
    private String countryName;

    public String getCountryName(){return countryName;}
}
