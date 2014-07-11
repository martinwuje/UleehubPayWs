package com.icbc.b2c.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.config.AlipayConfig;
import com.icbc.b2c.config.IcbcB2cConfig;
import com.uleehub.pay.entity.TradeInfo;
import com.uleehub.pay.service.TradeInfoService;
import com.uleehub.webservice.soap.pay.response.GetPayStatusResult;

/**
 * <p>User: mtwu
 * <p>Date: 2014-7-3
 * <p>Version: 1.0
 */
public interface IcbcB2cQuery {
	
	/**
	 * 
	 * @param tradeInfo
	 * @return
	 */
	GetPayStatusResult query(String orderNo, String tranDate);
	
}
