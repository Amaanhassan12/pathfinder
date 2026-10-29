<%@ include file="header.jsp" %>
<div class="form-box wide card">
  <h2 style="margin-bottom:14px">Post a new job</h2>
  <form action="postJob" method="post">
    <label>Job title</label><input type="text" name="title" required>
    <label>Company</label><input type="text" name="company" value="<c:out value='${sessionScope.user.name}'/>" required>
    <label>Location</label><input type="text" name="location" required>
    <label>Salary (optional)</label><input type="text" name="salary" placeholder="e.g. 6-8 LPA">
    <label>Description</label><textarea name="description" rows="6" maxlength="4000" required></textarea>
    <button class="btn" type="submit">Publish Job</button>
  </form>
</div>
<%@ include file="footer.jsp" %>
