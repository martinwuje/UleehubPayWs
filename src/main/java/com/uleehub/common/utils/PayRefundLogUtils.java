package com.uleehub.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayRefundLogUtils {

    public static final Logger PAY_ERROR_LOG = LoggerFactory.getLogger("pay-error");
    public static final Logger PAY_ACCESS_LOG = LoggerFactory.getLogger("pay-access");
    public static final Logger REFUND_ERROR_LOG = LoggerFactory.getLogger("refund-error");
    public static final Logger REFUND_ACCESS_LOG = LoggerFactory.getLogger("refund-access");

    /**
     * 
     * TODO:描述
     * <dl><dt><b>业务描述：交易成功日志</b></dt><dd>
     * TODO:主要业务逻辑描述
     * </dd></dl>
     * @param method 请求方法
     * @param params
     * @param obj
     * @param url
     * <dt><b>修改历史：</b></dt>
     * <dd>1.00	2014-7-2 rx  创建.</dd>
     * @see TODO:
     */
    public static void logPayAccess(Map<String, String> params,Object obj,String method,String url) {
    	getPayAccessLog().info("[method="+method+"]:"+url+"?"+mapToString(params));
        try {
        	getPayAccessLog().info("[method="+method+"]:"+objectToString(obj));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * 
     * TODO:描述
     * <dl><dt><b>业务描述：交易失败日志</b></dt><dd>
     * @param method 请求方法
     * @param tradeInfo 交易信息
     * @param str 失败原因
     * <dt><b>修改历史：</b></dt>
     * <dd>1.00	2014-7-2 rx  创建.</dd>
     * @throws Exception 
     * @see TODO:
     */
    public static void logPayError(Map<String, String> params,Object obj,String method,String error,String url){
    	getPayErrorLog().info("[method="+method+"]:"+error+url+"?"+mapToString(params));
    	try {
    		getPayErrorLog().info("[method="+method+"]:"+error+objectToString(obj));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * 
     * TODO:描述
     * <dl><dt><b>业务描述：退款成功日志</b></dt><dd>
     * TODO:主要业务逻辑描述
     * </dd></dl>
     * @param method 请求方法
     * @param params
     * @param obj
     * @param url
     * <dt><b>修改历史：</b></dt>
     * <dd>1.00	2014-7-2 rx  创建.</dd>
     * @see TODO:
     */
    public static void logRefundAccess(Map<String, String> params,Object obj,String method,String url) {
    	getRefundAccessLog().info("[method="+method+"]:"+url+"?"+mapToString(params));
        try {
        	getRefundAccessLog().info("[method="+method+"]:"+objectToString(obj));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * 
     * TODO:描述
     * <dl><dt><b>业务描述：退款失败日志</b></dt><dd>
     * @param params url参数
     * @param method 请求方法
     * @param obj 交易信息
     * @param error 失败原因
     * <dt><b>修改历史：</b></dt>
     * <dd>1.00	2014-7-2 rx  创建.</dd>
     * @throws Exception 
     * @see TODO:
     */
    public static void logRefundError(Map<String, String> params,Object obj,String method,String error,String url){
    	getRefundErrorLog().info("[method="+method+"]:"+error+url+"?"+mapToString(params));
    	try {
    		getRefundErrorLog().info("[method="+method+"]:"+error+objectToString(obj));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * 
     * TODO:描述
     * <dl><dt><b>业务描述：拼接url</b></dt><dd>
     * TODO:主要业务逻辑描述
     * @param method 请求方法
     * @param params
     * @return
     * <dt><b>修改历史：</b></dt>
     * <dd>1.00	2014-7-2 rx  创建.</dd>
     * @see TODO:
     */
    public static String mapToString(Map<String, String> params) {
    	if(params == null || params.size() == 0){
    		return "";
    	}
    	StringBuilder s = new StringBuilder();
    	Iterator<String> it = params.keySet().iterator();
    	  while (it.hasNext()) {
    	    String value = it.next();
    	    s.append(value);
    	    s.append("=");
    	    s.append(params.get(value));
    	    s.append("&");
    	  }
    	  s = new StringBuilder(s.toString().substring(0, s.toString().length()-1));
        return s.toString();
    }
    /**
     * 
     * TODO:描述
     * <dl><dt><b>业务描述：获取对象属性值</b></dt><dd>
     * TODO:主要业务逻辑描述
     * @param params
     * @return
     * <dt><b>修改历史：</b></dt>
     * <dd>1.00	2014-7-2 rx  创建.</dd>
     * @see TODO:
     */
    public static String objectToString(Object model) throws Exception{
    	if(model == null){
    		return "";
    	}
	    StringBuilder s = new StringBuilder();
		s.append("[");
		Field[] field = model.getClass().getDeclaredFields(); 
		for(int j=0 ; j<field.length ; j++){     //遍历所有属性
             String name = field[j].getName();    //获取属性的名字
             s.append(name);
             s.append("=");
             name = name.substring(0, 1).toUpperCase()+name.substring(1, name.length());
             Method m = model.getClass().getMethod("get"+name);
             s.append(m.invoke(model));
             s.append(",");
		 }
		s = new StringBuilder(s.toString().substring(0, s.toString().length()-1));
		s.append("]");
        return s.toString();
    }

    protected static String getUsername() {
        return (String) SecurityUtils.getSubject().getPrincipal();
    }

	public static Logger getPayErrorLog() {
		return PAY_ERROR_LOG;
	}

	public static Logger getPayAccessLog() {
		return PAY_ACCESS_LOG;
	}

	public static Logger getRefundErrorLog() {
		return REFUND_ERROR_LOG;
	}

	public static Logger getRefundAccessLog() {
		return REFUND_ACCESS_LOG;
	}

    

}
