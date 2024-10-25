package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessionRepository {

    @Autowired
    private SessionJpaRepository sessionJpaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Session create(Session session) {
        return sessionJpaRepository.saveAndFlush(session);
    }

    public Session update(Session session) {
        return sessionJpaRepository.saveAndFlush(session);
    }

    public void delete(Long id) {
        sessionJpaRepository.deleteById(id);
    }

    public Session find(Long id) {
        return sessionJpaRepository.getOne(id);
    }

    public List<Session> list() {
        return sessionJpaRepository.findAll();
    }

    public List<Session> getSessionsThatHaveName(String name) {
        return sessionJpaRepository.findBySessionNameContains(name);
    }

    public Long countSessions(String name) {
        return sessionJpaRepository.countBySessionNameContains(name);
    }

    public List<Session> countSessionsLength(int length) {
        return sessionJpaRepository.findBySessionLengthGreaterThan(length);
    }

    public Page<Session> getSessionsWithName(String name, Pageable pageable) {
        return sessionJpaRepository.getSessionsWithName(name, pageable);
    }

    public List<Session> selectSessions() {
        return sessionJpaRepository.customGetSessions();
    }


}
