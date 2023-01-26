package controller.algo;

import model.Bot;
import org.json.JSONObject;

public interface Algo extends Runnable{
    void run();

    void connectToWebSocket();

    void checkOrder(JSONObject jsonObject);

    void stop();

    Bot getBot();
}
