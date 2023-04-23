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
public class BMGetEmail extends Plugin {
    public static final int CAMERA_REQUEST_CODE = 564;

    public static PluginCall newCall;

    @PluginMethod()
    public void getEmail(PluginCall call) {
        String value = call.getString("key");
        if (value.equals("getEmail")) {
            try {
                Intent data = new Intent(Intent.ACTION_SENDTO);
                data.setData(Uri.parse("mailto:buddy.cuscare@hotmail.com"));
                getActivity().startActivity(data);
            } catch (Exception e) {

            }
        }

    }
}
