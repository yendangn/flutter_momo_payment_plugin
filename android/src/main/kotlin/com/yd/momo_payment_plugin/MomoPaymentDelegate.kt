package com.yd.momo_payment_plugin

import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry
import vn.momo.momo_partner.AppMoMoLib

class MomoPaymentDelegate(private var registrar: PluginRegistry.Registrar? = null) {

    private var resultDelegate: MomoPaymentResultDelegate? = null

    init {
        resultDelegate = MomoPaymentResultDelegate(registrar?.activity())
        registrar?.addActivityResultListener(resultDelegate)
    }

    fun requestPayment(result: MethodChannel.Result, momoRequestPaymentData: Any) {
        resultDelegate?.setPendingResult(Constant.METHOD_REQUEST_PAYMENT, result)

        AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT)
        AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN)

        val paymentInfo: HashMap<String, Any> = momoRequestPaymentData as HashMap<String, Any>
        val isDevelopmentMode: Boolean? = paymentInfo["isDevelopmentMode"] as Boolean?

        if (isDevelopmentMode == null || !isDevelopmentMode) {
            AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.PRODUCTION)
        } else {
            AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT)
        }

        AppMoMoLib.getInstance().requestMoMoCallBack(registrar?.activity(), paymentInfo)
    }

}