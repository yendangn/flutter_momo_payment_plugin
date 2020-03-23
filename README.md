
# momo_payment_plugin


## Setup

* Register your application at momo business home page : https://business.momo.vn

### 1. Android set up:
* Step 1: Request Internet permission at  ```AndroidManifest.xml``` file:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

### 2. iOS set up:
* Step 1: Update ```Info.plist``` file  as below:
```xml
<key>CFBundleURLTypes</key>
<array>
  <dict>
    <key>CFBundleURLName</key>
    <string></string>
    <key>CFBundleURLSchemes</key>
    <array>
      <string>partnerSchemeId</string>
    </array>
  </dict>
</array>
<key>LSApplicationQueriesSchemes</key>
<array>
  <string>momo</string>
</array>
<key>NSAppTransportSecurity</key>
<dict>
  <key>NSAllowsArbitraryLoads</key>
  <true/>
</dict>
```

* Step 2: Define call back url for momo  at ```AppDelegate.swift ``` file:
```swift
import momo_payment_plugin
```
```swift
override func application(_ application: UIApplication, open url: URL, sourceApplication: String?, annotation: Any) -> Bool {
        MoMoPayment.handleOpenUrl(url: url, sourceApp: sourceApplication!)
        return true
    }

override func application(_ app: UIApplication, open url: URL, options: [UIApplicationOpenURLOptionsKey : Any]) -> Bool {
        MoMoPayment.handleOpenUrl(url: url, sourceApp: "")
        return true
    }
```

### Get more at: https://developers.momo.vn/#/

## How to user plugin?

* Step 1: Import :
```Dart
import 'package:momo_payment_plugin/momo_payment_data.dart';
import 'package:momo_payment_plugin/momo_payment_plugin.dart';
import 'package:momo_payment_plugin/momo_payment_result.dart';
```
* Step 2: Use ```MomoPaymentPlugin``` to
```Dart
MomoPaymentData momoPaymentData = MomoPaymentData(
  appScheme: "[your_appScheme]",
  merchantname: '[your_merchantname]',
  merchantcode: '[your_merchantcode]',
  amount: 2000,
  orderId: 'orderId',
  orderLabel: 'orderLabel',
  merchantnamelabel: "sdsds",
  fee: 200,
  description: null,
  requestId: 'requestId',
  partnerCode: '[your_partnerCode]',
);

MomoPaymentResult momoPaymentResult =
    await MomoPaymentPlugin().requestPayment(momoPaymentData);
```
### Detail of ```MomoPaymentData```:
```Dart
class MomoPaymentData {
String merchantname;
String merchantcode;
String appScheme;
double amount;
String orderId;
String orderLabel;
String merchantnamelabel;
double fee;
String description;
String requestId;
String partnerCode;
String extraData;
bool isDevelopmentMode;
}
```

### Detail of ```MomoPaymentResult```:
```Dart
class MomoPaymentResult {
bool isSuccess;
int status;
String token;
String phonenumber;
}
```
