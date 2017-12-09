<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://bootswatch.com/4/solar/bootstrap.min.css">

    <title>Add new bus</title>
</head>
<body>
<form method="post">
    <form action="/BusServlet/insert/${garage.id}" method="post">
        <fieldset>

            <label>Identification Number</label>
            <input type="text" placeholder="identification number"
                   name="indentificationNumber"
                   value="<c:out value="${bus.indentifiactionNumber}"/>">

            <br><label>Model</label>
            <input type="text" placeholder="enter model"
                   name="model"
                   value="<c:out value="${bus.model}"/>">

            <br><label>Capacity</label>
            <input type="number" placeholder="enter capacity"
                   name="capacity"
                   value="<c:out value="${bus.capacity}"/>">

            <br><label>Date of the construction</label>
            <input type="date" placeholder="construction date"
                   name="dataConstruction"
                   value="<c:out value="${bus.dataConstruction}"/>">


        </fieldset>

        <input type="submit" value="Add bus" />
    </form>
</form>
</body>
</html>