package com.icbc.b2c.query.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>User: mtwu
 * <p>Date: 2014-7-3
 * <p>Version: 1.0
 */
/**
 * 使用JAXB2.0标注的待转换Java Bean.
 */
//根节点
@XmlRootElement(name="ICBCAPI")
//指定子节点的顺序
@XmlType(propOrder = { "pub", "in", "out"})
public class QueryResponse {
	
	private QueryResponsePub pub;
	private QueryResponseIn in;
	private QueryResponseOut out;
	
	
	public QueryResponsePub getPub() {
		return pub;
	}
	public void setPub(QueryResponsePub pub) {
		this.pub = pub;
	}
	public QueryResponseIn getIn() {
		return in;
	}
	public void setIn(QueryResponseIn in) {
		this.in = in;
	}
	public QueryResponseOut getOut() {
		return out;
	}
	public void setOut(QueryResponseOut out) {
		this.out = out;
	}
	
	
	
	
}
