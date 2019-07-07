package com.example.tc.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefUtil {

    private static final String SHARED_PREFERENCE_TAG = "SHARED_PREF_TAG";
    public static final String BLOG_DATA = "BLOG_DATA";
    public static final String BLOG_DATA_AT_10X_INDEX = "BLOG_DATA_AT_10X_INDEX";
    public static final String BLOG_DATA_AT_0TH_INDEX = "BLOG_DATA_AT_0TH_INDEX";
    public static final String BLOG_WORD_COUNT = "BLOG_WORD_COUNT";

    public static void saveStringInSP(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringFromSP(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_TAG, Context.MODE_PRIVATE);
        return preferences.getString(key, null);
    }

}
