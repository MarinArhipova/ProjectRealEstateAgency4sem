//package ru.itis.repositories;
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//import ru.itis.models.User;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import java.util.List;
//
//@Component
//public class UsersRepositoryHibernateImpl implements UsersRepository {
//
//    @Autowired
//    SessionFactory sessionFactory;
//
//    EntityManager em;
//
//    @Autowired
//    public UsersRepositoryHibernateImpl(@Qualifier(value = "entityManagerFactory") EntityManagerFactory emf) {
//        em = emf.createEntityManager();
//    }
//
//    @Override
//    public User findByEmail(String email) {
//        User u = em.find(User.class, email);
//        return u;
//    }
//
//    @Override
//    public User findByCookie(String cookieValue) {
//        User u = em.find(User.class, cookieValue);
//        return u;
//    }
//
//    @Override
//    public List<User> findAll() {
//        List users = sessionFactory.openSession()
//                .createQuery("from User").list();
//        return users;
//    }
//
//    @Override
//    public void save(User model) {
//        em.getTransaction().begin();
//        em.persist(model);
//        em.getTransaction().commit();
//    }
//
//    @Override
//    public void delete(User model) {
//
//    }
//
//    @Override
//    public void update(User model) {
//
//    }
//}
