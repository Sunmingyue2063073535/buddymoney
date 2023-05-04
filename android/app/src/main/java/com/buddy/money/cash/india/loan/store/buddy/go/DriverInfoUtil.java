package com.buddy.money.cash.india.loan.store.buddy.go;


import static android.content.Context.TELEPHONY_SERVICE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_1xRTT;
import static android.telephony.TelephonyManager.NETWORK_TYPE_CDMA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EDGE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_0;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_A;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_B;
import static android.telephony.TelephonyManager.NETWORK_TYPE_GPRS;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSDPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSPAP;
import static android.telephony.TelephonyManager.NETWORK_TYPE_IDEN;
import static android.telephony.TelephonyManager.NETWORK_TYPE_LTE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_NR;
import static android.telephony.TelephonyManager.NETWORK_TYPE_UMTS;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;


public class DriverInfoUtil {


    public static String getCpuName() {
        try {
            FileReader fr = new FileReader("/proc/cpuinfo"); //__xor__
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
            for (int i = 0; i < array.length; i++) {
            }
            return array[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getCpuNumCores() {
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }
        try {
            File dir = new File("/sys/devices/system/cpu/"); //__xor__
            File[] files = dir.listFiles(new CpuFilter());
            return files.length;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }


    public static String getSerialNumber() {
        String serial = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties"); //__xor__
            Method get = c.getMethod("get", String.class); //__xor__
            serial = (String) get.invoke(c, "ro.serialno"); //__xor__
            if (serial.isEmpty()) {
                Method get2 = c.getMethod("get", String.class, String.class);
                serial = (String) get2.invoke(c, "ril.serialnumber", "unknown"); //__xor__
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serial;
    }

    @SuppressLint("MissingPermission")
    public static String getMacAddress(Context context) {
        String mac = "";
        try {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            mac = wifi.getConnectionInfo().getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mac;
    }




    public static String getDriverOsVersion() {
        try {
            String device = Build.VERSION.RELEASE;
            return device;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDriverSDKVersion() {
        try {

            String version_sdk = Build.VERSION.SDK;
            return version_sdk;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDriverProduct() {
        try {
            String product = Build.PRODUCT;
            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }





    public static String getDriverDevice() {
        try {
            String device = Build.DEVICE;
            return device;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getRadioVersion() {
        try {
            String radioVersion = Build.getRadioVersion();
            return radioVersion;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }




    public static long getAvailableExternalMemorySize() {
        try {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } catch (Exception e) {
            return 0;
        }
    }

    public static long getTotalExternalMemorySize() {
        try {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        } catch (Exception e) {
            return 0;
        }
    }

    public static long getAvailableInternalMemorySize() {
        try {
            File path = Environment.getDataDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } catch (Exception e) {
            return 0;
        }
    }

    public static long getTotalInternalMemorySize() {
        try {
            File path = Environment.getDataDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        } catch (Exception e) {
            return 0;
        }
    }

    private static final String MEM_INFO_PATH = "/proc/meminfo"; //__xor__
    public static final String MEMTOTAL = "MemTotal"; //__xor__
    public static final String MEMFREE = "MemFree"; //__xor__

    public static String getRamTotalSize(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.totalMem + "";

    }

    public static float[] getMemory(Context context) {
        try {
            float appMaxMemory = (float) (Runtime.getRuntime().maxMemory() * 1.0 / (1024 * 1024));
            float appAvailableMemory = (float) (Runtime.getRuntime().totalMemory() * 1.0 / (1024 * 1024));
            float appFreeMemory = (float) (Runtime.getRuntime().freeMemory() * 1.0 / (1024 * 1024));
            float[] memoryList = {appMaxMemory, appAvailableMemory, appFreeMemory};
            return memoryList;
        } catch (Exception e) {
            float[] memoryList = {0, 0, 0};
            return memoryList;
        }
    }

    public static long[] getOsTime(Context context) {
        try {
            long[] times = {SystemClock.elapsedRealtime(), SystemClock.uptimeMillis()};
            return times;
        } catch (Exception e) {
            long[] times = {0, 0};
            return times;
        }
    }

    public static int[] getBattery(Context context) {
        try {
            Intent batteryInfoIntent = context.getApplicationContext().registerReceiver(null,
                    new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            int level = batteryInfoIntent.getIntExtra("level", 0); //__xor__
            int max = batteryInfoIntent.getIntExtra("scale", 100); //__xor__
            int[] batterys = {max, level};
            return batterys;
        } catch (Exception e) {
            int[] batterys = {0, 0};
            return batterys;
        }
    }


    public static String getRamAvailSize(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem + "";
    }

    public static String getRootDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();

    }

    public static String getExternalStorageDirectory() {
        return System.getenv("SECONDARY_STORAGE"); //__xor__
    }


    public static String getPhysicalSize(Context context) {
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display defaultDisplay = windowManager.getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            return Double.toString(Math.sqrt(Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d) + Math.pow((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi), 2.0d)));
        } catch (Exception e) {
            return null;
        }
    }

    public static String getLocalDNS() {
        try {
            Process cmdProcess = null;
            BufferedReader reader = null;
            String dnsIP = "";
            try {
                cmdProcess = Runtime.getRuntime().exec("getprop net.dns1"); //__xor__
                reader = new BufferedReader(new InputStreamReader(cmdProcess.getInputStream()));
                dnsIP = reader.readLine();
                return dnsIP;
            } catch (IOException e) {
                return null;
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                }
                cmdProcess.destroy();
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isEmulator() {
        try {
            return Build.FINGERPRINT.startsWith("generic") //__xor__
                    || Build.FINGERPRINT.toLowerCase().contains("vbox") //__xor__
                    || Build.FINGERPRINT.toLowerCase().contains("test-keys") //__xor__
//                || Build.FINGERPRINT.startsWith("unknown") // 魅族MX4: unknown
                    || Build.MODEL.contains("google_sdk") //__xor__
                    || Build.MODEL.contains("sdk") //__xor__
                    || Build.MODEL.contains("Emulator") //__xor__
                    //|| Build.MODEL.contains("Android SDK built for x86")
                    || Build.MANUFACTURER.contains("Genymotion") //__xor__
                    || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) //__xor__
                    || "google_sdk".equals(Build.PRODUCT); //__xor__
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isRooted() {

        try {
            // get from build info
            String buildTags = Build.TAGS;
            if (buildTags != null && buildTags.contains("test-keys")) { //__xor__
                return true;
            }

            String[] paths = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", //__xor__
                    "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", //__xor__
                    "/system/sd/xbin/su", //__xor__
                    "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"}; //__xor__
            for (String path : paths) {
                if (new File(path).exists()) return true;
            }

            Process process = null;
            try {
                process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"}); //__xor__
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                if (in.readLine() != null) return true;
                return false;
            } catch (Throwable t) {
                return false;
            } finally {
                if (process != null) process.destroy();
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static String getOsLanguage(Context context) {
        try {
            Locale locale = context.getResources().getConfiguration().locale;
            String language = locale.getLanguage();
            return language;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getISO3Language(Context context) {
        try {
            Locale locale = context.getResources().getConfiguration().locale;
            String language = locale.getISO3Language();
            return language;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getISO3Country(Context context) {
        try {
            Locale locale = context.getResources().getConfiguration().locale;
            String country = locale.getISO3Country();
            return country;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getTimeZoneId() {
        try {
            String timeZoneId = TimeZone.getDefault().getID();
            return timeZoneId;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getLocaleDisplayLanguage() {
        try {
            String displayLanguage = Locale.getDefault().getDisplayLanguage();
            return displayLanguage;
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressLint("MissingPermission")
    public static String getImsi(Context context) {
        try {
            if (!checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                return tm.getSubscriberId();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static Map getCellInfo(Context context) {
        Map jsonObject = new HashMap();
        if (!checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            int dbm = -1;
            int cid = -1;
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            List<CellInfo> cellInfoList;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                cellInfoList = tm.getAllCellInfo();
                if (null != cellInfoList) {
                    for (CellInfo cellInfo : cellInfoList) {
                        if (cellInfo instanceof CellInfoGsm) {
                            CellSignalStrengthGsm cellSignalStrengthGsm = ((CellInfoGsm) cellInfo).getCellSignalStrength();
                            dbm = cellSignalStrengthGsm.getDbm();
                            cid = ((CellInfoGsm) cellInfo).getCellIdentity().getCid();
                        } else if (cellInfo instanceof CellInfoCdma) {
                            CellSignalStrengthCdma cellSignalStrengthCdma = ((CellInfoCdma) cellInfo).getCellSignalStrength();
                            dbm = cellSignalStrengthCdma.getDbm();
                            cid = ((CellInfoCdma) cellInfo).getCellIdentity().getBasestationId();
                        } else if (cellInfo instanceof CellInfoWcdma) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                                CellSignalStrengthWcdma cellSignalStrengthWcdma = ((CellInfoWcdma) cellInfo).getCellSignalStrength();
                                dbm = cellSignalStrengthWcdma.getDbm();
                                cid = ((CellInfoWcdma) cellInfo).getCellIdentity().getCid();
                            }
                        } else if (cellInfo instanceof CellInfoLte) {
                            CellSignalStrengthLte cellSignalStrengthLte = ((CellInfoLte) cellInfo).getCellSignalStrength();
                            dbm = cellSignalStrengthLte.getDbm();
                            cid = ((CellInfoLte) cellInfo).getCellIdentity().getCi();
                        }
                    }
                }
            }
            jsonObject.put("dbm", dbm); //__xor__
            jsonObject.put("cid", cid); //__xor__
        }
        return jsonObject;
    }

    public static long getLastBootTime(Context context) {
        try {
            long lastBootTime = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            return lastBootTime;
        } catch (Exception e) {
            return 0;
        }
    }

    @SuppressLint("MissingPermission")
    public static String getDriverUUID(Context context) {
        boolean flag = checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        if (!flag) {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            final String tmDevice, tmSerial, tmPhone, androidId;
            tmDevice = "" + tm.getDeviceId();
            tmSerial = "" + tm.getSimSerialNumber();
            androidId = "" + Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            return deviceUuid.toString(); // uniqueId

        }
        return "";
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

    @SuppressLint("MissingPermission")
    public static String getDriverIMIE(Context context) {
        try {
            boolean flag = checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
            if (!flag) {
                return getTelephonyManager(context).getDeviceId();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }


    private static TelephonyManager telephonyManager;


    private static TelephonyManager getTelephonyManager(Context context) {
        if (telephonyManager == null) {
            telephonyManager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        }
        return telephonyManager;
    }

    public static Map getSDInfo() {
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                Map jsonObject = new HashMap();
                File path = Environment.getExternalStorageDirectory();
                StatFs sf = new StatFs(path.getPath());
                // SMT_RANDOM_SORT_BEGIN
                long blockSize = sf.getBlockSize();
                long totalBlock = sf.getBlockCount();
                long availableBlock = sf.getAvailableBlocks();
                long totalLong = totalBlock * blockSize;
                long freeLong = availableBlock * blockSize;
                long useLong = totalLong - freeLong;
                // SMT_RANDOM_SORT_END
                // SMT_RANDOM_SORT_BEGIN
                jsonObject.put("totalSize", totalLong); //__xor__
                jsonObject.put("freeSize", freeLong); //__xor__
                jsonObject.put("useSize", useLong); //__xor__
                // SMT_RANDOM_SORT_END
                return jsonObject;
            } else if (Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED)) {
                return null;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<String> getSystemPhotoList(Context context) {
        List<String> result = new ArrayList<String>();
        try {
            if (!checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

                ContentResolver contentResolver = context.getContentResolver();
                Cursor cursor = contentResolver.query(uri, null, null, null, null);
                if (cursor == null || cursor.getCount() <= 0) {
                    return null;
                }
                while (cursor.moveToNext()) {
                    int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    String path = cursor.getString(index);
                    File file = new File(path);
                    if (file.exists()) {
                        result.add(path);
                    }
                }
            }
        } catch (Exception e) {
        }
        return result;
    }

    public static Boolean isSimCardReady(Context context) {
        try {
            TelephonyManager telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int simState = telMgr.getSimState();
            switch (simState) {
                case TelephonyManager.SIM_STATE_ABSENT:
                    break;
                case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                    break;
                case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                    // do something
                    break;
                case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                    // do something
                    break;
                case TelephonyManager.SIM_STATE_READY:
                    return true;
                case TelephonyManager.SIM_STATE_UNKNOWN:
                    // do something
                    break;
            }
            return false;
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressLint("MissingPermission")
    public static String getDataNetworkType(Context context) {
        String ret = null;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                switch (telephonyManager.getDataNetworkType()) {
                    // SMT_RANDOM_SORT_BEGIN
                    case NETWORK_TYPE_EDGE:
                    case NETWORK_TYPE_GPRS:
                    case NETWORK_TYPE_CDMA:
                    case NETWORK_TYPE_IDEN:
                    case NETWORK_TYPE_1xRTT:
                        // SMT_RANDOM_SORT_END
                        ret = "2G";
                        break;
                    // SMT_RANDOM_SORT_BEGIN
                    case NETWORK_TYPE_UMTS:
                    case NETWORK_TYPE_HSDPA:
                    case NETWORK_TYPE_HSPA:
                    case NETWORK_TYPE_HSPAP:
                    case NETWORK_TYPE_EVDO_0:
                    case NETWORK_TYPE_EVDO_A:
                    case NETWORK_TYPE_EVDO_B:
                        // SMT_RANDOM_SORT_END
                        ret = "3G";
                        break;
                    case NETWORK_TYPE_LTE:
                        ret = "4G";
                        break;
                    case NETWORK_TYPE_NR:
                        ret = "5G";
                        break;
                    default:
                        ret = "Unknown";
                }
            }
        } catch (Exception e) {
        }
        return ret;
    }

    public static String getNetworkOperatorName(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getNetworkOperatorName();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getSimCountryIso(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getSimCountryIso();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isMobileData(Context context) {
        boolean mobileDataEnabled = false; // Assume disabled

        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            Class cmClass = Class.forName(cm.getClass().getName());
            Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
            method.setAccessible(true); // Make the method callable
            // get the setting for "mobile data"
            mobileDataEnabled = (Boolean) method.invoke(cm);
        } catch (Exception e) {
            // Some problem accessible private API
            // TODO do whatever error handling you want here
        }
        return mobileDataEnabled;
    }

    public static String getNetworkOperator(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getNetworkOperator();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getNetworkType(Context context) {
        try {
            TelephonyManager teleMan = (TelephonyManager)
                    context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                int networkType = teleMan.getNetworkType();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                        return "1xRTT";  //__xor__
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                        return "CDMA"; //__xor__
                    case NETWORK_TYPE_EDGE:
                        return "EDGE"; //__xor__
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        return "EVDO rev. B"; //__xor__
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                        return "GPRS"; //__xor__
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                        return "HSDPA"; //__xor__
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                        return "HSPA"; //__xor__
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        return "HSPA+"; //__xor__
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                        return "eHRPD"; //__xor__
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                        return "HSUPA"; //__xor__
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        return "iDen"; //__xor__
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        return "LTE"; //__xor__
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        return "EVDO rev. 0"; //__xor__
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                        return "EVDO rev. A"; //__xor__
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                        return "UMTS"; //__xor__
                    case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                        return "Unknown"; //__xor__
                }
            }
        } catch (Exception e) {
        }

        return "Unknown"; //__xor__
    }

    @SuppressLint("MissingPermission")
    public static String getSimSerialNumber(Context context) {
        boolean flag = checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        if (!flag) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getSimSerialNumber();
        }
        return "";
    }

    public static String getBrand() {
        try {
            String brand = Build.BRAND;
            return brand;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getModel() {
        try {
            String model = Build.MODEL;
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getLine1Number(Context context) {
        boolean flag = checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        if (!flag) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getLine1Number();
        }
        return "";
    }

    public static String getMNC(Context context) {
        try {
            Configuration cfg = context.getResources().getConfiguration();
            return cfg.mnc + "";
        } catch (Exception e) {
            return null;
        }
    }

    private static final String marshmallowMacAddress = "02:00:00:00:00:00";
    private static final String fileAddressMac = "/sys/class/net/wlan0/address"; //__xor__

    @SuppressLint("MissingPermission")
    public static String getAdresseMAC(Context context) {
        WifiManager wifiMan = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInf = wifiMan.getConnectionInfo();
        if (wifiInf != null && marshmallowMacAddress.equals(wifiInf.getMacAddress())) {
            String result = null;
            try {
                result = getAdressMacByInterface();
                if (result != null) {
                    return result;
                } else {
                    result = getAddressMacByFile(wifiMan);
                    return result;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (wifiInf != null && wifiInf.getMacAddress() != null) {
                return wifiInf.getMacAddress();
            } else {
                return "";
            }
        }
        return marshmallowMacAddress;
    }

    private static String getAdressMacByInterface() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (nif.getName().equalsIgnoreCase("wlan0")) {
                    byte[] macBytes = nif.getHardwareAddress();
                    if (macBytes == null) {
                        return "";
                    }

                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02X:", b));
                    }

                    if (res1.length() > 0) {
                        res1.deleteCharAt(res1.length() - 1);
                    }
                    return res1.toString();
                }
            }

        } catch (Exception e) {
        }
        return null;
    }

    private static String getAddressMacByFile(WifiManager wifiMan) throws Exception {
        String ret;
        int wifiState = wifiMan.getWifiState();

        wifiMan.setWifiEnabled(true);
        File fl = new File(fileAddressMac);
        FileInputStream fin = new FileInputStream(fl);
        ret = crunchifyGetStringFromStream(fin);
        fin.close();

        boolean enabled = WifiManager.WIFI_STATE_ENABLED == wifiState;
        wifiMan.setWifiEnabled(enabled);
        return ret;
    }

    private static String crunchifyGetStringFromStream(InputStream crunchifyStream) throws IOException {
        if (crunchifyStream != null) {
            Writer crunchifyWriter = new StringWriter();

            char[] crunchifyBuffer = new char[2048];
            try {
                Reader crunchifyReader = new BufferedReader(new InputStreamReader(crunchifyStream, "UTF-8"));
                int counter;
                while ((counter = crunchifyReader.read(crunchifyBuffer)) != -1) {
                    crunchifyWriter.write(crunchifyBuffer, 0, counter);
                }
            } finally {
                crunchifyStream.close();
            }
            return crunchifyWriter.toString();
        } else {
            return "No Contents"; //__xor__
        }
    }

    public static String getPhone(Context context){
        String tel1 = "";
        try {
            TelephonyManager tm=(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            @SuppressLint("MissingPermission")
            String tel = tm.getLine1Number();//获取本机号码
            tel1 = tel;
        }catch (Exception e) {

        }
        return tel1;
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

    public static String getDeviceId(Context context) {
        boolean flag = checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        if (!flag) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            @SuppressLint("MissingPermission") String deviceId = tm.getDeviceId();
            return deviceId;
        }
        return "";
    }

    public static String getMCC(Context context) {
        try {
            Configuration cfg = context.getResources().getConfiguration();
            return cfg.mcc + "";
        } catch (Exception e) {
            return null;
        }
    }

    public static String getKeyboard(Context context) {
        try {
            Configuration cfg = context.getResources().getConfiguration();
            return cfg.keyboard + "";
        } catch (Exception e) {
            return null;
        }
    }

    public static String getPhoneType(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String phoneTypeStr = "";
            int phoneType = tm.getPhoneType();
            switch (phoneType) {
                case TelephonyManager.PHONE_TYPE_CDMA:
                    phoneTypeStr = "CDMA"; //__xor__
                    break;
                case TelephonyManager.PHONE_TYPE_GSM:
                    phoneTypeStr = "GSM"; //__xor__
                    break;
                case TelephonyManager.PHONE_TYPE_SIP:
                    phoneTypeStr = "SIP"; //__xor__
                    break;
                case TelephonyManager.PHONE_TYPE_NONE:
                    phoneTypeStr = "None"; //__xor__
                    break;
            }
            return phoneTypeStr;
        } catch (Exception e) {
            return null;
        }
    }


    public static boolean checkSelfPermission(Context context, String permission) {
        if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            return true;

        } else {
            return false;
        }
    }
}
