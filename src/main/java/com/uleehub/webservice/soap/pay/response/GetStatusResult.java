package com.uleehub.webservice.soap.pay.response;

import javax.xml.bind.annotation.XmlType;

import com.uleehub.webservice.soap.pay.WsConstants;
import com.uleehub.webservice.soap.pay.response.base.WSResult;
/**
 * 
 * @author mtwu
 *
 */
@XmlType(name = "GetStatusResult", namespace = WsConstants.NS)
public class GetStatusResult extends WSResult {
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

}
