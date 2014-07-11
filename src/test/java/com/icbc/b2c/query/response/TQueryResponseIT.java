package com.icbc.b2c.query.response;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Assert;
import org.junit.Test;

import com.uleehub.util.mapper.JaxbMapper;

/**
 * <p>
 * User: mtwu
 * <p>
 * Date: 2014-7-3
 * <p>
 * Version: 1.0
 */
public class TQueryResponseIT {

	public QueryResponse createQueryResponse() {
		QueryResponse queryResponse = new QueryResponse();
		QueryResponsePub pub = new QueryResponsePub();
		QueryResponseIn in = new QueryResponseIn();
		QueryResponseOut out = new QueryResponseOut();
		queryResponse.setPub(pub);
		queryResponse.setIn(in);
		queryResponse.setOut(out);
		pub.setaPIName("接口名称1");
		pub.setaPIVersion("接口版本号1");
		in.setOrderNum("订单号1");
		in.setShopAccount("商城账号");
		in.setShopCode("商家号码");
		in.setTranDate("20140703");
		return queryResponse;
	}

	protected String createXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append("<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\" ?>");
		sb.append("<ICBCAPI>");
		sb.append("<pub>");
		sb.append("<APIName>接口名称</APIName>");
		sb.append("<APIVersion>接口版本号</APIVersion>");
		sb.append("</pub>");
		sb.append("<in>");
		sb.append("<orderNum>订单号</orderNum>");
		sb.append("<tranDate>交易日期</tranDate>");
		sb.append("<ShopCode>商家号码</ShopCode>");
		sb.append("<ShopAccount>商城账号</ShopAccount>");
		sb.append("</in>");
		sb.append("<out>");
		sb.append("<tranSerialNum>指令序号</tranSerialNum>");
		sb.append("<tranStat>订单处理状态</tranStat>");
		sb.append("<bankRem>指令错误信息</bankRem>");
		sb.append("<amount>订单总金额</amount>");
		sb.append("<currType>支付币种</currType>");
		sb.append("<tranTime>返回通知日期时间</tranTime>");
		sb.append("<ShopAccount>商城账号</ShopAccount>");
		sb.append("<PayeeName>商城户名</PayeeName>");
		sb.append("<JoinFlag>校验联名标志</JoinFlag>");
		sb.append("<MerJoinFlag>商城联名标志</MerJoinFlag>");
		sb.append("<CustJoinFlag>客户联名标志</CustJoinFlag>");
		sb.append("<CustJoinNum>联名会员号</CustJoinNum>");
		sb.append("<CertID>商户签名证书id</CertID>");
		sb.append("</out>");
		sb.append("</ICBCAPI>");
		return sb.toString();
	}

	@Test
	public void toXml() {
		QueryResponse queryResponse = createQueryResponse();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(QueryResponse.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			String xml = JaxbMapper.toXml(queryResponse, "UTF-8");
			Assert.assertNotNull(xml);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void toObject() {
		String xml  = createXml();
		QueryResponse response = JaxbMapper.fromXml(xml, QueryResponse.class);
		Assert.assertEquals("订单总金额", response.getOut().getAmount());
	}
}
