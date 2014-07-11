package com.uleehub.pay.alipay.service;

import org.junit.Assert;
import org.junit.Test;

import com.alipay.config.AlipayConfig;
import com.icbc.b2c.config.IcbcB2cConfig;
import com.uleehub.pay.entity.TradeInfo;

/**
 * <p>
 * User: mtwu
 * <p>
 * Date: 2014-6-26
 * <p>
 * Version: 1.0
 */
public class TradeInforIT extends BaseTradeInforIT {
	@Test
	public void testCreateTradeInfo() {
		TradeInfo tradeInfo = createDefaultTradeInfo();
		Assert.assertNotNull(tradeInfo);
	}
	@Test
	public void testFindByOutTradeNoAndPrivider() {
		TradeInfo tradeInfo = tradeInfoService.findByOutTradeNoAndProvider("12345678", AlipayConfig.provider);
		Assert.assertNotNull(tradeInfo);
	}
	
	
}
