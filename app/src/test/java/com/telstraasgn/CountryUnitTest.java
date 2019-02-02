package com.telstraasgn;

import com.telstraasgn.activity.CountryContract;
import com.telstraasgn.activity.CountryPresenterImpl;
import com.telstraasgn.app.App;
import com.telstraasgn.model.CountryData;
import com.telstraasgn.model.CountryDataList;
import com.telstraasgn.utils.ConnectionManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class CountryUnitTest {


    @Mock
    private ConnectionManager connectionManager;
    @Mock
    private CountryContract.MainView view;
    @Mock
    private CountryContract.GetCountryDataIntractor view1;
    private CountryPresenterImpl mPresenter;

    private ArrayList<CountryDataList> stocks;
    private CountryData countryData;

    @Before
    public void setUp() throws Exception
    {
        mPresenter = new CountryPresenterImpl(view,view1);
    }

    @Test
    public void internetConnectionTest(){
        when(connectionManager.isConnectingToInternet()).thenReturn(true);
        mPresenter.requestDataFromServer();
        //verify(connectionManager).showMessage(R.string.action_refresh);

    }

    @Test
    public void addition_isCorrect() throws Exception
    {
        assertEquals(4, 2 + 2);
    }
}