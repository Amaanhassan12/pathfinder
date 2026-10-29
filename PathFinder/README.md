# PathFinder (JSP + Servlets + Hibernate + MySQL)

## Setup in NetBeans
1. Install JDK 8+, Apache Tomcat 10.1, MySQL 8.
2. NetBeans: File > Open Project > select the `PathFinder` folder (Maven web app).
3. Tools > Servers > Add Server > Apache Tomcat 10.1; set it as the project's server.
4. Edit `src/main/resources/hibernate.cfg.xml` and set your MySQL username/password
   (the `jobportal` database and all tables are created automatically).
5. Right-click project > Clean and Build, then Run. Open http://localhost:8080/PathFinder/

## Use
- Register as an Employer, post jobs.
- Register as a Job Seeker (second browser/incognito), search and apply.
- Employer dashboard shows applicants and cover notes.

## Notes
This version targets Apache Tomcat 10.1 (Jakarta EE 10), JDK 11+.
