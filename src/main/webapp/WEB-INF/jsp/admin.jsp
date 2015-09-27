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

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script language="JavaScript" src="js/calendar/tcal.js"></script>
    <link rel="stylesheet" href="js/calendar/tcal.css">
    <title>Admin panel</title>
</head>
<body>
<div id="header">
    <div id="hello">
        Hello,
        <b><sec:authentication property="principal.username" /></b>

    </div>
    <div id="hello">Today is: ${now.toString()}</div>
    <h3 id="title">BroAirlines</h3>

    <div id="realheader">
        <div id="line">
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Logout!" id="link">
                <sec:csrfInput/>
            </form>
        </div>
        <div id="line">
            <div id="link">
                <a href="<c:url value="/cabinet"/>">Cabinet</a>
            </div>
        </div>
        <div id="line">
            <div id="link">
                <a href="<c:url value="/main"/>">Main</a>
            </div>
        </div>
        <div id="line">
            <div id="link">
                <a href="<c:url value="/admin/cities"/>">City panel</a>
            </div>
        </div>
        <div id="line">
            <div id="link">
                <a href="<c:url value="/admin/airplanes"/>">Airplane panel</a>
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
                Vendor name and seats<br/>
                <select name="airplane" id="input">
                    <c:forEach var="item" items="${airplanes}">
                        <option value="${item.id}" ${item.vendorName == flight.airplane.vendorName ? 'selected="selected"' : ''}>${item.vendorName}: ${item.numOfSeats}</option>
                    </c:forEach>
                </select><br/>
                From:<br/>
                <select name="fromcity" id="input">
                    <c:forEach var="item" items="${cities}">
                        <option value="${item.id}" ${item.name == flight.flyFromCity.name ? 'selected="selected"' : ''}>${item.name}</option>
                    </c:forEach>
                </select><br/>
                Date,time:<br/>
                <input name="date" class="tcal" style="border: dotted 1px; border-radius: 3px" value="${flightTime}" size="16"
                       maxlength="16" required><br/>
                To:<br/>
                <select name="tocity" id="input">
                    <c:forEach var="item" items="${cities}">
                        <option value="${item.id}" ${item.name == flight.flyToCity.name ? 'selected="selected"' : ''}>${item.name}</option>
                    </c:forEach>
                </select><br/>
                Flight cost:<br/>
                <input name="price" id="input" type="number" step="0.01" min="0" value="${flight.initPrice}" size="6" maxlength="6"
                       required><br/>
                <input type="submit" value="Add flight" id="link">
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
