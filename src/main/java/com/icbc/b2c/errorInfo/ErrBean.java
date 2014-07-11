package com.icbc.b2c.errorInfo;

public class ErrBean
{
  public static final String const_Err = "错误";
  public static final String const_Err_InterfaceName = "错误：接口名称不正确，取值必须为ICBC_PERBANK_B2C";
  public static final String const_Err_InterfaceVersion = "错误：接口版本号不正确，取值必须为1.0.0.11";
  public static final String const_Err_OrderDate = "错误：交易日期时间格式不正确，正确的格式为：YYYYMMDDHHmmss。";
  public static final String const_Err_CurType = "错误：支付币种不正确，应为001。";
  public static final String const_Err_MerID = "错误：商城代码格式不正确。";
  public static final String const_Err_OrderInfoVector = "错误：没有订单数据。";
  public static final String const_Err_Orderid = "错误：订单号不能为空，且不能超过30位。";
  public static final String const_Err_Amount_1 = "错误：订单金额不能超过10位。";
  public static final String const_Err_Amount_2 = "错误：订单金额格式不正确。";
  public static final String const_Err_InstallmentTimes_1 = "错误：分期付款期数不能为空。";
  public static final String const_Err_InstallmentTimes_2 = "错误：分期付款期数错误，取值必须为：1、3、6、9、12、18、24。";
  public static final String const_Err_merAcct = "错误：商户账号不能为空，且不能超过19位。";
  public static final String const_Err_GoodsID = "错误：商品编号不能超过30位。";
  public static final String const_Err_GoodsName = "错误：商品名称不能为空，且不能超过60位。";
  public static final String const_Err_GoodsNum = "错误：商品数量不能超过10位。";
  public static final String const_Err_CarriageAmt1 = "错误：已含运费金额不能超过10位。";
  public static final String const_Err_CarriageAmt2 = "错误：已含运费金额格式不正确。";
  public static final String const_Err_VerifyJoinFlag = "错误：检验联名标志不能为空，且取值只能为1或0。";
  public static final String const_Err_Language = "错误：语言版本格式不正确，取值只能为：EN_US或ZH_CN。";
  public static final String const_Err_CreditType = "错误：支持订单支付的银行卡种类格式错误，取值范围为0、1、2";
  public static final String const_Err_NotifyType = "错误：通知类型格式错误，取值只能为：HS或AG。";
  public static final String const_Err_ResultType = "错误：结果发送类型不正确，取值为：0或1。";
  public static final String const_Err_MerReference = "错误：商户reference最大长度不能超过200。";
  public static final String const_Err_MerCustomIp_1 = "错误：客户端IP最大长度不能超过20。";
  public static final String const_Err_MerCustomIp_2 = "错误：当商户reference项送空时，商户IP必输。";
  public static final String const_Err_GoodsType = "错误：虚拟商品/实物商品标志位输入值不正确，只能输入：0或1";
  public static final String const_Err_MerCustomID = "错误：买家用户号最大长度不能超过100。";
  public static final String const_Err_MerCustomPhone = "错误：买家联系电话最大长度不能超过20。";
  public static final String const_Err_GoodsAddress = "错误：收货地址最大长度不能超过200。";
  public static final String const_Err_MerOrderRemark = "错误：订单备注最大长度不能超过200。";
  public static final String const_Err_MerHint = "错误：商城提示最大长度不能超过120。";
  public static final String const_Err_Remark1 = "错误：备注字段1最大长度不能超过100。";
  public static final String const_Err_Remark2 = "错误：备注字段2最大长度不能超过100。";
  public static final String const_Err_MerURL_1 = "错误：返回商户URL不能为空。";
  public static final String const_Err_MerURL_2 = "错误：返回商户URL最大长度不能超过1024。";
  public static final String const_Err_MerVAR = "错误：返回商户变量最大长度不能超过1024。";
  public static final String const_Err_Sign = "错误：签名失败,签名返回为空。请检查证书私钥和私钥保护口令是否正确。";
  public static final String const_Err_Config = "错误：配置文件有误，请检查配置文件存放路径和配置文件格式是否正确。";
  public static final String const_Err_DecSignMsg = "错误：签名信息BASE64解码失败。";
  public static final String const_Err_DesNotify = "错误：通知信息BASE64解码失败。";
}
