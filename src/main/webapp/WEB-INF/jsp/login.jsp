<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%--
Created by IntelliJ IDEA.
User: dennis
Date: 25.05.2015
Time: 16:08
To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Login</title>
</head>
<body>
<div style="padding-top: 100px">
    <h3 id="title">Login</h3>
</div>
<div>
    <div align="center" id="centre">

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>
        <form name="loginForm" action="<c:url value="login"/>" method="POST">
            Email:<br/>
            <input type="email" id="username" name="username" value="" size="32" maxlength="32" required
                   autocomplete="off"><br/>
            Password:<br/>
            <input type="password" id="password" name="password" value="" size="32" maxlength="32" required
                   autocomplete="off"><br/>
            <input type="submit" name="submit" value="Login!" id="link">
            <sec:csrfInput/>
        </form>
        <a href="<c:url value="/register"/>" id="link">New user</a>

    </div>
</div>

</body>
</html>