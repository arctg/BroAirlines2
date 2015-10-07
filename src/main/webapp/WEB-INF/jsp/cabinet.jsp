<%--
  Created by IntelliJ IDEA.
  User: dennis
  Date: 28.05.2015
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title><spring:message code="cabinet.title"/></title>
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
                <div><spring:message code="myorders"/>:</div>
                <div></div>
            </div>

            <c:forEach var="item" items="${nearOrders}">
                <div id="flight">
                    <div id="flightHeader"></div>
                    <div>
                        <div class="box">
                            <div><spring:message code="orderid"/>:</div>
                            <div id="values">${item.id}</div>
                        </div>
                        <div class="box">
                            <div><spring:message code="flightid"/>:</div>
                            <div id="values">${item.flight.id}</div>
                        </div>
                        <div class="box">
                            <div><spring:message code="airplane"/>:</div>
                            <div id="values">${item.flight.airplane.vendorName}-${item.flight.airplane.id}</div>
                        </div>
                        <div class="box">
                            <div><spring:message code="flightdate"/>:</div>
                            <div id="values"><fmt:formatDate value="${item.flight.flightTime.time}" type="both"
                                                             pattern="yyyy-MM-dd HH:mm"/></div>
                        </div>
                        <div class="box">
                            <div><spring:message code="direction"/>:</div>
                            <div id="values">${item.flight.flyFromCity.name} - ${item.flight.flyToCity.name}</div>
                        </div>
                        <div class="box">
                            <div><spring:message code="priorityboarding"/>:</div>
                            <div id="values">${item.priorityBoarding}</div>
                        </div>
                        <div class="box">
                            <div><spring:message code="baggage"/>:</div>
                            <div id="values">${item.luggage}</div>
                        </div>
                        <div class="box">
                            <div><spring:message code="totalcost"/>:</div>
                            <div id="values">${item.totalPrice}</div>
                        </div>
                    </div>
                    <div id="flightFooter">
                        <%--<div style="text-align:right">--%>
                            <%--<div id="line">--%>
                                <%--<form name="registerForm" method="POST" action="Controller" id="form">--%>
                                    <%--<input type="hidden" name="command" value="deleteorder"/>--%>
                                    <%--<input type="hidden" name="orderId" value="${item.id}"/>--%>
                                    <%--<input name="submit" type="submit" value="<spring:message code="cancelorder"/>" id="link"/>--%>
                                <%--</form>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div align="center" id="centre">
            <div style="margin-bottom: 30px; margin-top: 10px; text-align: center; font-family: Tahoma; font-size: 13px;">
                <div><spring:message code="mypastorders"/>:</div>
                <div></div>
            </div>

            <c:forEach var="item" items="${pastOrders}">
                <div id="flight">
                    <div id="flightHeader"></div>
                    <div>
                        <div class="box">
                            <div><fmt:message key="orderid"/>:</div>
                            <div id="values">${item.id}</div>
                        </div>
                        <div class="box">
                            <div><fmt:message key="flightid"/>:</div>
                            <div id="values">${item.flight.id}</div>
                        </div>
                        <div class="box">
                            <div><fmt:message key="airplane"/>:</div>
                            <div id="values">${item.flight.airplane.vendorName}</div>
                        </div>
                        <div class="box">
                            <div><fmt:message key="flightdate"/>:</div>
                            <div id="values"><fmt:formatDate value="${item.flight.flightTime.time}" type="both"
                                                             pattern="yyyy-MM-dd HH:mm"/></div>
                        </div>
                        <div class="box">
                            <div><fmt:message key="direction"/>:</div>
                            <div id="values">${item.flight.flyFromCity.name}-${item.flight.flyToCity.name}</div>
                        </div>
                        <div class="box">
                            <div><fmt:message key="priorityboarding"/>:</div>
                            <div id="values">${item.priorityBoarding}</div>
                        </div>
                        <div class="box">
                            <div><fmt:message key="baggage"/>:</div>
                            <div id="values">${item.luggage}</div>
                        </div>
                        <div class="box">
                            <div><fmt:message key="totalcost"/>:</div>
                            <div id="values">${item.totalPrice}</div>
                        </div>
                    </div>
                    <div id="flightFooter">
                        <div style="text-align:right">
                            <div id="line">
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</div>
<div id="footer">
    <div id="realfooter"> BroAirlines. <mytag:ver/> By Dennis Kryachko. 2015. &#60;trainee&#62;</div>
</div>
</body>
</html>
