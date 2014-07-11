
package com.uleehub.webservice.soap.alipay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>TradeInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="TradeInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="body" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exter_invoke_ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="out_trade_no" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="purchaser_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="purchaser_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="show_url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subject_num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="total_fee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TradeInfo", propOrder = {
    "body",
    "exterInvokeIp",
    "id",
    "num",
    "outTradeNo",
    "purchaserId",
    "purchaserName",
    "showUrl",
    "subject",
    "subjectNum",
    "totalFee"
})
public class TradeInfo {

    protected String body;
    @XmlElement(name = "exter_invoke_ip")
    protected String exterInvokeIp;
    protected Long id;
    protected String num;
    @XmlElement(name = "out_trade_no")
    protected String outTradeNo;
    @XmlElement(name = "purchaser_id")
    protected String purchaserId;
    @XmlElement(name = "purchaser_name")
    protected String purchaserName;
    @XmlElement(name = "show_url")
    protected String showUrl;
    protected String subject;
    @XmlElement(name = "subject_num")
    protected String subjectNum;
    @XmlElement(name = "total_fee")
    protected String totalFee;

    /**
     * 获取body属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBody() {
        return body;
    }

    /**
     * 设置body属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBody(String value) {
        this.body = value;
    }

    /**
     * 获取exterInvokeIp属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExterInvokeIp() {
        return exterInvokeIp;
    }

    /**
     * 设置exterInvokeIp属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExterInvokeIp(String value) {
        this.exterInvokeIp = value;
    }

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * 获取num属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNum() {
        return num;
    }

    /**
     * 设置num属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNum(String value) {
        this.num = value;
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
     * 获取purchaserId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurchaserId() {
        return purchaserId;
    }

    /**
     * 设置purchaserId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaserId(String value) {
        this.purchaserId = value;
    }

    /**
     * 获取purchaserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurchaserName() {
        return purchaserName;
    }

    /**
     * 设置purchaserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaserName(String value) {
        this.purchaserName = value;
    }

    /**
     * 获取showUrl属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShowUrl() {
        return showUrl;
    }

    /**
     * 设置showUrl属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShowUrl(String value) {
        this.showUrl = value;
    }

    /**
     * 获取subject属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置subject属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * 获取subjectNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubjectNum() {
        return subjectNum;
    }

    /**
     * 设置subjectNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubjectNum(String value) {
        this.subjectNum = value;
    }

    /**
     * 获取totalFee属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalFee() {
        return totalFee;
    }

    /**
     * 设置totalFee属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalFee(String value) {
        this.totalFee = value;
    }

}
