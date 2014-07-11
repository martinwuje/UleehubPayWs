package com.icbc.b2c.model;

public class OrderInfo {
	private String orderid;
	private String amount;
	private String installmentTimes;
	private String goodsID;
	private String goodsName;
	private String goodsNum;
	private String carriageAmt;

	public String getOrderid() {
		return this.orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getInstallmentTimes() {
		return this.installmentTimes;
	}

	public void setInstallmentTimes(String installmentTimes) {
		this.installmentTimes = installmentTimes;
	}

	public String getGoodsID() {
		return this.goodsID;
	}

	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsNum() {
		return this.goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getCarriageAmt() {
		return this.carriageAmt;
	}

	public void setCarriageAmt(String carriageAmt) {
		this.carriageAmt = carriageAmt;
	}
}
