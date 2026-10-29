package com.jobportal.servlet;

import com.jobportal.dao.PortalDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

/** Public job listing with keyword / location search. */
@WebServlet("/jobs")
public class JobsServlet extends HttpServlet {
    private final PortalDAO dao = new PortalDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q = req.getParameter("q") == null ? "" : req.getParameter("q").trim();
        String loc = req.getParameter("location") == null ? "" : req.getParameter("location").trim();
        req.setAttribute("jobs", dao.searchJobs(q, loc));
        req.setAttribute("q", q);
        req.setAttribute("loc", loc);
        req.getRequestDispatcher("/jobs.jsp").forward(req, resp);
    }
}
