<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <title>Add city and region</title>
</head>
<body>


<div id="header">
    <div id="hello">
        hello,
        <b><sec:authentication property="principal.username"/></b>

    </div>
    <%--<div id="hello"><fmt:message key="todayis"/>: <mytag:todayis/></div>--%>
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
                <a href="<c:url value="/admin"/>">Admin panel</a>
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
                    Vendor name:<br/>
                    <input name="vendorName"
                           id="input"
                           type="text"
                           size="20"
                           maxlength="32"
                           required><br/>
                    Number of seats:<br/>
                    <input name="numOfSeats"
                           id="input"
                           type="number"
                           step="1" min="2"
                           max="1000"
                           value=""
                           size="6"
                           maxlength="6"
                           required><br/>
                    <input type="submit" value="Add airplane" id="link">
                    <sec:csrfInput/>
                </form>

                <c:out value="${id}"/>

                <div style="margin-top: 50px; margin-bottom: 50px">
                    <table class="table-style-one">
                        <td><c:out value="ID"/></td>
                        <td><c:out value="Vendor Name"/></td>
                        <td><c:out value="Number of Seats"/></td>
                        <c:forEach var="airplane" items="${airplanes}">
                            <tr>
                                <td><c:out value="${airplane.id}"/></td>
                                <td><c:out value="${airplane.vendorName}"/></td>
                                <td><c:out value="${airplane.numOfSeats}"/></td>
                                <td>

                                    <form action="" method="post">
                                        <input type="submit" value="Edit!" id="link">
                                        <sec:csrfInput/>
                                    </form>

                                </td>
                                <td>

                                    <form action="" method="post">
                                        <input type="submit" value="Delete!" id="link">
                                        <sec:csrfInput/>
                                    </form>

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
