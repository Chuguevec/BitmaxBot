package service;

import dao.OrderPool;
import model.Bot;
import model.order.Order;
import model.order.OrderType;
import model.order.Side;
import org.json.JSONObject;
import service.client.BitmexClient;

public class OrderService {
    private BitmexClient client;
    private OrderPool orderPool;
    private Bot bot;

    public OrderService(Bot bot) {
        this.bot = bot;
        client = new BitmexClient(bot);
        orderPool = new OrderPool();
    }

    //создаем, выставляем Ордера и добавляем в пулл ордеров.
    public void createAndSendOrders(double currentPrice) {
        float step = bot.getStep();
        for (int i = 0; i < bot.getLvl(); i++) {
            Order order = new Order(bot.getSymbol().toString()
                    , Side.BUY.toString()
                    , bot.getCoefficient()
                    , currentPrice - step
                    , OrderType.LIMIT.toString());
            step += step;
            String orderID = client.sendOrderReturnOrderID(order);
            order.setOrderID(orderID);
            orderPool.addOrder(order);
        }
    }

    //вызывается из WEbSocket при получении сообщения
    //Если ордер был исполнен, перевыставляет согласно условию
    public void checkOrder(JSONObject jsonObject) {
        System.out.println(jsonObject);
        if (jsonObject.has("ordStatus")) { // так можно?
            String orderStatus = jsonObject.getString("ordStatus"); // так можно?
            if (orderStatus.equals("Filled")) { // так можно?
                String filledOrderID = jsonObject.get("orderID").toString(); // так можно?
                Order order = orderPool.getOrder(filledOrderID);
                if (order != null) {
                    orderPool.removeOrder(filledOrderID);
                    Order newOrder = createOrderFromAnother(order);
                    String orderID = client.sendOrderReturnOrderID(newOrder);
                    newOrder.setOrderID(orderID);
                    orderPool.addOrder(newOrder);
                }
            }
        }
    }

    //Создает ордер с противоположным Side с соотвествующей ценой
    private Order createOrderFromAnother(Order order) {
        String symbol = order.getSymbol();
        String side;
        double orderQty = order.getOrderQty();
        double price;
        String ordType = order.getOrdType();
        if (order.getSide().equals(Side.SELL.toString())) {
            side = Side.BUY.toString();
            price = order.getPrice() - bot.getStep();
        } else {
            side = Side.SELL.toString();
            price = order.getPrice() + bot.getStep();
        }
        return new Order(symbol, side, orderQty, price, ordType);
    }
}
