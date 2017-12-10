<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
    <title>Add new garage</title>
</head>
<body>
<form  method="post">
    <form action="/GarageServlet/insert" method="post">
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
                    <td>
                        <input size="75" type="text" placeholder="garage owner" name="owner"
                        pattern="[A-Z][a-z]{1,30}\s[A-Z][a-z]{1,30}\s[A-Z][a-z]{1,30}"
                        title="Example: Tomyuk Mykola Petrovich"
                        required value="<c:out value="${garage.owner}"/>">
                    </td>
                     <td>
                         <input size="75" type="text" placeholder="garage address" name="adress"
                         pattern="[A-Z][a-z]{1,}\s[a-z]{2}.\s\d{1,}-[A-Z]" title="Example: Golovna st. 78-I"
                         required value="<c:out value="${garage.adress}"/>">
                    </td>
                </tr>
                </tbody>
            </table>
        </fieldset>

        <input type="submit" value="Add garage" />
    </form>
</form>
</body>
</html>