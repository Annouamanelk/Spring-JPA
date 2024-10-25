package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SessionCustomJpaRepositoryImpl implements SessionCustomJpaRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Session> customGetSessions() {
        return em.createQuery("select s from Session s").getResultList();
    }
}
