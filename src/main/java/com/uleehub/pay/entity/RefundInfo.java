package com.uleehub.pay.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.uleehub.common.entity.BaseEntity;
import com.uleehub.common.repository.support.annotation.EnableQueryCache;

/**
 * <p>User: mtwu
 * <p>Date: 2014-6-27
 * <p>Version: 1.0
 */
@Entity
@Table(name = "tb_pay_refundinfo")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RefundInfo extends BaseEntity<Long> {
	// 提供商
	@NotNull(message = "not.null")
	@Column(name = "provider")
	private String provider;

	// 调用服务接口
	@NotNull(message = "not.null")
	@Column(name = "service")
	private String service;

	// 退款类型(退款类型1退款2提现)
	@NotNull(message = "not.null")
	@Column(name = "refund_type")
	private Integer refundType;

	// 账户信息
	@NotNull(message = "not.null")
	@Column(name = "seller_email")
	private String sellerEmail;

	// 退款金额
	@NotNull(message = "not.null")
	@Column(name = "total_fee")
	private Float totalFee;

	// 退款当天日期
	@NotNull(message = "not.null")
	@Column(name = "refund_date")
	private Date refundDate;
	// 退款批次号
	@NotNull(message = "not.null")
	@Column(name = "batch_no")
	private String batchNo;
	// 退款笔数
	@NotNull(message = "not.null")
	@Column(name = "batch_num")
	private Integer batchNum;
	// 退款详细数据
	@NotNull(message = "not.null")
	@Column(name = "detail_data")
	private String detailData;
	// 客服端IP
	@NotNull(message = "not.null")
	@Column(name = "exter_invoke_ip")
	private String exterInvokeIp;
	// 数据报文
	@Column(name = "data_packet")
	private String dataPacket;
	// 退款状态(0交易中1交易成功2交易失败)
	@NotNull(message = "not.null")
	@Column(name = "refund_status")
	private Integer refundStatus;
	// 错误代码
	@Column(name = "error_code")
	private String errorCode;
	// 确认日期
	@Column(name = "verify_date")
	private Date verifyDate;
	// 买家ID
	@NotNull(message = "not.null")
	@Column(name = "purchaser_id")
	private String purchaserId;
	// 买家信息
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

	public Integer getRefundType() {
		return refundType;
	}

	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public Float getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Float totalFee) {
		this.totalFee = totalFee;
	}

	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Integer getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(Integer batchNum) {
		this.batchNum = batchNum;
	}

	public String getDetailData() {
		return detailData;
	}

	public void setDetailData(String detailData) {
		this.detailData = detailData;
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

	public Integer getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
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

	public RefundInfo() {
	}

	public RefundInfo(String provider, String service, Integer refundType, String sellerEmail, Float totalFee, Date refundDate, String batchNo, Integer batchNum, String detailData,
			String exterInvokeIp, Integer refundStatus, String purchaserId, String purchaserName) {
		this.provider = provider;
		this.service = service;
		this.refundType = refundType;
		this.sellerEmail = sellerEmail;
		this.totalFee = totalFee;
		this.refundDate = refundDate;
		this.batchNo = batchNo;
		this.batchNum = batchNum;
		this.detailData = detailData;
		this.exterInvokeIp = exterInvokeIp;
		this.refundStatus = refundStatus;
		this.purchaserId = purchaserId;
		this.purchaserName = purchaserName;
	}

}
