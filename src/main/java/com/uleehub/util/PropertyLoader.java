/**
 *
 *Copyright (c) 2011. All rights reserved.
 *@author jowen
 *@version 2011-3-4下午07:59:18
 */
package com.uleehub.util;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icbc.b2c.config.IcbcB2cConfig;

/**
 * 读取weboa.properties中的配置信息
 * 
 * @author mtwu
 * 
 */

public class PropertyLoader {
	Logger log = LoggerFactory.getLogger(PropertyLoader.class);
	
	private static final String ALIPAY="alipay/alipay";
	private static final String ICBCB2C="icbc/icbc_b2c";
	
	/**
	 * 根据KEY读取相应的VALUE,来自workflow
	 * 
	 * @param key
	 * @return
	 */
	public static String getAliConfigProperty(String key){
		String result = null;
		ResourceBundle rb = ResourceBundle.getBundle(ALIPAY);
		result = rb.getString(key);
		return result;
	}
	public static String getIcbcB2cProperty(String key){
		String result = null;
		ResourceBundle rb = ResourceBundle.getBundle(ICBCB2C);
		result = rb.getString(key);
		return result;
	}
}
