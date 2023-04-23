package com.buddy.money.cash.india.loan.store.buddy.go;

import android.os.Bundle;

import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //注册插件
        registerPlugin(BMGetTokenPlugin.class);
        registerPlugin(BMGetEmail.class);
        registerPlugin(BMGetPermission.class);
        super.onCreate(savedInstanceState);
    }
}
