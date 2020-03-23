import Flutter
import UIKit

public class SwiftMomoPaymentPlugin: NSObject, FlutterPlugin {
    
    var momoFlutterResult: FlutterResult? = nil
    
    public static func register(with registrar: FlutterPluginRegistrar) {
        let channel = FlutterMethodChannel(name: "com.yd.momo/momo_payment", binaryMessenger: registrar.messenger())
        let instance = SwiftMomoPaymentPlugin()
        registrar.addMethodCallDelegate(instance, channel: channel)
    }
    
    public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
        
        if(call.method == "requestPayment") {
            self.setUpMoMoChanelMethod(call: call, result: result)
        } else {
            result(FlutterMethodNotImplemented)
        }
    }
    
    private func setUpMoMoChanelMethod (call: FlutterMethodCall, result: @escaping FlutterResult) {
        self.momoFlutterResult = result
        NotificationCenter.default.removeObserver(self, name: NSNotification.Name(rawValue: "NoficationCenterTokenReceived"), object: nil)
        
        NotificationCenter.default.addObserver(self, selector: #selector(self.NoficationCenterTokenReceived), name:NSNotification.Name(rawValue: "NoficationCenterTokenReceived"), object: nil)
        
        guard let paymentInfo = call.arguments as? NSMutableDictionary else {
            result([
                "isSuccess" : false])
            return
        }
        
        MoMoPayment.createPaymentInformation(info: paymentInfo)
        MoMoPayment.requestToken()
    }
    
    
    @objc func NoficationCenterTokenReceived(notif: NSNotification) {
        
        let response:NSMutableDictionary = notif.object! as! NSMutableDictionary
        
        let _statusStr = "\(response["status"] as! String)"
        if (_statusStr == "0") {
            self.momoFlutterResult?([
                "isSuccess" : true,
                "status" : 0,
                "token" : response["data"],
                "phonenumber" : response["phonenumber"]
            ])
        } else{
            self.momoFlutterResult?([
                "isSuccess" : false,
                "status" : Double(_statusStr),
            ])
        }
    }
}
