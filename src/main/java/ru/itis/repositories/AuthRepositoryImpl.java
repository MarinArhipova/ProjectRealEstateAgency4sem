package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.itis.models.Auth;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthRepositoryImpl implements AuthRepository {
//    @Autowired
//    SessionFactory sessionFactory;
//
//    EntityManager em;
//
//    @Autowired
//    public AuthRepositoryImpl(@Qualifier(value = "entityManagerFactory") EntityManagerFactory emf) {
//        em = emf.createEntityManager();
//    }
//
//    @Override
//    public Optional<Auth> findByCookieValue(String cookieValue) {
//        Auth u = em.find(Auth.class, cookieValue);
//return Optional.ofNullable(u);
//    }
//
//    @Override
//    public List<Auth> findAll() {
//        return null;
//    }
//
//    @Override
//    public void save(Auth model) {
//        em.getTransaction().begin();
//        em.persist(model);
//        em.getTransaction().commit();
//    }
//
//    @Override
//    public void delete(Auth model) {
//
//    }
//
//    @Override
//    public void update(Auth model) {
//
//    }

    //language=SQL
    private static final String SQL_INSERT =
            "insert into auth(user_id, cookie_value) values (?, ?)";

    //language=SQL
    private static final String SQL_SELECT_BY_COOKIE_VALUE =
            "select * from auth where cookie_value = ?";

    //language=SQL
    private static final String SQL_DELETE_BY_COOKIE_VALUE =
            "delete * from auth where cookie_value = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Auth> authRowMapper = new RowMapper<Auth>() {
        @Override
        public Auth mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Auth.builder()
                    .id(rs.getLong("user_id"))
                    .cookieValue(rs.getString("cookie_value"))
                    .build();
        }
    };

    @Override
    public List<Auth> findAll() {
        return null;
    }

//    @Override
//    public Optional<Auth> findOne(Long id) {
//        return null;
//    }

    @Override
    public void save(Auth model) {
        jdbcTemplate.update(SQL_INSERT, model.getId(), model.getCookieValue());
    }
//    public void save(Auth model) {
//        jdbcTemplate.update(SQL_INSERT, model.getUserId(), model.getCookieValue());
//    }

    @Override
    public void delete(Auth model) {
        jdbcTemplate.update(SQL_DELETE_BY_COOKIE_VALUE, model.getCookieValue());
    }

    @Override
    public void update(Auth model) {

    }

//    @Override
//    public User findOneByEmail(String email) {
//        Session session = this.sessionFactory.openSession();
//        Query query = session.createQuery("from Driver where email=:email");
//        query.setParameter("email", email);
//        return (User) query.uniqueResult();
//    }

    @Override
    public Optional<Auth> findByCookieValue(String cookieValue) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_COOKIE_VALUE, authRowMapper, cookieValue));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
