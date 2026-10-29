<%@ include file="header.jsp" %>
<div class="hero">
  <h1>Find your next career move</h1>
  <p>Search openings from top employers, or post a job and hire great people.</p>
  <form class="search" action="jobs" method="get">
    <input type="text" name="q" placeholder="Job title or company">
    <input type="text" name="location" placeholder="Location">
    <button class="btn" type="submit">Search</button>
  </form>
</div>
<div class="container">
  <div class="card"><h3>For Job Seekers</h3><p class="meta">Create a free account, browse listings and apply with a cover note in one click.</p><a class="btn small" href="register">Join as Job Seeker</a></div>
  <div class="card"><h3>For Employers</h3><p class="meta">Post openings and review every applicant from your dashboard.</p><a class="btn small" href="register">Join as Employer</a></div>
</div>
<%@ include file="footer.jsp" %>
