package com.alipay.config;

import com.uleehub.util.PropertyLoader;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//支付地址
	
	
	public final static String provider = "ALIPAY";
	public final static String refund_data_spliter = "#";
	public final static String refund_data_item_spliter = "\\^";

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	//服务接口名称
	public static String alipay_service = "create_direct_pay_by_user";
	public static String alirefund_service = "refund_fastpay_by_platform_pwd";
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088901629210026";
	// 商户的私钥
	public static String key = "kr6ikckbbcly5zbor1cchjb2urz8o8zf";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 签名方式 不需修改
	public static String sign_type = "MD5";
	// 卖家支付宝帐户
	public static String seller_email = "sunp@sctis.com";
	//支付页面跳转同步通知页面路径
	public static String alipay_return_url = "";
	//支付服务器异步通知页面路径
	public static String alipay_notify_url = "";
	//退款服务器异步通知页面路径
	public static String alirefund_notify_url = "";
	//支付地址
	public static String alipay_gateway_url="";
	
	
	public static synchronized void reload(){
		seller_email = PropertyLoader.getAliConfigProperty(AlipayContext.ALIPAY_SELLER_EMAIL);
		partner = PropertyLoader.getAliConfigProperty(AlipayContext.ALIPAY_PARTNER);
		key = PropertyLoader.getAliConfigProperty(AlipayContext.ALIPAY_KEY);
		log_path = PropertyLoader.getAliConfigProperty(AlipayContext.ALIPAY_LOG_PATH);
		sign_type = PropertyLoader.getAliConfigProperty(AlipayContext.ALIPAY_SIGN_TYPE);
		input_charset = PropertyLoader.getAliConfigProperty(AlipayContext.ALIPAY_INPUT_CHARSET);
		alipay_return_url = PropertyLoader.getAliConfigProperty(AlipayContext.ALIPAY_RETURN_URL);
		alipay_notify_url = PropertyLoader.getAliConfigProperty(AlipayContext.ALIPAY_NOTIFY_URL);
		alirefund_notify_url = PropertyLoader.getAliConfigProperty(AlipayContext.ALIREFUND_NOTIFY_URL);
		alipay_gateway_url = PropertyLoader.getAliConfigProperty(AlipayContext.ALIPAY_GATEWAY_URL);
		alipay_service = PropertyLoader.getAliConfigProperty(AlipayContext.ALIPAY_SERVICE);
		alirefund_service = PropertyLoader.getAliConfigProperty(AlipayContext.ALIREFUND_SERVICE);
	}
	
	static {
		AlipayConfig.reload();
	}
}
