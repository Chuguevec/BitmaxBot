package controller.servlet;

import controller.algo.BitmexAlgo;
import model.Bot;
import concurrent.ConcurrentList;
import model.order.Symbol;
import service.validator.BitmexValidator;
import service.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "InitServlet", value = "/init")
public class InitServlet extends HttpServlet {
    private final ConcurrentList concurrentList = new ConcurrentList();
    private final List<Bot> botList = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("botList", botList);
        getServletContext().setAttribute("concurrentList", concurrentList);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Создание новой сессии
        HttpSession currentSession = req.getSession(true);

        //Получаем ApiKey и ApiSecret
        String apiKey = req.getParameter("apiKey");
        String apiSec = req.getParameter("apiSec");
        Symbol symbol = Symbol.valueOf(req.getParameter("symbol"));
        float step = Float.parseFloat(req.getParameter("step"));
        float coefficient = Float.parseFloat(req.getParameter("coefficient"));
        int lvl = Integer.parseInt(req.getParameter("lvl"));

        //Создание Бота
        Bot bot = new Bot(apiKey, apiSec, step, coefficient, lvl, symbol);
        botList.add(bot);


        //Проверяем валидность ключей
        Validator validator = new BitmexValidator();
        boolean isValid = validator.validateApiKeyApiSec(bot);

        // Если все ок, создаем объект алгоритма и передаем его в отдельный поток
        //Далее перенаправляем на страницу бот
        if (isValid) {
            BitmexAlgo bitmexAlgo = new BitmexAlgo(bot);
            concurrentList.addAlgo(bitmexAlgo);
            getServletContext().getRequestDispatcher("/bot.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/bot.jsp").forward(req, resp);
        }

    }
}
