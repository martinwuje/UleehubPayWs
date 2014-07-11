package com.icbc.b2c.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.joda.time.DateTime;

import com.icbc.b2c.config.IcbcB2cConfig;
import com.icbc.b2c.errorInfo.ErrorInfoHelper;
import com.icbc.b2c.model.B2cConfig;
import com.icbc.b2c.query.response.QueryResponse;
import com.icbc.ssl.AuthSSLProtocolSocketFactory;
import com.uleehub.pay.entity.TradeInfo;
import com.uleehub.util.mapper.JaxbMapper;
import com.uleehub.webservice.soap.pay.response.GetPayStatusResult;

public class IcbcB2CUtil {
	public static B2cConfig loadXML(String path) {
		FileInputStream fi = null;
		B2cConfig C = null;
		try {
			fi = new FileInputStream(path);
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(fi);
			Element root = doc.getRootElement();
			List b2c = root.getChildren();
			Element E = null;
			C = new B2cConfig();
			E = (Element) b2c.get(0);

			C.setInterfaceName(E.getChild("interfaceName").getText().trim());

			C.setMerAcct(E.getChild("merAcct").getText().trim());

			C.setVerifyJoinFlag(E.getChild("verifyJoinFlag").getText().trim());

			C.setLanguage(E.getChild("Language").getText().trim());

			C.setCurType(E.getChild("curType").getText().trim());

			C.setMerID(E.getChild("merID").getText().trim());

			C.setCreditType(E.getChild("creditType").getText().trim());

			C.setNotifyType(E.getChild("notifyType").getText().trim());

			C.setResultType(E.getChild("resultType").getText().trim());

			C.setMerReference(E.getChild("merReference").getText().trim());

			C.setMerHint(E.getChild("merHint").getText().trim());

			C.setRemark1(E.getChild("remark1").getText().trim());

			C.setRemark2(E.getChild("remark2").getText().trim());

			C.setMerURL(E.getChild("merURL").getText().trim());

			C.setMerVAR(E.getChild("merVAR").getText().trim());

			C.setUserCrtPath(E.getChild("userCrtPath").getText().trim());

			C.setUserKeyPath(E.getChild("userKeyPath").getText().trim());

			C.setPassword(E.getChild("password").getText().trim());

			C.setJksPath(E.getChild("jksPath").getText().trim());

			C.setJksPassword(E.getChild("jksPassword").getText().trim());

			C.setApiQueryHost(E.getChild("apiQueryHost").getText().trim());

			String strPort = E.getChild("apiQueryHostPort").getText().trim();
			int port = Integer.parseInt(strPort);
			C.setApiQueryHostPort(port);

			C.setPublicCrtPath(E.getChild("publicCrtPath").getText().trim());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return C;
	}
	public static B2cConfig loadDefault() {
		FileInputStream fi = null;
		B2cConfig C = null;
		try {
			C = new B2cConfig();
			C.setInterfaceName(IcbcB2cConfig.interfaceName);
			C.setInterfaceVersion(IcbcB2cConfig.interfaceVersion);
			C.setLanguage(IcbcB2cConfig.language);
			C.setVerifyJoinFlag(IcbcB2cConfig.verifyJoinFlag);
			C.setCurType(IcbcB2cConfig.curType);
			C.setMerID(IcbcB2cConfig.merID);
			C.setCreditType(IcbcB2cConfig.creditType);
			C.setNotifyType(IcbcB2cConfig.notifyType);
			C.setResultType(IcbcB2cConfig.resultType);
			C.setMerReference(IcbcB2cConfig.merReference);
			C.setMerAcct(IcbcB2cConfig.merAcct);
			C.setMerHint(IcbcB2cConfig.merHint);
			C.setMerURL(IcbcB2cConfig.merURL);
			C.setMerVAR(IcbcB2cConfig.merVAR);
			C.setUserCrtPath(IcbcB2cConfig.userCrtPath);
			C.setUserKeyPath(IcbcB2cConfig.userKeyPath);
			C.setPassword(IcbcB2cConfig.password);
			C.setPassword("123456");
			C.setPublicCrtPath(IcbcB2cConfig.userCrtPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return C;
	}
	
	public static HttpClient buildQueryHttpClient() throws Exception{
		try {
			File sJks = new File(IcbcB2cConfig.query_serverJksPath);
			File uJks = new File(IcbcB2cConfig.query_userJksPath);
			URL sJksUrl = sJks.toURL();
			URL uJksUrl = uJks.toURL();
			AuthSSLProtocolSocketFactory asspsf =new AuthSSLProtocolSocketFactory(uJksUrl, IcbcB2cConfig.query_userJksPassword,
					sJksUrl, IcbcB2cConfig.query_serverJksPassword);
			Protocol authhttps = new Protocol("https",  asspsf, IcbcB2cConfig.query_sslPort);
			HttpClient client = new HttpClient();
			client.getHostConfiguration().setHost(IcbcB2cConfig.query_host, IcbcB2cConfig.query_hostPort, authhttps);
			return client;
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * 
	 * @param orderNo
	 * @param tranDate
	 * @return
	 * @throws Exception
	 */
	public static PostMethod buildQueryPostMethod(String orderNo, String tranDate) throws Exception{
		try {
			PostMethod httpget = new PostMethod(IcbcB2cConfig.query_postMethod);
			httpget.setParameter("APIName",IcbcB2cConfig.query_APIName);
			httpget.setParameter("APIVersion",IcbcB2cConfig.query_APIVersion);
			//String sengmsg = URLEncoder.encode("<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\" ?><ICBCAPI><in><orderNum>A0011</orderNum><tranDate>20061103</tranDate><ShopCode>0200EC20000071</ShopCode><ShopAccount>0200029109000025233</ShopAccount></in></ICBCAPI>");
			String[] replaceStr = new String[]{"${orderNum}","${tranDate}","${ShopCode}","${ShopAccount}"};
			String[] replaceValue = new String[]{orderNo, tranDate, IcbcB2cConfig.merID, IcbcB2cConfig.merAcct};
			String merReqData = StringUtils.replaceEach(IcbcB2cConfig.query_MerReqData, replaceStr, replaceValue);
			httpget.setParameter("MerReqData",merReqData);
			return httpget;
		} catch (Exception e) {
			throw e;
		}
	}
	public static GetPayStatusResult processQueryResponseBody(String xml, GetPayStatusResult result){
		QueryResponse response = JaxbMapper.fromXml(xml, QueryResponse.class);
		result.setOutTradeNo(response.getIn().getOrderNum());
		Integer tranStat = Integer.parseInt(response.getOut().getTranStat());
		switch (tranStat) {
		//0-支付成功，未清算
		case 0:
		//0-支付成功，已清算
		case 1:
			result.setStatus(String.valueOf(1));
			result.setBankInfo("支付成功");
			break;
		case 2:
			result.setStatus(String.valueOf(2));
			result.setBankInfo("支付失败: ["+ErrorInfoHelper.getInfo(response.getOut().getBankRem())+"]");
			break;
		case 3:
			result.setStatus(String.valueOf(3));
			result.setBankInfo("支付可疑: ["+ErrorInfoHelper.getInfo(response.getOut().getBankRem())+"]");
			break;
		default:
			result.setCode(String.valueOf(2));
			result.setMessage(ErrorInfoHelper.getInfo(response.getOut().getBankRem()));
			result.setBankInfo("支付失败: ["+ErrorInfoHelper.getInfo(response.getOut().getBankRem())+"]");
			break;
		}
		return result;
	}
	public static boolean checkNum(String s) {
		long amtInt = -1L;
		try {
			amtInt = Long.valueOf(s).longValue();
		} catch (Exception ee) {
			return false;
		}

		return amtInt > 0L;
	}
	public static void main(String args[]){
		String result = StringUtils.replaceEach(IcbcB2cConfig.query_MerReqData, new String[]{"${orderNum}","${tranDate}","${ShopCode}","${ShopAccount}"}, new String[]{"1","2","3",""});
		
		File sJks = new File(IcbcB2cConfig.query_userJksPath);
		try {
			URL sJksUrl = sJks.toURL();
			System.out.println(sJksUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}