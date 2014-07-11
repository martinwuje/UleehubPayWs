package com.icbc.b2c.config;

import com.uleehub.util.PropertyLoader;


/**
 * <p>
 * User: mtwu
 * <p>
 * Date: 2014-6-30
 * <p>
 * Version: 1.0
 */
public class IcbcB2cConfig {
	
	//字符集
	public static String input_charset = "utf-8";
	
	public final static String provider = "ICBC";
	// 接口名称
	public static String interfaceName = "";
	// 接口版本号
	public static String interfaceVersion = "";
	// 语言版本 选输，默认为中文版 取值：“EN_US”为英文版； 取值：“ZH_CN”或其他为中文版。 注意：大小写敏感。 
	public static String language = "ZH_CN";
	//检验联名标志 必输， 取值“1”：客户支付时，网银判断该客户是否与商户联名，是则按上送金额扣帐，否则展现未联名错误；取值“0”：不检验客户是否与商户联名，按上送金额扣帐。	 
	public static String verifyJoinFlag = "0";
	//支付币种 必输，用来区分一笔支付的币种，目前工行只支持使用人民币（001）支付。取值： “001”
	public static String curType = "001";
	//商户代码 必输，唯一确定一个商户的代码，由商户在工行开户时，由工行告知商户。
	public static String merID = "123456";
	//商户账号 必输，每笔订单一个，可以相同；商户入账账号，只能交易时指定。（商户付给银行手续费的账户，可以在开户的时候指定，也可以用交易指定方式；用交易指定方式则使用此商户账号）
	public static String merAcct = "";
	//支持订单支付的银行卡种类, 必输 默认“2”。取值范围为0、1、2，其中0表示仅允许使用借记卡支付，1表示仅允许使用信用卡支付，2表示借记卡和信用卡都能对订单进行支付
	public static String creditType = "2";
	//通知类型,必输在交易转账处理完成后把交易结果通知商户的处理模式。取值“HS”：在交易完成后实时将通知信息以HTTP协议POST方式，主动发送给商户，发送地址为商户端随订单数据提交的接收工行支付结果的URL即表单中的merURL字段；取值“AG”：在交易完成后不通知商户。商户需使用浏览器登录工行的B2C商户服务网站，或者使用工行提供的客户端程序API主动获取通知信息。
	public static String notifyType = "HS";
	//结果发送类型,选输取值“0”：无论支付成功或者失败，银行都向商户发送交易通知信息；取值“1”，银行只向商户发送交易成功的通知信息。只有通知方式为HS时此值有效，如果使用AG方式，可不上送此项，但签名数据中必须包含此项，取值可为空。
	public static String resultType = "0";
	//商户reference,选输，上送商户网站域名（例如：pay.某B2C商城.com），支持首字段通配符（例如：*.某B2C商城.com）。如果上送，工行会在客户支付订单时，校验商户上送域名与客户跳转工行支付页面之前网站域名的一致性。
	public static String merReference = "";
	//客户端IP,选输，工行在支付页面显示该信息。注意：1、	使用IPV4格式。2、	上送的是客户端的公网IP。3、	当商户reference项送空时，该项必输。
	public static String merCustomIp = "";
	//虚拟商品/实物商品标志位,选输，若输入：取值“0”：虚拟商品；取值“1”，实物商品。
	public static String goodsType = "0";
	//商城提示
	public static String merHint = "";
	//返回商户URL,必输 必须合法的URL，交易结束，将客户引导到商户的此url，即通过客户浏览器post交易结果信息到商户的此URL 注意：该URL应使用http协议（不能使用https协议），端口号应为80或不指定。
	public static String merURL = "";
	//返回商户变量 选输, 商户自定义，当返回银行结果时，作为一个隐藏域变量，商户可以用此变量维护session等等。由客户端浏览器支付完成后提交通知结果时是明文传输，建议商户对此变量使用额外安全防范措施，如签名、base64
	public static String merVAR = "";
	//商城公钥文件
	public static String userCrtPath;
	//商城私钥文件
	public static String userKeyPath;
	//商城证书私钥密码
	public static String password;
	
	
	//jks文件
	public static String query_serverJksPath;
	//jks密码
	public static String query_serverJksPassword;
	//jks文件
	public static String query_userJksPath;
	//jks密码
	public static String query_userJksPassword;
	//SSL端口
	public static int query_sslPort;
	public static String query_host;
	public static int query_hostPort;
	public static String query_postMethod;
	public static String query_APIName;
	public static String query_APIVersion;
	public static String query_MerReqData;
	
