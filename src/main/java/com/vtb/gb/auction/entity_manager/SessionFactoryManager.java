package com.vtb.gb.auction.entity_manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryManager {
    private static final SessionFactory factory;

    static {
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static Session getSession() {
        return factory.getCurrentSession();
    }

    public static void close() {
        if (factory != null) {
            factory.close();
        }
    }
}
