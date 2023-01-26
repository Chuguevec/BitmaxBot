import controller.algo.BitmexAlgo;
import model.Bot;

import model.order.Symbol;
import service.validator.BitmexValidator;

public class App {
    public static void main(String[] args) {

        Bot bot = new Bot("gEKIxScgwg1u7UcubZVuWhFg", "wvlOqch4QQ4nMxxLPtwt7HoSb67SrYM7A0IwS1mxRIIVeZqq",25, 100, 2, Symbol.XBTUSD);
        BitmexAlgo bitmexAlgo = new BitmexAlgo(bot);
        BitmexValidator validator = new BitmexValidator();
        if (validator.validateApiKeyApiSec(bot)){
            System.out.println("Ключи валидны!");
            System.out.println("Start bot...");
            bitmexAlgo.run();
        }

    }
}
