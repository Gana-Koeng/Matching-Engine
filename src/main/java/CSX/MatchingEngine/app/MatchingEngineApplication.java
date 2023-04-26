package CSX.MatchingEngine.app;

import CSX.MatchingEngine.app.service.MessageService;

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
            while (true) {
                // System.out.println("Great! Get Message Successfully\uD83E\uDD24");
                tcpString = messageService.getMsg();
                System.out.println("Message: " + tcpString);
                int i = 0;
                List<Order> orderList = new ArrayList<>();


                Order order = new Order();
                //Sub String From Message Get from TCP broker
                order.issueCode = tcpString.substring(2, 12);
                order.orderType = Integer.parseInt(tcpString.substring(12, 13));
                order.brokerId = tcpString.substring(13, 16);
                order.accountNo = tcpString.substring(16, 22);
                order.orderQty = Integer.parseInt(tcpString.substring(32, 42));
                order.orderUV = Integer.parseInt(tcpString.substring(42, 52));
                order.orderDate = Integer.parseInt(tcpString.substring(52, 60));

                String key = order.issueCode + order.orderType + order.orderUV + order.accountNo;

                if (hm.get(key) == null) {
                    orderList.add(order);
                } else {
                    orderList = hm.get(key);
                    orderList.add(order);
                }

                hm.put(key, orderList);


//                    quotationDataSending.sending(rawSocketHandler);
//Test String
//                System.out.println("issueCode: " + hm.get(key).get(0).issueCode);
//                System.out.println("orderType: " + hm.get(key).get(0).orderType);
//                System.out.println("brokerId : " + hm.get(key).get(0).brokerId);
//                System.out.println("accountNo: " + hm.get(key).get(0).accountNo);
//                System.out.println("orderQty : " + hm.get(key).get(0).orderQty);
//                System.out.println("orderUV : " + hm.get(key).get(0).orderUV);
//                System.out.println("orderDate: " + hm.get(key).get(0).orderDate);

                System.out.println("Initial list of elements: " + hm);
//                    return response;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

//		quotationDataSending.sending(rawSocketHandler);


    }


    static class Order {
        String issueCode;
        String accountNo;
        int orderType;
        String brokerId;
        int orderUV;
        int orderQty;
        int orderDate;


    }

}
