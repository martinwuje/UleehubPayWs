package com.icbc.b2c.query;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.config.AlipayConfig;
import com.icbc.b2c.config.IcbcB2cConfig;
import com.uleehub.pay.entity.TradeInfo;
import com.uleehub.pay.service.TradeInfoService;
import com.uleehub.test.BaseIT;
import com.uleehub.webservice.soap.pay.response.GetPayStatusResult;

/**
 * <p>User: mtwu
 * <p>Date: 2014-7-4
 * <p>Version: 1.0
 */
public class TIcbcB2cQueryIT extends BaseIT{
	
	@Autowired
	private TradeInfoService tradeInfoService;
	
	@Test
	public void queryT(){
		//TradeInfo tradeInfo = tradeInfoService.findByOutTradeNoAndProvider("123457", IcbcB2cConfig.provider);
		TradeInfo tradeInfo = tradeInfoService.findByOutTradeNoAndProvider("123456", AlipayConfig.provider);
		String tranDate = new DateTime(tradeInfo.getTradeDate().getTime()).toString("yyyyMMdd");
		IcbcB2cQuery icbcB2cQuery = new IcbcB2cQueryImpl();
		GetPayStatusResult result = icbcB2cQuery.query(tradeInfo.getOutTradeNo(), tranDate);
		System.out.println(result.getStatus());
	}
}
