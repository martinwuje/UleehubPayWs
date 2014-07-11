package com.uleehub.webservice.soap.pay.response.dto;

import javax.jws.WebParam;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.uleehub.webservice.soap.pay.WsConstants;

/**
 * Web Service传输User信息的DTO.
 * 
 * 只传输外部接口需要的属性.使用JAXB 2.0的annotation标注JAVA-XML映射,尽量使用默认约定.
 * 
 * @author mtwu
 */
@XmlRootElement
@XmlType(name = "TradeInfo", namespace = WsConstants.NS)
public class TradeInfoDTO {
	

	private Long id;
	private String out_trade_no;
	private String subject;
	private String subject_num;
	private String num;
	private String total_fee;
	private String body;
	private String show_url;
	private String exter_invoke_ip;
	private String purchaser_id;
	private String purchaser_name;
	public Long getId() {
		return id;
	}

	public void setId(Long value) {
		id = value;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getSubject_num() {
		return subject_num;
	}

	public void setSubject_num(String subject_num) {
		this.subject_num = subject_num;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getShow_url() {
		return show_url;
	}

	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}

	public String getExter_invoke_ip() {
		return exter_invoke_ip;
	}

	public void setExter_invoke_ip(String exter_invoke_ip) {
		this.exter_invoke_ip = exter_invoke_ip;
	}
	
	
	public String getPurchaser_id() {
		return purchaser_id;
	}

	public void setPurchaser_id(String purchaser_id) {
		this.purchaser_id = purchaser_id;
	}

	public String getPurchaser_name() {
		return purchaser_name;
	}

	public void setPurchaser_name(String purchaser_name) {
		this.purchaser_name = purchaser_name;
	}
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	/**
	 * 重新实现toString()函数方便在日志中打印DTO信息.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
