package com.buddy.money.cash.india.loan.store.buddy.go;

import android.app.Application;

import io.branch.referral.Branch;

public class BuddyApp extends Application
{

    @Override
    public void onCreate() {
        super.onCreate();
        Branch.enableLogging();
        Branch.getAutoInstance(this);
    }

}