
package test.com.uleehub.webservice.soap.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>payByDTOResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="payByDTOResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://uleehub.pay.com}GetUserResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "payByDTOResponse", propOrder = {
    "_return"
})
public class PayByDTOResponse {

    @XmlElement(name = "return")
    protected GetUserResult _return;

    /**
     * ��ȡreturn���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link GetUserResult }
     *     
     */
    public GetUserResult getReturn() {
        return _return;
    }

    /**
     * ����return���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link GetUserResult }
     *     
     */
    public void setReturn(GetUserResult value) {
        this._return = value;
    }

}
