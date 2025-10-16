package com.zebra.rxloggertestapp;

public interface Log {
    boolean isDebug = true;

    static void v(String tag, String msg) {
        String tag_ = isDebug ? "pankaj_" + tag : tag;
        android.util.Log.v(tag_, msg);
    }

    static void v(String tag, String msg, Throwable tr) {
        String tag_ = isDebug ? "pankaj_" + tag : tag;
        android.util.Log.v(tag_, msg, tr);
    }

    static void d(String tag, String msg) {
        String tag_ = isDebug ? "pankaj_" + tag : tag;
        android.util.Log.d(tag_, msg);
    }

    static void d(String tag, String msg, Throwable tr) {
        String tag_ = isDebug ? "pankaj_" + tag : tag;
        android.util.Log.d(tag_, msg, tr);
    }

    static void i(String tag, String msg) {
        String tag_ = isDebug ? "pankaj_" + tag : tag;
        android.util.Log.i(tag_, msg);
    }

    static void i(String tag, String msg, Throwable tr) {
        String tag_ = isDebug ? "pankaj_" + tag : tag;
        android.util.Log.i(tag_, msg, tr);
    }

    static void w(String tag, String msg) {
        String tag_ = isDebug ? "pankaj_" + tag : tag;
        android.util.Log.w(tag_, msg);
    }

    static void w(String tag, String msg, Throwable tr) {
        String tag_ = isDebug ? "pankaj_" + tag : tag;
        android.util.Log.w(tag_, msg, tr);
    }

    static void w(String tag, Throwable tr) {
        String tag_ = isDebug ? "pankaj_" + tag : tag;
        android.util.Log.w(tag_, tr);
    }

    static void e(String tag, String msg) {
        String tag_ = isDebug ? "pankaj_" + tag : tag;
        android.util.Log.e(tag_, msg);
    }

    static void e(String tag, String msg, Throwable tr) {
        String tag_ = isDebug ? "pankaj_" + tag : tag;
        android.util.Log.e(tag_, msg, tr);
    }

    static void wtf(String tag, String msg) {
        String tag_ = isDebug ? "pankaj_" + tag : tag;
        android.util.Log.wtf(tag_, msg);
    }

    static void wtf(String tag, Throwable tr) {
        String tag_ = isDebug ? "pankaj_" + tag : tag;
        android.util.Log.wtf(tag_, tr);
    }

    static void wtf(String tag, String msg, Throwable tr) {
        String tag_ = isDebug ? "pankaj_" + tag : tag;
        android.util.Log.wtf(tag_, msg, tr);
    }
}
