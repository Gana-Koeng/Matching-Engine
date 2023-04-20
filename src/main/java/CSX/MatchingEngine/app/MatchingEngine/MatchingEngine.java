//package CSX.MatchingEngine.app.MatchingEngine;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
//public class MatchingEngine {
//
//    // List of orders
//    private List<Order> orders;
//
//    /**
//     * Constructor for MatchingEngine
//     */
//    public MatchingEngine() {
//        orders = new ArrayList<>();
//    }
//
//    /**
//     * Adds an order to the list of orders
//     *
//     * @param order the order to be added
//     */
//    public void addOrder(Order order) {
//        orders.add(order);
//    }
//
//    /**
//     * Removes an order from the list of orders
//     *
//     * @param order the order to be removed
//     */
//    public void removeOrder(Order order) {
//        orders.remove(order);
//    }
//
//    /**
//     * Matches orders in the list of orders
//     */
//    public void matchOrders() {
//        // Sort orders by price
//        Collections.sort(orders, new Comparator<Order>() {
//            @Override
//            public int compare(Order o1, Order o2) {
//                return o1.getPrice() - o2.getPrice();
//            }
//        });
//
//        // Iterate through orders and match them
//        for (int i = 0; i < orders.size(); i++) {
//            Order order = orders.get(i);
//            if (order.getType() == OrderType.BUY) {
//                for (int j = i + 1; j < orders.size(); j++) {
//                    Order otherOrder = orders.get(j);
//                    if (otherOrder.getType() == OrderType.SELL && order.getPrice() >= otherOrder.getPrice()) {
//                        // Match orders
//                        int quantity = Math.min(order.getQuantity(), otherOrder.getQuantity());
//                        order.setQuantity(order.getQuantity() - quantity);
//                        otherOrder.setQuantity(otherOrder.getQuantity() - quantity);
//
//                        // Remove orders if quantity is 0
//                        if (order.getQuantity() == 0) {
//                            orders.remove(order);
//                        }
//                        if (otherOrder.getQuantity() == 0) {
//                            orders.remove(otherOrder);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * Order class represents an order
//     */
//    private static class Order {
//        private OrderType type;
//        private int price;
//        private int quantity;
//
//        /**
//         * Constructor for Order
//         *
//         * @param type the type of the order
//         * @param price the price of the order
//         * @param quantity the quantity of the order
//         */
//        public Order(OrderType type, int price, int quantity) {
//            this.type = type;
//            this.price = price;
//            this.quantity = quantity;
//        }
//
//        /**
//         * Gets the type of the order
//         *
//         * @return the type of the order
//         */
//        public OrderType getType() {
//            return type;
//        }
//
//        /**
//         * Gets the price of the order
//         *
//         * @return the price of the order
//         */
//        public int getPrice() {
//            return price;
//        }
//
//        /**
//         * Gets the quantity of the order
//         *
//         * @return the quantity of the order
//         */
//        public int getQuantity() {
//            return quantity;
//        }
//
//        /**
//         * Sets the quantity of the order
//         *
//         * @param quantity the quantity of the order
//         */
//        public void setQuantity(int quantity) {
//            this.quantity = quantity;
//        }
//    }
//
//    /**
//     * Enum for order type
//     */
//    private enum OrderType {
//        BUY, SELL
//    }
//}