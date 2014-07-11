
package test.com.uleehub.webservice.soap.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>pay complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="pay">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="out_trade_no" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="total_fee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="body" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="show_url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exter_invoke_ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pay", propOrder = {
    "outTradeNo",
    "subject",
    "totalFee",
    "body",
    "showUrl",
    "exterInvokeIp"
})
public class Pay {

    @XmlElement(name = "out_trade_no")
    protected String outTradeNo;
    protected String subject;
    @XmlElement(name = "total_fee")
    protected String totalFee;
    protected String body;
    @XmlElement(name = "show_url")
    protected String showUrl;
    @XmlElement(name = "exter_invoke_ip")
    protected String exterInvokeIp;

    /**
     * ��ȡoutTradeNo���Ե�ֵ��
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
     * ����outTradeNo���Ե�ֵ��
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
     * ��ȡsubject���Ե�ֵ��
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
     * ����subject���Ե�ֵ��
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
     * ��ȡtotalFee���Ե�ֵ��
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
     * ����totalFee���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalFee(String value) {
        this.totalFee = value;
    }

    /**
     * ��ȡbody���Ե�ֵ��
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
     * ����body���Ե�ֵ��
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
     * ��ȡshowUrl���Ե�ֵ��
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
     * ����showUrl���Ե�ֵ��
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
     * ��ȡexterInvokeIp���Ե�ֵ��
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
     * ����exterInvokeIp���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExterInvokeIp(String value) {
        this.exterInvokeIp = value;
    }

}
