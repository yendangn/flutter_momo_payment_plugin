class MomoPaymentResult {
  bool isSuccess;
  int status;
  String token;
  String phonenumber;

  MomoPaymentResult({
    this.isSuccess,
    this.status,
    this.token,
    this.phonenumber,
  });

  static MomoPaymentResult fromMap(Map<String, dynamic> map) {
    return MomoPaymentResult(
      isSuccess: map['isSuccess'],
      status: map['status'] != null ? map['status'] : null,
      token: map['token'] != null ? map['token'] : null,
      phonenumber: map['phonenumber'] != null ? map['phonenumber'] : null,
    );
  }
}
