package webSocket;

import controller.algo.Algo;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;

public class WebSocket extends WebSocketClient {
    private Algo algo;

    public WebSocket(URI serverUri, Algo algo) {
        super(serverUri);
        this.algo = algo;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("opened connection");
    }

    @Override
    public void onMessage(String message) {
        JSONObject jsonObject = new JSONObject(message);
        if (jsonObject.has("data")) {
            System.out.println(message);
            JSONArray jsonArray = (JSONArray) jsonObject.get("data");
            if (!jsonArray.isEmpty()) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(0);
                algo.checkOrder(jsonObject2);
            }
        } else {
            System.out.println("received: " + message);
        }

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }
}
