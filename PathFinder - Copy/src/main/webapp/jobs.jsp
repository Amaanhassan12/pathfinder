<%@ include file="header.jsp" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<div class="container">
  <form class="search" action="jobs" method="get" style="margin-bottom:24px">
    <input type="text" name="q" value="<c:out value='${q}'/>" placeholder="Job title or company">
    <input type="text" name="location" value="<c:out value='${loc}'/>" placeholder="Location">
    <button class="btn" type="submit">Search</button>
  </form>
  <h2 class="count-box">${jobs.size()} job's found</h2>
  <c:forEach var="j" items="${jobs}">
    <div class="card">
      <div class="row">
        <div>
          <h3><c:out value="${j.title}"/></h3>
          <div class="meta"><c:out value="${j.company}"/> &middot; <c:out value="${j.location}"/> &middot; Posted <fmt:formatDate value="${j.postedOn}" pattern="dd MMM yyyy"/></div>
        </div>
        <c:if test="${not empty j.salary}"><span class="tag"><c:out value="${j.salary}"/></span></c:if>
      </div>
      <p style="margin:8px 0 12px"><c:out value="${j.description}"/></p>
      <c:choose>
        <c:when test="${sessionScope.user.role == 'SEEKER'}"><a class="btn small" href="apply?id=${j.id}">Apply Now</a></c:when>
        <c:when test="${empty sessionScope.user}"><a class="btn small" href="login">Login to apply</a></c:when>
      </c:choose>
    </div>
  </c:forEach>
</div>
<%@ include file="footer.jsp" %>
