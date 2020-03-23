#import "MomoPaymentPlugin.h"
#import <momo_payment_plugin/momo_payment_plugin-Swift.h>

@implementation MomoPaymentPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftMomoPaymentPlugin registerWithRegistrar:registrar];
}
@end
