
package com.uleehub.webservice.soap.alirefund;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.uleehub.webservice.soap.alirefund package. 
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

    private final static QName _RefundResponse_QNAME = new QName("http://uleehub.pay.com", "refundResponse");
    private final static QName _QueryRefundStatus_QNAME = new QName("http://uleehub.pay.com", "queryRefundStatus");
    private final static QName _QueryRefundStatusResponse_QNAME = new QName("http://uleehub.pay.com", "queryRefundStatusResponse");
    private final static QName _Refund_QNAME = new QName("http://uleehub.pay.com", "refund");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.uleehub.webservice.soap.alirefund
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RefundResponse }
     * 
     */
    public RefundResponse createRefundResponse() {
        return new RefundResponse();
    }

    /**
     * Create an instance of {@link QueryRefundStatusResponse }
     * 
     */
    public QueryRefundStatusResponse createQueryRefundStatusResponse() {
        return new QueryRefundStatusResponse();
    }

    /**
     * Create an instance of {@link QueryRefundStatus }
     * 
     */
    public QueryRefundStatus createQueryRefundStatus() {
        return new QueryRefundStatus();
    }

    /**
     * Create an instance of {@link Refund }
     * 
     */
    public Refund createRefund() {
        return new Refund();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RefundResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uleehub.pay.com", name = "refundResponse")
    public JAXBElement<RefundResponse> createRefundResponse(RefundResponse value) {
        return new JAXBElement<RefundResponse>(_RefundResponse_QNAME, RefundResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryRefundStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uleehub.pay.com", name = "queryRefundStatus")
    public JAXBElement<QueryRefundStatus> createQueryRefundStatus(QueryRefundStatus value) {
        return new JAXBElement<QueryRefundStatus>(_QueryRefundStatus_QNAME, QueryRefundStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryRefundStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uleehub.pay.com", name = "queryRefundStatusResponse")
    public JAXBElement<QueryRefundStatusResponse> createQueryRefundStatusResponse(QueryRefundStatusResponse value) {
        return new JAXBElement<QueryRefundStatusResponse>(_QueryRefundStatusResponse_QNAME, QueryRefundStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Refund }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uleehub.pay.com", name = "refund")
    public JAXBElement<Refund> createRefund(Refund value) {
        return new JAXBElement<Refund>(_Refund_QNAME, Refund.class, null, value);
    }

}
