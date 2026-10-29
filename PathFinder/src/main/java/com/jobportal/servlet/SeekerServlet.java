package com.jobportal.servlet;

import com.jobportal.dao.PortalDAO;
import com.jobportal.model.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

/** /apply (job seekers) and /dashboard (both roles). */
@WebServlet(urlPatterns = {"/apply", "/dashboard"})
public class SeekerServlet extends HttpServlet {
    private final PortalDAO dao = new PortalDAO();

    private User current(HttpServletRequest req) {
        HttpSession s = req.getSession(false);
        return s == null ? null : (User) s.getAttribute("user");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = current(req);
        if (u == null) { resp.sendRedirect(req.getContextPath() + "/login"); return; }

        if (req.getServletPath().equals("/apply")) {         // show apply form
            Job job = dao.getJob(Long.parseLong(req.getParameter("id")));
            req.setAttribute("job", job);
            req.setAttribute("already", dao.hasApplied(job.getId(), u.getId()));
            req.getRequestDispatcher("/apply.jsp").forward(req, resp);
            return;
        }
        if ("EMPLOYER".equals(u.getRole())) {
            req.setAttribute("myJobs", dao.jobsByEmployer(u.getId()));
            req.setAttribute("applicants", dao.applicationsForEmployer(u.getId()));
        } else {
            req.setAttribute("myApps", dao.applicationsByUser(u.getId()));
        }
        req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = current(req);
        if (u == null || !"SEEKER".equals(u.getRole())) { resp.sendRedirect(req.getContextPath() + "/login"); return; }
        req.setCharacterEncoding("UTF-8");

        Long jobId = Long.parseLong(req.getParameter("jobId"));
        if (!dao.hasApplied(jobId, u.getId())) {
            Application a = new Application();
            a.setJob(dao.getJob(jobId));
            a.setApplicant(u);
            a.setCoverNote(req.getParameter("coverNote"));
            dao.saveApplication(a);
        }
        resp.sendRedirect(req.getContextPath() + "/dashboard");
    }
}
