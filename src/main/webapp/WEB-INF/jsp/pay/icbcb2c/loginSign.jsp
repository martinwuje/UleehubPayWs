<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
<TITLE>工行新B2C测试页面回显</TITLE>
<%@ page contentType="text/html; charset=GBK" %>

<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<%@ page import="com.icbc.b2c.model.*" %>
<%@ page import="com.icbc.b2c.pay.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>

<%@ page import="javax.servlet.http.HttpServletRequest" %>


<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="cn.com.infosec.icbc.ReturnValue" %>


<% 
String InterfaceName="ICBC_PERBANK_B2C";
String MerCert="f:/AllWorkSpace/Uleehub/UleehubPayWs/src/main/webapp/WEB-INF/jsp/pay/icbcb2c/cltest.cer";
String MerSignMsg="f:/AllWorkSpace/Uleehub/UleehubPayWs/src/main/webapp/WEB-INF/jsp/pay/icbcb2c/cltest.key";
String TranData="";
String InterfaceVersion="1.0.0.11";//接口名称

System.out.println(request.getParameter("MerCustomPhone"));
DiskFileUpload upload = new DiskFileUpload();
List items = upload.parseRequest(request);

String OrderDate="20140630222620";//交易日期时间
String GoodsType="1";//虚拟商品/实物商品标志位
String MerCustomID="123456";//买家用户号
String MerCustomPhone="13466780886";//买家联系电话
String GoodsAddress="三里屯";//收货地址
String orderCount="1";//订单数量
String HangSupportFlag="true";//是否支持异步标志
String HangTimeInterval="24";//订单有效时间
String IP="192.168.1.2";//商户IP



System.out.println("orderCount============="+orderCount);
System.out.println("interfaceVersion============="+InterfaceVersion);
IcbcB2CPay pay=new IcbcB2CPayImpl(); 
TranData t=new TranData();//tranData对象
List l=new ArrayList();

t.setOrderDate(OrderDate);
t.setGoodsType(GoodsType);//1.0.0.3 1.0.0.8接口无需上送
t.setMerCustomID(MerCustomID);//1.0.0.3 1.0.0.8接口无需上送
t.setMerCustomPhone(MerCustomPhone);//1.0.0.3 1.0.0.8接口无需上送
t.setGoodsAddress(GoodsAddress);//1.0.0.3 1.0.0.8接口无需上送
t.setInterfaceVersion(InterfaceVersion);
t.setHangSupportFlag(HangSupportFlag);//1.0.0.3 1.0.0.8接口无需上送
t.setHangTimeInterval(HangTimeInterval);//1.0.0.3 1.0.0.8接口无需上送
t.setMerCustomIp(IP);//1.0.0.3 1.0.0.8接口无需上送
int j=Integer.parseInt(orderCount);

for(int i=1;i<j+1;i++)//此处为两个订单信息
{		
	
	Iterator iter2 = items.iterator();
	String Amount="200";//订单金额
	String CarriageAmt="200";//已含运费金额
	String GoodsID="001";//商品编号
	String GoodsName="威尼熊";//商品名称
	String GoodsNum="1";//商品数量
	String InstallmentTimes="1";//分期付款期数
	String Orderid="0001";//订单号
	
	OrderInfo order=new OrderInfo();//订单对象
	order.setAmount(Amount);
	order.setCarriageAmt(CarriageAmt);
	order.setGoodsID(GoodsID);
	order.setGoodsName(GoodsName);
	order.setGoodsNum(GoodsNum);
	order.setInstallmentTimes(InstallmentTimes);//1.0.0.3 1.0.0.8接口无需上送
	order.setOrderid(Orderid);
	l.add(order);
		
	if(l.size()>0)//至少有一个订单信息
	{
		t.setOrderInfoVector(l);//将订单信息加到tranData对象中
		FormData fd=pay.createFormData(t);//调用CreateFormData方法，生成formData中的表单数据
		//test=fd.getTranData();
		InterfaceName=fd.getInterfaceName();
		//InterfaceVersion=fd.getInterfaceVersion();
		MerCert=fd.getMerCert();
		MerSignMsg=fd.getMerSignMsg();
		 TranData=fd.getTranData();
		//request.setAttribute("InterfaceName", fd.getInterfaceName());	//生成的接口名称
		//request.setAttribute("InterfaceVersion", fd.getInterfaceVersion());	//生成的接口版本号
		//request.setAttribute("MerCert", fd.getMerCert());	//生成的交易数据
		//request.setAttribute("MerSignMsg", fd.getMerSignMsg());	//生成的订单签名数据
		//request.setAttribute("TranData", fd.getTranData());	//生成的商城证书公钥
	}
}
%>
<html>
<head>

<BODY bgcolor=Silver>
<font face='Arial' size='4' color='Green'>代理商户B2C支付测试页面</font>
<FORM  name="order" METHOD=POST ACTION="https://mybank3.dccnet.com.cn/servlet/NewB2cMerPayReqServlet">
  <table width="98%"  border="1">
   <tr>
      <td width="27%">接口名称</td>
      <td width="26%"><INPUT id="interfaceName" NAME="interfaceName" TYPE="text" value="<%=InterfaceName%>" ></td>
	</tr>
<tr>      
<td width="20%">接口版本号</td>
      <td width="27%">
		<INPUT id=interfaceVersion NAME="interfaceVersion" TYPE="text" value="<%=InterfaceVersion%>" >	
	</td>
    </tr>

    <tr>
      <td width="14%">接口数据</td>
      <td width="26%">
      <textarea id="tranData" name="tranData" style='height:140; width:600;'><%=TranData%></textarea>
    </tr>
     <tr>
      <td width="14%">签名数据</td>
      <td width="26%">
      <INPUT id="merSignMsg" NAME="merSignMsg" TYPE="text" value="<%=MerSignMsg%>">
    </tr>
    <tr>
      <td width="14%">证书数据</td>
      <td width="26%">
      <INPUT id="merCert" NAME="merCert" TYPE="text" value="<%=MerCert%>">
    </tr>
 </table>
	<table>
	<tr>
		<td>
			<INPUT TYPE="submit" value=" 提 交 订 单 ">
		</td>
		<td>
			<INPUT  type="button" value=" 返 回 修 改 " onClick="javascript:history.go(-1)">
		</td>
	</tr>
	</table>
	<%System.out.println("HangSupportFlag======="+HangSupportFlag); %>
	<INPUT id="HangSupportFlag" NAME="HangSupportFlag" TYPE="hidden" value="<%=HangSupportFlag%>">
	<INPUT id="HangTimeInterval" NAME="HangTimeInterval" TYPE="hidden" value="<%=HangTimeInterval%>">
</FORM>
注：各输入域均无合法性检查，可任意填写进行新模式B2C测试。
</BODY>
</html>


