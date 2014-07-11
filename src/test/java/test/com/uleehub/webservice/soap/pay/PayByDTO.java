
package test.com.uleehub.webservice.soap.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>payByDTO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="payByDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="user" type="{http://uleehub.pay.com}TradeInfo" minOccurs="0"/>
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
    "user"
})
public class PayByDTO {

    protected TradeInfo user;

    /**
     * ��ȡuser���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link TradeInfo }
     *     
     */
    public TradeInfo getUser() {
        return user;
    }

    /**
     * ����user���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link TradeInfo }
     *     
     */
    public void setUser(TradeInfo value) {
        this.user = value;
    }

}
