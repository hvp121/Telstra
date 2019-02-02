package com.telstraasgn.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.telstraasgn.R;
import com.telstraasgn.adapter.RecyclerViewDataAdapter;
import com.telstraasgn.model.CountryData;

import java.util.ArrayList;

public class CountryActivity extends Activity implements CountryContract.MainView {

    private CountryContract.presenter presenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView internetText;
    private RecyclerViewDataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        internetText = findViewById(R.id.internetText);
        recyclerView = findViewById(R.id.recycler_view_list);


        initializeSwipeRefreshView();
        initializeRecyclerView();

        presenter = new CountryPresenterImpl(this, new CountryDataIntractorImpl());
        presenter.requestDataFromServer();

    }

    private void initializeSwipeRefreshView() {
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    //Initialize Toolbar and RecycleView
    private void initializeRecyclerView() {


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CountryActivity.this);
        recyclerView.setLayoutManager(layoutManager);

    }

    //Method for data set in RecyclerView
    @Override
    public void setDataToRecyclerView(ArrayList<CountryData> countryDataArrayList) {
        internetText.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter = new RecyclerViewDataAdapter(countryDataArrayList);
        recyclerView.setAdapter(adapter);

    }
    @Override
    public void emptyRecyclerView() {
        recyclerView.setVisibility(View.GONE);
        internetText.setVisibility(View.VISIBLE);
        internetText.setText("No Data Available");
    }


    //Connection Lost Error message
    @Override
    public void onResponseFailure(Throwable throwable) {
        recyclerView.setVisibility(View.GONE);
        internetText.setVisibility(View.VISIBLE);
        internetText.setText(getResources().getString(R.string.error_internet));
        Toast.makeText(CountryActivity.this,""+getResources().getString(R.string.responsefailure) + throwable.getMessage(),Toast.LENGTH_LONG).show();

    }

    //Set Title for ActionBar
    @Override
    public void setActionBarTitle(String countryName) {
            getActionBar().setTitle(countryName);
    }

    @Override
    public void connectionNotAvailable() {
        recyclerView.setVisibility(View.GONE);
        internetText.setVisibility(View.VISIBLE);
        internetText.setText(getResources().getString(R.string.error_internet));
    }

    // Destroy Method for activity and Presenter class
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

}
