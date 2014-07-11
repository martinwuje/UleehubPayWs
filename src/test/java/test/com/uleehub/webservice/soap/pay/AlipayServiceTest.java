package test.com.uleehub.webservice.soap.pay;

import java.util.concurrent.ExecutionException;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

/*
 *@description
 *
 *@author mtwu
 */
public class AlipayServiceTest {
	public static void main(String[] args) throws Exception {
		new AlipayServiceTest().testAsync1();
	}
	public void testAsync2() throws Exception {
		AliPayService_Service service = new AliPayService_Service();
		AliPayService port = service.getAliPayServiceImplPort();
		TradeInfo tradeInfo = new TradeInfo();
		tradeInfo.setOutTradeNo("123456");
		tradeInfo.setSubject("test");
		tradeInfo.setTotalFee("200");
		tradeInfo.setShowUrl("http://163.com/detail/1");
		port.payAsync("123456", "test", "200", "test", "http://163.com/detail/1", "127.0.0.01",  new AsyncHandler<PayResponse>() {
			public void handleResponse(Response<PayResponse> res) {
				try {
					PayResponse response = null;
					response = res.get();
					GetUserResult rs = response.getReturn();
					String message = rs.getSHtmlText();
					System.out.println("mssage: "+message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	public void testAsync1() throws Exception {
		AliPayService_Service service = new AliPayService_Service();
		// service.setHandlerResolver(new HandlerResolver() {
		// public List<Handler> getHandlerChain(PortInfo arg0) {
		// List<Handler> handlerList = new ArrayList<Handler>();
		// handlerList.add(new ClientAuthenticationHandler());
		// return handlerList;
		// }
		// });

		AliPayService port = service.getAliPayServiceImplPort();
		Response<PayResponse> response = port.payAsync("123456", "test", "200", "test", "http://163.com/detail/1", "127.0.0.01");
		while (!response.isDone()) {
			System.out.println("is not done");
			Thread.sleep(1000);
		}
		PayResponse payResponse;
		try {
			payResponse = response.get();
			GetUserResult result = payResponse.getReturn();
			System.out.println("\nrtn msg=" + result.getCode() + " " + result.getSHtmlText());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
