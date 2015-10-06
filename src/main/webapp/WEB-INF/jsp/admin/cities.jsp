<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <title><spring:message code="cities.title"/></title>
</head>
<body>

<div id="header">
    <div id="hello">
        <div>
            <a href="?lang=en">en</a>|<a href="?lang=ru">ru</a>
        </div>
        <spring:message code="hello"/>,
        <b style="color:cadetblue">
            <sec:authentication property="principal.username"/>

        </b>
    </div>
    <div id="hello"><spring:message code="todayis"/>: ${now}</div>

    <h3 id="title">BroAirlines</h3>


    <div id="realheader">
        <div id="line">
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="<spring:message code="Logout"/>" id="link">
                <sec:csrfInput/>
            </form>
        </div>
        <div id="line">
            <div id="link">
                <a href="<c:url value="/cabinet"/>"><spring:message code="Cabinet"/></a>
            </div>
        </div>
        <div id="line">
            <div id="link">
                <a href="<c:url value="/main"/>"><spring:message code="Main"/></a>
            </div>
        </div>
        <div id="line">
            <div id="link">
                <a href="<c:url value="/admin/cities"/>"><spring:message code="cityPanel"/></a>
            </div>
        </div>
        <div id="line">
            <div id="link">
                <a href="<c:url value="/admin"/>"><spring:message code="AdminPanel"/></a>
            </div>
        </div>
    </div>
</div>


<div id="content">
    <div id="content1">
        <div align="center" id="centre">

            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>

            <div style="margin-top: 30px">
            <form name="cityRegionForm" action="addcities" method="POST">
                <spring:message code="cities.todo"/><br/>
                <div style="height: 10px"></div>
                <textarea name="lines" value="" width="300" height="600"></textarea><br/>
                <input type="submit" name="submit" value="<spring:message code="cities.add"/>" id="link">
                <sec:csrfInput/>
            </form>
            </div>


        </div>
    </div>
</div>


<div id="footer">
    <div id="realfooter"> BroAirlines. By Dennis Kryachko. 2015. &#60;trainee&#62;</div>
</div>

</body>
</html>
