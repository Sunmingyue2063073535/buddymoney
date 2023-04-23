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


@CapacitorPlugin(name = "UAM3IHKS")
@StringFogIgnore
public class BMGetTokenPlugin extends Plugin {

    public static PluginCall newCall;
    @PluginMethod()
    public void buddymoney(PluginCall call) {
        JSObject ret = new JSObject();
        String value = call.getString("key");
        if (value.equals("getRequestHeadToken")) {
            ret.put("gaId", getGaid(getContext()));
            ret.put("androidId", getAndroidID(getContext()));
            ret.put("appVersionName", getAppVersionName(getContext()));
        }
        call.resolve(ret);
    }
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {

        }
        return versionName;
    }
    public static String getAndroidID(Context context) {
        try {
            String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            //LogUtil.e("androidID: " + androidID);
            return androidID;
        } catch (Exception e) {
            return null;
        }
    }
    public static String getGaid(Context context) {
        try {
            String Gaid = new AdvertisingIdClient(context).getAdvertisingIdInfo(context).getId();
            return Gaid;
        } catch (Exception e) {
            return null;
        }
    }



}
