<%-- 
    Document   : error
    Created on : Aug 10, 2015, 5:27:56 PM
    Author     : andrii
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    </body>
</html>
