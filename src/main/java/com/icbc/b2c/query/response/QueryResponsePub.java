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
@XmlType(name = "pub", propOrder = {  
	    "aPIName",  
	    "aPIVersion"
	})  
public class QueryResponsePub {
	private String aPIName;
	private String aPIVersion;
	
	public String getaPIName() {
		return aPIName;
	}
	@XmlElement(name = "APIName")
	public void setaPIName(String aPIName) {
		this.aPIName = aPIName;
	}
	
	public String getaPIVersion() {
		return aPIVersion;
	}
	
	@XmlElement(name = "APIVersion")
	public void setaPIVersion(String aPIVersion) {
		this.aPIVersion = aPIVersion;
	}
}
