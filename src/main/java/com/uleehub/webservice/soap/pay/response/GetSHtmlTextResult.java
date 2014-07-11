package com.uleehub.webservice.soap.pay.response;

import javax.xml.bind.annotation.XmlType;

import com.uleehub.webservice.soap.pay.WsConstants;
import com.uleehub.webservice.soap.pay.response.base.WSResult;
/**
 * 
 * @author mtwu
 *
 */
@XmlType(name = "GetSHtmlTextResult", namespace = WsConstants.NS)
public class GetSHtmlTextResult extends WSResult {
	private String sHtmlText;

	public String getsHtmlText() {
		return sHtmlText;
	}

	public void setsHtmlText(String sHtmlText) {
		this.sHtmlText = sHtmlText;
	}

}
