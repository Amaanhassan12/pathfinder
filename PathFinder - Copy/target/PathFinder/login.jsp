<%@ include file="header.jsp" %>
<div class="form-box card">
  <h2 style="margin-bottom:14px">Login</h2>
  <c:if test="${param.registered == '1'}"><div class="alert ok">Registration successful. Please log in.</div></c:if>
  <c:if test="${not empty error}"><div class="alert err"><c:out value="${error}"/></div></c:if>
  <form action="login" method="post">
    <label>Email</label><input type="email" name="email" required>
    <label>Password</label><input type="password" name="password" required>
    <button class="btn" type="submit">Login</button>
  </form>
  <p class="meta" style="margin-top:12px">New here? <a href="register">Create an account</a></p>
</div>
<%@ include file="footer.jsp" %>
