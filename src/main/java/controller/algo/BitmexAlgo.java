package controller.algo;

import model.Bot;
import service.OrderService;
import service.builder.BitmexMessageBuilder;
import dao.OrderPool;
import model.Message;
import org.json.JSONObject;
import service.client.BitmexClient;
import service.signature.Signature;
import url.URL;
import webSocket.WebSocket;

import java.net.URI;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BitmexAlgo implements Algo {
    private BitmexClient client;
    private OrderPool orderPool;
    private Bot bot;
    private WebSocket webSocket;
    private OrderService orderService;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private boolean isRun;

    public BitmexAlgo(Bot bot) {
        isRun = true;
        this.bot = bot;
        client = new BitmexClient(bot);
        orderPool = new OrderPool();
        webSocket = new WebSocket(URI.create(URL.WEBSOCKET_URL), this);
        orderService = new OrderService(bot);
    }

    @Override
    public void run() {
        //получаем текущую цену
        double currentPriceSymbol = client.getCurrentPrice(bot.getSymbol());
        System.out.println("Текущая цена " + bot.getSymbol() + ": " +currentPriceSymbol);

        //Запускаем webSocket
        connectToWebSocket();

        //выставляем ордера
        orderService.createAndSendOrders(currentPriceSymbol);

        //Запускаем реконект для вебсокет
        checkConnection();
    }



    public void connectToWebSocket() {
        webSocket.connect();
        while (!webSocket.isOpen()) {
        }
        long expires = Instant.now().getEpochSecond() + 100;
        String signature = new Signature().getSignature(bot.getApiSec(), "GET", "/realtime", expires, ""); //Можно verb и path вынести в статик?
        Message message1 = new BitmexMessageBuilder()
                .addOp("authKeyExpires") // так можно?
                .addArgs(bot.getApiKey())
                .addArgs(expires)
                .addArgs(signature)
                .build();
        webSocket.send(message1.toString());
        Message message2 = new BitmexMessageBuilder()
                .addOp("subscribe") // так можно?
                .addArgs("order") // так можно?
                .build();
        webSocket.send(message2.toString());
    }

    @Override
    public void checkOrder(JSONObject jsonObject) {
        orderService.checkOrder(jsonObject);
    }


    @Override
    public void stop() {
        webSocket.close();
        executorService.shutdown();
        isRun = false;
    }


    public Bot getBot() {
        return bot;
    }

    // не понятно работает ли этот метод
    private void checkConnection() {
        executorService.execute(() -> {
            while (isRun) {
                if (!webSocket.isOpen()) {
                    webSocket.reconnect();
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }
}
