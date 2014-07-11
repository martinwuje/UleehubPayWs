
package com.uleehub.webservice.soap.alirefund;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>refund complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="refund">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batch_no" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="batch_num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="detail_data" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exter_invoke_ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="purchaser_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="purchaser_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refund", propOrder = {
    "batchNo",
    "batchNum",
    "detailData",
    "exterInvokeIp",
    "purchaserId",
    "purchaserName"
})
public class Refund {

    @XmlElement(name = "batch_no")
    protected String batchNo;
    @XmlElement(name = "batch_num")
    protected String batchNum;
    @XmlElement(name = "detail_data")
    protected String detailData;
    @XmlElement(name = "exter_invoke_ip")
    protected String exterInvokeIp;
    @XmlElement(name = "purchaser_id")
    protected String purchaserId;
    @XmlElement(name = "purchaser_name")
    protected String purchaserName;

    /**
     * 获取batchNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * 设置batchNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchNo(String value) {
        this.batchNo = value;
    }

    /**
     * 获取batchNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchNum() {
        return batchNum;
    }

    /**
     * 设置batchNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchNum(String value) {
        this.batchNum = value;
    }

    /**
     * 获取detailData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetailData() {
        return detailData;
    }

    /**
     * 设置detailData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetailData(String value) {
        this.detailData = value;
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

}
