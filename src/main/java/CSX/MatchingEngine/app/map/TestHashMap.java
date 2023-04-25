package CSX.MatchingEngine.app.map;

import CSX.MatchingEngine.app.BaseResponse;
import CSX.MatchingEngine.app.service.MessageService;
import CSX.MatchingEngine.app.websocket.etc.RawSocketHandler;
import CSX.MatchingEngine.app.websocket.send.QuotationDataSending;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class TestHashMap {

    final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    final QuotationDataSending quotationDataSending;
    final RawSocketHandler rawSocketHandler;
    final MessageService messageService;
    private static final int TCP_PORT = 5000;

    public TestHashMap(NamedParameterJdbcTemplate namedParameterJdbcTemplate, QuotationDataSending quotationDataSending, RawSocketHandler rawSocketHandler, MessageService messageService) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.quotationDataSending = quotationDataSending;
        this.rawSocketHandler = rawSocketHandler;
        this.messageService = messageService;
    }

    @GetMapping(value = {"api/v1/test1/hashmap"})
    BaseResponse TestHashMap() {
        BaseResponse response = new BaseResponse();
        HashMap<String, List<Order>> hm = new HashMap<>();

//        String tcpString = "";
//        try {
//            messageService.start(TCP_PORT);
//            System.out.println("msg server start");
//
//            System.out.println(messageService);
//            tcpString = messageService.getMsg();
//            System.out.println(tcpString);
//        } catch (Exception e) {
//            messageService.stop();
//        }

//        int i = 0;
//        List<Order> orderList = new ArrayList<>();
//
//        while (i < 3) {
//            Order order = new Order();
//            order.issueCode = tcpString.substring(0, 5);
////            order.issueCode = "KH0001";
//            order.orderType = "2";
//            order.orderPrice = 5000;
//            order.accNo = "00100" + i;
//
//            String key = order.issueCode + order.orderType + order.orderPrice + order.accNo;
//
//            if (hm.get(key) == null) {
//                orderList.add(order);
//            } else {
//                orderList = hm.get(key);
//                orderList.add(order);
//            }
//
//            hm.put(key, orderList);
//            i++;
//        }
//
//        quotationDataSending.sending(rawSocketHandler);
//
//
//        System.out.println("Initial list of elements: " + hm);
        return response;


    }

    class Order {
        String issueCode;
        String accNo;
        String orderType;
        int orderPrice;
    }
}
