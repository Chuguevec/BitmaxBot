package service.validator;

import model.Bot;
import service.signature.Signature;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;

public class BitmexValidator implements Validator {
    private final HttpClient client;

    public BitmexValidator() {
        client = HttpClient.newHttpClient();
    }

    @Override
    public boolean validateApiKeyApiSec(Bot bot) {
        String baseUrl = "https://testnet.bitmex.com";  //уберу
        String verb = "GET";                            //уберу или нет?
        String path = "/api/v1/user";                   //уберу
        long expires = Instant.now().getEpochSecond() + 100;
        String data = "";
        String signature = new Signature().getSignature(bot.getApiSec(), verb, path, expires, data);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .header("api-expires", String.valueOf(expires))
                .header("api-key", bot.getApiKey())
                .header("api-signature", signature)
                .uri(URI.create(baseUrl + path))
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.statusCode() == 200;
    }
}
