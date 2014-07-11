/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.uleehub.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uleehub.common.service.BaseService;
import com.uleehub.pay.entity.RefundInfo;
import com.uleehub.pay.repository.RefundInfoRepository;

/**
 * <p>User: mtwu
 * <p>Date: 14-6-26 下午1:45
 * <p>Version: 1.0
 */
@Service
public class RefundInfoService extends BaseService<RefundInfo, Long> {
	
	public RefundInfo findBybatchNo(String batchNo){
		return getRefundInfoRepository().findBybatchNo(batchNo);
	}
	private RefundInfoRepository getRefundInfoRepository() {
        return (RefundInfoRepository) baseRepository;
    }
}
