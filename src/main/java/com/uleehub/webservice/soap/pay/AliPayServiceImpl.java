package com.uleehub.webservice.soap.pay;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.model.BeanWrapper;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.uleehub.pay.entity.TradeInfo;
import com.uleehub.pay.service.TradeInfoService;
import com.uleehub.webservice.soap.pay.common.BaseWebService;
import com.uleehub.webservice.soap.pay.response.GetSHtmlTextResult;
import com.uleehub.webservice.soap.pay.response.GetStatusResult;
import com.uleehub.webservice.soap.pay.response.dto.TradeInfoDTO;

/**
 * 支付WebService服务端实现类.
 * 
 * 
 * @author mtwu
 */
// serviceName指明WSDL中<wsdl:service>与<wsdl:binding>元素的名称,
// endpointInterface属性指向Interface类全称.
@WebService(serviceName = "AliPayService", endpointInterface = "com.uleehub.webservice.soap.pay.AliPayService", targetNamespace = WsConstants.NS)
public class AliPayServiceImpl extends BaseWebService implements AliPayService {
	private static Logger logger = LoggerFactory.getLogger(AliPayServiceImpl.class);
	
	private TradeInfoService tradeInfoService;
	/**
	 * 
	 * @param out_trade_no 商户订单号
	 * @param subject 订单名称
	 * @param total_fee 付款金额
	 * @param body 订单描述
	 * @param show_url 商品展示地址
	 * @param exter_invoke_ip 客户端的IP地址
	 * @return
	 */
	@Override
	public GetSHtmlTextResult pay(String out_trade_no, String subject, String subject_num, String total_fee, String body, String show_url, String exter_invoke_ip, String purchaser_id, String purchaser_name) {
		GetSHtmlTextResult result = new GetSHtmlTextResult();
		try {
			Validate.notNull(out_trade_no, "tradeInfoDTO参数为空");
			Validate.notNull(total_fee, "交易金额参数为空");
			String payment_type = "1";
			// 防钓鱼时间戳
			String anti_phishing_key = "";
			// 持久化订单
			TradeInfo tradeInfo = tradeInfoService.findByOutTradeNo(out_trade_no);
			if(tradeInfo != null){ //判断订单不为空
				if(tradeInfo.getTradeStatus() == 0){  //初始状态
					tradeInfo.setBody(body);
					tradeInfo.setTotalFee(Float.parseFloat(total_fee));
					tradeInfo.setSubject(subject);
					tradeInfo.setSubjectNum(Integer.parseInt(subject_num));
					tradeInfo.setBody(body);
					tradeInfo.setExterInvokeIp(exter_invoke_ip);
					tradeInfo.setShowUrl(show_url);
				}
				else{
					throw new RuntimeException("交易已完成或已被关闭");
				}
			}
			//判断订单为空
			else{
				tradeInfo = new TradeInfo(out_trade_no, subject, Integer.parseInt(subject_num), total_fee, body, show_url, exter_invoke_ip, Integer.parseInt(payment_type), AlipayConfig.seller_email, AlipayConfig.provider, purchaser_id, purchaser_name);
			}
			tradeInfo.setService(AlipayConfig.alipay_service);
			tradeInfoService.save(tradeInfo);
			
			// 把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", AlipayConfig.alipay_service);
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("payment_type", payment_type);
			sParaTemp.put("notify_url", AlipayConfig.alipay_notify_url);
			sParaTemp.put("return_url", AlipayConfig.alipay_return_url);
			sParaTemp.put("seller_email", AlipayConfig.seller_email);
			sParaTemp.put("out_trade_no", out_trade_no);
			sParaTemp.put("subject", subject);
			sParaTemp.put("total_fee", total_fee);
			sParaTemp.put("body", body);
			sParaTemp.put("show_url", show_url);
			sParaTemp.put("anti_phishing_key", anti_phishing_key);
			sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
			// 建立请求
			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
			result.setsHtmlText(sHtmlText);
			System.out.print(sHtmlText);
		} catch (IllegalArgumentException e) {
			return handleParameterError(result, e);
		} catch (RuntimeException e) {
			return handleGeneralError(result, e);
		}
		return result;
	}

