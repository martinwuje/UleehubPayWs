
package com.uleehub.webservice.soap.alirefund;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>GetSHtmlTextResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="GetSHtmlTextResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://uleehub.pay.com}WSResult">
 *       &lt;sequence>
 *         &lt;element name="sHtmlText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetSHtmlTextResult", propOrder = {
    "sHtmlText"
})
public class GetSHtmlTextResult
    extends WSResult
{

    protected String sHtmlText;

    /**
     * 获取sHtmlText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSHtmlText() {
        return sHtmlText;
    }

    /**
     * 设置sHtmlText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSHtmlText(String value) {
        this.sHtmlText = value;
    }

}
