package com.icbc.b2c.pay;

import java.io.FileInputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.infosec.icbc.ReturnValue;

import com.icbc.b2c.config.IcbcB2cConfig;
import com.icbc.b2c.model.B2cConfig;
import com.icbc.b2c.model.FormData;
import com.icbc.b2c.model.OrderInfo;
import com.icbc.b2c.model.TranData;
import com.icbc.b2c.util.IcbcB2CUtil;

public class IcbcB2CPayImpl implements IcbcB2CPay {
	static Logger logger = LoggerFactory.getLogger(IcbcB2CPayImpl.class);

	public String packTranData(B2cConfig b2cConfig, TranData tranData) {
		String xml = "";
		try {
			tranData.setInterfaceName(b2cConfig.getInterfaceName());
			System.out.println("tranData.getInterfaceVersion()=======" + tranData.getInterfaceVersion());

			tranData.setMerAcct(b2cConfig.getMerAcct());
			tranData.setVerifyJoinFlag(b2cConfig.getVerifyJoinFlag());
			tranData.setLanguage(b2cConfig.getLanguage());
			tranData.setCurType(b2cConfig.getCurType());
			tranData.setMerID(b2cConfig.getMerID());
			tranData.setCreditType(b2cConfig.getCreditType());
			tranData.setNotifyType(b2cConfig.getNotifyType());
			tranData.setResultType(b2cConfig.getResultType());
			tranData.setMerReference(b2cConfig.getMerReference());

			tranData.setMerHint(b2cConfig.getMerHint());
			tranData.setRemark1(b2cConfig.getRemark1());
			tranData.setRemark2(b2cConfig.getRemark2());
			tranData.setMerURL(b2cConfig.getMerURL());
			tranData.setMerVAR(b2cConfig.getMerVAR());

			xml = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?><B2CReq>";
			if (!"ICBC_PERBANK_B2C".equals(tranData.getInterfaceName())) {
				System.out.println("错误：接口名称不正确，取值必须为ICBC_PERBANK_B2C");
				logger.error("错误：接口名称不正确，取值必须为ICBC_PERBANK_B2C");
				return null;
			}

			xml = xml + "<interfaceName>" + tranData.getInterfaceName() + "</interfaceName>";

			if ((!"1.0.0.11".equals(tranData.getInterfaceVersion())) && (!"1.0.0.13".equals(tranData.getInterfaceVersion())) && (!"1.0.0.14".equals(tranData.getInterfaceVersion()))
					&& (!"1.0.0.3".equals(tranData.getInterfaceVersion())) && (!"1.0.0.8".equals(tranData.getInterfaceVersion()))) {
				System.out.println("错误：接口版本号不正确，取值必须为1.0.0.11,1.0.0.13,1.0.0.14,1.0.0.3,1.0.0.8");
				logger.error("错误：接口版本号不正确，取值必须为1.0.0.11,1.0.0.13,1.0.0.14,1.0.0.3,1.0.0.8");
				return null;
			}

			xml = xml + "<interfaceVersion>" + tranData.getInterfaceVersion() + "</interfaceVersion>";
			if ((!"1.0.0.3".equals(tranData.getInterfaceVersion())) && (!"1.0.0.8".equals(tranData.getInterfaceVersion()))) {
				if ((tranData.getOrderDate().getBytes().length != 14) || (!IcbcB2CUtil.checkNum(tranData.getOrderDate()))) {
					System.out.println("错误：交易日期时间格式不正确，正确的格式为：YYYYMMDDHHmmss。");
					logger.error("错误：交易日期时间格式不正确，正确的格式为：YYYYMMDDHHmmss。");
					return null;
				}

				xml = xml + "<orderInfo><orderDate>" + tranData.getOrderDate() + "</orderDate>";

				if (!"001".equals(tranData.getCurType())) {
					System.out.println("错误：支付币种不正确，应为001。");
					logger.error("错误：支付币种不正确，应为001。");
					return null;
				}

				xml = xml + "<curType>" + tranData.getCurType() + "</curType>";

				if ((tranData.getMerID() == null) || ("".equals(tranData.getMerID())) || (tranData.getMerID().getBytes().length > 20)) {
					System.out.println("错误：商城代码格式不正确。");
					logger.error("错误：商城代码格式不正确。");
					return null;
				}

				xml = xml + "<merID>" + tranData.getMerID() + "</merID>";

				xml = xml + "<subOrderInfoList>";
				List orderList = tranData.getOrderInfoVector();
				if ((orderList == null) || (orderList.size() == 0)) {
					System.out.println("错误：没有订单数据。");
					logger.error("错误：没有订单数据。");
					return null;
				}
				for (int i = 0; i < orderList.size(); i++) {
					OrderInfo order = (OrderInfo) orderList.get(i);
					xml = xml + "<subOrderInfo>";
					if ((order.getOrderid() == null) || ("".equals(order.getOrderid())) || (order.getOrderid().getBytes().length > 30)) {
						System.out.println("错误：订单号不能为空，且不能超过30位。");
						logger.error("错误：订单号不能为空，且不能超过30位。");
						return null;
					}

					xml = xml + "<orderid>" + order.getOrderid() + "</orderid>";

					if (order.getAmount().getBytes().length > 10) {
						System.out.println("错误：订单金额不能超过10位。");
						logger.error("错误：订单金额不能超过10位。");
						return null;
					}
					if (!IcbcB2CUtil.checkNum(order.getAmount())) {
						System.out.println("错误：订单金额格式不正确。");
						logger.error("错误：订单金额格式不正确。");
						return null;
					}

					xml = xml + "<amount>" + order.getAmount() + "</amount>";

					if ((order.getInstallmentTimes() == null) || ("".equals(order.getInstallmentTimes()))) {
						System.out.println("错误：分期付款期数不能为空。");
						logger.error("错误：分期付款期数不能为空。");
						return null;
					}
					if ((!"1".equals(order.getInstallmentTimes())) && (!"3".equals(order.getInstallmentTimes())) && (!"6".equals(order.getInstallmentTimes()))
							&& (!"9".equals(order.getInstallmentTimes())) && (!"12".equals(order.getInstallmentTimes())) && (!"18".equals(order.getInstallmentTimes()))
							&& (!"24".equals(order.getInstallmentTimes()))) {
						System.out.println("错误：分期付款期数错误，取值必须为：1、3、6、9、12、18、24。");
						logger.error("错误：分期付款期数错误，取值必须为：1、3、6、9、12、18、24。");
						return null;
					}

					xml = xml + "<installmentTimes>" + order.getInstallmentTimes() + "</installmentTimes>";

					if ((tranData.getMerAcct() == null) || ("".equals(tranData.getMerAcct())) || (tranData.getMerAcct().getBytes().length > 19)) {
						System.out.println("错误：商户账号不能为空，且不能超过19位。");
						logger.error("错误：商户账号不能为空，且不能超过19位。");
						return null;
					}

					xml = xml + "<merAcct>" + tranData.getMerAcct() + "</merAcct>";

					if ((!"".equals(order.getGoodsID())) && (order.getGoodsID() != null)) {
						if (order.getGoodsID().getBytes().length > 30) {
							System.out.println("错误：商品编号不能超过30位。");
							logger.error("错误：商品编号不能超过30位。");
							return null;
						}

						xml = xml + "<goodsID>" + order.getGoodsID() + "</goodsID>";
					} else {
						xml = xml + "<goodsID></goodsID>";
					}
					if ((order.getGoodsName() == null) || ("".equals(order.getGoodsName())) || (order.getGoodsName().getBytes().length > 60)) {
						System.out.println("错误：商品名称不能为空，且不能超过60位。");
						logger.error("错误：商品名称不能为空，且不能超过60位。");
						return null;
					}

					xml = xml + "<goodsName>" + order.getGoodsName() + "</goodsName>";

					if ((!"".equals(order.getGoodsNum())) && (order.getGoodsNum() != null)) {
						if (order.getGoodsNum().getBytes().length > 10) {
							System.out.println("错误：商品数量不能超过10位。");
							logger.error("错误：商品数量不能超过10位。");
							return null;
						}

						xml = xml + "<goodsNum>" + order.getGoodsNum() + "</goodsNum>";
					} else {
						xml = xml + "<goodsNum></goodsNum>";
					}
					if ((!"".equals(order.getCarriageAmt())) && (order.getCarriageAmt() != null)) {
						if (order.getCarriageAmt().getBytes().length > 10) {
							System.out.println("错误：已含运费金额不能超过10位。");
							logger.error("错误：已含运费金额不能超过10位。");
							return null;
						}
						if ((!IcbcB2CUtil.checkNum(order.getCarriageAmt())) && (!"0".equals(order.getCarriageAmt()))) {
							System.out.println("错误：已含运费金额格式不正确。");
							logger.error("错误：已含运费金额格式不正确。");
							return null;
						}

						xml = xml + "<carriageAmt>" + order.getCarriageAmt() + "</carriageAmt>";
					} else {
						xml = xml + "<carriageAmt></carriageAmt>";
					}

					xml = xml + "</subOrderInfo>";
				}
				xml = xml + "</subOrderInfoList></orderInfo><custom>";
				if ((!"1".equals(tranData.getVerifyJoinFlag())) && (!"0".equals(tranData.getVerifyJoinFlag()))) {
					System.out.println("错误：检验联名标志不能为空，且取值只能为1或0。");
					logger.error("错误：检验联名标志不能为空，且取值只能为1或0。");
					return null;
				}

				xml = xml + "<verifyJoinFlag>" + tranData.getVerifyJoinFlag() + "</verifyJoinFlag>";

				if ((!"".equals(tranData.getLanguage())) && (tranData.getLanguage() != null)) {
					if ((!"EN_US".equals(tranData.getLanguage())) && (!"ZH_CN".equals(tranData.getLanguage()))) {
						System.out.println("错误：语言版本格式不正确，取值只能为：EN_US或ZH_CN。");
						logger.error("错误：语言版本格式不正确，取值只能为：EN_US或ZH_CN。");
						return null;
					}

					xml = xml + "<Language>" + tranData.getLanguage() + "</Language>";
				} else {
					xml = xml + "<Language></Language>";
				}
				if ("1.0.0.14".equals(tranData.getInterfaceVersion())) {
					System.out.println("tranData.getHangSupportFlag()=======" + tranData.getHangSupportFlag());
					xml = xml + "<HangSupportFlag>" + tranData.getHangSupportFlag() + "</HangSupportFlag>";
					xml = xml + "<HangTimeInterval>" + tranData.getHangTimeInterval() + "</HangTimeInterval>";
				}
				xml = xml + "</custom><message>";
				if ((!"0".equals(tranData.getCreditType())) && (!"1".equals(tranData.getCreditType())) && (!"2".equals(tranData.getCreditType()))) {
					System.out.println("错误：支持订单支付的银行卡种类格式错误，取值范围为0、1、2");
					logger.error("错误：支持订单支付的银行卡种类格式错误，取值范围为0、1、2");
					return null;
				}

				xml = xml + "<creditType>" + tranData.getCreditType() + "</creditType>";

				if ((!"".equals(tranData.getNotifyType())) && (tranData.getNotifyType() != null)) {
					if ((!"HS".equals(tranData.getNotifyType())) && (!"AG".equals(tranData.getNotifyType()))) {
						System.out.println("错误：通知类型格式错误，取值只能为：HS或AG。");
						logger.error("错误：通知类型格式错误，取值只能为：HS或AG。");
						return null;
					}

					xml = xml + "<notifyType>" + tranData.getNotifyType() + "</notifyType>";
				} else {
					xml = xml + "<notifyType></notifyType>";
				}
				if ((!"".equals(tranData.getResultType())) && (tranData.getResultType() != null)) {
					if ((!"0".equals(tranData.getResultType())) && (!"1".equals(tranData.getResultType()))) {
						System.out.println("错误：结果发送类型不正确，取值为：0或1。");
						logger.error("错误：结果发送类型不正确，取值为：0或1。");
						return null;
					}

					xml = xml + "<resultType>" + tranData.getResultType() + "</resultType>";
				} else {
					xml = xml + "<resultType></resultType>";
				}
				if ((!"".equals(tranData.getMerReference())) && (tranData.getMerReference() != null)) {
					if (tranData.getMerReference().getBytes().length > 200) {
						System.out.println("错误：商户reference最大长度不能超过200。");
						logger.error("错误：商户reference最大长度不能超过200。");
						return null;
					}

					xml = xml + "<merReference>" + tranData.getMerReference() + "</merReference>";
				} else {
					xml = xml + "<merReference></merReference>";
				}
				if ((!"".equals(tranData.getMerCustomIp())) && (tranData.getMerCustomIp() != null)) {
					if (tranData.getMerCustomIp().getBytes().length > 20) {
						System.out.println("错误：客户端IP最大长度不能超过20。");
						logger.error("错误：客户端IP最大长度不能超过20。");
						return null;
					}

					xml = xml + "<merCustomIp>" + tranData.getMerCustomIp() + "</merCustomIp>";
				} else {
					if (("".equals(tranData.getMerReference())) || (tranData.getMerReference() == null)) {
						System.out.println("错误：当商户reference项送空时，商户IP必输。");
						logger.error("错误：当商户reference项送空时，商户IP必输。");
						return null;
					}

					xml = xml + "<merCustomIp></merCustomIp>";
				}

				if ((!"".equals(tranData.getGoodsType())) && (tranData.getGoodsType() != null)) {
					if ((!"0".equals(tranData.getGoodsType())) && (!"1".equals(tranData.getGoodsType()))) {
						System.out.println("错误：虚拟商品/实物商品标志位输入值不正确，只能输入：0或1");
						logger.error("错误：虚拟商品/实物商品标志位输入值不正确，只能输入：0或1");
						return null;
					}

					xml = xml + "<goodsType>" + tranData.getGoodsType() + "</goodsType>";
				} else {
					xml = xml + "<goodsType></goodsType>";
				}
				if ((!"".equals(tranData.getMerCustomID())) && (tranData.getMerCustomID() != null)) {
					if (tranData.getMerCustomID().getBytes().length > 100) {
						System.out.println("错误：买家用户号最大长度不能超过100。");
						logger.error("错误：买家用户号最大长度不能超过100。");
						return null;
					}

					xml = xml + "<merCustomID>" + tranData.getMerCustomID() + "</merCustomID>";
				} else {
					xml = xml + "<merCustomID></merCustomID>";
				}
				if ((!"".equals(tranData.getMerCustomPhone())) && (tranData.getMerCustomPhone() != null)) {
					if (tranData.getMerCustomPhone().getBytes().length > 20) {
						System.out.println("错误：买家联系电话最大长度不能超过20。");
						logger.error("错误：买家联系电话最大长度不能超过20。");
						return null;
					}

					xml = xml + "<merCustomPhone>" + tranData.getMerCustomPhone() + "</merCustomPhone>";
				} else {
					xml = xml + "<merCustomPhone></merCustomPhone>";
				}
				if ((!"".equals(tranData.getGoodsAddress())) && (tranData.getGoodsAddress() != null)) {
					if (tranData.getGoodsAddress().getBytes().length > 200) {
						System.out.println("错误：收货地址最大长度不能超过200。");
						logger.error("错误：收货地址最大长度不能超过200。");
						return null;
					}

					xml = xml + "<goodsAddress>" + tranData.getGoodsAddress() + "</goodsAddress>";
				} else {
					xml = xml + "<goodsAddress></goodsAddress>";
				}
				if ((!"".equals(tranData.getMerOrderRemark())) && (tranData.getMerOrderRemark() != null)) {
					if (tranData.getMerOrderRemark().getBytes().length > 200) {
						System.out.println("错误：订单备注最大长度不能超过200。");
						logger.error("错误：订单备注最大长度不能超过200。");
						return null;
					}

					xml = xml + "<merOrderRemark>" + tranData.getMerOrderRemark() + "</merOrderRemark>";
				} else {
					xml = xml + "<merOrderRemark></merOrderRemark>";
				}
				if ((!"".equals(tranData.getMerHint())) && (tranData.getMerHint() != null)) {
					if (tranData.getMerHint().getBytes().length > 120) {
						System.out.println("错误：商城提示最大长度不能超过120。");
						logger.error("错误：商城提示最大长度不能超过120。");
						return null;
					}

					xml = xml + "<merHint>" + tranData.getMerHint() + "</merHint>";
				} else {
					xml = xml + "<merHint></merHint>";
				}
				if ((!"".equals(tranData.getRemark1())) && (tranData.getRemark1() != null)) {
					if (tranData.getRemark1().getBytes().length > 100) {
						System.out.println("错误：备注字段1最大长度不能超过100。");
						logger.error("错误：备注字段1最大长度不能超过100。");
						return null;
					}

					xml = xml + "<remark1>" + tranData.getRemark1() + "</remark1>";
				} else {
					xml = xml + "<remark1></remark1>";
				}
				if ((!"".equals(tranData.getRemark2())) && (tranData.getRemark2() != null)) {
					if (tranData.getRemark2().getBytes().length > 100) {
						System.out.println("错误：备注字段2最大长度不能超过100。");
						logger.error("错误：备注字段2最大长度不能超过100。");
						return null;
					}

					xml = xml + "<remark2>" + tranData.getRemark2() + "</remark2>";
				} else {
					xml = xml + "<remark2></remark2>";
				}
				if (("".equals(tranData.getMerURL())) || (tranData.getMerURL() == null)) {
					System.out.println("错误：返回商户URL不能为空。");
					logger.error("错误：返回商户URL不能为空。");
					return null;
				}
				if (tranData.getMerURL().getBytes().length > 1024) {
					System.out.println("错误：返回商户URL最大长度不能超过1024。");
					logger.error("错误：返回商户URL最大长度不能超过1024。");
					return null;
				}

				xml = xml + "<merURL>" + tranData.getMerURL() + "</merURL>";

				if ((!"".equals(tranData.getMerVAR())) && (tranData.getMerVAR() != null)) {
					if (tranData.getMerVAR().getBytes().length > 1024) {
						System.out.println("错误：返回商户变量最大长度不能超过1024。");
						logger.error("错误：返回商户变量最大长度不能超过1024。");
						return null;
					}

					xml = xml + "<merVAR>" + tranData.getMerVAR() + "</merVAR>";
				} else {
					xml = xml + "<merVAR></merVAR>";
				}

				xml = xml + "</message></B2CReq>";
			}
			if (("1.0.0.3".equals(tranData.getInterfaceVersion())) || ("1.0.0.8".equals(tranData.getInterfaceVersion()))) {
				if ((tranData.getOrderDate().getBytes().length != 14) || (!IcbcB2CUtil.checkNum(tranData.getOrderDate()))) {
					System.out.println("错误：交易日期时间格式不正确，正确的格式为：YYYYMMDDHHmmss。");
					logger.error("错误：交易日期时间格式不正确，正确的格式为：YYYYMMDDHHmmss。");
					return null;
				}

				xml = xml + "<orderInfo><orderDate>" + tranData.getOrderDate() + "</orderDate>";
				List orderList = tranData.getOrderInfoVector();
				OrderInfo order = (OrderInfo) orderList.get(0);
				if ((order.getOrderid() == null) || ("".equals(order.getOrderid())) || (order.getOrderid().getBytes().length > 30)) {
					System.out.println("错误：订单号不能为空，且不能超过30位。");
					logger.error("错误：订单号不能为空，且不能超过30位。");
					return null;
				}

				xml = xml + "<orderid>" + order.getOrderid() + "</orderid>";

				if (order.getAmount().getBytes().length > 10) {
					System.out.println("错误：订单金额不能超过10位。");
					logger.error("错误：订单金额不能超过10位。");
					return null;
				}
				if (!IcbcB2CUtil.checkNum(order.getAmount())) {
					System.out.println("错误：订单金额格式不正确。");
					logger.error("错误：订单金额格式不正确。");
					return null;
				}

				xml = xml + "<amount>" + order.getAmount() + "</amount>";
				if (!"001".equals(tranData.getCurType())) {
					System.out.println("错误：支付币种不正确，应为001。");
					logger.error("错误：支付币种不正确，应为001。");
					return null;
				}

				xml = xml + "<curType>" + tranData.getCurType() + "</curType>";
				if ((tranData.getMerID() == null) || ("".equals(tranData.getMerID())) || (tranData.getMerID().getBytes().length > 20)) {
					System.out.println("错误：商城代码格式不正确。");
					logger.error("错误：商城代码格式不正确。");
					return null;
				}

				xml = xml + "<merID>" + tranData.getMerID() + "</merID>";
				if ((tranData.getMerAcct() == null) || ("".equals(tranData.getMerAcct())) || (tranData.getMerAcct().getBytes().length > 19)) {
					System.out.println("错误：商户账号不能为空，且不能超过19位。");
					logger.error("错误：商户账号不能为空，且不能超过19位。");
					return null;
				}

				xml = xml + "<merAcct>" + tranData.getMerAcct() + "</merAcct></orderInfo><custom>";
				if ((!"1".equals(tranData.getVerifyJoinFlag())) && (!"0".equals(tranData.getVerifyJoinFlag()))) {
					System.out.println("错误：检验联名标志不能为空，且取值只能为1或0。");
					logger.error("错误：检验联名标志不能为空，且取值只能为1或0。");
					return null;
				}

				xml = xml + "<verifyJoinFlag>" + tranData.getVerifyJoinFlag() + "</verifyJoinFlag>";

				if ((!"".equals(tranData.getLanguage())) && (tranData.getLanguage() != null)) {
					if ((!"EN_US".equals(tranData.getLanguage())) && (!"ZH_CN".equals(tranData.getLanguage()))) {
						System.out.println("错误：语言版本格式不正确，取值只能为：EN_US或ZH_CN。");
						logger.error("错误：语言版本格式不正确，取值只能为：EN_US或ZH_CN。");
						return null;
					}

					xml = xml + "<Language>" + tranData.getLanguage() + "</Language>";
				} else {
					xml = xml + "<Language></Language>";
				}

				xml = xml + "</custom><message>";
				if ((!"".equals(order.getGoodsID())) && (order.getGoodsID() != null)) {
					if (order.getGoodsID().getBytes().length > 30) {
						System.out.println("错误：商品编号不能超过30位。");
						logger.error("错误：商品编号不能超过30位。");
						return null;
					}

					xml = xml + "<goodsID>" + order.getGoodsID() + "</goodsID>";
				} else {
					xml = xml + "<goodsID></goodsID>";
				}
				if ((order.getGoodsName() == null) || ("".equals(order.getGoodsName())) || (order.getGoodsName().getBytes().length > 60)) {
					System.out.println("错误：商品名称不能为空，且不能超过60位。");
					logger.error("错误：商品名称不能为空，且不能超过60位。");
					return null;
				}

				xml = xml + "<goodsName>" + order.getGoodsName() + "</goodsName>";

				if ((!"".equals(order.getGoodsNum())) && (order.getGoodsNum() != null)) {
					if (order.getGoodsNum().getBytes().length > 10) {
						System.out.println("错误：商品数量不能超过10位。");
						logger.error("错误：商品数量不能超过10位。");
						return null;
					}

					xml = xml + "<goodsNum>" + order.getGoodsNum() + "</goodsNum>";
				} else {
					xml = xml + "<goodsNum></goodsNum>";
				}
				if ((!"".equals(order.getCarriageAmt())) && (order.getCarriageAmt() != null)) {
					if (order.getCarriageAmt().getBytes().length > 10) {
						System.out.println("错误：已含运费金额不能超过10位。");
						logger.error("错误：已含运费金额不能超过10位。");
						return null;
					}
					if ((!IcbcB2CUtil.checkNum(order.getCarriageAmt())) && (!"0".equals(order.getCarriageAmt()))) {
						System.out.println("错误：已含运费金额格式不正确。");
						logger.error("错误：已含运费金额格式不正确。");
						return null;
					}

					xml = xml + "<carriageAmt>" + order.getCarriageAmt() + "</carriageAmt>";
				} else {
					xml = xml + "<carriageAmt></carriageAmt>";
				}
				if ((!"".equals(tranData.getMerHint())) && (tranData.getMerHint() != null)) {
					if (tranData.getMerHint().getBytes().length > 120) {
						System.out.println("错误：商城提示最大长度不能超过120。");
						logger.error("错误：商城提示最大长度不能超过120。");
						return null;
					}

					xml = xml + "<merHint>" + tranData.getMerHint() + "</merHint>";
				} else {
					xml = xml + "<merHint></merHint>";
				}
				if ((!"".equals(tranData.getRemark1())) && (tranData.getRemark1() != null)) {
					if (tranData.getRemark1().getBytes().length > 100) {
						System.out.println("错误：备注字段1最大长度不能超过100。");
						logger.error("错误：备注字段1最大长度不能超过100。");
						return null;
					}

					xml = xml + "<remark1>" + tranData.getRemark1() + "</remark1>";
				} else {
					xml = xml + "<remark1></remark1>";
				}
				if ((!"".equals(tranData.getRemark2())) && (tranData.getRemark2() != null)) {
					if (tranData.getRemark2().getBytes().length > 100) {
						System.out.println("错误：备注字段2最大长度不能超过100。");
						logger.error("错误：备注字段2最大长度不能超过100。");
						return null;
					}

					xml = xml + "<remark2>" + tranData.getRemark2() + "</remark2>";
				} else {
					xml = xml + "<remark2></remark2>";
				}
				if (("".equals(tranData.getMerURL())) || (tranData.getMerURL() == null)) {
					System.out.println("错误：返回商户URL不能为空。");
					logger.error("错误：返回商户URL不能为空。");
					return null;
				}
				if (tranData.getMerURL().getBytes().length > 1024) {
					System.out.println("错误：返回商户URL最大长度不能超过1024。");
					logger.error("错误：返回商户URL最大长度不能超过1024。");
					return null;
				}

				xml = xml + "<merURL>" + tranData.getMerURL() + "</merURL>";

				if ((!"".equals(tranData.getMerVAR())) && (tranData.getMerVAR() != null)) {
					if (tranData.getMerVAR().getBytes().length > 1024) {
						System.out.println("错误：返回商户变量最大长度不能超过1024。");
						logger.error("错误：返回商户变量最大长度不能超过1024。");
						return null;
					}

					xml = xml + "<merVAR>" + tranData.getMerVAR() + "</merVAR>";
				} else {
					xml = xml + "<merVAR></merVAR>";
				}
				if ("1.0.0.8".equals(tranData.getInterfaceVersion())) {
					if ((!"0".equals(tranData.getCreditType())) && (!"1".equals(tranData.getCreditType())) && (!"2".equals(tranData.getCreditType()))) {
						System.out.println("错误：支持订单支付的银行卡种类格式错误，取值范围为0、1、2");
						logger.error("错误：支持订单支付的银行卡种类格式错误，取值范围为0、1、2");
						return null;
					}

					xml = xml + "<creditType>" + tranData.getCreditType() + "</creditType>";
				}

				xml = xml + "</message></B2CReq>";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return xml;
	}

	public FormData createFormData(String xmlPath, TranData tranData) {
		FormData formdata = new FormData();
		try {
			B2cConfig b2cConfig = IcbcB2CUtil.loadXML(xmlPath);
			if (b2cConfig != null) {
				String tranDataXmlStr = packTranData(b2cConfig, tranData);
				System.out.println("tranData明文串：" + tranDataXmlStr);
				logger.info("tranData明文串：" + tranDataXmlStr);
				if (tranDataXmlStr == null) {
					return null;
				}

				String pwd = b2cConfig.getPassword();
				String userCrtPath = b2cConfig.getUserCrtPath();
				String userKeyPath = b2cConfig.getUserKeyPath();
				byte[] byteTranData = tranDataXmlStr.getBytes();

				byte[] Base64TranData = ReturnValue.base64enc(byteTranData);
				String StrBase64TranData = new String(Base64TranData).toString();
				System.out.println("tranData明文串的base64编码：" + StrBase64TranData);
				logger.info("tranData明文串的base64编码：" + StrBase64TranData);

				char[] keyPass = pwd.toCharArray();

				FileInputStream in1 = new FileInputStream(userCrtPath);
				byte[] bcert = new byte[in1.available()];
				in1.read(bcert);
				in1.close();

				FileInputStream in2 = new FileInputStream(userKeyPath);
				byte[] bkey = new byte[in2.available()];
				in2.read(bkey);
				in2.close();

				byte[] sign = ReturnValue.sign(byteTranData, byteTranData.length, bkey, keyPass);
				if (sign == null) {
					System.out.println("错误：签名失败,签名返回为空。请检查证书私钥和私钥保护口令是否正确。");
					logger.error("错误：签名失败,签名返回为空。请检查证书私钥和私钥保护口令是否正确。");
					return null;
				}

				System.out.println("签名成功。");

				byte[] EncSign = ReturnValue.base64enc(sign);
				String SignMsgBase64 = new String(EncSign).toString();
				System.out.println("签名信息BASE64编码：" + SignMsgBase64);
				logger.info("签名信息BASE64编码：" + SignMsgBase64);

				byte[] EncCert = ReturnValue.base64enc(bcert);
				String CertBase64 = new String(EncCert).toString();
				System.out.println("证书公钥BASE64编码：" + CertBase64);
				logger.info("证书公钥BASE64编码：" + CertBase64);

				formdata.setInterfaceName("ICBC_PERBANK_B2C");

				formdata.setTranData(StrBase64TranData);

				formdata.setMerSignMsg(SignMsgBase64);

				formdata.setMerCert(CertBase64);

				return formdata;
			}

			System.out.println("错误：配置文件有误，请检查配置文件存放路径和配置文件格式是否正确。");
			logger.error("错误：配置文件有误，请检查配置文件存放路径和配置文件格式是否正确。");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	public FormData createFormData(TranData tranData) {
		FormData formdata = new FormData();
		try {
			B2cConfig b2cConfig = IcbcB2CUtil.loadDefault();
			if (b2cConfig != null) {
				String tranDataXmlStr = packTranData(b2cConfig, tranData);
				System.out.println("tranData明文串：" + tranDataXmlStr);
				logger.info("tranData明文串：" + tranDataXmlStr);
				if (tranDataXmlStr == null) {
					return null;
				}

				String pwd = b2cConfig.getPassword();
				String userCrtPath = b2cConfig.getUserCrtPath();
				String userKeyPath = b2cConfig.getUserKeyPath();
				byte[] byteTranData = tranDataXmlStr.getBytes();

				byte[] Base64TranData = ReturnValue.base64enc(byteTranData);
				String StrBase64TranData = new String(Base64TranData).toString();
				System.out.println("tranData明文串的base64编码：" + StrBase64TranData);
				logger.info("tranData明文串的base64编码：" + StrBase64TranData);

				char[] keyPass = pwd.toCharArray();

				FileInputStream in1 = new FileInputStream(userCrtPath);
				byte[] bcert = new byte[in1.available()];
				in1.read(bcert);
				in1.close();

				FileInputStream in2 = new FileInputStream(userKeyPath);
				byte[] bkey = new byte[in2.available()];
				in2.read(bkey);
				in2.close();

				byte[] sign = ReturnValue.sign(byteTranData, byteTranData.length, bkey, keyPass);
				if (sign == null) {
					System.out.println("错误：签名失败,签名返回为空。请检查证书私钥和私钥保护口令是否正确。");
					logger.error("错误：签名失败,签名返回为空。请检查证书私钥和私钥保护口令是否正确。");
					return null;
				}

				System.out.println("签名成功。");

				byte[] EncSign = ReturnValue.base64enc(sign);
				String SignMsgBase64 = new String(EncSign).toString();
				System.out.println("签名信息BASE64编码：" + SignMsgBase64);
				logger.info("签名信息BASE64编码：" + SignMsgBase64);

				byte[] EncCert = ReturnValue.base64enc(bcert);
				String CertBase64 = new String(EncCert).toString();
				System.out.println("证书公钥BASE64编码：" + CertBase64);
				logger.info("证书公钥BASE64编码：" + CertBase64);

				formdata.setInterfaceName("ICBC_PERBANK_B2C");

				formdata.setTranData(StrBase64TranData);

				formdata.setMerSignMsg(SignMsgBase64);

				formdata.setMerCert(CertBase64);

				return formdata;
			}

			System.out.println("错误：配置文件有误，请检查配置文件存放路径和配置文件格式是否正确。");
			logger.error("错误：配置文件有误，请检查配置文件存放路径和配置文件格式是否正确。");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	public String buildRequest(FormData formData){
		try {
			String interfaceName=formData.getInterfaceName();
			//InterfaceVersion=fd.getInterfaceVersion();
			String merCert=formData.getMerCert();
			String merSignMsg=formData.getMerSignMsg();
			String tranData=formData.getTranData();
			
			
			StringBuffer sbHtml = new StringBuffer();
			sbHtml.append("<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>代理商户B2C支付页面</title></head><body>");
			sbHtml.append("<form id=\"icbcpaysubmit\" name=\"alipaysubmit\" action=\"" + IcbcB2cConfig.icbc_b2c_gateway_url
					+ "\">");
			
			sbHtml.append("<input type=\"hidden\" name=\"interfaceName\" value=\"" + interfaceName + "\"/>");
			sbHtml.append("<input type=\"hidden\" name=\"interfaceVersion\" value=\"" + IcbcB2cConfig.interfaceVersion + "\"/>");
			sbHtml.append("<input type=\"hidden\" name=\"tranData\" value=\"" + tranData + "\"/>");
			sbHtml.append("<input type=\"hidden\" name=\"merSignMsg\" value=\"" + merSignMsg + "\"/>");
			sbHtml.append("<input type=\"hidden\" name=\"merCert\" value=\"" + merCert + "\"/>");
			sbHtml.append("<input type=\"hidden\" name=\"HangSupportFlag\" value=\"" + IcbcB2cConfig.icbc_b2c_hangSupportFlag + "\"/>");
			sbHtml.append("<input type=\"hidden\" name=\"HangTimeInterval\" value=\"" + IcbcB2cConfig.icbc_b2c_hangTimeInterval+ "\"/>");
			//submit按钮控件请不要含有name属性
			sbHtml.append("<input type=\"submit\" value=\"提交\" style=\"display:none;\"></form>");
			sbHtml.append("<script>document.forms['icbcpaysubmit'].submit();</script>");
			sbHtml.append("</body></html>");
			return sbHtml.toString();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
