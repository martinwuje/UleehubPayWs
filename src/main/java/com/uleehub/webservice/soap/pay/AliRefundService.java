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
@WebService(name = "AliRefundService", targetNamespace = WsConstants.NS)
public interface AliRefundService {
	/**
	 * 退款接口
	 * @param batch_no 退款批次号唯一，格式：当天日期[8位]+序列号[3至24位]，如：201008010000001
	 * @param batch_num 退款笔数，退款详情“#”字符出现的数量加1
	 * @param detail_data 退款详情 例如订单编号1^退款金额1^退款理由1 
	 * @param exter_invoke_ip
	 * @return
	 */
	GetSHtmlTextResult refund(@WebParam(name="batch_no") String batch_no, @WebParam(name="batch_num") String batch_num,@WebParam(name="detail_data") String detail_data, @WebParam(name="exter_invoke_ip") String exter_invoke_ip, @WebParam(name="purchaser_id") String purchaser_id,@WebParam(name="purchaser_name") String purchaser_name) ;
	
	/**
	 * 查询退款状态
	 * @param out_trade_no
	 * @return
	 */
	GetStatusResult queryRefundStatus(@WebParam(name = "batch_no") String batch_no);
}
