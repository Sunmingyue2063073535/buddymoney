package com.buddy.money.cash.india.loan.store.buddy.go;

import android.app.Application;

import io.branch.referral.Branch;

public class VIATQQJMMyApp extends Application
{

    @Override
    public void onCreate() {
        super.onCreate();
        onInit();
    }


    private void onInit() {
        Branch.enableLogging();
        Branch.getAutoInstance(this);
    }
}