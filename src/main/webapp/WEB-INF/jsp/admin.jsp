<%--
  Created by IntelliJ IDEA.
  User: dennis
  Date: 26.05.2015
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script language="JavaScript" src="js/calendar/tcal.js"></script>
    <link rel="stylesheet" href="js/calendar/tcal.css">
    <title><spring:message code="AdminPanel"/></title>
</head>
<body>
<div id="header">
    <div id="hello">
        <div>
            <a href="?lang=en">en</a>|<a href="?lang=ru">ru</a>
        </div>
        <spring:message code="hello"/>,<b style="color:cadetblue">
            <sec:authentication property="principal.username"/>
        </b>
    </div>
    <div id="hello"><spring:message code="todayis"/>: ${now.toString()}</div>

    <h3 id="title"><spring:message code="AdminPanel"/></h3>

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
                <a href="<c:url value="/admin/airplanes"/>"><spring:message code="airplanePanel"/></a>
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

            <form name="addflight" method="POST" action="addflight">
                <input type="hidden" name="command" value="addflight"/>
                <spring:message code="adminpanel.vendornameseats"/><br/>
                <select name="airplane" id="input">
                    <c:forEach var="item" items="${airplanes}">
                        <option value="${item.id}" ${item.vendorName == flight.airplane.vendorName ? 'selected="selected"' : ''}>${item.vendorName}: ${item.numOfSeats}</option>
                    </c:forEach>
                </select><br/>
                <spring:message code="adminpanel.from"/>:<br/>
                <select name="fromcity" id="input">
                    <c:forEach var="item" items="${cities}">
                        <option value="${item.id}" ${item.name == flight.flyFromCity.name ? 'selected="selected"' : ''}>${item.name}</option>
                    </c:forEach>
                </select><br/>
                <spring:message code="adminpanel.date"/>:<br/>
                <input name="date" class="tcal" style="border: dotted 1px; border-radius: 3px" value="${flightTime}"
                       size="16"
                       maxlength="16" required><br/>
                <spring:message code="adminpanel.to"/>:<br/>
                <select name="tocity" id="input">
                    <c:forEach var="item" items="${cities}">
                        <option value="${item.id}" ${item.name == flight.flyToCity.name ? 'selected="selected"' : ''}>${item.name}</option>
                    </c:forEach>
                </select><br/>
                <spring:message code="adminpanel.flightcost"/>:<br/>
                <input name="price" id="input" type="number" step="0.01" min="0" value="${flight.initPrice}" size="6"
                       maxlength="6"
                       required><br/>
                <input type="hidden" name="id" value="${flight.id}">
                <input type="submit" value="<spring:message code="adminpanel.addflight"/>" id="link">
                <sec:csrfInput/>
            </form>
        </div>
    </div>
</div>
<div id="footer">
    <div id="realfooter"> BroAirlines. <mytag:ver/> By Dennis Kryachko. 2015. &#60;trainee&#62;</div>
</div>

</body>
</html>
