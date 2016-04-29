package com.example.mukesh.airpollution;
/**
 * Created by tarun on 27/4/16.
 */
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.example.mukesh.airpollution.R;

public class SettingActivity extends PreferenceActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (key.equals(getString(R.string.state_title))) {
            editor.putString(key, "");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}