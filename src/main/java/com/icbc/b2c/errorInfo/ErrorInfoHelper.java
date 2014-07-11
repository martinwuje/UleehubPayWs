/**
 *
 *Copyright (c) 2011. All rights reserved.
 *@author jowen
 *@version 2011-3-4下午07:59:18
 */
package com.icbc.b2c.errorInfo;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * * 读取weboa.properties中的配置信息
 * <p>User: mtwu
 * <p>Date: 2014-7-3
 * <p>Version: 1.0
 */

public class ErrorInfoHelper {
	Logger log = LoggerFactory.getLogger(ErrorInfoHelper.class);
	
	private static final String ICBCB2C_ERRINFO="icbc/icbc_b2c_errinfo";
	
	/**
	 * 根据KEY读取相应的VALUE,来自workflow
	 * 
	 * @param key
	 * @return
	 */
	public static String getInfo(String key){
		String result = key;
		try {
			ResourceBundle rb = ResourceBundle.getBundle(ICBCB2C_ERRINFO);
			result = rb.getString(key);
			
		} catch (MissingResourceException e) {
		}
		return result;
	}
	public static void main(String args[]){
		
		System.out.println(ErrorInfoHelper.getInfo("123"));
		
	}
}
