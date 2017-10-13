package com.hgz.test.greendao3;

import com.hgz.test.greendao3.app.MyApplication;

/**
 * Created by Administrator on 2017/10/13.
 */

public class GreenDaoManager{

    private static GreenDaoManager mInstance;
    private DaoSession daoSession;
    private DaoMaster daoMaster;

    public GreenDaoManager(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.context(), "user.db", null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }
    public static GreenDaoManager getInstance(){
        if (mInstance==null){
            mInstance=new GreenDaoManager();
        }
        return  mInstance;
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }
    public DaoSession getNewSession(){
        daoSession=daoMaster.newSession();
        return  daoSession;
    }
}
