<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>工行新B2C测试页面回显</TITLE>
<%@ page contentType="text/html; charset=utf-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">


<script type="text/javascript">
function check(){
	if(document.theForm.IP.value=="")
	{alert("请输入本机IP地址");
	return;}
	document.theForm.submit();
	}
</script>
</head>
<body bgcolor="#999966" >

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	<td width="15" nowrap="nowrap">&nbsp;</td>
	<td height="60" colspan="3" class="logo" nowrap="nowrap"><br />
	  工商银行B2C电子商务支付通软件支付部分演示</td>
	<td width="100%">&nbsp;</td>
	</tr>

	<tr bgcolor="#ffffff">
	<td colspan="5"><img src="mm_spacer.gif" alt="" width="1" height="1" border="0" /></td>
	</tr>

	<tr bgcolor="#a4c2c2">
	<td width="15" nowrap="nowrap">&nbsp;</td>
	<td height="36" colspan="2" id="navigation" class="navText"><a href="javascript:;">HOME</a></td>
	<td>&nbsp;</td>
	<td width="100%">&nbsp;</td>
	</tr>

	<tr bgcolor="#ffffff">
	<td colspan="5"><img src="mm_spacer.gif" alt="" width="1" height="1" border="0" /></td>
	</tr>

	<tr bgcolor="#ffffff">
	<td valign="top" width="15"><img src="mm_spacer.gif" alt="" width="15" height="1" border="0" /></td>
	<td valign="top" width="140"><img src="mm_spacer.gif" alt="" width="140" height="1" border="0" /></td>
	<td width="505" valign="top"><br />

		<FORM  name="theForm" METHOD="post" action="${ctx }/pay/icbcb2c/loginSign?emulatorUrl=${ctx }&applicationUrl=${ctx }" enctype="multipart/form-data">

	<input type="button" class="button" value="去结算" onclick="check()">
	<table border="0" cellspacing="0" cellpadding="2" width="440">
		<tr>
		<td class="pageName">产品支付</td>
		</tr>

		<tr>
		<td class="bodyText">
		<table width="315" border="1">
		  <tr>
			<td>接口版本号</td>
		  	<td>
				<select NAME="InterfaceVersion" id="InterfaceVersion">
					<option value="1.0.0.11" selected>1.0.0.11</option>
					<option value="1.0.0.13">1.0.0.13</option>
					<option value="1.0.0.14">1.0.0.14</option>
					<option value="1.0.0.3">1.0.0.3</option>
					<option value="1.0.0.8">1.0.0.8</option>
				</select>	
			</td>
          </tr>
          <tr>
            <td width="176">交易日期时间</td>
            <td width="123"><INPUT NAME="OrderDate" TYPE="text" value="2014-06-29" ></td>
          </tr>
          <tr>
            <td>虚拟商品/实物商品标志位</td>
            <td><INPUT NAME="GoodsType" TYPE="text" value="1" ></td>
          </tr>
          <tr>
            <td>买家用户号</td>
            <td><INPUT NAME="MerCustomID" TYPE="text" value="123456" ></td>
          </tr>
          <tr>
            <td>买家联系电话</td>
            <td><INPUT NAME="MerCustomPhone" TYPE="text" value="13466780886" ></td>
          </tr>
          <tr>
            <td>收货地址</td>
            <td><INPUT NAME="GoodsAddress" TYPE="text" value="三里屯" ></td>
          </tr>
			<tr>
            <td>本机IP</td>
            <td><INPUT NAME="IP" TYPE="text" value="192.168.1.2"></td>
          </tr>
		   <tr>
			<td colspan="2" bgcolor="#999999">以下两个字段仅1.0.0.14接口生效：</td>
		  </tr>
		  <tr>
			<td bgcolor="#999999">异步支付标志</td>
      <td bgcolor="#999999"><select NAME="HangSupportFlag" id="HangSupportFlag">
        <option value="0">不支持异步 
        <option value="1" selected>支持异步
      </select></td>
		  </tr>
			<tr>
				 <td bgcolor="#999999">订单有效时间</td>
      <td bgcolor="#999999"><INPUT NAME="HangTimeInterval" TYPE="text" id="HangTimeInterval" value="24" size="5">
      （小时）</td>
    </tr>
			<tr>
				<td>订单数量 </td>
				<td><select id="orderCount" name="orderCount" onChange="makeOrders(this.value)">
        <option value="0">0</option>
        <option value="1" selected>1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
        <option value="10">10</option>
            </select></td>
			</tr>
          <tr>
            <td colspan="2" bgcolor="#999999" align="center">第1件商品</td>
            </tr>
          <tr>
            <td>订单金额</td>
            <td><INPUT NAME="Amount1" TYPE="text" value="1" ></td>
          </tr>
          <tr>
            <td>已含运费金额</td>
            <td><INPUT NAME="CarriageAmt1" TYPE="text" value="20" ></td>
          </tr>
          <tr>
            <td>商品编号</td>
            <td><INPUT NAME="GoodsID1" TYPE="text" value="001" ></td>
          </tr>
          <tr>
            <td>商品名称</td>
            <td><INPUT NAME="GoodsName1" TYPE="text" value="威尼熊" ></td>
          </tr>
          <tr>
            <td>商品数量</td>
            <td><INPUT NAME="GoodsNum1" TYPE="text" value="1" ></td>
          </tr>
          <tr>
            <td>分期付款期数</td>
            <td><INPUT NAME="InstallmentTimes1" TYPE="text" value="1" ></td>
          </tr>
          <tr>
            <td>订单号</td>
            <td><INPUT NAME="Orderid1" TYPE="text" value="0001" ></td>
          </tr>
		<tr>
      <td colspan="4"><div id="orders"></div></td></tr>
        </table>
		<p>&nbsp;</p>

		<br /></td>
		</tr>
	</table>
		</form>
		</td>
	<td valign="top">&nbsp;</td>
	<td width="100%">&nbsp;</td>
	</tr>

	<tr>
	<td width="15">&nbsp;</td>
    <td width="140">&nbsp;</td>
    <td width="505">&nbsp;</td>
    <td width="100">&nbsp;</td>
    <td width="100%">&nbsp;</td>
  </tr>
  
