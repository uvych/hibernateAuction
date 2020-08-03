package com.vtb.gb.auction.repo;

import com.vtb.gb.auction.domain.User;
import com.vtb.gb.auction.entity_manager.ManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepo {

    public UserRepo() {
    }

    public List<User> getUser(){
        EntityManager em = null;
        List<User> list;
        try {
            em = ManagerFactory.getEntityManager();
            em.getTransaction().begin();
            list = em.createQuery("from User")
                    .getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return list;
    }
}
