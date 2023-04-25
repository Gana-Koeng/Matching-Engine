package CSX.MatchingEngine.app;

import CSX.MatchingEngine.app.service.MessageService;
import CSX.MatchingEngine.app.websocket.etc.IpHandshakeInterceptor;
import CSX.MatchingEngine.app.websocket.etc.RawSocketHandler;
import CSX.MatchingEngine.app.websocket.send.QuotationDataSending;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;


public class MatchingEngineApplication {


	private static final int TCP_PORT = 5000;
	public static void main(String[] args) {


		BaseResponse response = new BaseResponse();
		HashMap<String, List<Order>> hm = new HashMap<>();

		MessageService messageService = new MessageService();


		String tcpString = "";
        while (true) {
            try {

                messageService.start(TCP_PORT);
                System.out.println("msg server start");
                tcpString = messageService.getMsg();
                System.out.println(tcpString);

            } catch (Exception e) {
                messageService.stop();
            }
        }
//		quotationDataSending.sending(rawSocketHandler);

	}


	class Order {
		String issueCode;
		String accNo;
		String orderType;
		int orderPrice;
	}

}
