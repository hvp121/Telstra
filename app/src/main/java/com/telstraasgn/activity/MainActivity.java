package com.telstraasgn.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.telstraasgn.R;
import com.telstraasgn.adapter.RecyclerViewDataAdapter;
import com.telstraasgn.model.CountryData;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity implements MainContract.MainView {

    private MainContract.presenter presenter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeToolbarAndRecyclerView();
        initProgressBar();

        presenter = new MainPresenterImpl(this, new GetCountryDataIntractorImpl());
        presenter.requestDataFromServer();
    }
    private void initializeToolbarAndRecyclerView() {


        recyclerView = findViewById(R.id.recycler_view_employee_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            presenter.onRefreshButtonClick();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToRecyclerView(ArrayList<CountryData> CountryDataArrayList) {
        ArrayList<CountryData> CleanCountryDataArrayList = new ArrayList<>();
        for (int i = 0;i<CountryDataArrayList.size();i++){
            if(CountryDataArrayList.get(i).getTitle()!=null&&CountryDataArrayList.get(i).getDescription()!=null
                && CountryDataArrayList.get(i).getImageHref()!=null){
                CleanCountryDataArrayList.add(CountryDataArrayList.get(i));
            }
        }
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(CleanCountryDataArrayList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void setActionBarTitle(String countryName) {
        if(countryName!=null)
            getActionBar().setTitle(countryName);
        else
            getActionBar().setTitle("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }
}
