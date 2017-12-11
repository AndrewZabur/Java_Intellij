<%@ page import="java.time.LocalDate" %>
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
        <title>Update bus</title>
</head>
<body>
<form method="post">
    <form action="/BusServlet/update/${bus.id}/${garage.id}" method="post">
        <fieldset>
           <table border=1  class="table table-condensed">
               <thead>
                   <tr>
                       <th>Identification Number</th>
                       <th>Model</th>
                       <th>Capacity</th>
                       <th>Date of the construction</th>
                   </tr>
               </thead>
               <tbody>
               <tr>

                   <td>
                       <input type="text" placeholder="identification number" name="indentificationNumber"
                       pattern="[A-Z]{2}\d{4}[A-Z]{2}" title="Example: IU9876NB" required
                        value="<c:out value="${bus.indentificationNumber}"/>">
                   </td>
                   <td>
                        <input type="text" placeholder="enter model" name="model"
                        required value="<c:out value="${bus.model}"/>">
                   </td>
                  <td>
                        <input type="number" placeholder="enter capacity" name="capacity"
                        required value="<c:out value="${bus.capacity}"/>">
                  </td>
                   <td>
                        <input type="date" placeholder="construction date" name="dataConstruction"
                        max="<%=LocalDate.now()%>" 
                        required value="<c:out value="${bus.dataConstruction}"/>">
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
