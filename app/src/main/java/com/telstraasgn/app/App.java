package com.telstraasgn.app;

import android.app.Application;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;

public class App extends Application
{
    public static App mInstance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized App getInstance()
    {
        return mInstance;
    }
}
