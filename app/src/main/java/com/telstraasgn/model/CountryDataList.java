package com.telstraasgn.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CountryDataList {
    @SerializedName("rows")
    private ArrayList<CountryData> countryDataList;

    public ArrayList<CountryData> getCountryDataArrayList() {
        return countryDataList;
    }

    @SerializedName("title")
    private String countryName;

    public String getCountryName(){return countryName;}
}
