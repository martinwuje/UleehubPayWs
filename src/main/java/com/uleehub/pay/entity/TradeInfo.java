/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.uleehub.pay.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.uleehub.common.entity.BaseEntity;
import com.uleehub.common.repository.support.annotation.EnableQueryCache;
import com.uleehub.webservice.soap.pay.response.dto.TradeInfoDTO;

/**
 * 支付信息
 * <p>
 * User: mtwu
 * <p>
 * Date: 13-2-19 上午9:00
 * <p>
 * Version: 1.0
 */
@Entity
@Table(name = "tb_pay_tradeinfo")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TradeInfo extends BaseEntity<Long> {
	/**
	 * 
	 */

	// 提供商
	@NotNull(message = "not.null")
	@Column(name = "provider")
	private String provider;

	// 调用服务接口
	@NotNull(message = "not.null")
	@Column(name = "service")
	private String service;

	// 支付类型(1购买商品)
	@NotNull(message = "not.null")
	@Column(name = "payment_type")
	private Integer paymentType;

	// 账户信息
	@NotNull(message = "not.null")
	@Column(name = "seller_email")
	private String sellerEmail;

	// 订单编号
	@NotNull(message = "not.null")
	@Column(name = "out_trade_no")
	private String outTradeNo;
	
	// 交易号
	@Column(name = "trade_no")
	private String tradeNo;
		
	// 商品名称
	@Column(name = "subject")
	private String subject;
	
	// 商品数量
	@Column(name = "subject_num")
	private Integer subjectNum;
		
	// 订单编号
	@NotNull(message = "not.null")
	@Column(name = "total_fee")
	private Float totalFee;

	// 商品描述
	@Column(name = "body")
	private String body;

	// 展示url
	@Column(name = "show_url")
	private String showUrl;

	// 客户端IP
	@Column(name = "exter_invoke_ip")
	private String exterInvokeIp;

	// 数据报文
	@Column(name = "data_packet")
	private String dataPacket;

	// 交易状态(0交易中1成功2失败)
	@Column(name = "trade_status")
	private Integer tradeStatus;
	
	//交易日期
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "trade_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tradeDate;
	
	//确认日期
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "verify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date verifyDate;
	
	@Column(name = "error_code")
	private String errorCode;
	
	@Column(name = "remark")
	private String remark;
	//买家ID
	@NotNull(message = "not.null")
	@Column(name = "purchaser_id")
	private String purchaserId;
	//买家信息
	@NotNull(message = "not.null")
	@Column(name = "purchaser_name")
	private String purchaserName;
	
	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	public Integer getSubjectNum() {
		return subjectNum;
	}

	public void setSubjectNum(Integer subjectNum) {
		this.subjectNum = subjectNum;
	}

	public Float getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Float totalFee) {
		this.totalFee = totalFee;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

	public String getExterInvokeIp() {
		return exterInvokeIp;
	}

	public void setExterInvokeIp(String exterInvokeIp) {
		this.exterInvokeIp = exterInvokeIp;
	}

	public String getDataPacket() {
		return dataPacket;
	}

	public void setDataPacket(String dataPacket) {
		this.dataPacket = dataPacket;
	}

	

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
	public Integer getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getPurchaserId() {
		return purchaserId;
	}

	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
	}

	public String getPurchaserName() {
		return purchaserName;
	}

	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}

	public TradeInfo(){}
	public TradeInfo(String out_trade_no, String subject, Integer subject_num, String total_fee, String body, String show_url, String exter_invoke_ip, Integer payment_type, String seller_email, String provider , String purchaser_id, String purchaser_name){
		setOutTradeNo(out_trade_no);
		setSubject(subject);
		setSubjectNum(subject_num);
		setTotalFee(Float.parseFloat(total_fee));
		setBody(body);
		setShowUrl(show_url);
		setExterInvokeIp(exter_invoke_ip);
		setPaymentType(payment_type);
		setSellerEmail(seller_email);
		setProvider(provider);
		setTradeStatus(Integer.valueOf(0));
		setTradeDate(DateTime.now().toDate());
		setPurchaserId(purchaser_id);
		setPurchaserName(purchaser_name);
	}
	public TradeInfo(TradeInfoDTO tradeInfoDTO, Integer payment_type, String seller_email, String provider){
		setOutTradeNo(tradeInfoDTO.getOut_trade_no());
		setSubject(tradeInfoDTO.getSubject());
		setSubjectNum(Integer.parseInt(tradeInfoDTO.getSubject_num()));
		setTotalFee(Float.parseFloat(tradeInfoDTO.getTotal_fee()));
		setBody(tradeInfoDTO.getBody());
		setShowUrl(tradeInfoDTO.getShow_url());
		setExterInvokeIp(tradeInfoDTO.getExter_invoke_ip());
		setPaymentType(payment_type);
		setSellerEmail(seller_email);
		setProvider(provider);
		setTradeStatus(Integer.valueOf(0));
		setTradeDate(DateTime.now().toDate());
		setPurchaserId(tradeInfoDTO.getPurchaser_id());
		setPurchaserName(tradeInfoDTO.getPurchaser_name());
	}
	
}
