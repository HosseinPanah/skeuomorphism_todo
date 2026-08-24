# Android default proguard rules
# https://developer.android.com/studio/build/shrink-code

# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   https://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public static android.webkit.JavascriptInterface *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces you get when you have a bug in release
# builds. It might also be useful for profile guided optimization.
#-keepattributes *Annotations*,LineNumberTable,LocalVariableTable,SourceFile

# Preserve all @JvmStatic methods.
-keepclassmembers class * {
    @androidx.annotation.Keep
    @kotlin.Metadata
    public *
}

# Preserve all Composable functions
-keepclassmembers class * {
    @androidx.compose.runtime.Composable
    public *
}

# Preserve all ViewModel classes
-keep class * extends androidx.lifecycle.ViewModel

# Preserve Room database classes
-keep class * extends androidx.room.Database
-keep class * implements androidx.room.Dao
-keep class * extends androidx.room.Entity

# Preserve all annotation classes
-keepattributes *Annotation*

# Preserve all method names for better stack traces
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
