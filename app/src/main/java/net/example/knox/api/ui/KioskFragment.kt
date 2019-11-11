package net.example.knox.api.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import net.example.knox.api.App
import net.example.knox.api.R
import net.example.knox.api.samsung.KnoxAPI
 
class KioskFragment: PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    val kApi = KnoxAPI()
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.kiosk_prefs, rootKey)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

        when(key) {
            "allowMultiWindowMode" -> kApi.setAllowMultiWindowMode(sharedPreferences!!.getBoolean(key, false))
            "allowTaskManager" -> kApi.setAllowTaskManager(sharedPreferences!!.getBoolean(key, false))
        }
    }

    override fun onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause()

    }

    override fun onResume() {
        super.onResume()
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    }
}