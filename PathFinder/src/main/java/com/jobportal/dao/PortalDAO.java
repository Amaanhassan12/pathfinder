package com.jobportal.dao;

import com.jobportal.model.*;
import com.jobportal.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PortalDAO {

    private void save(Object o) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.persist(o);
            tx.commit();
        } catch (RuntimeException e) { if (tx != null) tx.rollback(); throw e; }
    }

    // ---------- Users ----------
    public void saveUser(User u) { save(u); }

    public User findUserByEmail(String email) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from User where email = :e", User.class)
                    .setParameter("e", email).uniqueResult();
        }
    }

    // ---------- Jobs ----------
    public void saveJob(Job j) { save(j); }

    public Job getJob(Long id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) { return s.get(Job.class, id); }
    }

    public List<Job> searchJobs(String keyword, String location) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Job j where (lower(j.title) like :k or lower(j.company) like :k) "
                    + "and lower(j.location) like :l order by j.postedOn desc", Job.class)
                    .setParameter("k", "%" + keyword.toLowerCase() + "%")
                    .setParameter("l", "%" + location.toLowerCase() + "%").list();
        }
    }

    public List<Job> jobsByEmployer(Long employerId) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Job j where j.employer.id = :id order by j.postedOn desc", Job.class)
                    .setParameter("id", employerId).list();
        }
    }

    public void deleteJob(Long jobId, Long employerId) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            Job j = s.get(Job.class, jobId);
            if (j != null && j.getEmployer().getId().equals(employerId)) {
                s.createMutationQuery("delete from Application a where a.job.id = :id").setParameter("id", jobId).executeUpdate();
                s.remove(j);
            }
            tx.commit();
        } catch (RuntimeException e) { if (tx != null) tx.rollback(); throw e; }
    }

    // ---------- Applications ----------
    public void saveApplication(Application a) { save(a); }

    public boolean hasApplied(Long jobId, Long userId) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Long c = s.createQuery("select count(a) from Application a where a.job.id = :j and a.applicant.id = :u", Long.class)
                    .setParameter("j", jobId).setParameter("u", userId).uniqueResult();
            return c != null && c > 0;
        }
    }

    public List<Application> applicationsByUser(Long userId) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Application a where a.applicant.id = :id order by a.appliedOn desc", Application.class)
                    .setParameter("id", userId).list();
        }
    }

    public List<Application> applicationsForEmployer(Long employerId) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Application a where a.job.employer.id = :id order by a.appliedOn desc", Application.class)
                    .setParameter("id", employerId).list();
        }
    }
}
