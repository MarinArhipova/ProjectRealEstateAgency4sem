package ru.itis.repositories;

import org.springframework.stereotype.Component;
import ru.itis.models.Auth;

import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class AuthRepositoryImpl implements AuthRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Auth findByCookieValue(String cookieValue) {
//        Auth u = em.find(Auth.class, cookieValue);
//        return u;
        Query q = em.createQuery("SELECT u FROM Auth u WHERE cookieValue = :val");
        q.setParameter("val", cookieValue);
        Auth u = (Auth) q.getSingleResult();
        return Optional.ofNullable(u).get();
    }

    @Override
    public List<Auth> findAll() {
        return null;
    }

    @Override
    @Transactional
    public void save(Auth model) {
        Query q = em.createNativeQuery("insert into auth(id, cookie_value) values (?, ?)");
        q.setParameter(1, model.getUserId());
        q.setParameter(2, model.getCookieValue());
        q.executeUpdate();
    }

    @Override
    public void delete(Auth model) {

    }

    @Override
    public void update(Auth model) {

    }
}
