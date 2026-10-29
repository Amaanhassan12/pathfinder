<%@ include file="header.jsp" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<div class="container">
  <h2 class="welcome-box">Welcome, <c:out value="${sessionScope.user.name}"/></h2>

  <c:if test="${sessionScope.user.role == 'EMPLOYER'}">
    <div class="row" style="margin-bottom:10px"><h3>My job postings</h3><a class="btn small" href="postJob">+ Post a Job</a></div>
    <table>
      <tr><th>Title</th><th>Location</th><th>Posted</th><th></th></tr>
      <c:forEach var="j" items="${myJobs}">
        <tr><td><c:out value="${j.title}"/></td><td><c:out value="${j.location}"/></td>
          <td><fmt:formatDate value="${j.postedOn}" pattern="dd MMM yyyy"/></td>
          <td><form action="deleteJob" method="post" onsubmit="return confirm('Delete this job and its applications?')">
            <input type="hidden" name="id" value="${j.id}" style="display:none">
            <button class="btn danger small" type="submit">Delete</button></form></td></tr>
      </c:forEach>
      <c:if test="${empty myJobs}"><tr><td colspan="4">No jobs posted yet.</td></tr></c:if>
    </table>

    <h3 style="margin:28px 0 10px">Applicants</h3>
    <table>
      <tr><th>Applicant</th><th>Email</th><th>Job</th><th>Applied</th><th>Cover note</th></tr>
      <c:forEach var="a" items="${applicants}">
        <tr><td><c:out value="${a.applicant.name}"/></td>
          <td><a href="mailto:${a.applicant.email}"><c:out value="${a.applicant.email}"/></a></td>
          <td><c:out value="${a.job.title}"/></td>
          <td><fmt:formatDate value="${a.appliedOn}" pattern="dd MMM yyyy"/></td>
          <td><c:out value="${a.coverNote}"/></td></tr>
      </c:forEach>
      <c:if test="${empty applicants}"><tr><td colspan="5">No applications yet.</td></tr></c:if>
    </table>
  </c:if>

  <c:if test="${sessionScope.user.role == 'SEEKER'}">
    <div class="blue-box"> <h3 style="margin-bottom:10px">My applications</h3>
    <table>
      <tr><th>Job</th><th>Company</th><th>Location</th><th>Applied</th></tr>
      <c:forEach var="a" items="${myApps}">
        <tr><td><c:out value="${a.job.title}"/></td><td><c:out value="${a.job.company}"/></td>
          <td><c:out value="${a.job.location}"/></td><td><fmt:formatDate value="${a.appliedOn}" pattern="dd MMM yyyy"/></td></tr>
      </c:forEach>
      <c:if test="${empty myApps}"><tr><td colspan="4">You haven't applied to any jobs yet. <a href="jobs">Browse jobs</a></td></tr></c:if>
    </table>
    </div>
  </c:if>
</div>
<%@ include file="footer.jsp" %>
