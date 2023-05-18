package CSX.MatchingEngine.app;

import CSX.MatchingEngine.app.service.MessageService;
import CSX.MatchingEngine.app.websocket.etc.IpHandshakeInterceptor;
import CSX.MatchingEngine.app.websocket.etc.RawSocketHandler;
import CSX.MatchingEngine.app.websocket.send.QuotationDataSending;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@SpringBootApplication
public class MatchingEngineApplication {


    private static final int TCP_PORT = 5000;

    public static void main(String[] args) {
        IpHandshakeInterceptor ipHandshakeInterceptor = new IpHandshakeInterceptor();

        RawSocketHandler rawSocketHandler = new RawSocketHandler(ipHandshakeInterceptor);

        QuotationDataSending quotationDataSending = new QuotationDataSending();

        SpringApplication.run(MatchingEngineApplication.class, args);

        BaseResponse response = new BaseResponse();
        HashMap<String, List<Order>> hm = new HashMap<>();

        MessageService messageService = new MessageService();

        String tcpString = "";
//        quotationDataSending.sending(rawSocketHandler, "testeteets");

        try {
            messageService.start(TCP_PORT);
            while (true) {
                // System.out.println("Great! Get Message Successfully\uD83E\uDD24");
                tcpString = messageService.getMsg();
                System.out.println("");
                System.out.println("Websocket Message: " + tcpString);
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

                String key = order.issueCode + order.orderUV + order.orderType;
                String key_buy = order.issueCode + order.orderUV + "1";
                String key_sell = order.issueCode + order.orderUV + "2";

                int total_qty = 0;

                if (hm.get(key) == null) {
                    total_qty = order.orderQty;
                    if (hm.get(key_buy) != null) {
                        for (Order ord : hm.get(key_buy)) {
                            total_qty = ord.orderQty - total_qty;
//
                        }
                        System.out.println("QTY BUY: " + total_qty);

                    } else if (hm.get(key_sell) != null) {
                        for (Order ord : hm.get(key_sell)) {
                            total_qty = ord.orderQty + total_qty;
                        }
                        System.out.println("QTY SELL: " + total_qty);
                    } else {
                        orderList.add(order);
                        hm.put(key, orderList);
                    }

                } else {
                    orderList = hm.get(key);
                    orderList.add(order);
                    for (Order ord : hm.get(key)) {
                        total_qty += ord.orderQty;
                    }
                    hm.put(key, orderList);
                }



//                quotationDataSending.sending(rawSocketHandler,messageService);
//Test String

//                System.out.println("issueCode: " + hm.get(key).get(0).issueCode);
//                System.out.println("orderType: " + hm.get(key).get(0).orderType);
//                System.out.println("brokerId : " + hm.get(key).get(0).brokerId);
//                System.out.println("accountNo: " + hm.get(key).get(0).accountNo);
//                System.out.println("orderQty : " + hm.get(key).get(0).orderQty);
//                System.out.println("orderUV : " + hm.get(key).get(0).orderUV);
//                System.out.println("orderDate: " + hm.get(key).get(0).orderDate);

                System.out.println("Initial list of elements: " + hm);
                System.out.println("Total Order quantity: " + total_qty);
                System.out.println(" ");

            }
        } catch (Exception e) {
            System.out.println(e);
        }
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
