//package ru.itis.repositories;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//import ru.itis.models.Basket;
//import ru.itis.models.Product;
//import ru.itis.models.User;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class BasketRepositoryHibernateImpl implements BasketRepository {
//
//    @Autowired
//    SessionFactory sessionFactory;
//
//    EntityManager em;
//
//    @Autowired
//    public BasketRepositoryHibernateImpl(@Qualifier(value = "entityManagerFactory") EntityManagerFactory emf) {
//        em = emf.createEntityManager();
//    }
//    @Override
//    public void save(Basket model) {
//
//    }
//
//    @Override
//    public void delete(Basket model) {
//
//    }
//
//    @Override
//    public void update(Basket model) {
//
//    }
//
//    @Override
//    public Optional<Basket> findOne(Long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<Product> findAllProductsByUserID(User user) {
//        Basket b = em.find(Basket.class, user.getUserID();
//        List products = sessionFactory.openSession()
//                .createQuery("from Product").list();
//        return products;
//    }
//
//    @Override
//    public void addProductToBasket(long productId, long basketId) {
//
//    }
//
//    @Override
//    public Basket getBasketByUserId(long userId) {
//        return null;
//    }
//
//    @Override
//    public void deleteProductsByUserID(Long id) {
//
//    }
//
//    @Override
//    public List<Basket> findAll() {
//        return null;
//    }
//}
