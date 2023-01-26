package service.client;

import model.Bot;
import model.order.Order;
import model.order.Symbol;
import org.json.JSONArray;
import org.json.JSONObject;
import service.signature.Signature;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;

public class BitmexClient {
    private final HttpClient client;
    private final Bot bot;

    public BitmexClient(Bot bot) {
        client = HttpClient.newHttpClient();
        this.bot = bot;
    }

    //Выставляет ордер и возвращает полученый ID
    public String sendOrderReturnOrderID(Order order) {
        String baseUrl = "https://testnet.bitmex.com";
        String verb = "POST";
        String path = "/api/v1/order";
        long expires = Instant.now().getEpochSecond() + 100;
        String signature = new Signature().getSignature(bot.getApiSec(), verb, path, expires, order.toString());
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(order.toString()))
                .header("api-expires", String.valueOf(expires))
                .header("api-key", bot.getApiKey())
                .header("api-signature", signature)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Accept", "application/json")
                .uri(URI.create(baseUrl + path))
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        JSONObject jsonObject = new JSONObject(response.body());
        return jsonObject.getString("orderID");
    }

    public Double getCurrentPrice(Symbol symbol) {
        String baseUrl = "https://testnet.bitmex.com";
        String path = "/api/v1/orderBook/L2";
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(baseUrl + path + "?symbol=" + symbol + "&depth=1"))
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        JSONArray jsonArray = new JSONArray(response.body());
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        return Double.valueOf(String.valueOf(jsonObject.get("price")));
    }
}
