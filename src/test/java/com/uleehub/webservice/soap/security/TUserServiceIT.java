package com.uleehub.webservice.soap.security;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.WebServiceException;

import junit.framework.Assert;

import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.binding.soap.saaj.SAAJOutInterceptor;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.junit.BeforeClass;
import org.junit.Test;
  
public class TUserServiceIT {  
  
    private static final String address = "http://localhost:9000/ws/security/userService";  
      
    @BeforeClass  
    public static void setUpBeforeClass() throws Exception {  
        JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();  
        factoryBean.getInInterceptors().add(new LoggingInInterceptor());  
        factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());  
  
        Map<String, Object> props = new HashMap<String, Object>();  
        props.put("action", "UsernameToken");  
        props.put("passwordType", "PasswordText");  
        props.put("passwordCallbackClass", WsServerAuthHandler.class.getName());  
        WSS4JInInterceptor wss4JInInterceptor = new WSS4JInInterceptor(props);  
        factoryBean.getInInterceptors().add(new SAAJInInterceptor());  
        factoryBean.getInInterceptors().add(wss4JInInterceptor);  
          
        factoryBean.setServiceClass(UserServiceImpl.class);  
        factoryBean.setAddress(address);  
        factoryBean.create();  
    }  
  
    @Test  
    public void testList() {  
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();  
        factoryBean.setAddress(address);  
        factoryBean.setServiceClass(UserService.class);  
        Object obj = factoryBean.create();  
          
        Client client = ClientProxy.getClient(obj);
        Endpoint endpoint = client.getEndpoint();
          
        Map<String,Object> props = new HashMap<String,Object>();  
        props.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);  
        props.put(WSHandlerConstants.USER, "admin");  
        props.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT); 
     // 指定在调用远程ws之前触发的回调函数WsClientAuthHandler，其实类似于一个拦截器
        props.put(WSHandlerConstants.PW_CALLBACK_CLASS, WsClientAuthHandler.class.getName());  
        SAAJOutInterceptor saaJOutInterceptor = new SAAJOutInterceptor();
        WSS4JOutInterceptor wss4JOutInterceptor = new WSS4JOutInterceptor(props);
        ArrayList list = new ArrayList();  
        // 添加cxf安全验证拦截器，必须  
        list.add(saaJOutInterceptor);  
        list.add(wss4JOutInterceptor);
        endpoint.getOutInterceptors().addAll(list);
          
        HTTPConduit conduit = (HTTPConduit) client.getConduit();  
        HTTPClientPolicy policy = new HTTPClientPolicy();  
        policy.setConnectionTimeout(600 * 1000);  
        policy.setReceiveTimeout(600 * 1000);  
        conduit.setClient(policy);
        
        
        UserService service = (UserService) obj;  
        try {  
            List<User> users = service.list();  
            Assert.assertNotNull(users);  
            Assert.assertEquals(10, users.size());  
        } catch(Exception e) {  
            if (e instanceof WebServiceException   
                    && e.getCause() instanceof SocketTimeoutException) {  
                System.err.println("This is timeout exception.");  
            } else {  
                e.printStackTrace();  
            }  
        }  
    }  
  
} 
