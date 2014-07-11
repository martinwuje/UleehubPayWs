/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.uleehub.pay.service;

import org.springframework.stereotype.Service;

import com.uleehub.common.service.BaseService;
import com.uleehub.pay.entity.TradeInfo;
import com.uleehub.pay.repository.TradeInfoRepository;

/**
 * <p>User: mtwu
 * <p>Date: 14-6-26 下午1:45
 * <p>Version: 1.0
 */
@Service
public class TradeInfoService extends BaseService<TradeInfo, Long> {
	public TradeInfo findByOutTradeNo(String outTradeNo){
		return getTradeInfoRepository().findByOutTradeNo(outTradeNo);
	}
	public TradeInfo findByOutTradeNoAndProvider(String outTradeNo, String provider){
		return getTradeInfoRepository().findByOutTradeNoAndProvider(outTradeNo, provider);
	}
	private TradeInfoRepository getTradeInfoRepository() {
        return (TradeInfoRepository) baseRepository;
    }
}