</table>
<script language="JavaScript" type="text/javascript">
function makeOrders(o){
var html="";
for(var oid=1;oid<o;oid++){
//oid=i+1;
html+='<table width="100%" border="1"><tr><td>'
+'<tr><td colspan="2" bgcolor="#999999" align="center">第'+(oid+1)+'件商品</td></tr>'
+'<tr><td>订单金额</td><td><INPUT NAME="Amount'+(oid+1)+'" TYPE="text" value="1" ></td></tr>'
+'<tr><td>已含运费金额</td><td><INPUT NAME="CarriageAmt'+(oid+1)+'" TYPE="text" value="20" ></td></tr>'
+'<tr><td>商品编号</td><td><INPUT NAME="GoodsID'+(oid+1)+'" TYPE="text" value="001" ></td></tr>'
+'<tr><td>商品名称</td><td><INPUT NAME="GoodsName'+(oid+1)+'" TYPE="text" value="威尼熊" ></td></tr>'
+'<tr><td>商品数量</td><td><INPUT NAME="GoodsNum'+(oid+1)+'" TYPE="text" value="1" ></td></tr>'
+'<tr><td>分期付款期数</td><td><INPUT NAME="InstallmentTimes'+(oid+1)+'" TYPE="text" value="1" ></td></tr>'
+'<tr><td>订单号</td><td><INPUT NAME="Orderid'+(oid+1)+'" TYPE="text" value="'+(oid+1)+'" ></td></tr></td></tr></table>';
}
document.getElementById("orders").innerHTML=html;
}

</script>
</body>
</html>
