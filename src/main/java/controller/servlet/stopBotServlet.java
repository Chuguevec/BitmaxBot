package controller.servlet;

import concurrent.ConcurrentList;
import controller.algo.Algo;
import model.Bot;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "stopBotServlet", value = "/stop")
public class stopBotServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConcurrentList concurrentList = (ConcurrentList) getServletContext().getAttribute("concurrentList");
        List<Bot> botList = (List<Bot>) getServletContext().getAttribute("botList");

        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("Удаляем бота с id: " + id);

        Algo algo = null;
        for (Algo algo1 : concurrentList.getAlgoList()) {
            if (algo1.getBot().getId() == id) {
                algo = algo1;
            }
        }
        if (algo != null) {
            concurrentList.deleteBot(algo);
        }

        botList.remove(algo.getBot());
        getServletContext().getRequestDispatcher("/bot.jsp").forward(request, response);
    }
}
