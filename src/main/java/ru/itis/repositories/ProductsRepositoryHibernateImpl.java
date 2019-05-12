package ru.itis.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.itis.models.Product;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class ProductsRepositoryHibernateImpl implements ProductsRepository {

    @Autowired
    SessionFactory sessionFactory;

    EntityManager em;

    @Autowired
    public ProductsRepositoryHibernateImpl(@Qualifier(value = "entityManagerFactory") EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }

    @Override
    public List<Product> findAllProducts(String category) {
        Session session = this.sessionFactory.openSession();
        Query q = session.createQuery("FROM Product WHERE category = :category");
        q.setParameter("category", category);
         List<Product> products = q.getResultList();
        return products;
    }

    @Override
    public Product findProductById(Long id) {
        Product u = em.find(Product.class, id);
        return u;
    }

    @Override
    public List<Product> findAll() {
        List products = sessionFactory.openSession()
                .createQuery("from Product").list();
        return products;
    }

    @Override
    public void save(Product model) {
        em.getTransaction().begin();
        em.persist(model);
        em.getTransaction().commit();

//        Session session = this.sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//        session.persist(model);
//        tx.commit();
//        session.close();
    }

    @Override
    public void delete(Product model) {

    }

    @Override
    public void update(Product model) {

    }
}
