-optimizationpasses 1
-ignorewarnings
-obfuscationdictionary dictionary_rules.txt
-classobfuscationdictionary dictionary_rules.txt
-packageobfuscationdictionary dictionary_rules.txt


#Flutter Wrapper
-dontwarn io.branch.**
-dontwarn okhttp3.**


-keep class com.appsflyer.** { *; }
-dontwarn com.android.installreferrer

-keepattributes *Annotation*
-keep class com.google.android.gms.** { *; }
-keep class com.facebook.applinks.** { *; }
-keepclassmembers class com.facebook.applinks.** { *; }
-keep class com.facebook.** { *; }
-keep class io.branch.** { *; }

-keep class com.android.installreferrer.** { *; }
-dontwarn com.android.installreferrer.**

# android x
-dontwarn com.google.android.material.**
-keep class com.google.android.material.** { *; }
-dontwarn androidx.**
-keep class androidx.** { *; }
-keep interface androidx.** { *; }

# yitu
-keep class com.oliveapp.camerasdk.** {*;}
-keepattributes InnerClasses
-keep class com.oliveapp.**.R$* {*;}
-keep class com.sand.land.money.cash.loan.loan.R$* {*;}
-keep class com.sand.land.money.cash.loan.loan.**.R$* {*;}
-keep class com.sand.land.money.cash.loan.loan.bean.** {*;}



#-keep class org.greenrobot.eventbus.android.AndroidComponentsImpl

# -keep dfsdk
-dontwarn com.dfsdk.**
-keep class com.dfsdk.** {*;}

-keep class **.R$* {*;}
-keep class com.ms.banner.** {*;}

-dontwarn java.lang.**
-keep class java.lang.** {*;}


-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }
-keepattributes InnerClasses