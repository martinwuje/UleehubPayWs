/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.uleehub.pay.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.uleehub.common.utils.PayRefundLogUtils;
import com.uleehub.pay.entity.RefundInfo;
import com.uleehub.pay.entity.TradeInfo;
import com.uleehub.pay.service.RefundInfoService;
import com.uleehub.pay.service.TradeInfoService;
import com.uleehub.webservice.soap.pay.util.GenerateBatchNoSequenceUtil;

/**
 * <p>
 * User: mtwu
 * <p>
 * Date: 2014-06-26
 * <p>
 * Version: 1.0
 */
@Controller
@RequestMapping(value = "/pay/alirefund")
public class AliRefundController {
	@Autowired
	private RefundInfoService refundInfoService;
	private static final String path = "pay/alirefund/";

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String aliPayIndex() {
		return path + "index";
	}

	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	@ResponseBody
	public void aliPayPay(HttpServletRequest request, HttpServletResponse response, String WIDbatch_no) throws Exception {
		PrintWriter out = response.getWriter();
		String url = request.getRequestURL().toString();
		RefundInfo refundInfo = refundInfoService.findBybatchNo(WIDbatch_no);
		if (refundInfo != null) { // 判断订单不为空
			if (refundInfo.getRefundStatus().equals(0)) { // 初始状态
			} else {
				//获取请求信息
				Map<String,String> params = new Hashtable<String,String>();
				@SuppressWarnings("rawtypes")
				Map requestParams = request.getParameterMap();
				for (@SuppressWarnings("rawtypes")
				Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
					String name = (String) iter.next();
					String[] values = (String[]) requestParams.get(name);
					String valueStr = "";
					for (int i = 0; i < values.length; i++) {
						valueStr = (i == values.length - 1) ? valueStr + values[i]
								: valueStr + values[i] + ",";
					}
					params.put(name, valueStr);
				}
				//退款失败日志
				PayRefundLogUtils.logRefundError(params, refundInfo,"pay","验证失败。",url);
				throw new RuntimeException("交易已完成或已被关闭");
			}
		} else {
			refundInfo = new RefundInfo();
		}
		DateTime now = DateTime.now();
		refundInfo.setBatchNo("201406270000001");
		refundInfo.setBatchNum(1);
		refundInfo.setDetailData("2014062653853876^0.01^协商退款");
		refundInfo.setExterInvokeIp("192.168.1.1");
		refundInfo.setProvider(AlipayConfig.provider);
		refundInfo.setPurchaserId("uleehub.com");
		refundInfo.setPurchaserName("游历信息");
		refundInfo.setRefundDate(now.toDate());
		refundInfo.setRefundStatus(Integer.valueOf(0));
		refundInfo.setRefundType(Integer.valueOf(1));
		refundInfo.setSellerEmail(AlipayConfig.seller_email);
		refundInfo.setService(AlipayConfig.alirefund_service);
		refundInfo.setTotalFee(0.01F);
		refundInfoService.save(refundInfo);

		// 服务器异步通知页面路径
		String notify_url = AlipayConfig.alirefund_notify_url;
		// 需http://格式的完整路径，不允许加?id=123这类自定义参数

		// 卖家支付宝帐户
		//String seller_email = new String(request.getParameter("WIDseller_email").getBytes("ISO-8859-1"), "UTF-8");
		String seller_email = AlipayConfig.seller_email;
		// 必填

		// 退款当天日期
		//String refund_date = new String(request.getParameter("WIDrefund_date").getBytes("ISO-8859-1"), "UTF-8");
		String refund_date = DateTime.now().toString("yyyy-MM-dd hh:mm:ss");
		// 必填，格式：年[4位]-月[2位]-日[2位] 小时[2位 24小时制]:分[2位]:秒[2位]，如：2007-10-01
		// 13:13:13

		// 批次号
		//String batch_no = new String(request.getParameter("WIDbatch_no").getBytes("ISO-8859-1"), "UTF-8");
		String batch_no = "201406270000001";
		// 必填，格式：当天日期[8位]+序列号[3至24位]，如：201008010000001

		// 退款笔数
		//String batch_num = new String(request.getParameter("WIDbatch_num").getBytes("ISO-8859-1"), "UTF-8");
		String batch_num = "1";
		// 必填，参数detail_data的值中，“#”字符出现的数量加1，最大支持1000笔（即“#”字符出现的数量999个）

		// 退款详细数据
		//String detail_data = new String(request.getParameter("WIDdetail_data").getBytes("ISO-8859-1"), "UTF-8");
		String detail_data = "2014062653853876^0.01^协商退款";
		// 必填，具体格式请参见接口技术文档

		// ////////////////////////////////////////////////////////////////////////////////

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alirefund_service");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("seller_email", seller_email);
		sParaTemp.put("refund_date", refund_date);
		sParaTemp.put("batch_no", batch_no);
		sParaTemp.put("batch_num", batch_num);
		sParaTemp.put("detail_data", detail_data);
		// 建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
		//退款日志
		PayRefundLogUtils.logRefundAccess(sParaTemp,refundInfo,"pay",url);
		out.print(sHtmlText);
	}

	@RequestMapping(value = "/notifyUrl", method = RequestMethod.POST)
	@ResponseBody
	public void aliPayNotifyUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = null;
		String url = request.getRequestURL().toString();
		try {
			out = response.getWriter();
			//获取支付宝POST过来反馈信息
			Map<String,String> params = new Hashtable<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//批次号

			String batch_no = new String(request.getParameter("batch_no").getBytes("ISO-8859-1"),"UTF-8");

			//批量退款数据中转账成功的笔数

			String success_num = new String(request.getParameter("success_num").getBytes("ISO-8859-1"),"UTF-8");

			//批量退款数据中的详细信息
			String result_details = new String(request.getParameter("result_details").getBytes("ISO-8859-1"),"UTF-8");
			
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

			if(AlipayNotify.verify(params)){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码

				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				
				//判断是否在商户网站中已经做过了这次通知返回的处理
					//如果没有做过处理，那么执行商户的业务程序
					//如果有做过处理，那么不执行商户的业务程序
				RefundInfo refundInfo = refundInfoService.findBybatchNo(batch_no);
				refundInfo.setVerifyDate(DateTime.now().toDate());
				refundInfo.setRefundStatus(Integer.valueOf(1));
				refundInfoService.save(refundInfo);
				//退款成功日志
				PayRefundLogUtils.logRefundAccess(requestParams,refundInfo,"notifyUrl",url);
				
				out.println("success");	//请不要修改或删除
				
				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

				//////////////////////////////////////////////////////////////////////////////////////////
			}else{//验证失败
				RefundInfo refundInfo = refundInfoService.findBybatchNo(batch_no);
				//退款失败日志
				PayRefundLogUtils.logRefundError(requestParams, refundInfo,"notifyUrl","验证失败。",url);
				out.println("fail");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			out.flush();
			out.close();
		}
	}

	

	
	public RefundInfoService getRefundInfoService() {
		return refundInfoService;
	}

	public void setRefundInfoService(RefundInfoService refundInfoService) {
		this.refundInfoService = refundInfoService;
	}

}
