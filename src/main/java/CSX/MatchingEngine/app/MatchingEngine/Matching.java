package CSX.MatchingEngine.app.MatchingEngine;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * This class implements a matching engine broker system.
 * It provides methods to add and remove orders, and to match orders.
 */
public class Matching {
    private static final Logger LOGGER = Logger.getLogger(Matching.class.getName());
    private Map<String, Order> orders;

    public Matching() {
        this.orders = new HashMap<>();
    }

    /**
     * Adds an order to the system.
     *
     * @param order the order to add
     * @return true if the order was added successfully, false otherwise
     */
    public boolean addOrder(Order order) {
        if (order == null) {
            LOGGER.warning("Order is null, cannot add to system");
            return false;
        }
        if (orders.containsKey(order.getId())) {
            LOGGER.warning("Order with id " + order.getId() + " already exists in system, cannot add");
            return false;
        }
        orders.put(order.getId(), order);
        LOGGER.info("Order with id " + order.getId() + " added to system");
        return true;
    }

    /**
     * Removes an order from the system.
     *
     * @param orderId the id of the order to remove
     * @return true if the order was removed successfully, false otherwise
     */
    public boolean removeOrder(String orderId) {
        if (orderId == null) {
            LOGGER.warning("Order id is null, cannot remove from system");
            return false;
        }
        if (!orders.containsKey(orderId)) {
            LOGGER.warning("Order with id " + orderId + " does not exist in system, cannot remove");
            return false;
        }
        orders.remove(orderId);
        LOGGER.info("Order with id " + orderId + " removed from system");
        return true;
    }

    /**
     * Matches orders in the system.
     *
     * @return true if the orders were matched successfully, false otherwise
     */
    public boolean matchOrders() {
        if (orders.size() < 2) {
            LOGGER.warning("Cannot match orders, not enough orders in system");
            return false;
        }
        for (Order order1 : orders.values()) {
            for (Order order2 : orders.values()) {
                if (order1.getId().equals(order2.getId())) {
                    continue;
                }
                if (order1.getPrice() == order2.getPrice()) {
                    int quantity = Math.min(order1.getQuantity(), order2.getQuantity());
                    order1.setQuantity(order1.getQuantity() - quantity);
                    order2.setQuantity(order2.getQuantity() - quantity);
                    LOGGER.info("Matched orders " + order1.getId() + " and " + order2.getId() + " with quantity " + quantity);
                }
            }
        }
        return true;
    }
}