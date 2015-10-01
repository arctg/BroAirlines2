<%--
  Created by IntelliJ IDEA.
  User: dennis
  Date: 24.05.2015
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">

    <title><spring:message code="reg.title.Register"/></title>
</head>
<div style="padding-top: 100px">
    <h3 id="title"><spring:message code="reg.title2.Register"/></h3>
</div>
</hr id="line">
<div>
    <div align="center" id="centre">

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form name="registerForm" method="POST" action="reg">
            <spring:message code="reg.fistname"/>:<br/>
            <input type="text" name="firstName" value="" size="32" maxlength="32" required autocomplete="off"> <br/>
            <spring:message code="reg.lastname"/>:<br/>
            <input type="text" id="one" name="lastName" value="" size="32" maxlength="32" required autocomplete="off"><br/>
            <spring:message code="email"/>:<br/>
            <input type="email" id="two" name="email" value="" size="32" maxlength="32" required autocomplete="off"><br/>
            <spring:message code="password"/><br/>
            <input type="password" name="password" value="" size="32" maxlength="32" required autocomplete="off"><br/>
            <td><spring:message code="reg.phone"/>:<br/><spring:message code="reg.example"/>:+38(09)3763-1973<br/></td>
            <input type="text" name="phone" value="" size="16" maxlength="16" required autocomplete="off" pattern="[\+]\d{2}[\(]\d{2}[\)]\d{4}[\-]\d{4}"><br/>
            <input type="submit" name="submit" value="<spring:message code="reg.reg"/>" id="link">
            <sec:csrfInput/>
        </form>
        <a href="<c:url value="/login"/>" id="link"><spring:message code="reg.login"/></a>
        <div style="margin-top: 3px;">
            <a href="?lang=en">en</a>|<a href="?lang=ru">ru</a>
        </div>
    </div>
</div>
</body>
</html>