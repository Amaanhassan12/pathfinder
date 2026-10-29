package com.jobportal.servlet;

import com.jobportal.dao.PortalDAO;
import com.jobportal.model.User;
import com.jobportal.util.HibernateUtil;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

/** Handles /register, /login and /logout. */
@WebServlet(urlPatterns = {"/register", "/login", "/logout"})
public class AuthServlet extends HttpServlet {
    private final PortalDAO dao = new PortalDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/logout")) {
            HttpSession s = req.getSession(false);
            if (s != null) s.invalidate();
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            req.getRequestDispatcher(path.equals("/register") ? "/register.jsp" : "/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email").trim().toLowerCase();
        String password = req.getParameter("password");

        if (req.getServletPath().equals("/register")) {
            String name = req.getParameter("name").trim();
            String role = "EMPLOYER".equals(req.getParameter("role")) ? "EMPLOYER" : "SEEKER";
            if (name.isEmpty() || email.isEmpty() || password.length() < 6) {
                req.setAttribute("error", "All fields are required and the password needs at least 6 characters.");
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                return;
            }
            if (dao.findUserByEmail(email) != null) {
                req.setAttribute("error", "That email is already registered.");
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                return;
            }
            dao.saveUser(new User(name, email, HibernateUtil.hash(password), role));
            resp.sendRedirect(req.getContextPath() + "/login?registered=1");
        } else { // login
            User u = dao.findUserByEmail(email);
            if (u == null || !u.getPassword().equals(HibernateUtil.hash(password))) {
                req.setAttribute("error", "Invalid email or password.");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }
            req.getSession().setAttribute("user", u);
            resp.sendRedirect(req.getContextPath() + "/dashboard");
        }
    }
}
