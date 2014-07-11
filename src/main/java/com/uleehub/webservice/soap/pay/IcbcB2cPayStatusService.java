package com.uleehub.webservice.soap.pay;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.uleehub.webservice.soap.pay.response.GetPayStatusResult;
import com.uleehub.webservice.soap.pay.response.GetSHtmlTextResult;
import com.uleehub.webservice.soap.pay.response.GetStatusResult;
import com.uleehub.webservice.soap.pay.response.dto.TradeInfoDTO;

/**
 * <p>获取银行支付状态服务
 * 
 * JAX-WS2.0的WebService接口定义类.
 * 
 * 使用JAX-WS2.0 annotation设置WSDL中的定义.
 * 使用WSResult及其子类类包裹返回结果.
 * 使用DTO传输对象隔绝系统内部领域对象的修改对外系统的影响.
 * 
 * @author mtwu
 */
//name 指明wsdl中<wsdl:portType>元素的名称
@WebService(name = "IcbcB2cPayStatusService", targetNamespace = WsConstants.NS)
public interface IcbcB2cPayStatusService {
	
	GetPayStatusResult query(@WebParam(name="out_trade_no") String out_trade_no) ;
	
}
