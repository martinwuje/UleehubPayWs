package com.icbc.b2c.notify;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import cn.com.infosec.icbc.ReturnValue;

import com.icbc.b2c.model.B2cConfig;
import com.icbc.b2c.model.NotifyData;
import com.icbc.b2c.model.NotifyOrderInfo;
import com.icbc.b2c.pay.IcbcB2CPayImpl;
import com.icbc.b2c.util.IcbcB2CUtil;

public class IcbcB2CNotifyImpl implements IcbcB2CNotify {
	static Logger logger = LoggerFactory.getLogger(IcbcB2CPayImpl.class.getName());
	public NotifyData createNotifyData(String notifyData, String signMsg, String xmlPath) {
		B2cConfig C = IcbcB2CUtil.loadXML(xmlPath);
		if (C != null) {
			String publicCrtPath = C.getPublicCrtPath();
			return createNotifyDataByCrtPath(notifyData, signMsg, publicCrtPath);
		}
		return null;
	}

	public NotifyData createNotifyDataByCrtPath(String notifyData, String signMsg, String publicCrtPath) {
		NotifyData nd = null;
		try {
			if (StringUtils.isNotEmpty(publicCrtPath)) {
				logger.info("银行证书存放路径：" + publicCrtPath);
				FileInputStream in1 = new FileInputStream(publicCrtPath);
				byte[] bcert = new byte[in1.available()];
				in1.read(bcert);
				in1.close();

				byte[] byteSM = signMsg.getBytes();
				byte[] DecSignMsg = ReturnValue.base64dec(byteSM);
				if (DecSignMsg == null) {
					System.out.println("错误：签名信息BASE64解码失败。");
					logger.error("错误：签名信息BASE64解码失败。");
					return null;
				}
				byte[] byteNd = notifyData.getBytes();
				byte[] DesNd = ReturnValue.base64dec(byteNd);
				if (DesNd == null) {
					System.out.println("错误：通知信息BASE64解码失败。");
					logger.error("错误：通知信息BASE64解码失败。");
					return null;
				}

				logger.info("银行对通知结果的签名数据：" + signMsg);
				int result = ReturnValue.verifySign(DesNd, DesNd.length, bcert, DecSignMsg);

				if (result == 0) {
					String notifyDataStr = new String(DesNd).toString();
					logger.info("通知结果数据：" + notifyDataStr);
					nd = xmlElements(notifyDataStr);
					logger.info("验签成功！");
					return nd;
				}

				logger.error("验签失败！");
				return null;
			}

			System.out.println("错误：配置文件有误，请检查配置文件存放路径和配置文件格式是否正确。");
			logger.error("错误：配置文件有误，请检查配置文件存放路径和配置文件格式是否正确。");
			return null;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return null;
		} catch (SignatureException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public NotifyData xmlElements(String xmlDoc) {
		StringReader read = new StringReader(xmlDoc);

		InputSource source = new InputSource(read);

		SAXBuilder sb = new SAXBuilder();

		NotifyData nd = new NotifyData();

		NotifyOrderInfo no = new NotifyOrderInfo();

		List OrderInfoList = new ArrayList();
		try {
			nd.setNotifyData(xmlDoc);

			Document doc = sb.build(source);

			Element root = doc.getRootElement();
			System.out.println(root.getName());

			List B2CReq = root.getChildren();
			Element E = null;
			E = (Element) B2CReq.get(0);
			nd.setInterfaceName(E.getTextTrim());
			E = (Element) B2CReq.get(1);
			nd.setInterfaceVersion(E.getTextTrim());
			if (("1.0.0.3".equals(nd.getInterfaceVersion())) || ("1.0.0.8".equals(nd.getInterfaceVersion()))) {
				E = (Element) B2CReq.get(2);
				List orderInfo = E.getChildren();
				E = (Element) orderInfo.get(0);
				nd.setOrderDate(E.getTextTrim());
				E = (Element) orderInfo.get(1);
				no.setOrderid(E.getTextTrim());
				E = (Element) orderInfo.get(2);
				no.setAmount(E.getTextTrim());
				E = (Element) orderInfo.get(3);
				nd.setCurType(E.getTextTrim());
				E = (Element) orderInfo.get(4);
				nd.setMerID(E.getTextTrim());
				E = (Element) orderInfo.get(5);
				no.setMerAcct(E.getTextTrim());
			}

			if (("1.0.0.11".equals(nd.getInterfaceVersion())) || ("1.0.0.13".equals(nd.getInterfaceVersion())) || ("1.0.0.14".equals(nd.getInterfaceVersion()))) {
				E = (Element) B2CReq.get(2);
				List orderInfo = E.getChildren();
				E = (Element) orderInfo.get(0);
				nd.setOrderDate(E.getTextTrim());
				E = (Element) orderInfo.get(1);
				nd.setCurType(E.getTextTrim());
				E = (Element) orderInfo.get(2);
				nd.setMerID(E.getTextTrim());
				E = (Element) orderInfo.get(3);
				List subOrderInfoList = E.getChildren();
				OrderInfoList = new ArrayList();
				for (int i = 0; i < subOrderInfoList.size(); i++) {
					E = (Element) subOrderInfoList.get(i);
					List subOrderInfo = E.getChildren();
					E = (Element) subOrderInfo.get(0);
					no.setOrderid(E.getTextTrim());
					E = (Element) subOrderInfo.get(1);
					no.setAmount(E.getTextTrim());
					E = (Element) subOrderInfo.get(2);
					no.setInstallmentTimes(E.getTextTrim());
					E = (Element) subOrderInfo.get(3);
					no.setMerAcct(E.getTextTrim());
					E = (Element) subOrderInfo.get(4);
					no.setTranSerialNo(E.getTextTrim());
					OrderInfoList.add(no);
				}

				nd.setSubOrderInfoList(OrderInfoList);
			}
			E = (Element) B2CReq.get(3);
			List custom = E.getChildren();
			E = (Element) custom.get(0);
			nd.setVerifyJoinFlag(E.getTextTrim());
			E = (Element) custom.get(1);
			nd.setJoinFlag(E.getTextTrim());
			E = (Element) custom.get(2);
			nd.setUserNum(E.getTextTrim());

			E = (Element) B2CReq.get(4);
			List bank = E.getChildren();
			if (("1.0.0.11".equals(nd.getInterfaceVersion())) || ("1.0.0.13".equals(nd.getInterfaceVersion())) || ("1.0.0.14".equals(nd.getInterfaceVersion()))) {
				E = (Element) bank.get(0);
				nd.setTranBatchNo(E.getTextTrim());
			}
			if (("1.0.0.3".equals(nd.getInterfaceVersion())) || ("1.0.0.8".equals(nd.getInterfaceVersion()))) {
				E = (Element) bank.get(0);
				no.setTranSerialNo(E.getTextTrim());

				OrderInfoList.add(no);
				nd.setSubOrderInfoList(OrderInfoList);
			}
			E = (Element) bank.get(1);
			nd.setNotifyDate(E.getTextTrim());
			E = (Element) bank.get(2);
			nd.setTranStat(E.getTextTrim());
			E = (Element) bank.get(3);
			nd.setComment(E.getTextTrim());
		} catch (JDOMException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return null;
		}
		return nd;
	}
}

/*
 * Location:
 * E:\游历信息\工行电子商务B2C测试资料5.30\工行电子商务B2C测试资料5.30\接口文档\电子商务商户接口集成化开发客户端工具（
 * 简化商户端开发工作量）\电子商务B2C接口二次开发任务\电子商务B2C接口二次开发任务\lib\icbcB2CApi.jar Qualified
 * Name: com.icbc.b2c.notify.IcbcB2CNotifyImpl JD-Core Version: 0.6.0
 */