package com.icbc.b2c.query;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.icbc.b2c.config.IcbcB2cConfig;
import com.icbc.b2c.util.IcbcB2CUtil;
import com.uleehub.pay.entity.TradeInfo;
import com.uleehub.pay.service.TradeInfoService;
import com.uleehub.webservice.soap.pay.response.GetPayStatusResult;
import com.uleehub.webservice.soap.pay.response.base.WSResult;

/**
 * <p>User: mtwu
 * <p>Date: 2014-7-3
 * <p>Version: 1.0
 */
public class IcbcB2cQueryImpl implements IcbcB2cQuery{
	Logger logger = LoggerFactory.getLogger(IcbcB2cQueryImpl.class);
	
	
	
	/**
	 * 
	 * @param out_trade_no 订单号
	 * @return
	 */
	public GetPayStatusResult query(String orderNo, String tranDate){
		GetPayStatusResult result = new GetPayStatusResult();
		try {
			
			if(StringUtils.isNotBlank(orderNo) && StringUtils.isNotBlank(tranDate)){
				HttpClient httpClient = IcbcB2CUtil.buildQueryHttpClient();
				PostMethod postmethod = IcbcB2CUtil.buildQueryPostMethod(orderNo, tranDate);
				httpClient.executeMethod(postmethod);
				int statusCode = postmethod.getStatusCode();
				switch (statusCode) {
				case 200:
					String responseBody = postmethod.getResponseBodyAsString();
					IcbcB2CUtil.processQueryResponseBody(responseBody, result);
					break;
				default:
					String msg = "查询请求出现错误，HttpStatusCode:["+statusCode + "]"+"  ResponseBody:["+postmethod.getResponseBodyAsString()+"]";
					logger.error(msg);
					result.setError(WSResult.SYSTEM_ERROR, msg);
				}
			}
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(WSResult.SYSTEM_ERROR, e.getMessage());
		}
		return result;
	}
	
	
}
