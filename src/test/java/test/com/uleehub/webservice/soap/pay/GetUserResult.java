
package test.com.uleehub.webservice.soap.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>GetUserResult complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="GetUserResult">
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
@XmlType(name = "GetUserResult", propOrder = {
    "sHtmlText"
})
public class GetUserResult
    extends WSResult
{

    protected String sHtmlText;

    /**
     * ��ȡsHtmlText���Ե�ֵ��
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
     * ����sHtmlText���Ե�ֵ��
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
