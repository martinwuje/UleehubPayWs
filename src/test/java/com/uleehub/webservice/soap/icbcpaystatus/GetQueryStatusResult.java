
package com.uleehub.webservice.soap.icbcpaystatus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>GetQueryStatusResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="GetQueryStatusResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://uleehub.pay.com}WSResult">
 *       &lt;sequence>
 *         &lt;element name="bankInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outTradeNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetQueryStatusResult", propOrder = {
    "bankInfo",
    "outTradeNo",
    "status"
})
public class GetQueryStatusResult
    extends WSResult
{

    protected String bankInfo;
    protected String outTradeNo;
    protected String status;

    /**
     * 获取bankInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankInfo() {
        return bankInfo;
    }

    /**
     * 设置bankInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankInfo(String value) {
        this.bankInfo = value;
    }

    /**
     * 获取outTradeNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * 设置outTradeNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutTradeNo(String value) {
        this.outTradeNo = value;
    }

    /**
     * 获取status属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
