
package test.com.uleehub.webservice.soap.pay;

import java.util.concurrent.Future;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.Response;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.7-b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "AliPayService", targetNamespace = "http://uleehub.pay.com")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AliPayService {


    /**
     * 
     * @param showUrl
     * @param body
     * @param totalFee
     * @param exterInvokeIp
     * @param subject
     * @param outTradeNo
     * @return
     *     returns javax.xml.ws.Response<test.com.uleehub.webservice.soap.pay.PayResponse>
     */
    @WebMethod(operationName = "pay")
    @RequestWrapper(localName = "pay", targetNamespace = "http://uleehub.pay.com", className = "test.com.uleehub.webservice.soap.pay.Pay")
    @ResponseWrapper(localName = "payResponse", targetNamespace = "http://uleehub.pay.com", className = "test.com.uleehub.webservice.soap.pay.PayResponse")
    public Response<PayResponse> payAsync(
        @WebParam(name = "out_trade_no", targetNamespace = "")
        String outTradeNo,
        @WebParam(name = "subject", targetNamespace = "")
        String subject,
        @WebParam(name = "total_fee", targetNamespace = "")
        String totalFee,
        @WebParam(name = "body", targetNamespace = "")
        String body,
        @WebParam(name = "show_url", targetNamespace = "")
        String showUrl,
        @WebParam(name = "exter_invoke_ip", targetNamespace = "")
        String exterInvokeIp);

    /**
     * 
     * @param showUrl
     * @param body
     * @param totalFee
     * @param exterInvokeIp
     * @param subject
     * @param asyncHandler
     * @param outTradeNo
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod(operationName = "pay")
    @RequestWrapper(localName = "pay", targetNamespace = "http://uleehub.pay.com", className = "test.com.uleehub.webservice.soap.pay.Pay")
    @ResponseWrapper(localName = "payResponse", targetNamespace = "http://uleehub.pay.com", className = "test.com.uleehub.webservice.soap.pay.PayResponse")
    public Future<?> payAsync(
        @WebParam(name = "out_trade_no", targetNamespace = "")
        String outTradeNo,
        @WebParam(name = "subject", targetNamespace = "")
        String subject,
        @WebParam(name = "total_fee", targetNamespace = "")
        String totalFee,
        @WebParam(name = "body", targetNamespace = "")
        String body,
        @WebParam(name = "show_url", targetNamespace = "")
        String showUrl,
        @WebParam(name = "exter_invoke_ip", targetNamespace = "")
        String exterInvokeIp,
        @WebParam(name = "asyncHandler", targetNamespace = "")
        AsyncHandler<PayResponse> asyncHandler);

    /**
     * 
     * @param showUrl
     * @param body
     * @param totalFee
     * @param exterInvokeIp
     * @param subject
     * @param outTradeNo
     * @return
     *     returns test.com.uleehub.webservice.soap.pay.GetUserResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "pay", targetNamespace = "http://uleehub.pay.com", className = "test.com.uleehub.webservice.soap.pay.Pay")
    @ResponseWrapper(localName = "payResponse", targetNamespace = "http://uleehub.pay.com", className = "test.com.uleehub.webservice.soap.pay.PayResponse")
    public GetUserResult pay(
        @WebParam(name = "out_trade_no", targetNamespace = "")
        String outTradeNo,
        @WebParam(name = "subject", targetNamespace = "")
        String subject,
        @WebParam(name = "total_fee", targetNamespace = "")
        String totalFee,
        @WebParam(name = "body", targetNamespace = "")
        String body,
        @WebParam(name = "show_url", targetNamespace = "")
        String showUrl,
        @WebParam(name = "exter_invoke_ip", targetNamespace = "")
        String exterInvokeIp);

    /**
     * 
     * @param user
     * @return
     *     returns javax.xml.ws.Response<test.com.uleehub.webservice.soap.pay.PayByDTOResponse>
     */
    @WebMethod(operationName = "payByDTO")
    @RequestWrapper(localName = "payByDTO", targetNamespace = "http://uleehub.pay.com", className = "test.com.uleehub.webservice.soap.pay.PayByDTO")
    @ResponseWrapper(localName = "payByDTOResponse", targetNamespace = "http://uleehub.pay.com", className = "test.com.uleehub.webservice.soap.pay.PayByDTOResponse")
    public Response<PayByDTOResponse> payByDTOAsync(
        @WebParam(name = "user", targetNamespace = "")
        TradeInfo user);

    /**
     * 
     * @param asyncHandler
     * @param user
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod(operationName = "payByDTO")
    @RequestWrapper(localName = "payByDTO", targetNamespace = "http://uleehub.pay.com", className = "test.com.uleehub.webservice.soap.pay.PayByDTO")
    @ResponseWrapper(localName = "payByDTOResponse", targetNamespace = "http://uleehub.pay.com", className = "test.com.uleehub.webservice.soap.pay.PayByDTOResponse")
    public Future<?> payByDTOAsync(
        @WebParam(name = "user", targetNamespace = "")
        TradeInfo user,
        @WebParam(name = "asyncHandler", targetNamespace = "")
        AsyncHandler<PayByDTOResponse> asyncHandler);

    /**
     * 
     * @param user
     * @return
     *     returns test.com.uleehub.webservice.soap.pay.GetUserResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "payByDTO", targetNamespace = "http://uleehub.pay.com", className = "test.com.uleehub.webservice.soap.pay.PayByDTO")
    @ResponseWrapper(localName = "payByDTOResponse", targetNamespace = "http://uleehub.pay.com", className = "test.com.uleehub.webservice.soap.pay.PayByDTOResponse")
    public GetUserResult payByDTO(
        @WebParam(name = "user", targetNamespace = "")
        TradeInfo user);

}