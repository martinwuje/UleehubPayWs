/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.uleehub.pay.repository;

import com.uleehub.common.repository.BaseRepository;
import com.uleehub.pay.entity.TradeInfo;


/**
 * <p>User: mtwu
 * <p>Date: 13-2-4 下午3:00
 * <p>Version: 1.0
 */
public interface TradeInfoRepository extends BaseRepository<TradeInfo, Long> {

	TradeInfo findByOutTradeNo(String outTradeNo);
	TradeInfo findByOutTradeNoAndProvider(String outTradeNo, String provider);
	
}


