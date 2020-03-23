package com.yd.momo_payment_plugin

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class MomoPaymentPlugin(private val registrar: Registrar) : MethodCallHandler {

    private var momoPaymentDelegate = MomoPaymentDelegate(registrar)

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val momoPaymentPlugin = MomoPaymentPlugin(registrar)
            val channel = MethodChannel(registrar.messenger(), Constant.CHANNEL_NAME)
            channel.setMethodCallHandler(momoPaymentPlugin)
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        when (call.method) {
            Constant.METHOD_REQUEST_PAYMENT -> {
                momoPaymentDelegate.requestPayment(result, call.arguments)
            }
            else -> result.notImplemented()
        }
    }

}