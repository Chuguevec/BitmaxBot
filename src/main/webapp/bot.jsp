<%@ page import="model.Bot" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Mega
  Date: 26.12.2022
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Bot</title>
  <link href="static/main.css" rel="stylesheet">
</head>
<body>
<%
    ServletContext servletContext = session.getServletContext();
  List<Bot> bots = (List<Bot>) servletContext.getAttribute("botList");
%>
 <%
  if (bots != null && !bots.isEmpty()){
      for (Bot bot : bots) {
          out.println("<div class=\"registration-cssave\">");
          out.println("<form method=\"post\" , action=\"/stop?id=" + bot.getId() + "\">");
          out.println("<div>Bot");
          out.println("</div>");
          out.println("<div>Api_Key: " + bot.getApiKey());
          out.println("</div>");
          out.println("<div>");
          out.println("Symbol: " + bot.getSymbol());
          out.println("</div>");
          out.println("<div class=\"form-group\">");
          out.println(" <button class=\"btn btn-primary btn-block create-account\" type=\"submit\">Stop</button>");
          out.println("</div>");
          out.println("</form>");
          out.println("</div>");
    }
  }
%>

<div class="registration-cssave">
  <form method="post" , action="/init">
    <h3 class="text-center">Добавить бота</h3>
    <div class="form-group">
      <input class="form-control item" type="text" name="apiKey" id="apiKey" placeholder="apiKey" required>
    </div>
    <div class="form-group">
      <input class="form-control item" type="text" name="apiSec" id="apiSec" placeholder="apiSec" required>
    </div>
    <div class="form-group">
      <input class="form-control item" type="text" name="symbol" id="symbol" placeholder="symbol пара">
    </div>
    <div class="form-group">
      <input class="form-control item" type="text" name="step" id="step" placeholder="step">
    </div>
    <div class="form-group">
      <input class="form-control item" type="text" name="lvl" id="lvl" placeholder="lvl">
    </div>
    <div class="form-group">
      <input class="form-control item" type="text" name="coefficient" id="coefficient" placeholder="coefficient">
    </div>
    <div class="form-group">
      <button class="btn btn-primary btn-block create-account" type="submit">Запустить</button>
    </div>
  </form>
</div>
</body>
</html>
