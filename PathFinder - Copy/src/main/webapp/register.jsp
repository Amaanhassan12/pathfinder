<%@ include file="header.jsp" %>
<div class="form-box card">
  <h2 style="margin-bottom:14px">Create account</h2>
  <c:if test="${not empty error}"><div class="alert err"><c:out value="${error}"/></div></c:if>
  <form action="register" method="post">
    <label>Full name / Company</label><input type="text" name="name" required>
    <label>Email</label><input type="email" name="email" required>
    <label>Password (min 6 chars)</label><input type="password" name="password" minlength="6" required>
    <label>I am a</label>
    <select name="role"><option value="SEEKER">Job Seeker</option><option value="EMPLOYER">Employer</option></select>
    <button class="btn" type="submit">Register</button>
  </form>
  <p class="meta" style="margin-top:12px">Already registered? <a href="login">Login</a></p>
</div>
<%@ include file="footer.jsp" %>
