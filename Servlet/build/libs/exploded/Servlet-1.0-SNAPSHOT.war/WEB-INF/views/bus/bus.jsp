<%--
  Created by IntelliJ IDEA.
  User: Андрій Забурянний
  Date: 06.12.2017
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Buses</title>
    <link rel="stylesheet" href="https://bootswatch.com/4/solar/bootstrap.min.css">
    <style>
        th{
            text-align: center;
        }
        p{
            font-size: x-large;
            color: grey;
        }
        div{
            background-color: aqua;
        }
    </style>
</head>
<body>
<p>
    Owner: ${garage.owner}
    <br>Address: ${garage.adress}
</p>
<%--<table border=1 class="table table-condensed">
    <thead>
    <tr style="background-color: aqua">
        <th>Owner</th>
        <th>Address</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td style="text-align: center">${garage.owner}</td>
        <td style="text-align: center">${garage.adress}</td>
    </tr>
    </tbody>
</table>--%>
<table border=1 class="table table-condensed">

    <thead>
    <tr style="background-color: aqua">
        <th>Model</th>
        <th>Identification Number</th>
        <th>Date of the construction</th>
        <th>Capacity</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="buses" items="${buses}" >
        <tr>
            <td>${buses.model}</td>
            <td>${buses.indentificationNumber}</td>
            <td>${buses.dataConstruction}</td>
            <td>${buses.capacity}</td>
            <td><a
                    href="/BusServlet/update/${buses.id}/${garage.id}"><button>Update</button></a></td>
            <td><a
                    href="/BusServlet/delete/${buses.id}/${garage.id}"><button>Delete</button></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br><a href="/BusServlet/insert/${garage.id}"><button>Add new bus</button></a>

</body>
</html>
