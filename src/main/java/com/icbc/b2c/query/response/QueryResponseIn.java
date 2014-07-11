package com.icbc.b2c.query.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>User: mtwu
 * <p>Date: 2014-7-3
 * <p>Version: 1.0
 */
@XmlType(name = "in", propOrder = {  
	    "orderNum",  
	    "tranDate",
	    "shopCode",
	    "shopAccount"
	})  
public class QueryResponseIn {
	private String orderNum;
	private String tranDate;
	private String shopCode;
	private String shopAccount;
	
	public String getOrderNum() {
		return orderNum;
	}
	@XmlElement(name = "orderNum")
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getTranDate() {
		return tranDate;
	}
	
	@XmlElement(name = "tranDate")
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}
	public String getShopCode() {
		return shopCode;
	}
	
	@XmlElement(name = "ShopCode")
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	public String getShopAccount() {
		return shopAccount;
	}
	
	@XmlElement(name = "ShopAccount")
	public void setShopAccount(String shopAccount) {
		this.shopAccount = shopAccount;
	}
	
	
}
