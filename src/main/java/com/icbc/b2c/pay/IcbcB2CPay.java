package com.icbc.b2c.pay;

import com.icbc.b2c.model.B2cConfig;
import com.icbc.b2c.model.FormData;
import com.icbc.b2c.model.TranData;

public interface IcbcB2CPay
{
  public String packTranData(B2cConfig paramB2cConfig, TranData paramTranData);

  public FormData createFormData(String paramString, TranData paramTranData);
  
  public FormData createFormData(TranData paramTranData);
  
  public String buildRequest(FormData formData);
}

/* Location:           E:\游历信息\工行电子商务B2C测试资料5.30\工行电子商务B2C测试资料5.30\接口文档\电子商务商户接口集成化开发客户端工具（简化商户端开发工作量）\电子商务B2C接口二次开发任务\电子商务B2C接口二次开发任务\lib\icbcB2CApi.jar
 * Qualified Name:     com.icbc.b2c.pay.IcbcB2CPay
 * JD-Core Version:    0.6.0
 */