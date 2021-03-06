package com.uleehub.webservice.soap.icbcpaystatus;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.1
 * 2014-07-05T17:31:09.385+08:00
 * Generated source version: 2.7.1
 * 
 */
@WebServiceClient(name = "IcbcB2cPayStatusService", 
                  wsdlLocation = "http://localhost:8080/UleehubPayWs/cxf/uleepay/IcbcB2cPayStatusService?wsdl",
                  targetNamespace = "http://uleehub.pay.com") 
public class IcbcB2CPayStatusService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://uleehub.pay.com", "IcbcB2cPayStatusService");
    public final static QName IcbcB2CPayStatusServiceImplPort = new QName("http://uleehub.pay.com", "IcbcB2cPayStatusServiceImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/UleehubPayWs/cxf/uleepay/IcbcB2cPayStatusService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(IcbcB2CPayStatusService_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/UleehubPayWs/cxf/uleepay/IcbcB2cPayStatusService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public IcbcB2CPayStatusService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public IcbcB2CPayStatusService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IcbcB2CPayStatusService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns IcbcB2CPayStatusService
     */
    @WebEndpoint(name = "IcbcB2cPayStatusServiceImplPort")
    public IcbcB2CPayStatusService getIcbcB2CPayStatusServiceImplPort() {
        return super.getPort(IcbcB2CPayStatusServiceImplPort, IcbcB2CPayStatusService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IcbcB2CPayStatusService
     */
    @WebEndpoint(name = "IcbcB2cPayStatusServiceImplPort")
    public IcbcB2CPayStatusService getIcbcB2CPayStatusServiceImplPort(WebServiceFeature... features) {
        return super.getPort(IcbcB2CPayStatusServiceImplPort, IcbcB2CPayStatusService.class, features);
    }

}
