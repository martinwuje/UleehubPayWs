package com.uleehub.webservice.soap.pay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.uleehub.pay.entity.RefundInfo;
import com.uleehub.pay.entity.TradeInfo;
import com.uleehub.pay.service.RefundInfoService;
import com.uleehub.pay.service.TradeInfoService;
import com.uleehub.webservice.soap.pay.common.BaseWebService;
import com.uleehub.webservice.soap.pay.response.GetSHtmlTextResult;
import com.uleehub.webservice.soap.pay.response.GetStatusResult;

/**
 * 退款接口WebService服务端实现类.
 * 
 * 
 * @author mtwu
 */
// serviceName指明WSDL中<wsdl:service>与<wsdl:binding>元素的名称,
// endpointInterface属性指向Interface类全称.
@WebService(serviceName = "AliRefundService", endpointInterface = "com.uleehub.webservice.soap.pay.AliRefundService", targetNamespace = WsConstants.NS)
public class AliRefundServiceImpl extends BaseWebService implements AliRefundService {
	private static Logger logger = LoggerFactory.getLogger(AliRefundServiceImpl.class);
	
	private RefundInfoService refundInfoService;
	
	private TradeInfoService tradeInfoService;
	/**
	 * 退款接口
	 * @param batch_no 退款批次号唯一，格式：当天日期[8位]+序列号[3至24位]，如：201008010000001
	 * @param batch_num 退款笔数，退款详情“#”字符出现的数量加1
	 * @param detail_data 退款详情 例如订单编号1^退款金额1^退款理由1 
	 * @param exter_invoke_ip
	 * @return
	 */
	@Override
	public GetSHtmlTextResult refund(String batch_no, String batch_num, String detail_data, String exter_invoke_ip, @WebParam(name="purchaser_id") String purchaser_id,@WebParam(name="purchaser_name") String purchaser_name) {
		GetSHtmlTextResult result = new GetSHtmlTextResult();
		try {
			Validate.notNull(batch_no, "退款批次号参数为空");
			Validate.notNull(batch_num, "退款笔数参数为空");
			Validate.notNull(detail_data, "交易详情参数为空");
			Integer batchNum = Integer.valueOf(batch_num);
			//定义退款总金额
			Float totalFee = 0f;
			//处理自定义的退款详情【订单编号1^退款金额1^退款理由1#订单编号2^退款金额2^退款理由2】，把订单编号替换成交易编号
			String[] datas = detail_data.split(AlipayConfig.refund_data_spliter);
			List<String> ldetailData = new ArrayList<String>();
			Validate.isTrue(batchNum.intValue() == datas.length, "退款笔数与交易详情不匹配");
			for (int i = 0; i < datas.length; i++) {
				String[] item = datas[i].split(AlipayConfig.refund_data_item_spliter);
				Validate.isTrue(item.length == 3, "交易详情参数错误");
				TradeInfo tradeInfo = tradeInfoService.findByOutTradeNo(item[0]);
				Validate.notNull(tradeInfo, "订单编号错误 ["+item[0]+"]");
				Validate.notNull(tradeInfo.getTradeNo(), "订单未支付或支付失败 ["+item[0]+"]");
				ldetailData.add(tradeInfo.getTradeNo()+AlipayConfig.refund_data_item_spliter+item[1]+AlipayConfig.refund_data_item_spliter+item[2]);
				totalFee+=Float.valueOf(item[1]);
			}
			//得到提交给支付宝的退款详情, 【交易号^退款金额^退款理由】
			String detailData = StringUtils.join(ldetailData, AlipayConfig.refund_data_spliter);
			//判断是否存在退款批次号, 如果存在就更新数据
			RefundInfo refundInfo = refundInfoService.findBybatchNo(batch_no);
			if(null == refundInfo){
				refundInfo = new RefundInfo();
			}
			DateTime now = DateTime.now();
			refundInfo.setBatchNo(batch_no);
			refundInfo.setBatchNum(batchNum);
			refundInfo.setDetailData(detailData);
			refundInfo.setExterInvokeIp(exter_invoke_ip);
			refundInfo.setProvider(AlipayConfig.provider);
			refundInfo.setPurchaserId(purchaser_id);
			refundInfo.setPurchaserName(purchaser_name);
			refundInfo.setRefundDate(now.toDate());
			refundInfo.setRefundStatus(Integer.valueOf(0));
			refundInfo.setRefundType(Integer.valueOf(1));
			refundInfo.setSellerEmail(AlipayConfig.seller_email);
			refundInfo.setService(AlipayConfig.alirefund_service);
			refundInfo.setTotalFee(totalFee);
			refundInfoService.save(refundInfo);
			// ////////////////////////////////////////////////////////////////////////////////
			// 把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "alirefund_service");
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("notify_url", AlipayConfig.alirefund_notify_url);
			sParaTemp.put("seller_email", AlipayConfig.seller_email);
			sParaTemp.put("refund_date", now.toString("yyyy-MM-dd hh:mm:ss"));
			sParaTemp.put("batch_no", batch_no);
			sParaTemp.put("batch_num", batch_num);
			sParaTemp.put("detail_data", refundInfo.getDetailData());
			// 建立请求
			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
			result.setsHtmlText(sHtmlText);
			
		} catch (IllegalArgumentException e) {
			return handleParameterError(result, e);
		} catch (RuntimeException e) {
			return handleGeneralError(result, e);
		}
		return result;
	}

	
	@Override
	public GetStatusResult queryRefundStatus(String batch_no) {
		GetStatusResult result = new GetStatusResult();
		try {
			Validate.notBlank(batch_no, "batch_no参数为空");
			RefundInfo refundInfo = refundInfoService.findBybatchNo(batch_no);
			Integer status = refundInfo.getRefundStatus();
			result.setStatus(status.toString());
			
		} catch (IllegalArgumentException e) {
			return handleParameterError(result, e);
		} catch (RuntimeException e) {
			return handleGeneralError(result, e);
		}
		return result;
	}


	@Override
	public Logger getLogger() {
		return logger;
	}

	@Autowired
	public void setRefundInfoService(RefundInfoService refundInfoService) {
		this.refundInfoService = refundInfoService;
	}
	
	@Autowired
	public void setTradeInfoService(TradeInfoService tradeInfoService) {
		this.tradeInfoService = tradeInfoService;
	}
}
