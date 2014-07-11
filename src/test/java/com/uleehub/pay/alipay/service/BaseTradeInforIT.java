/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.uleehub.pay.alipay.service;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.config.AlipayConfig;
import com.uleehub.pay.entity.TradeInfo;
import com.uleehub.pay.service.TradeInfoService;
import com.uleehub.test.BaseIT;

/**
 * <p>TradeInfo: mtwu
 * <p>Date: 13-4-5 下午3:24
 * <p>Version: 1.0
 */
public abstract class BaseTradeInforIT extends BaseIT {


    @Autowired
    protected TradeInfoService tradeInfoService;


    protected int maxtRetryCount = 10;

    @Before
    public void setUp() {

        TradeInfo tradeInfo = tradeInfoService.findByOutTradeNo("123456");
        if (tradeInfo != null) {
        	System.out.println(tradeInfo.getExterInvokeIp());
            tradeInfoService.delete(tradeInfo);//因为用户是逻辑删除 此处的目的主要是清 缓存
            delete(tradeInfo);              //所以还需要物理删除
        }
        clear();
    }

    @After
    public void tearDown() {
    }


    protected TradeInfo createTradeInfo(String out_trade_no, String subject, Integer subject_num, String total_fee, String body, String show_url, String exter_invoke_ip, Integer payment_type, String seller_email, String provider , String purchaser_id, String purchaser_name){
        TradeInfo tradeInfo = new TradeInfo(out_trade_no, subject, subject_num, total_fee, body, show_url, exter_invoke_ip, payment_type, seller_email, provider, purchaser_id, purchaser_name);
        tradeInfoService.saveAndFlush(tradeInfo);
        return tradeInfo;
    }

    protected TradeInfo createDefaultTradeInfo() {
        return createTradeInfo("12345678", "iPad Air", 1,  "3580.88","苹果iPad","http://apple/air/4","192.168.1.8",1,"sunp@sctis.com",AlipayConfig.provider, "5101000818", "川旅旅行社");
    }

}
