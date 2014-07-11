package com.uleehub.pay.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.infosec.icbc.ReturnValue;

import com.icbc.b2c.config.IcbcB2cConfig;
import com.icbc.b2c.model.NotifyData;
import com.icbc.b2c.model.OrderInfo;
import com.icbc.b2c.notify.IcbcB2CNotifyImpl;
import com.icbc.b2c.pay.IcbcB2CPayImpl;
import com.uleehub.pay.entity.TradeInfo;
import com.uleehub.pay.service.TradeInfoService;

/**
 * <p>User: mtwu
 * <p>Date: 2014-6-28
 * <p>Version: 1.0
 */
@Controller
@RequestMapping(value = "/pay/icbcb2c")
public class IcbcB2cPayController {
	static Logger logger = LoggerFactory.getLogger(IcbcB2cPayController.class);
	@Autowired
	private TradeInfoService tradeInfoService;
	
	private static final String path = "pay/icbcb2c/";

	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String aliPayIndex(@PathVariable("page") String page) {
		return path + page;
	}
	
	@RequestMapping(value = "/{page}", method = RequestMethod.POST)
	public String aliPayIndexPost(@PathVariable("page") String page) {
		return path + page;
	}
	
	@RequestMapping(value = "/notifyUrl", method = RequestMethod.POST)
	@ResponseBody
	public void aliPayNotifyUrl(HttpServletRequest request, HttpServletResponse response, String notifyData, String signMsg) throws Exception{
		PrintWriter out = null;
		try {
			//notifyData=new String(ReturnValue.base64dec(notifyData.getBytes()));
			IcbcB2CNotifyImpl notifyImpl=new IcbcB2CNotifyImpl();
			NotifyData notifyData1=new NotifyData();
			notifyData1=notifyImpl.createNotifyDataByCrtPath(notifyData, signMsg, IcbcB2cConfig.userCrtPath);
			if(notifyData1==null)
			{
				System.out.println("返回值为null，解析银行通知消息报错。");
				logger.error("返回值为null，解析银行通知消息报错。notifyData["+new String(ReturnValue.base64dec(notifyData.getBytes()))+"]");
			}
			else
			{
				String orderId = "";
				List<OrderInfo> ol = notifyData1.getSubOrderInfoList();
				if(ol.size() > 0){
					OrderInfo orderInfo = ol.get(0);
					orderId = orderInfo.getOrderid();
				}
				TradeInfo tradeInfo = tradeInfoService.findByOutTradeNo(orderId);
				
				String tranBatchNo = notifyData1.getTranBatchNo();
				tradeInfo.setTradeNo(tranBatchNo);
				DateTime dateTime;
				DateTimeFormatter df = DateTimeFormat.forPattern("yyyyMMddhhmmss");
				dateTime = df.parseDateTime(notifyData1.getNotifyDate());
				tradeInfo.setVerifyDate(dateTime.toDate());
				tradeInfo.setTradeStatus(Integer.parseInt(notifyData1.getTranStat()));
				tradeInfoService.save(tradeInfo);
				out.print("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			out.flush();
			out.close();
		}
	}


	public void setTradeInfoService(TradeInfoService tradeInfoService) {
		this.tradeInfoService = tradeInfoService;
	}
	
}
