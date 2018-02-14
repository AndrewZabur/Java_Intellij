<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://bootswatch.com/4/solar/bootstrap.min.css">
    <style>
        th{
            background-color: aqua;
            text-align: center;
        }
    </style>
    <title>All Garages</title>

</head>
<body>
<table border=1  class="table table-condensed table-hover">
    <thead>
    <tr>
        <th>Owner</th>
        <th>Address</th>
        <th>Buses</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${garages}" var="garages">
        <tr>
            <td>${garages.owner}</td>
            <td>${garages.adress}</td>
            <td><a href="/BusServlet/${garages.id}"><button>Show buses</button></a></td>
            <td><a
                    href="/GarageServlet/update/${garages.id}"><button>Update</button></a></td>
            <td><a
                    href="/GarageServlet/delete/${garages.id}"><button>Delete</button></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>
    <a href="/GarageServlet/insert"><button>Add Garage</button></a>
</p>
</body>
</html>