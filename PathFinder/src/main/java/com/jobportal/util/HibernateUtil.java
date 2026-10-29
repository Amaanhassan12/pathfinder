package com.jobportal.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    public static SessionFactory getSessionFactory() { return FACTORY; }

    /** SHA-256 hash for passwords (use BCrypt in a production system). */
    public static String hash(String s) {
        try {
            byte[] d = MessageDigest.getInstance("SHA-256").digest(s.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : d) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) { throw new RuntimeException(e); }
    }
}
