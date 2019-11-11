package net.example.knox.api

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import com.samsung.android.knox.license.KnoxEnterpriseLicenseManager
import net.example.knox.api.samsung.KnoxAPI

/**
 * This class handles the call backs from the deivce admin activation & the Knox License Activation
 */
class LicenseAndAdminReceiver: DeviceAdminReceiver() {

    private val KNOX_LICENSE = "<INSERT Your Developer Key Here>"
    private val LOG_TAG = "KAPI_SETUP"
    private val knoxApi = KnoxAPI()

    override fun onReceive(context: Context, intent: Intent) {

        val action: String? = intent.action;
        Log.d(LOG_TAG, "LicenseOnReceived = ${action}")

        if (action.equals(ACTION_DEVICE_ADMIN_ENABLED)) {
            checkKnox(context)
        } else if(action.equals(KnoxEnterpriseLicenseManager.ACTION_LICENSE_STATUS)) {
            val errorCode = KnoxEnterpriseLicenseManager.EXTRA_LICENSE_ERROR_CODE
            if(errorCode.equals(KnoxEnterpriseLicenseManager.ERROR_NONE)) {
                Log.d(LOG_TAG, "SS License Manager")
            } else {
                Log.d(LOG_TAG, "License Failed. Reason: " +  intent.getStringExtra(KnoxEnterpriseLicenseManager.EXTRA_LICENSE_STATUS))
            }
        }
    }

    fun checkKnox(mContext: Context): Boolean {
        if(!knoxApi.isKnoxEnalbed) activateKnoxLicense(mContext)
        return knoxApi.isKnoxEnalbed
    }

    private fun activateKnoxLicense(mContext: Context) {
        if(isNetConnected(mContext)) {
            val knoxLicenseManager = KnoxEnterpriseLicenseManager.getInstance(mContext)
            knoxLicenseManager.activateLicense(KNOX_LICENSE)
        }
    }

    @Suppress("DEPRECATION")
    private fun isNetConnected(mContext: Context): Boolean {
        val connManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = connManager.activeNetworkInfo
        return if (ni !=null && ni.isConnected) true else false
    }

    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        Log.d(LOG_TAG, "DeviceAdmin Enabled")
    }

    override fun onDisabled(context: Context, intent: Intent) {
        super.onDisabled(context, intent)
        Log.d(LOG_TAG, "DeviceAdmin Disabled.")
    }
}