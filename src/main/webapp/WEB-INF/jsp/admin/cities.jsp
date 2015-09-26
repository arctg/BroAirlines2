<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dennis
  Date: 9/22/2015
  Time: 4:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
    <title>Add city and region</title>
</head>
<body>
<div style="padding-top: 100px">
    <h3 id="title">Add city and region</h3>
</div>
<div>
    <div align="center" id="centre">

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>


        <form name="cityRegionForm" action="addcities" method="POST">
            Put here city and regon in format City:Region<br/>
            <textarea name="lines" value="" width="300" height="600"></textarea><br/>
            <input type="submit" name="submit" value="Add!" id="link">
            <sec:csrfInput/>
        </form>

        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Logout!" id="link">
            <sec:csrfInput/>
        </form>

    </div>
</div>

</body>
</html>
