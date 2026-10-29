<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en"><head>
<meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1">
<title>PathFinder</title>
<link href="https://fonts.googleapis.com/css2?family=Playball&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head><body>
<div class="nav">
  <a class="logo" href="${pageContext.request.contextPath}/index.jsp">Path<span>Finder</span></a>
  <div class="links">
    <a href="${pageContext.request.contextPath}/jobs">Browse Jobs</a>
    <c:choose>
      <c:when test="${not empty sessionScope.user}">
        <c:if test="${sessionScope.user.role == 'EMPLOYER'}"><a href="${pageContext.request.contextPath}/postJob">Post a Job</a></c:if>
        <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/logout">Logout (<c:out value="${sessionScope.user.name}"/>)</a>
      </c:when>
      <c:otherwise>
        <a href="${pageContext.request.contextPath}/login">Login</a>
        <a href="${pageContext.request.contextPath}/register">Register</a>
      </c:otherwise>
    </c:choose>
  </div>
</div>
