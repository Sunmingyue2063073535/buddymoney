package com.buddy.money.cash.india.loan.store.buddy.go;

import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "MdsjsPlusg")
public class MdsjhlsPlugin extends com.getcapacitor.Plugin {

    @PluginMethod()
    public void getPlus(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("BBJS","sdkjdsjk");
        call.resolve(ret);
    }
}
