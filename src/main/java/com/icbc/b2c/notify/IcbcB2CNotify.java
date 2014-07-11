package com.icbc.b2c.notify;

import com.icbc.b2c.model.NotifyData;

public abstract interface IcbcB2CNotify
{
  public abstract NotifyData createNotifyData(String paramString1, String paramString2, String paramString3);

  public abstract NotifyData xmlElements(String paramString);
}

/* Location:           E:\游历信息\工行电子商务B2C测试资料5.30\工行电子商务B2C测试资料5.30\接口文档\电子商务商户接口集成化开发客户端工具（简化商户端开发工作量）\电子商务B2C接口二次开发任务\电子商务B2C接口二次开发任务\lib\icbcB2CApi.jar
 * Qualified Name:     com.icbc.b2c.notify.IcbcB2CNotify
 * JD-Core Version:    0.6.0
 */