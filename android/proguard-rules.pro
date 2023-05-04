# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


#ByWebView
-optimizationpasses 1
-obfuscationdictionary dictionary_rules.txt
-classobfuscationdictionary dictionary_rules.txt
-packageobfuscationdictionary dictionary_rules.txt

-ignorewarnings

-dontwarn io.branch.**
-dontwarn okhttp3.**

-keep class com.appsflyer.** { *; }
#-dontwarn com.android.installreferrer

-keepattributes *Annotation*
-keep class com.google.android.gms.** { *; }
-keep class com.facebook.applinks.** { *; }
-keepclassmembers class com.facebook.applinks.** { *; }
#-keep class com.facebook.FacebookSdk { *; }
-keep class com.facebook.** { *; }
-keep class io.branch.** { *; }

-keep class com.android.installreferrer.** { *; }
-dontwarn com.android.installreferrer.**


-keepattributes *Annotation*
-keepattributes *JavascriptInterface*

-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-dontwarn com.google.**

-keepattributes Signature
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-dontwarn com.google.gson.**
-keep class com.google.gson.** {*;}
-keep class com.google.protobuf.** {*;}
#-keep class com.sand.land.money.cash.loan.loan.bean.** {
#    *;
#}

# fastjson proguard rules
# https://github.com/alibaba/fastjson
-dontwarn com.alibaba.fastjson.**
-keepattributes Signature
-keepattributes *Annotation*

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}

-keep class com.alittle.pity.cash.loan.money.store.in.go.cash.pity.** {*;}

-keep class com.huantansheng.easyphotos.models.** { *; }



