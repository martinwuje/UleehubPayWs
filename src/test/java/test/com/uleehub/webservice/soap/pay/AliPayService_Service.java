
package test.com.uleehub.webservice.soap.pay;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.7-b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "AliPayService", targetNamespace = "http://uleehub.pay.com", wsdlLocation = "http://localhost:8080/UleehubPayWs/cxf/uleepay/aliPayService?wsdl")
public class AliPayService_Service
    extends Service
{

    private final static URL ALIPAYSERVICE_WSDL_LOCATION;
    private final static WebServiceException ALIPAYSERVICE_EXCEPTION;
    private final static QName ALIPAYSERVICE_QNAME = new QName("http://uleehub.pay.com", "AliPayService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/UleehubPayWs/cxf/uleepay/aliPayService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ALIPAYSERVICE_WSDL_LOCATION = url;
        ALIPAYSERVICE_EXCEPTION = e;
    }

    public AliPayService_Service() {
        super(__getWsdlLocation(), ALIPAYSERVICE_QNAME);
    }

    public AliPayService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), ALIPAYSERVICE_QNAME, features);
    }

    public AliPayService_Service(URL wsdlLocation) {
        super(wsdlLocation, ALIPAYSERVICE_QNAME);
    }

    public AliPayService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ALIPAYSERVICE_QNAME, features);
    }

    public AliPayService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AliPayService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns AliPayService
     */
    @WebEndpoint(name = "AliPayServiceImplPort")
    public AliPayService getAliPayServiceImplPort() {
        return super.getPort(new QName("http://uleehub.pay.com", "AliPayServiceImplPort"), AliPayService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AliPayService
     */
    @WebEndpoint(name = "AliPayServiceImplPort")
    public AliPayService getAliPayServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://uleehub.pay.com", "AliPayServiceImplPort"), AliPayService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ALIPAYSERVICE_EXCEPTION!= null) {
            throw ALIPAYSERVICE_EXCEPTION;
        }
        return ALIPAYSERVICE_WSDL_LOCATION;
    }

}
