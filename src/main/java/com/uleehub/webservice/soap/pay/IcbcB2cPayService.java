package com.uleehub.webservice.soap.pay;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.uleehub.webservice.soap.pay.response.GetSHtmlTextResult;
import com.uleehub.webservice.soap.pay.response.GetStatusResult;
import com.uleehub.webservice.soap.pay.response.dto.TradeInfoDTO;

/**
 * JAX-WS2.0的WebService接口定义类.
 * 
 * 使用JAX-WS2.0 annotation设置WSDL中的定义.
 * 使用WSResult及其子类类包裹返回结果.
 * 使用DTO传输对象隔绝系统内部领域对象的修改对外系统的影响.
 * 
 * @author mtwu
 */
//name 指明wsdl中<wsdl:portType>元素的名称
@WebService(name = "IcbcB2cPayService", targetNamespace = WsConstants.NS)
public interface IcbcB2cPayService {
	/**
	 * 支付接口，参数传递
	 * @param out_trade_no
	 * @param subject
	 * @param num 数量
	 * @param total_fee
	 * @param body
	 * @param show_url
	 * @param exter_invoke_ip
	 * @param purchaser_id
	 * @param purchaser_name
	 * @return String
	 */
	GetSHtmlTextResult pay(@WebParam(name="out_trade_no") String out_trade_no, @WebParam(name="subject") String subject, @WebParam(name="subject_num") String subject_num, @WebParam(name="total_fee") String total_fee,@WebParam(name="body") String body, @WebParam(name="show_url") String show_url, @WebParam(name="exter_invoke_ip") String exter_invoke_ip,@WebParam(name="purchaser_id") String purchaser_id,@WebParam(name="purchaser_name") String purchaser_name) ;
	/**
	 * 支付接口，使用DTO传输对象
	 * @param tradeInfoDTO
	 * @return String
	 */
	GetSHtmlTextResult payByDTO(@WebParam(name = "tradeInfo") TradeInfoDTO tradeInfoDTO);
	
	/**
	 * 查询支付状态
	 * @param out_trade_no
	 * @return
	 */
	GetStatusResult queryPayStatus(@WebParam(name = "out_trade_no") String out_trade_no);
}
