
package com.uleehub.webservice.soap.alipay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>payByDTO complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="payByDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tradeInfo" type="{http://uleehub.pay.com}TradeInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "payByDTO", propOrder = {
    "tradeInfo"
})
public class PayByDTO {

    protected TradeInfo tradeInfo;

    /**
     * 获取tradeInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TradeInfo }
     *     
     */
    public TradeInfo getTradeInfo() {
        return tradeInfo;
    }

    /**
     * 设置tradeInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TradeInfo }
     *     
     */
    public void setTradeInfo(TradeInfo value) {
        this.tradeInfo = value;
    }

}
