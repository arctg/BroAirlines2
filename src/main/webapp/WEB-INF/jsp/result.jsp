<%--
  Created by IntelliJ IDEA.
  User: dennis
  Date: 02.06.2015
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script language="JavaScript" src="js/calendar/tcal.js"></script>
    <link rel="stylesheet" href="js/calendar/tcal.css">
    <title><spring:message code="placeanorder"/></title>
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
    <div id="hello"><spring:message code="todayis"/>: ${now.toString()}</div>
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
                <a href="<c:url value="/main"/>" id="link"><spring:message code="Main"/></a>
            </div>
        </div>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div id="line">
                <div id="link">
                    <a href="<c:url value="/admin"/>"><spring:message code="AdminPanel"/></a>
                </div>
            </div>
        </sec:authorize>
    </div>
</div>
<div id="content">
    <div id="content1">
        <div align="center" id="centre">
            <div style="margin-bottom: 30px; margin-top: 10px; text-align: center; font-family: Tahoma; font-size: 13px;">
                <div><spring:message code="direction"/>: ${flight.flyFromCity.name} - ${flight.flyToCity.name}</div>
                <div><spring:message code="flightdate"/>: <fmt:formatDate value="${flight.flightTime.time}" type="both"
                                                                          pattern="yyyy-MM-dd HH:mm"/></div>
            </div>

            <div id="flight">
                <div id="flightHeader"></div>
                <div>
                    <div class="box">
                        <div><spring:message code="flightid"/>:</div>
                        <div id="values">${cart.flightId}</div>
                    </div>
                    <div class="box">
                        <div><spring:message code="result.freeplaces"/>:</div>
                        <div id="values">${flight.airplane.numOfSeats - flight.seats.size()}</div>
                    </div>
                    <div class="box">
                        <div><spring:message code="airplane"/>:</div>
                        <div id="values">${flight.airplane.vendorName}</div>
                    </div>
                    <div class="box">
                        <div><spring:message code="price"/>:</div>
                        <div id="values">${flight.initPrice}</div>
                    </div>
                    <div class="box">
                        <div><spring:message code="flightdate"/>:</div>
                        <div id="values"><fmt:formatDate value="${flight.flightTime.time}" type="both"
                                                         pattern="yyyy-MM-dd HH:mm"/></div>

                    </div>
                </div>
                <div id="flightFooter">
                    <div style="text-align:right">
                        <div class="box">
                            <form name="registerForm" method="POST" action="confirm" id="form">
                                <div><input type="checkbox" name="baggage" value="true"><spring:message code="baggage"/>
                                </div>
                                <div><input type="checkbox" name="priboarding" value="true"><spring:message
                                        code="priorityboarding"/></div>
                        </div>
                        <div id="line">
                            <input name="submit" type="submit" value="<spring:message code="placeanorder"/>" id="link"/>
                            <sec:csrfInput/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<div id="footer">
    <div id="realfooter"> BroAirlines. <mytag:ver/> By Dennis Kryachko. 2015. &#60;trainee&#62; </div>
</div>
</body>
</html>
