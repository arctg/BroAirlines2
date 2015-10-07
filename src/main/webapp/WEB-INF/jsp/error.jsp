<%-- 
    Document   : error
    Created on : Aug 10, 2015, 5:27:56 PM
    Author     : andrii
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>Something happened</h1>
        <c:if test="${url != null}"> URL: ${url} </c:if> </br>
        Exception: ${ex}
        StackTrace: ${st}

        <div id="line">
            <div id="link">
                <a href="<c:url value="/main"/>" id="link"><spring:message code="Error.BackToMain"/></a>
            </div>
        </div>
    </body>
</html>
