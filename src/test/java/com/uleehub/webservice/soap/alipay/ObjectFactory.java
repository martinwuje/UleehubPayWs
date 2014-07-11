
package com.uleehub.webservice.soap.alipay;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.uleehub.service.pay.alipay package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PayByDTOResponse_QNAME = new QName("http://uleehub.pay.com", "payByDTOResponse");
    private final static QName _Pay_QNAME = new QName("http://uleehub.pay.com", "pay");
    private final static QName _QueryPayStatus_QNAME = new QName("http://uleehub.pay.com", "queryPayStatus");
    private final static QName _TradeInfoDTO_QNAME = new QName("http://uleehub.pay.com", "tradeInfoDTO");
    private final static QName _QueryPayStatusResponse_QNAME = new QName("http://uleehub.pay.com", "queryPayStatusResponse");
    private final static QName _PayResponse_QNAME = new QName("http://uleehub.pay.com", "payResponse");
    private final static QName _PayByDTO_QNAME = new QName("http://uleehub.pay.com", "payByDTO");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.uleehub.service.pay.alipay
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryPayStatusResponse }
     * 
     */
    public QueryPayStatusResponse createQueryPayStatusResponse() {
        return new QueryPayStatusResponse();
    }

    /**
     * Create an instance of {@link PayResponse }
     * 
     */
    public PayResponse createPayResponse() {
        return new PayResponse();
    }

    /**
     * Create an instance of {@link PayByDTO }
     * 
     */
    public PayByDTO createPayByDTO() {
        return new PayByDTO();
    }

    /**
     * Create an instance of {@link PayByDTOResponse }
     * 
     */
    public PayByDTOResponse createPayByDTOResponse() {
        return new PayByDTOResponse();
    }

    /**
     * Create an instance of {@link Pay }
     * 
     */
    public Pay createPay() {
        return new Pay();
    }

    /**
     * Create an instance of {@link TradeInfo }
     * 
     */
    public TradeInfo createTradeInfo() {
        return new TradeInfo();
    }

    /**
     * Create an instance of {@link QueryPayStatus }
     * 
     */
    public QueryPayStatus createQueryPayStatus() {
        return new QueryPayStatus();
    }

    /**
     * Create an instance of {@link WSResult }
     * 
     */
    public WSResult createWSResult() {
        return new WSResult();
    }

    /**
     * Create an instance of {@link GetStatusResult }
     * 
     */
    public GetStatusResult createGetStatusResult() {
        return new GetStatusResult();
    }

    /**
     * Create an instance of {@link GetSHtmlTextResult }
     * 
     */
    public GetSHtmlTextResult createGetSHtmlTextResult() {
        return new GetSHtmlTextResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PayByDTOResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uleehub.pay.com", name = "payByDTOResponse")
    public JAXBElement<PayByDTOResponse> createPayByDTOResponse(PayByDTOResponse value) {
        return new JAXBElement<PayByDTOResponse>(_PayByDTOResponse_QNAME, PayByDTOResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Pay }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uleehub.pay.com", name = "pay")
    public JAXBElement<Pay> createPay(Pay value) {
        return new JAXBElement<Pay>(_Pay_QNAME, Pay.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPayStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uleehub.pay.com", name = "queryPayStatus")
    public JAXBElement<QueryPayStatus> createQueryPayStatus(QueryPayStatus value) {
        return new JAXBElement<QueryPayStatus>(_QueryPayStatus_QNAME, QueryPayStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TradeInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uleehub.pay.com", name = "tradeInfoDTO")
    public JAXBElement<TradeInfo> createTradeInfoDTO(TradeInfo value) {
        return new JAXBElement<TradeInfo>(_TradeInfoDTO_QNAME, TradeInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPayStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uleehub.pay.com", name = "queryPayStatusResponse")
    public JAXBElement<QueryPayStatusResponse> createQueryPayStatusResponse(QueryPayStatusResponse value) {
        return new JAXBElement<QueryPayStatusResponse>(_QueryPayStatusResponse_QNAME, QueryPayStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PayResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uleehub.pay.com", name = "payResponse")
    public JAXBElement<PayResponse> createPayResponse(PayResponse value) {
        return new JAXBElement<PayResponse>(_PayResponse_QNAME, PayResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PayByDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uleehub.pay.com", name = "payByDTO")
    public JAXBElement<PayByDTO> createPayByDTO(PayByDTO value) {
        return new JAXBElement<PayByDTO>(_PayByDTO_QNAME, PayByDTO.class, null, value);
    }

}
