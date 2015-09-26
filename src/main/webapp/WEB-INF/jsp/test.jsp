<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dennis
  Date: 9/17/2015
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello!</title>
</head>
<br>
Test success!</br>
Hello, <c:out value="${helloName}"/> </br>
<c:out value="${message}"/> </br>

<c:url var="logoutUrl" value="/logout"/>
<FORM action="${logoutUrl}" method="post">
    <INPUT type="submit" value="logout">
    <sec:csrfInput/>
</FORM>

</body>
</html>
