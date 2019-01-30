package com.telstraasgn.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CountryDataList {
    @SerializedName("notice_list")
    private ArrayList<CountryData> countryDataList;

    public ArrayList<CountryData> getNoticeArrayList() {
        return countryDataList;
    }

    public void setNoticeArrayList(ArrayList<CountryData> noticeArrayList) {
        this.countryDataList = noticeArrayList;
    }
}
