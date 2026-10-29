<%@ include file="header.jsp" %>
<div class="form-box wide card">
  <h2><c:out value="${job.title}"/></h2>
  <p class="meta"><c:out value="${job.company}"/> &middot; <c:out value="${job.location}"/></p>
  <p style="margin-bottom:16px"><c:out value="${job.description}"/></p>
  <c:choose>
    <c:when test="${already}"><div class="alert ok">You have already applied for this job.</div></c:when>
    <c:when test="${sessionScope.user.role != 'SEEKER'}"><div class="alert err">Only job seekers can apply.</div></c:when>
    <c:otherwise>
      <form action="apply" method="post">
        <input type="hidden" name="jobId" value="${job.id}">
        <label>Cover note</label><textarea name="coverNote" rows="5" maxlength="2000" placeholder="Tell the employer why you're a great fit"></textarea>
        <button class="btn" type="submit">Submit Application</button>
      </form>
    </c:otherwise>
  </c:choose>
</div>
<%@ include file="footer.jsp" %>
