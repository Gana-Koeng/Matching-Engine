package CSX.MatchingEngine.app.map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StoreOrder {
    public static void main(String[] args) {
        HashMap<String, List<Order>> map = new HashMap<>();

        // Creating orders
        Order order1 = new Order("KH0001", "0002", 200);
        Order order2 = new Order("KH0002", "0001", 300);
        Order order3 = new Order("KH0003", "0002", 100);
        Order order4 = new Order("KH0004", "005",  500);

        // Adding orders to the map
        List<Order> orders1 = new ArrayList<>();
        orders1.add(order1);
        orders1.add(order2);
        map.put("Sokhai", orders1);

        List<Order> orders2 = new ArrayList<>();
        orders2.add(order3);
        map.put("Gana", orders2);

        // Accessing orders using keys
        List<Order> sokhaiOrders = map.get("Sokhai");
        List<Order> ganaOrders = map.get("Gana");

        System.out.println("Sokhai's Orders:");
        for (Order order : sokhaiOrders) {
            System.out.println(order.toString());
        }

        System.out.println("Gana's Orders:");
        for (Order order : ganaOrders) {
            System.out.println(order.toString());
        }
    }
}

class Order {
    private String issueCode;
    private String type;
    private int quantity;

    public Order(String issueCode, String type, int quantity) {
        this.issueCode = issueCode;
        this.type = type;
        this.quantity = quantity;
    }

    public String toString() {
        return   issueCode + type + quantity;
    }
}