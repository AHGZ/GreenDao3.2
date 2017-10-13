package com.hgz.test.greendao3.app;

import android.app.Application;
import android.content.Context;

import com.hgz.test.greendao3.GreenDaoManager;

/**
 * Created by Administrator on 2017/10/13.
 */

public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        GreenDaoManager.getInstance();
    }
    public static Context context(){
        return context;
    }
}
