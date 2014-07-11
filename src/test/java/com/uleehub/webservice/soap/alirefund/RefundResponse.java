
package com.uleehub.webservice.soap.alirefund;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>refundResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="refundResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://uleehub.pay.com}GetSHtmlTextResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refundResponse", propOrder = {
    "_return"
})
public class RefundResponse {

    @XmlElement(name = "return")
    protected GetSHtmlTextResult _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GetSHtmlTextResult }
     *     
     */
    public GetSHtmlTextResult getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GetSHtmlTextResult }
     *     
     */
    public void setReturn(GetSHtmlTextResult value) {
        this._return = value;
    }

}
