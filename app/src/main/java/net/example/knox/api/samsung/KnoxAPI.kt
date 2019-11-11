package net.example.knox.api.samsung

import android.util.Log
import com.samsung.android.knox.EnterpriseDeviceManager
import com.samsung.android.knox.kiosk.KioskMode
import com.samsung.android.knox.restriction.RestrictionPolicy
import net.example.knox.api.App
import java.lang.RuntimeException

class KnoxAPI constructor(

    val edm: EnterpriseDeviceManager = EnterpriseDeviceManager.getInstance(App.mContext()),
    val restrictionPolicy: RestrictionPolicy = edm.restrictionPolicy,
    val kioskPolicy: KioskMode = edm.kioskMode

) {

    val LOG_TAG = "KnoxAPITest"
    var isKnoxEnalbed = false
    get() = getIsKnoxEnabled()

    /**
     * checking if a knox API can be set to the same state.  If success - app has access to APIs
     * if fails app does not have access to apis
     */
    private fun getIsKnoxEnabled():Boolean {
        try {
            restrictionPolicy.allowFactoryReset(restrictionPolicy.isFactoryResetAllowed)
            Log.w(LOG_TAG, "Knox API check Passed")
            return true
        } catch (e: RuntimeException) {
            Log.w(LOG_TAG, "Knox API check has failed.")
            return false
        }
    }

    fun setAllowMultiWindowMode(state: Boolean) {
        try {
            kioskPolicy.allowMultiWindowMode(state)
            Log.d(LOG_TAG, "Successfully set allowMultiWindowMode to: ${state}")
        } catch (e: RuntimeException) {
            Log.d(LOG_TAG, "Failed to set allowMultiWindowMode to: ${state}")
        }
    }

    fun getAllowMultiWindowModeState(): Boolean {
        var state = false
        try {
            state = kioskPolicy.isMultiWindowModeAllowed
            Log.d(LOG_TAG, "allowMultiWindowModeState: ${state}")
        } catch (e: SecurityException) {
            Log.d(LOG_TAG, "Failed to get allowMultiWindowMode")
        }
        return state
    }

    fun setAllowTaskManager(state: Boolean) {
        try {
            kioskPolicy.allowTaskManager(state)
            Log.d(LOG_TAG, "Successfully set allowTaskManager to: ${state}")
        } catch (e: RuntimeException) {
            Log.d(LOG_TAG, "Failed to set allowTaskManager to: ${state}")
        }
    }
}

