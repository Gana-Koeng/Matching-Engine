package CSX.MatchingEngine.app;

import CSX.MatchingEngine.app.map.TestHashMap;
import CSX.MatchingEngine.app.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MatchingEngineApplication {


	private static final int TCP_PORT = 5000;
	public static void main(String[] args) {


		BaseResponse response = new BaseResponse();
		HashMap<String, List<Order>> hm = new HashMap<>();
		MessageService messageService = new MessageService();
		String tcpString = "";
		try {
				messageService.start(TCP_PORT);
			while (true){
//				System.out.println("msg server start");
				tcpString = messageService.getMsg();
				System.out.println(tcpString);

				List<Order> orderList = new ArrayList<>();


					Order order = new Order();
					order.issueCode  = tcpString.substring(2, 12);
//					order.orderPrice = Integer.parseInt(tcpString.substring(0, ));
					order.orderType  = tcpString.substring(12, 13);
//					order.orderPrice = 5000;
					order.accNo = "00100";

					String key = order.issueCode ;

					if (hm.get(key) == null) {
						orderList.add(order);
					} else {
						orderList = hm.get(key);
						orderList.add(order);
					}

					hm.put(key, orderList);


//				quotationDataSending.sending(rawSocketHandler);


				System.out.println("Initial list of elements: " + hm);
				System.out.println("Issue Code: " + hm.get(key).get(0).issueCode);
				System.out.println("Type: " + hm.get(key).get(0).orderType);
			}
		} catch (Exception e) {
			messageService.stop();
		}
	}
	static class Order {
		String issueCode;
		String accNo;
		String orderType;
		int orderPrice;
	}
}
