package CSX.MatchingEngine.app.map;

import CSX.MatchingEngine.app.BaseResponse;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class TestHashMap {

    final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public TestHashMap(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @GetMapping(value = {"api/v1/test1/hashmap"})
    BaseResponse TestHashMap() {
        BaseResponse response = new BaseResponse();

        HashMap<String,List<Order>> hm= new HashMap<>();

        int i =  0;
        List<Order> orderList = new ArrayList<>();

        while(i < 3){
            Order order = new Order();
            order.issueCode = "KH0001";
            order.orderType = "2";
            order.orderPrice = 5000;
            order.accNo = "00100" + i;

            String key = order.issueCode + order.orderType + order.orderPrice + order.accNo;

            if(hm.get(key) == null){
                orderList.add(order);
            }else {
                orderList = hm.get(key);
                orderList.add(order);
            }

            hm.put(key, orderList);
            i++;
        }

        System.out.println("Initial list of elements: "+hm);

        return response;
    }


    class Order{
        String issueCode;
        String accNo;
        String orderType;
        int orderPrice;
    }
}
