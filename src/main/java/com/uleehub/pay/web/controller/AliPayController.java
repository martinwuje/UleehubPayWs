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
import com.uleehub.pay.entity.TradeInfo;
import com.uleehub.pay.service.TradeInfoService;

/**
 * <p>
 * User: mtwu
 * <p>
 * Date: 2014-06-26
 * <p>
 * Version: 1.0
 */
@Controller
@RequestMapping(value = "/pay/alipay")
public class AliPayController{
	@Autowired
	private TradeInfoService tradeInfoService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String aliPayIndex(){
		return "pay/alipay/index";
	}
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	@ResponseBody
	public void aliPayPay(HttpServletRequest request, HttpServletResponse response, String WIDout_trade_no)throws Exception{
		PrintWriter out = response.getWriter();
		TradeInfo tradeInfo = tradeInfoService.findByOutTradeNo(WIDout_trade_no);
		String url = request.getRequestURL().toString();
		
		if(tradeInfo != null){ //判断订单不为空
			if(tradeInfo.getTradeStatus().equals(0)){ //初始状态
			}
			else{
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
				//记录交易日志
				PayRefundLogUtils.logPayError(params, tradeInfo,"pay","验证失败。",url);
				throw new RuntimeException("交易已完成或已被关闭");
			}
		}else{
			tradeInfo = new TradeInfo(WIDout_trade_no, "iPad Air", 1,  "0.01","苹果iPad","http://apple/air/4","192.168.1.8",1,"sunp@sctis.com",AlipayConfig.provider, "5101000818", "川旅旅行社");
		}
		tradeInfo.setService(AlipayConfig.alipay_service);
		tradeInfoService.saveAndFlush(tradeInfo);
		Map<String, String> sParaTemp = new Hashtable<String, String>();
		sParaTemp.put("service", AlipayConfig.alipay_service);
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", tradeInfo.getPaymentType().toString());
		sParaTemp.put("notify_url", AlipayConfig.alipay_notify_url);
		sParaTemp.put("return_url", AlipayConfig.alipay_return_url);
		sParaTemp.put("seller_email", AlipayConfig.seller_email);
		//sParaTemp.put("seller_email", "alexanderwj@163.com");
		sParaTemp.put("out_trade_no", tradeInfo.getOutTradeNo());
		sParaTemp.put("subject", tradeInfo.getSubject());
		sParaTemp.put("total_fee", tradeInfo.getTotalFee().toString());
		sParaTemp.put("body", tradeInfo.getBody());
		sParaTemp.put("show_url", tradeInfo.getShowUrl());
		sParaTemp.put("anti_phishing_key", "");
		sParaTemp.put("exter_invoke_ip", tradeInfo.getExterInvokeIp());
		//记录交易日志
		PayRefundLogUtils.logPayAccess(sParaTemp,tradeInfo,"pay",url);
		// 建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
		out.print(sHtmlText);
	}
	@RequestMapping(value = "/notifyUrl", method = RequestMethod.POST)
	@ResponseBody
	public void aliPayNotifyUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = null;
		String url = request.getRequestURL().toString();
		try {
			out = response.getWriter();
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new Hashtable<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
				// "gbk");
				params.put(name, valueStr);
			}

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

			if (AlipayNotify.verify(params)) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码

				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

				if (trade_status.equals("TRADE_FINISHED")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序

					// 注意：
					// 该种交易状态只在两种情况下出现
					// 1、开通了普通即时到账，买家付款成功后。
					// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
				} else if (trade_status.equals("TRADE_SUCCESS")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					TradeInfo tradeInfo = tradeInfoService.findByOutTradeNo(out_trade_no);
					if(tradeInfo.getTradeStatus() != 1){
						tradeInfo.setTradeNo(trade_no);
						tradeInfo.setVerifyDate(DateTime.now().toDate());
						tradeInfo.setTradeStatus(Integer.valueOf(1));
						tradeInfoService.save(tradeInfo);
						//记录交易成功日志
						PayRefundLogUtils.logPayAccess(params,tradeInfo,"notifyUrl",url);
					}
					// 注意：
					// 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
				} 

				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
				out.println("success"); // 请不要修改或删除

				// ////////////////////////////////////////////////////////////////////////////////////////
			} else {// 验证失败
				TradeInfo tradeInfo = tradeInfoService.findByOutTradeNo(out_trade_no);
				//记录交易失败日志
				PayRefundLogUtils.logPayError(params, tradeInfo,"notifyUrl","验证失败。",url);
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
	@RequestMapping(value = "/returnUrl", method = RequestMethod.GET)
	public String aliPayReturnUrl(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		String url = request.getRequestURL().toString();
		//获取支付宝GET过来反馈信息
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
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		
		if(verify_result){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				TradeInfo tradeInfo = tradeInfoService.findByOutTradeNo(out_trade_no);
				if(tradeInfo.getTradeStatus() != 1){
					tradeInfo.setTradeNo(trade_no);
					tradeInfo.setVerifyDate(DateTime.now().toDate());
					tradeInfo.setTradeStatus(Integer.valueOf(1));
					tradeInfoService.save(tradeInfo);
					//记录交易成功日志
					PayRefundLogUtils.logPayAccess(params,tradeInfo,"returnUrl",url);
				}
			}
			
			//该页面可做页面美工编辑
			out.println("验证成功<br />");
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{
			TradeInfo tradeInfo = tradeInfoService.findByOutTradeNo(out_trade_no);
			//记录交易失败日志
			PayRefundLogUtils.logPayError(params, tradeInfo,"returnUrl","验证失败。",url);
			//该页面可做页面美工编辑
			out.println("验证失败");
		}
		return "pay/alipay/returnUrl";
	}
	
	public void setTradeInfoService(TradeInfoService tradeInfoService) {
		this.tradeInfoService = tradeInfoService;
	}
	
}
