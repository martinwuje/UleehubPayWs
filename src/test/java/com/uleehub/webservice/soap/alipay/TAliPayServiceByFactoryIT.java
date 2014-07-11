package com.uleehub.webservice.soap.alipay;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.WebServiceException;

import org.apache.cxf.binding.soap.saaj.SAAJOutInterceptor;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.junit.Assert;
import org.junit.Test;

import com.uleehub.webservice.soap.security.WsClientAuthHandler;

/**
 * <p>
 * User: mtwu
 * <p>
 * Date: 2014-7-2
 * <p>
 * Version: 1.0
 */
public class TAliPayServiceByFactoryIT {
	private static final String address = "http://localhost:8080/UleehubPayWs/cxf/uleepay/aliPayService?wsdl";

	@Test
	public void pay() {
		// 以下和服务端配置类似，不对，应该说服务端和这里的安全验证配置一致
		Map<String, Object> outProps = new HashMap<String, Object>();
		outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		outProps.put(WSHandlerConstants.USER, "admin");
		outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
		// 指定在调用远程ws之前触发的回调函数WsClientAuthHandler，其实类似于一个拦截器
		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, WsClientAuthHandler.class.getName());
		ArrayList list = new ArrayList();  
        // 添加cxf安全验证拦截器，必须  
        list.add(new SAAJOutInterceptor());  
        list.add(new WSS4JOutInterceptor(outProps));  
        
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setAddress(address);
		factoryBean.setServiceClass(AliPayService.class);

		//注入拦截器，用于加密安全验证信息  
		factoryBean.getOutInterceptors().addAll(list); 
		Object obj = factoryBean.create();
		
		Client client = ClientProxy.getClient(obj);
		Endpoint endpoint = client.getEndpoint();
		endpoint.getOutInterceptors().addAll(list);
		
		AliPayService service = (AliPayService) obj;
		java.lang.String outTradeNo = "1234566";
		java.lang.String subject = "iPad Air";
		java.lang.String subjectNum = "1";
		java.lang.String totalFee = "400";
		java.lang.String body = "iPad Air";
		java.lang.String showUrl = "http://apple.com/iPad/detail/1";
		java.lang.String exterInvokeIp = "192.168.2.3";
		java.lang.String purchaserId = "mtwu";
		java.lang.String purchaserName = "wujian";
		
		
		try {
			GetSHtmlTextResult response = service.pay(outTradeNo, subject, subjectNum, totalFee, body, showUrl, exterInvokeIp, purchaserId, purchaserName);
			Assert.assertNotNull(response);
			System.out.println("refund.code=" + response.code);
	        System.out.println("refund.message=" + response.message);
	        System.out.println("refund.result=" + response.getSHtmlText());
		} catch (Exception e) {
			if (e instanceof WebServiceException && e.getCause() instanceof SocketTimeoutException) {
				System.err.println("This is timeout exception.");
			} else {
				e.printStackTrace();
			}
		}
	}

}