	//支付地址
	public static String icbc_b2c_gateway_url;
	public static String icbc_b2c_hangSupportFlag;
	public static String icbc_b2c_hangTimeInterval;
	
	
	public static synchronized void reload(){
		IcbcB2cConfig.input_charset = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_INPUT_CHARSET);
		IcbcB2cConfig.interfaceName = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_INTERFACENAME);
		IcbcB2cConfig.interfaceVersion = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_INTERFACEVERSION);
		IcbcB2cConfig.language = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_LANGUAGE);
		IcbcB2cConfig.verifyJoinFlag = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_VERIFYJOINFLAG);
		IcbcB2cConfig.curType = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_CURTYPE);
		IcbcB2cConfig.merID = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_MERID);
		IcbcB2cConfig.merAcct = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_MERACCT);
		IcbcB2cConfig.creditType = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_CREDITTYPE);
		IcbcB2cConfig.notifyType = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_NOTIFYTYPE);
		IcbcB2cConfig.resultType = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_RESULTTYPE);
		IcbcB2cConfig.merReference = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_MERREFERENCE);
		IcbcB2cConfig.merCustomIp = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_MERCUSTOMIP);
		IcbcB2cConfig.goodsType = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_GOODSTYPE);
		IcbcB2cConfig.merHint = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_MERHINT);
		IcbcB2cConfig.merURL = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_MERURL);
		IcbcB2cConfig.merVAR = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_MERVAR);
		IcbcB2cConfig.userCrtPath = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_USERCRTPATH);
		IcbcB2cConfig.userKeyPath = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_USERKEYPATH);
		IcbcB2cConfig.password = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_PASSWORD);
		IcbcB2cConfig.icbc_b2c_gateway_url = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_GATEWAY_URL);
		IcbcB2cConfig.icbc_b2c_hangSupportFlag = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_HANGSUPPORTFLAG);
		IcbcB2cConfig.icbc_b2c_hangTimeInterval = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_HANGTIMEINTERVAL);
		IcbcB2cConfig.query_userJksPath = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_QUERY_USERJKSPATH);
		IcbcB2cConfig.query_userJksPassword= PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_QUERY_USERJKSPASSWORD);
		IcbcB2cConfig.query_sslPort = Integer.parseInt(PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_QUERY_SSLPORT));
		IcbcB2cConfig.query_host = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_QUERY_HOST);
		IcbcB2cConfig.query_hostPort = Integer.parseInt(PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_QUERY_HOSTPORT));
		IcbcB2cConfig.query_postMethod = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_QUERY_POSTMETHOD);
		IcbcB2cConfig.query_APIName = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_QUERY_APINAME);
		IcbcB2cConfig.query_APIVersion = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_QUERY_APIVERSION);
		IcbcB2cConfig.query_MerReqData = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_QUERY_MERREQDATA);
		IcbcB2cConfig.query_serverJksPath = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_QUERY_SERVERJKSPATH);
		IcbcB2cConfig.query_serverJksPassword = PropertyLoader.getIcbcB2cProperty(IcbcB2cContext.ICBC_B2C_QUERY_SERVERJKSPASSWORD);
	}
	
	static {
		IcbcB2cConfig.reload();
	}
}
