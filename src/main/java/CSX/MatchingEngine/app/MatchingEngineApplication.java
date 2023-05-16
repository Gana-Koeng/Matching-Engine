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
//        IpHandshakeInterceptor ipHandshakeInterceptor = new IpHandshakeInterceptor();
//
//        RawSocketHandler rawSocketHandler = new RawSocketHandler(ipHandshakeInterceptor);
//
//        QuotationDataSending quotationDataSending = new QuotationDataSending();

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

                String key = order.issueCode + order.orderUV + order.orderType;

                //make orderType : 1 = buy

                String key_buy = order.issueCode + order.orderUV + 1 ;

                //make orderType : 2 = sell

                String key_sell = order.issueCode + order.orderUV + 2 ;

                //make condition matching order with best price

                int total_qty  = 0;
                if (hm.get(key) == null) {
                    if (hm.get(key_buy) != null){
                        System.out.println("Null of Buy" + key_buy);
                    }
                    if (hm.get(key_sell) != null){
                        System.out.println("Null of Sell" + key_sell);
                    }
                    }
                    if (hm.get(key_buy) == null || hm.get(key_sell) == null)
                    {
                        orderList.add(order);
                        total_qty = order.orderQty;
                    }
                if (hm.get(key) != null)
                    {
                        orderList = hm.get(key);
                        orderList.add(order);
                        for (Order ord : hm.get(key))
                        {
                            total_qty += ord.orderQty;
                            if(ord.orderType != order.orderType)
                            {
                                total_qty -= ord.orderQty;
                            }
                        }
                    }
                    hm.put(key, orderList);
                //
                //
                //
                //
                //
                //
//                quotationDataSending.sending(rawSocketHandler,messageService);
//Test String
                System.out.println("issueCode: " + hm.get(key).get(0).issueCode);
                System.out.println("Initial list of elements: " + hm );
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
