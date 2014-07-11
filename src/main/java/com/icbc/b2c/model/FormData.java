package com.icbc.b2c.model;

public class FormData
{
  private String interfaceName;
  private String interfaceVersion;
  private String tranData;
  private String merSignMsg;
  private String merCert;

  public String getInterfaceName()
  {
    return this.interfaceName;
  }

  public void setInterfaceName(String interfaceName)
  {
    this.interfaceName = interfaceName;
  }

  public String getInterfaceVersion()
  {
    return this.interfaceVersion;
  }

  public void setInterfaceVersion(String interfaceVersion)
  {
    this.interfaceVersion = interfaceVersion;
  }

  public String getTranData()
  {
    return this.tranData;
  }

  public void setTranData(String tranData)
  {
    this.tranData = tranData;
  }

  public String getMerSignMsg()
  {
    return this.merSignMsg;
  }

  public void setMerSignMsg(String merSignMsg)
  {
    this.merSignMsg = merSignMsg;
  }

  public String getMerCert()
  {
    return this.merCert;
  }

  public void setMerCert(String merCert)
  {
    this.merCert = merCert;
  }
}
