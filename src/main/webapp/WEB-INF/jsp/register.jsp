<%--
  Created by IntelliJ IDEA.
  User: dennis
  Date: 24.05.2015
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <fmt:setBundle basename="manager.messages"/>
    <title>Register</title>
</head>
<div style="padding-top: 100px">
    <h3 id="title">Register</h3>
</div>
</hr id="line">
<div>
    <div align="center" id="centre">

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form name="registerForm" method="POST" action="reg">
            First name:<br/>
            <input type="text" name="firstName" value="" size="32" maxlength="32" required autocomplete="off"> <br/>
            Last name:<br/>
            <input type="text" id="one" name="lastName" value="" size="32" maxlength="32" required autocomplete="off"><br/>
            Email:<br/>
            <input type="email" id="two" name="email" value="" size="32" maxlength="32" required autocomplete="off"><br/>
            Password<br/>
            <input type="password" name="password" value="" size="32" maxlength="32" required autocomplete="off"><br/>
            <td>Phone:<br/>example:0931234567<br/></td>
            <input type="text" name="phone" value="" size="12" maxlength="12" required autocomplete="off"><br/>
            <input type="submit" name="submit" value="Register!" id="link">
            <sec:csrfInput/>
        </form>
        <a href="<c:url value="/login"/>" id="link">Login</a>
    </div>
</div>
</body>
</html>