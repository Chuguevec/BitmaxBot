package dao;

import model.order.Order;

import java.util.HashMap;

public class OrderPool {

    private final HashMap<String, Order> longOrders;
    private final HashMap<String, Order> shortOrders;

    public OrderPool() {
        longOrders = new HashMap<>();
        shortOrders = new HashMap<>();
    }

    public void addOrder(Order order) {
        String side = order.getSide();
        switch (side) {
            case "Sell":
                addShortOrder(order);
            case "Buy":
                addLongOrder(order);
        }
        longOrders.put(order.getOrderID(), order);
    }

    private void addLongOrder(Order order) {
        longOrders.put(order.getOrderID(), order);
    }

    private void addShortOrder(Order order) {
        shortOrders.put(order.getOrderID(), order);
    }

    public Order getLongOrders(String orderID) {
        return longOrders.get(orderID);
    }

    public Order getShortOrders(String orderID) {
        return shortOrders.get(orderID);
    }

    public Order getOrder(String orderID){
        if (longOrders.containsKey(orderID)){
            return longOrders.get(orderID);
        } else if (shortOrders.containsKey(orderID)) {
            return shortOrders.get(orderID);
        }
        return null;
    }

    public void removeOrder(String orderID) {
        longOrders.remove(orderID);
        shortOrders.remove(orderID);
    }
}
