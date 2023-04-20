//package CSX.MatchingEngine.app.execution;
//
//import org.springframework.core.annotation.Order;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.logging.Logger;
//
///**
// * This class provides a method for executing orders in a matching engine.
// */
//public class Execution{
//    private static final Logger LOGGER = Logger.getLogger(Execution.class.getName());
//
//    /**
//     * Executes orders in a matching engine.
//     *
//     * @param orders The list of orders to be executed.
//     * @return The list of executed orders.
//     */
//    public List<Order> executeOrders(List<Order> orders) {
//        if (orders == null || orders.isEmpty()) {
//            LOGGER.warning("No orders to execute.");
//            return Collections.emptyList();
//        }
//
//        List<Order> executedOrders = new ArrayList<>();
//        for (Order order : orders) {
//            if (order.isExecutable()) {
//                order.execute();
//                executedOrders.add(order);
//            }
//        }
//
//        return executedOrders;
//    }
//}