	@Override
	public GetSHtmlTextResult payByDTO(TradeInfoDTO tradeInfoDTO) {
		GetSHtmlTextResult result = new GetSHtmlTextResult();
		try {
			Validate.notNull(tradeInfoDTO, "tradeInfoDTO参数为空");
			Validate.notNull(tradeInfoDTO.getOut_trade_no(), "交易号参数为空");
			Validate.notNull(tradeInfoDTO.getTotal_fee(), "交易金额参数为空");
			// 支付类型
			String payment_type = "1";
			// 防钓鱼时间戳
			String anti_phishing_key = "";
			// 持久化订单
			TradeInfo tradeInfo = tradeInfoService.findByOutTradeNo(tradeInfoDTO.getOut_trade_no());
			if(tradeInfo != null){ //判断订单不为空
				if(tradeInfo.getTradeStatus() == 0){ //初始状态
					tradeInfo.setBody(tradeInfoDTO.getBody());
					tradeInfo.setTotalFee(Float.parseFloat(tradeInfoDTO.getTotal_fee()));
					tradeInfo.setSubject(tradeInfoDTO.getSubject());
					tradeInfo.setBody(tradeInfoDTO.getBody());
					tradeInfo.setExterInvokeIp(tradeInfoDTO.getExter_invoke_ip());
					tradeInfo.setShowUrl(tradeInfoDTO.getShow_url());
					tradeInfo.setPurchaserId(tradeInfoDTO.getPurchaser_id());
					tradeInfo.setPurchaserName(tradeInfoDTO.getPurchaser_name());
				}
				else{
					throw new RuntimeException("交易已完成或已被关闭");
				}
			}
			else{
				tradeInfo = new TradeInfo(tradeInfoDTO, Integer.parseInt(payment_type), AlipayConfig.seller_email, AlipayConfig.provider);
			}
			tradeInfo.setService(AlipayConfig.alipay_service);
			tradeInfoService.save(tradeInfo);
			// 把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "create_direct_pay_by_user");
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("payment_type", payment_type);
			sParaTemp.put("notify_url", AlipayConfig.alipay_notify_url);
			sParaTemp.put("return_url", AlipayConfig.alipay_return_url);
			sParaTemp.put("seller_email", AlipayConfig.seller_email);
			sParaTemp.put("out_trade_no", tradeInfoDTO.getOut_trade_no());
			sParaTemp.put("subject", tradeInfoDTO.getSubject());
			sParaTemp.put("total_fee", tradeInfoDTO.getTotal_fee());
			sParaTemp.put("body", tradeInfoDTO.getBody());
			sParaTemp.put("show_url", tradeInfoDTO.getShow_url());
			sParaTemp.put("anti_phishing_key", anti_phishing_key);
			sParaTemp.put("exter_invoke_ip", tradeInfoDTO.getExter_invoke_ip());
			// 建立请求
			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
			result.setsHtmlText(sHtmlText);
		} catch (IllegalArgumentException e) {
			return handleParameterError(result, e);
		} catch (RuntimeException e) {
			return handleGeneralError(result, e);
		}
		return result;
	}
	
	@Override
	public GetStatusResult queryPayStatus(String out_trade_no) {
		GetStatusResult result = new GetStatusResult();
		try {
			Validate.notBlank(out_trade_no, "out_trade_no参数为空");
			TradeInfo tradeInfo = tradeInfoService.findByOutTradeNo(out_trade_no);
			Integer status = tradeInfo.getTradeStatus();
			result.setStatus(status.toString());
			
		} catch (IllegalArgumentException e) {
			return handleParameterError(result, e);
		} catch (RuntimeException e) {
			return handleGeneralError(result, e);
		}
		return result;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}
	
	@Autowired
	public void setTradeInfoService(TradeInfoService tradeInfoService) {
		this.tradeInfoService = tradeInfoService;
	}
	
	
	
}
