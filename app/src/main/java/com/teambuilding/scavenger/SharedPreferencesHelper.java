package com.teambuilding.scavenger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesHelper {

    private SharedPreferences mPrefs;
    private static SharedPreferencesHelper instance;

    private SharedPreferencesHelper(){ }

    public static SharedPreferencesHelper getInstance(){
        if (instance == null) instance = new SharedPreferencesHelper();
        return instance;
    }

    public void Initialize(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private static final String PREF_KEY_COUNT = "key_count";
    private static final String PREF_KEY_ACTIVITY_INDEX = "key_activity_index";

    public int getCount() {
        return mPrefs.getInt(PREF_KEY_COUNT, 0);
    }

    public void setCount(int count) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt(PREF_KEY_COUNT, count);
        mEditor.commit();
    }

    public int getActivityIndex() {
        return mPrefs.getInt(PREF_KEY_ACTIVITY_INDEX, 0);
    }

    public void setActivityIndex(int index) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt(PREF_KEY_ACTIVITY_INDEX, index);
        mEditor.commit();
    }


}
