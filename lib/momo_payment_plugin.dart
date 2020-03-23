import 'dart:async';
import 'dart:convert';

import 'package:flutter/services.dart';
import 'package:momo_payment_plugin/constant.dart';
import 'package:momo_payment_plugin/momo_payment_data.dart';
import 'package:momo_payment_plugin/momo_payment_result.dart';

class MomoPaymentPlugin {
  static const MethodChannel _channel =
      const MethodChannel(Constant.channelName);

  Future<MomoPaymentResult> requestPayment(
      MomoPaymentData momoPaymentData) async {
    print(json.encode(momoPaymentData));
    try {
      Map<String, dynamic> result =
          await _channel.invokeMapMethod(Constant.methodRequestPayment, momoPaymentData.toJson());
      return MomoPaymentResult.fromMap(result);
    } on PlatformException catch (error) {
      print(error);
      return MomoPaymentResult(
        isSuccess: false,
      );
    }
  }
}
