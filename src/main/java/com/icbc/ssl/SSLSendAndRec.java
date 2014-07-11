/*
 * Created on 2006-2-19
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icbc.ssl;

import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;

public class SSLSendAndRec {

	/**
	 * 
	 */
	public SSLSendAndRec() {
		super();
	}

	public static void main(String[] args) throws Exception{
		Protocol authhttps = new Protocol("https",  

			    new AuthSSLProtocolSocketFactory(

			        new URL("file:///C:/Sun/certtest/my.jks"), "97654321",

			        new URL("file:///C:/Sun/certtest/my.jks"), "97654321"), 8443);

			HttpClient client = new HttpClient();

			client.getHostConfiguration().setHost("83.252.30.98", 8890, authhttps);
			//client.getHostConfiguration().setHost("83.252.30.98",8890);
			/*只能使用相对路径*/

			PostMethod httpget = new PostMethod("/servlet/ICBCINBSEBusinessServlet");
			httpget.setParameter("APIName","EAPI");
			httpget.setParameter("APIVersion","001.001.005.001");
			//String sengmsg = URLEncoder.encode("<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\" ?><ICBCAPI><in><orderNum>A0011</orderNum><tranDate>20061103</tranDate><ShopCode>0200EC20000071</ShopCode><ShopAccount>0200029109000025233</ShopAccount></in></ICBCAPI>");
			httpget.setParameter("MerReqData","<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\" ?><ICBCAPI><in><orderNum>A0011</orderNum><tranDate>20061103</tranDate><ShopCode>0200EC20000071</ShopCode><ShopAccount>0200029109000025233</ShopAccount></in></ICBCAPI>");
			
			client.executeMethod(httpget);
			
			System.out.println("server responding body :" + httpget.getResponseBodyAsString());
			
			System.out.println("server responding code :" + httpget.getStatusLine().toString());
	}
}

