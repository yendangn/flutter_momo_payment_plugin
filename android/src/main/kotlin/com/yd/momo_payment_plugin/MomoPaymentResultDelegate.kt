package com.yd.momo_payment_plugin

import android.app.Activity
import android.content.Intent
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry
import vn.momo.momo_partner.AppMoMoLib

class MomoPaymentResultDelegate(private val activity: Activity?) : PluginRegistry.ActivityResultListener {

    private var pendingResult: MethodChannel.Result? = null

    fun setPendingResult(methodName: String, result: MethodChannel.Result) {
        if (pendingResult != null) {
            result.error("pendingResult must be not null", null, null)
        }
        pendingResult = result
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO) {
                finishWithResult(data)
            }
        }

        return true
    }

    private fun finishWithResult(data: Intent?) {

        data?.let {

            val status = data.getIntExtra("status", -1)

            if (status == 0) {
                val token = data.getStringExtra("data")
                val phonenumber = data.getStringExtra("phonenumber")

                if (token == null || phonenumber == null) {
                    pendingResult?.success(MomoPaymentResults.error(-1))
                } else {
                    pendingResult?.success(MomoPaymentResults.success(token, phonenumber))
                }
            } else {
                pendingResult?.success(MomoPaymentResults.error(status))
            }

        } ?: run {
            pendingResult?.success(MomoPaymentResults.error(-1))
        }
    }
}
