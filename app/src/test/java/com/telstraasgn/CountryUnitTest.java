package com.telstraasgn;

import com.telstraasgn.activity.CountryContract;
import com.telstraasgn.activity.CountryPresenterImpl;
import com.telstraasgn.model.CountryData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class CountryUnitTest {

    @Mock
    private ArrayList<CountryData> mockCountryData;
    @Mock
    private String countryName;

    @Mock
    private CountryContract.MainView view;
    @Mock
    private CountryContract.GetCountryDataIntractor view1;
    private CountryPresenterImpl mPresenter;

    @Before
    public void setUp() throws Exception
    {
        mPresenter = new CountryPresenterImpl(view,view1);
    }

    /*@Test
    public void emptyDataTest() {
        assertEquals(view1.countryName, "Canada");
    }*/
}