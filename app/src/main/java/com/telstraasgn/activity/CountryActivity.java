package com.telstraasgn.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.telstraasgn.R;
import com.telstraasgn.adapter.RecyclerViewDataAdapter;
import com.telstraasgn.databinding.ActivityCountryBinding;
import com.telstraasgn.model.CountryData;

import java.util.ArrayList;

public class CountryActivity extends Activity implements CountryContract.MainView {

    private CountryContract.presenter presenter;
    private RecyclerViewDataAdapter adapter;
    private ActivityCountryBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_country);

        initializeSwipeRefreshView();
        initializeRecyclerView();

        presenter = new CountryPresenterImpl(this, new CountryDataIntractorImpl());
        presenter.requestDataFromServer();

    }

    private void initializeSwipeRefreshView() {
        mBinding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
                mBinding.swiperefresh.setRefreshing(false);
            }
        });
    }

    //Initialize Toolbar and RecycleView
    private void initializeRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CountryActivity.this);
        mBinding.recyclerViewFact.setLayoutManager(layoutManager);
    }

    //Method for data set in RecyclerView
    @Override
    public void setDataToRecyclerView(ArrayList<CountryData> countryDataArrayList) {
        mBinding.internetText.setVisibility(View.GONE);
        mBinding.recyclerViewFact.setVisibility(View.VISIBLE);
        adapter = new RecyclerViewDataAdapter(countryDataArrayList);
        mBinding.recyclerViewFact.setAdapter(adapter);

    }
    @Override
    public void emptyRecyclerView() {
        mBinding.recyclerViewFact.setVisibility(View.GONE);
        mBinding.internetText.setVisibility(View.VISIBLE);
        mBinding.internetText.setText("No Data Available");
    }


    //Connection Lost Error message
    @Override
    public void onResponseFailure(Throwable throwable) {
        mBinding.recyclerViewFact.setVisibility(View.GONE);
        mBinding.internetText.setVisibility(View.VISIBLE);
        mBinding.internetText.setText(getResources().getString(R.string.error_internet));
        Toast.makeText(CountryActivity.this,""+getResources().getString(R.string.responsefailure) + throwable.getMessage(),Toast.LENGTH_LONG).show();

    }

    //Set Title for ActionBar
    @Override
    public void setActionBarTitle(String countryName) {
            getActionBar().setTitle(countryName);
    }

    @Override
    public void connectionNotAvailable() {
        mBinding.recyclerViewFact.setVisibility(View.GONE);
        mBinding.internetText.setVisibility(View.VISIBLE);
        mBinding.internetText.setText(getResources().getString(R.string.error_internet));
    }

    // Destroy Method for activity and Presenter class
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

}
