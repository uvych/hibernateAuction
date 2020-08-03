package com.vtb.gb.auction.repo;

import com.vtb.gb.auction.domain.Item;
import com.vtb.gb.auction.domain.User;
import com.vtb.gb.auction.entity_manager.ManagerFactory;
import com.vtb.gb.auction.entity_manager.SessionFactoryManager;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.util.concurrent.ThreadLocalRandom;

public class ItemRepo {

    public ItemRepo() {
    }

    public void setNewBindPessimistic(User user){
        EntityManager em = null;
        try {
            em = ManagerFactory.getEntityManager();
            em.getTransaction().begin();
            long random = ThreadLocalRandom.current().nextLong(1, 5);
            Item item = em.createQuery("SELECT i FROM Item i WHERE i.id = :id", Item.class)
                    .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                    .setParameter("id", random)
                    .getSingleResult();

            Long bid = item.getBid();
            item.setBid(bid + 100L);
            item.setUser(user);
            em.getTransaction().commit();
            em.close();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }



    public void setNewBindOptimistic(User user){
        long random = ThreadLocalRandom.current().nextLong(1, 5);
        Session session = null;
        try {
            session = SessionFactoryManager.getSession();
            session.beginTransaction();
            Item item = session.createQuery("SELECT i FROM Item i WHERE i.id = :id", Item.class)
                    .setLockMode(LockModeType.OPTIMISTIC)
                    .setParameter("id", random)
                    .getSingleResult();

            Long bid = item.getBid();
            item.setBid(bid + 100L);
            item.setUser(user);

            session.getTransaction().commit();
        }catch (OptimisticLockException e) {
            assert session != null;
            session.getTransaction().rollback();
            setNewBindOptimistic(user);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
