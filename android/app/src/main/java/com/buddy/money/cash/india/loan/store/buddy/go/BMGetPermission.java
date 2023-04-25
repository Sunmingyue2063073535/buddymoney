package com.buddy.money.cash.india.loan.store.buddy.go;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.github.megatronking.stringfog.annotation.StringFogIgnore;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;


@CapacitorPlugin(name = "UAM3IHKS")
@StringFogIgnore
public class BMGetPermission extends Plugin {
    public static final int CAMERA_REQUEST_CODE = 564;

    public static PluginCall newCall;
    @PluginMethod()
    public void buddymoney(PluginCall call) {
        JSObject ret = new JSObject();
        String value = call.getString("key");
         if (value.equals("getPermission")) {
            newCall = call;
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[] {
                            Manifest.permission.READ_PHONE_STATE,Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_CONTACTS,Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_SMS},
                    CAMERA_REQUEST_CODE);
            return;
        }
        call.resolve(ret);
    }

}
