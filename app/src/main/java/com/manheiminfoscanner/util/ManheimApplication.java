package com.manheiminfoscanner.util;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.karumi.dexter.Dexter;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by zfolkz on 12/1/16 AD.
 */
public class ManheimApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Dexter.initialize(getApplicationContext());
        Realm.init(getApplicationContext());
        initFacebook();
        initRealm();
    }

    public void initRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("android.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public void initFacebook(){
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}
