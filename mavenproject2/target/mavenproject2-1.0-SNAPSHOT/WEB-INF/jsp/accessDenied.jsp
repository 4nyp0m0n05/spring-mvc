<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>AccessDenied page</title>
</head>
<body>
    Dear <strong>${pageContext.request.userPrincipal.name}</strong>, You are not authorized to access this page.
    <a href="<c:url value="/" />">Home</a>
</body>
</html>