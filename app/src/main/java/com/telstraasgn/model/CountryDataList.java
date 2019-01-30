package com.telstraasgn.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CountryDataList {
    @SerializedName("rows")
    private ArrayList<CountryData> countryDataList;

    public ArrayList<CountryData> getCountryDataArrayList() {
        return countryDataList;
    }

    public void setNoticeArrayList(ArrayList<CountryData> noticeArrayList) {
        this.countryDataList = noticeArrayList;
    }
}
