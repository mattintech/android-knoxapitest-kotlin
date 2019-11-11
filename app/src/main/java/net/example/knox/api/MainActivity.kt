package net.example.knox.api

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.example.knox.api.samsung.KnoxAPI
import net.example.knox.api.ui.KioskFragment
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {

    val kApi = KnoxAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startKioskFragment()

        if (checkAdmin())
            if (checkKnox()) {
                syncPrefsWithApi()
            } else
                checkKnox()
        else
            setupAdmin()
    }

    private fun startKioskFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.kiosk_prefs, KioskFragment())
            .commit()
    }

    private fun checkAdmin(): Boolean {
        val deviceAdmin = ComponentName(this, LicenseAndAdminReceiver::class.java)
        val dpm = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        return dpm.isAdminActive(deviceAdmin)
    }

    private fun setupAdmin() {
        val deviceAdmin = ComponentName(this, LicenseAndAdminReceiver::class.java)
        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, deviceAdmin)
        startActivityForResult(intent, 10)
    }

    private fun checkKnox(): Boolean {
        val admin = LicenseAndAdminReceiver()
        return admin.checkKnox(applicationContext)
    }

    //synchronize the UI with the platform API state
    private fun syncPrefsWithApi() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val sharedPref = sharedPreferences.edit()
        sharedPref.putBoolean("allowMultiWindowMode", kApi.getAllowMultiWindowModeState())
        sharedPref.commit()
    }
}
