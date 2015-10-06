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
    <title><spring:message code="airplanePanel"/></title>
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

<div>
    <div id="content">
        <div id="content1">
            <div align="center" id="centre">


                <form name="addairplane" method="POST" action="addairplane">
                    <input type="hidden" name="command" value="addairplane"/>
                    <spring:message code="airplane.vendorName"/>:<br/>
                    <input name="vendorName"
                           id="input"
                           type="text"
                           size="20"
                           maxlength="32"
                           required><br/>
                    <spring:message code="airplane.numOfSeats"/>:<br/>
                    <input name="numOfSeats"
                           id="input"
                           type="number"
                           step="1" min="2"
                           max="1000"
                           value=""
                           size="4"
                           maxlength="4"
                           required><br/>
                    <input type="submit" value="<spring:message code="airplane.add"/>" id="link">
                    <sec:csrfInput/>
                </form>

                <c:out value="${id}"/>

                <div style="margin-top: 50px; margin-bottom: 50px">
                    <table class="table-style-one">
                        <td><spring:message code="airplane.id"/></td>
                        <td><spring:message code="airplane.vendorName"/></td>
                        <td><spring:message code="airplane.numOfSeats"/></td>
                        <td><spring:message code="airplane.operable"/></td>
                        <c:forEach var="airplane" items="${airplanes}">
                            <tr>
                                <td><c:out value="${airplane.id}"/></td>
                                <td><c:out value="${airplane.vendorName}"/></td>
                                <td><c:out value="${airplane.numOfSeats}"/></td>
                                <td><c:out value="${airplane.isOperable()}"/></td>
                                <td>

                                    <form action="" method="post">
                                        <input type="hidden" name="id" value="${airplane.id}">
                                        <input type="submit" value="<spring:message code="edit"/>" id="link">
                                        <sec:csrfInput/>
                                    </form>

                                </td>
                                <td>

                                    <c:if test="${airplane.isOperable()==true}">
                                        <form action="changeairplanestate" method="post">
                                            <input type="hidden" name="id" value="${airplane.id}">
                                            <input type="submit" value="<spring:message code="inoperable"/>" id="link">
                                            <sec:csrfInput/>
                                        </form>
                                    </c:if>

                                    <c:if test="${airplane.isOperable()==false}">
                                        <form action="changeairplanestate" method="post">
                                            <input type="hidden" name="id" value="${airplane.id}">
                                            <input type="submit" value="<spring:message code="operable"/>" id="link">
                                            <sec:csrfInput/>
                                        </form>
                                    </c:if>



                                </td>
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
</div>
<div id="footer">
    <div id="realfooter"> BroAirlines. By Dennis Kryachko. 2015. &#60;trainee&#62;</div>
</div>


</body>
</html>
