package com.uleehub.webservice.soap.pay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.icbc.b2c.config.IcbcB2cConfig;
import com.icbc.b2c.model.FormData;
import com.icbc.b2c.model.OrderInfo;
import com.icbc.b2c.model.TranData;
import com.icbc.b2c.pay.IcbcB2CPay;
import com.icbc.b2c.pay.IcbcB2CPayImpl;
import com.icbc.b2c.query.IcbcB2cQuery;
import com.icbc.b2c.query.IcbcB2cQueryImpl;
import com.uleehub.pay.entity.TradeInfo;
import com.uleehub.pay.service.TradeInfoService;
import com.uleehub.webservice.soap.pay.common.BaseWebService;
import com.uleehub.webservice.soap.pay.response.GetPayStatusResult;
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
@WebService(serviceName = "IcbcB2cPayStatusService", endpointInterface = "com.uleehub.webservice.soap.pay.IcbcB2cPayStatusService", targetNamespace = WsConstants.NS)
public class IcbcB2cPayStatusServiceImpl extends BaseWebService implements IcbcB2cPayStatusService {
	private static Logger logger = LoggerFactory.getLogger(IcbcB2cPayStatusServiceImpl.class);
	
	private TradeInfoService tradeInfoService;
	
	
	@Override
	public GetPayStatusResult query(String out_trade_no) {
		GetPayStatusResult result = new GetPayStatusResult();
		try {
			TradeInfo tradeInfo = tradeInfoService.findByOutTradeNoAndProvider(out_trade_no, IcbcB2cConfig.provider);
			if(null == tradeInfo){
				result = new GetPayStatusResult();
				result.setError(GetPayStatusResult.PARAMETER_ERROR, "参数错误 out_trade_no:["+out_trade_no+"]");
			}
			else{
				String tranDate = new DateTime(tradeInfo.getTradeDate().getTime()).toString("yyyyMMdd");
				IcbcB2cQuery icbcB2cQuery = new IcbcB2cQueryImpl();
				result = icbcB2cQuery.query(tradeInfo.getOutTradeNo(), tranDate);
				return result;
			}
			
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
