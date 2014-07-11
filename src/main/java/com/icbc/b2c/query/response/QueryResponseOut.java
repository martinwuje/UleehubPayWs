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
@XmlType(name = "out", propOrder = {  
	    "tranSerialNum",  
	    "tranStat",
	    "bankRem",
	    "amount",
	    "currType",
	    "tranTime",
	    "shopAccount",
	    "payeeName",
	    "joinFlag",
	    "merJoinFlag",
	    "custJoinNum",
	    "certID"
	    
	})  
public class QueryResponseOut {
	
	private String tranSerialNum;  
	private String tranStat;
	private String bankRem;
	private String amount;
	private String currType;
	private String tranTime;
	private String shopAccount;
	private String payeeName;
	private String joinFlag;
	private String merJoinFlag;
	private String custJoinNum;
	private String certID;
    
	public String getTranSerialNum() {
		return tranSerialNum;
	}
	
	@XmlElement(name = "tranSerialNum",required=true)
	public void setTranSerialNum(String tranSerialNum) {
		this.tranSerialNum = tranSerialNum;
	}
	public String getTranStat() {
		return tranStat;
	}
	
	@XmlElement(name = "tranStat")
	public void setTranStat(String tranStat) {
		this.tranStat = tranStat;
	}
	public String getBankRem() {
		return bankRem;
	}
	
	@XmlElement(name = "bankRem")
	public void setBankRem(String bankRem) {
		this.bankRem = bankRem;
	}
	public String getAmount() {
		return amount;
	}
	
	@XmlElement(name = "amount")
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrType() {
		return currType;
	}
	
	@XmlElement(name = "currType")
	public void setCurrType(String currType) {
		this.currType = currType;
	}
	public String getTranTime() {
		return tranTime;
	}
	
	@XmlElement(name = "tranTime")
	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}
	public String getShopAccount() {
		return shopAccount;
	}
	
	@XmlElement(name = "ShopAccount")
	public void setShopAccount(String shopAccount) {
		this.shopAccount = shopAccount;
	}
	public String getPayeeName() {
		return payeeName;
	}
	
	@XmlElement(name = "PayeeName")
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getJoinFlag() {
		return joinFlag;
	}
	
	@XmlElement(name = "JoinFlag")
	public void setJoinFlag(String joinFlag) {
		this.joinFlag = joinFlag;
	}
	public String getMerJoinFlag() {
		return merJoinFlag;
	}
	
	@XmlElement(name = "MerJoinFlag")
	public void setMerJoinFlag(String merJoinFlag) {
		this.merJoinFlag = merJoinFlag;
	}
	public String getCustJoinNum() {
		return custJoinNum;
	}
	
	@XmlElement(name = "CustJoinNum")
	public void setCustJoinNum(String custJoinNum) {
		this.custJoinNum = custJoinNum;
	}
	public String getCertID() {
		return certID;
	}
	
	@XmlElement(name = "CertID")
	public void setCertID(String certID) {
		this.certID = certID;
	}
    
    
}
