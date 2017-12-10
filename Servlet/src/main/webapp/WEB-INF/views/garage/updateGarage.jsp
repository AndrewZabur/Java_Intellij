<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
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
    <title>Update garage</title>
</head>
<body>
<form  method="post">
    <form action="/GarageServlet/update/${garages.id}" method="post">
        <fieldset>
            <table border=1  class="table table-condensed">
                <thead>
                <tr>
                    <th>Owner</th>
                    <th>Address</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                <%--<label>Owner</label>--%>
            <td><input size="75" type="text" placeholder="garage owner"
                   name="owner"
                   value="<c:out value="${garage.owner}"/>">
            </td>
 <%--           <br><label>Address</label>
 --%>        <td>   <input size="75" type="text" placeholder="garage address"
                   name="adress"
                   value="<c:out value="${garage.adress}"/>">
                </td>
                </tr>
                </tbody>
            </table>
        </fieldset>

        <input type="submit" value="Update" />
    </form>
</form>
</body>
</html>