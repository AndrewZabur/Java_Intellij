<%--
  Created by IntelliJ IDEA.
  User: Андрій Забурянний
  Date: 11.12.2017
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="https://bootswatch.com/4/solar/bootstrap.min.css">
    <style>
        h3{
            text-align: center;
        }
    </style>
</head>
<body>
<h3 style="color: red">${errorMessage}</h3>
<a href="/BusServlet/insert/${garage.id}"><button>Try again!</button></a>
</body>
</html>
