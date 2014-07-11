package com.uleehub.webservice.soap.pay.response;

import javax.xml.bind.annotation.XmlType;

import com.uleehub.webservice.soap.pay.WsConstants;
import com.uleehub.webservice.soap.pay.response.base.WSResult;

/**
 * <p>User: mtwu
 * <p>Date: 2014-7-3
 * <p>Version: 1.0
 */
@XmlType(name = "GetPayStatusResult", namespace = WsConstants.NS)
public class GetPayStatusResult extends WSResult {
	
	private String outTradeNo;
	
	private String status;
	
	private String bankInfo;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getBankInfo() {
		return bankInfo;
	}

	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
	}
	
	
}
