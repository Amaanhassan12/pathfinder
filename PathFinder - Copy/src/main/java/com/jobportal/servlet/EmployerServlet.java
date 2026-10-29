package com.jobportal.servlet;

import com.jobportal.dao.PortalDAO;
import com.jobportal.model.Job;
import com.jobportal.model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

/** Employer-only actions: /postJob and /deleteJob. */
@WebServlet(urlPatterns = {"/postJob", "/deleteJob"})
public class EmployerServlet extends HttpServlet {
    private final PortalDAO dao = new PortalDAO();

    private User employer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession s = req.getSession(false);
        User u = s == null ? null : (User) s.getAttribute("user");
        if (u == null || !"EMPLOYER".equals(u.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return null;
        }
        return u;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (employer(req, resp) == null) return;
        req.getRequestDispatcher("/postJob.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = employer(req, resp);
        if (u == null) return;
        req.setCharacterEncoding("UTF-8");

        if (req.getServletPath().equals("/deleteJob")) {
            dao.deleteJob(Long.parseLong(req.getParameter("id")), u.getId());
        } else {
            Job j = new Job();
            j.setTitle(req.getParameter("title").trim());
            j.setCompany(req.getParameter("company").trim());
            j.setLocation(req.getParameter("location").trim());
            j.setSalary(req.getParameter("salary").trim());
            j.setDescription(req.getParameter("description").trim());
            j.setEmployer(u);
            dao.saveJob(j);
        }
        resp.sendRedirect(req.getContextPath() + "/dashboard");
    }
}
