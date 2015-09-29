<%--
  Created by IntelliJ IDEA.
  User: dennis
  Date: 24.05.2015
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="/jstl/mytags.tld" prefix="mytag" %>--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<fmt:setBundle basename="manager.messages"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script language="JavaScript" src="js/calendar/tcal.js"></script>
    <link rel="stylesheet" href="js/calendar/tcal.css">
    <title><spring:message code="Main"/></title>
</head>
<body>
<div id="header">
    <div id="hello">
        <div>
            <a href="?lang=en">en</a>|<a href="?lang=ru">ru</a>
        </div><spring:message code="hello"/>,
        <b style="color:cadetblue">
            <sec:authentication property="principal.username"/>
        </b>
    </div>
    <div id="hello"><spring:message code="todayis"/>: ${now.time}</div>

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
    <div id="content1" style="width:1000px">
        <div align="center" id="centre">

            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>


            <form name="findflight" method="GET" action="findflight">
                <input type="hidden" name="command" value="findflight"/>
                <spring:message code="adminpanel.from"/>:<br/>
                <select name="fromcity" id="input">
                    <c:forEach var="item" items="${cities}">
                        <option value="${item.id}" ${item.getName() == selectedDept ? 'selected="selected"' : ''}>${item.getName()}</option>
                    </c:forEach>
                </select><br/>
                <spring:message code="main.begindate"/>:<br/>
                <input name="begindate" class="tcal" style="border: dotted 1px; border-radius: 3px" value="" size="16"
                       maxlength="16" required><br/>
                <spring:message code="main.enddate"/>:<br/>
                <input name="enddate" class="tcal" style="border: dotted 1px; border-radius: 3px" value="" size="16"
                       maxlength="16" required><br/>
                <spring:message code="main.to"/>:<br/>
                <select name="tocity" id="input">
                    <c:forEach var="item" items="${cities}">
                        <option value="${item.id}" ${item.getName() == selectedDept ? 'selected="selected"' : ''}>${item.getName()}</option>
                    </c:forEach>
                </select><br/>
                <input type="submit" value="<spring:message code="findflight"/>!" id="link">
            </form>


            <c:if test="${not empty result}">


                <div style="margin-top: 50px; margin-bottom: 50px">
                    <div style="margin-bottom: 30px; margin-top: 10px; text-align: center; font-family: Tahoma; font-size: 13px;">
                        <div><spring:message code="direction"/>: ${result.get(0).flyFromCity.name} - ${result.get(0).flyToCity.name}</div>
                        <div><spring:message code="result.date"/>: ${daterangefrom} - ${daterangeto}</div>
                    </div>
                    <h4><spring:message code="result.title"/>:</h4>
                    <table class="table-style-one">
                        <td><spring:message code="flightid"/></td>
                        <td><spring:message code="direction"/></td>
                        <td><spring:message code="flightdate"/></td>
                        <td><spring:message code="airplane"/></td>
                        <td><spring:message code="places"/></td>
                        <td><spring:message code="price"/></td>
                        <c:forEach var="flight" items="${result}">
                            <tr>
                                <td><c:out value="${flight.id}"/></td>
                                <td><c:out value="${flight.flyFromCity.name} - ${flight.flyToCity.name}"/></td>
                                <td><c:out value="${flight.flightTime.time.toLocaleString()}"/></td>
                                <td><c:out value="${flight.airplane.id} - ${flight.airplane.vendorName}"/></td>
                                <td><c:out
                                        value="${flight.airplane.numOfSeats-flight.seats.size()}/${flight.airplane.numOfSeats}"/></td>
                                <td><c:out value="${flight.tempPrice}"/></td>
                                <td>

                                    <form action="placeanorder" method="post">
                                        <input type="hidden" value="${flight.id}" name="id">
                                        <input type="submit" value="<spring:message code="placeanorder"/>" id="link">
                                        <sec:csrfInput/>
                                    </form>

                                </td>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <td>

                                        <form action="editflight" method="post">
                                            <input type="hidden" name="id" value="${flight.id}">
                                            <input type="submit" value="<spring:message code="edit"/>" id="link">
                                            <sec:csrfInput/>
                                        </form>

                                    </td>
                                    <td>

                                        <form action="#" method="post">
                                            <input type="submit" value="<spring:message code="delete"/>" id="link">
                                            <sec:csrfInput/>
                                        </form>

                                    </td>
                                </sec:authorize>
                            </tr>
                        </c:forEach>
                    </table>
                    <div>
                        <c:forEach begin="1" end="${pages}" var="val">
                            <a href="<c:url value="/admin/airplanes?page=${val}"/>">${val}</a>
                        </c:forEach>
                    </div>
                </div>

            </c:if>


            <div style="margin-top: 50px; margin-bottom: 50px">
                <h4><spring:message code="10nearestFlights"/></h4>
                <table class="table-style-one">
                    <td><spring:message code="flightid"/></td>
                    <td><spring:message code="direction"/></td>
                    <td><spring:message code="flightdate"/></td>
                    <td><spring:message code="airplane"/></td>
                    <td><spring:message code="places"/></td>
                    <td><spring:message code="price"/></td>
                    <c:forEach var="flight" items="${flights}">
                        <tr>
                            <td><c:out value="${flight.id}"/></td>
                            <td><c:out value="${flight.flyFromCity.name} - ${flight.flyToCity.name}"/></td>
                            <td><c:out value="${flight.flightTime.time.toLocaleString()}"/></td>
                            <td><c:out value="${flight.airplane.id} - ${flight.airplane.vendorName}"/></td>
                            <td><c:out
                                    value="${flight.airplane.numOfSeats-flight.seats.size()}/${flight.airplane.numOfSeats}"/></td>
                            <td><c:out value="${flight.initPrice}"/></td>
                            <td>

                                <form action="placeanorder" method="post">
                                    <input type="hidden" value="${flight.id}" name="id">
                                    <input type="submit" value="<spring:message code="placeanorder"/>" id="link">
                                    <sec:csrfInput/>
                                </form>

                            </td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <td>

                                    <form action="editflight" method="post">
                                        <input type="hidden" name="id" value="${flight.id}">
                                        <input type="submit" value="<spring:message code="edit"/>" id="link">
                                        <sec:csrfInput/>
                                    </form>

                                </td>
                                <td>

                                    <form action="#" method="post">
                                        <input type="submit" value="<spring:message code="delete"/>" id="link">
                                        <sec:csrfInput/>
                                    </form>

                                </td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>
                </table>
                <div>
                    <c:forEach begin="1" end="${pages}" var="val">
                        <a href="<c:url value="/admin/airplanes?page=${val}"/>">${val}</a>
                    </c:forEach>
                </div>
            </div>

        </div>
    </div>
</div>
<div id="footer">
    <div id="realfooter"> BroAirlines. By Dennis Kryachko. 2015. &#60;trainee&#62;</div>
</div>
</body>
</html>
