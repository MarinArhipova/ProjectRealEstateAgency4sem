//package ru.itis.repositories;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Component;
//import ru.itis.models.Auth;
//
//import javax.sql.DataSource;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//@Component
//public class AuthRepositoryJdbcTemplateImpl implements AuthRepository {
//    //language=SQL
//    private static final String SQL_INSERT =
//            "insert into auth(id, cookie_value) values (?, ?)";
//
//    //language=SQL
//    private static final String SQL_SELECT_BY_COOKIE_VALUE =
//            "select * from auth where cookie_value = ?";
//
//    //language=SQL
//    private static final String SQL_DELETE_BY_COOKIE_VALUE =
//            "delete * from auth where cookie_value = ?";
//
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public AuthRepositoryJdbcTemplateImpl(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    private RowMapper<Auth> authRowMapper = new RowMapper<Auth>() {
//        @Override
//        public Auth mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return Auth.builder()
//                    .id(rs.getLong("user_id"))
//                    .cookieValue(rs.getString("cookie_value"))
//                    .build();
//        }
//    };
//
//    @Override
//    public List<Auth> findAll() {
//        return null;
//    }
//
//    @Override
//    public void save(Auth model) {
//        jdbcTemplate.update(SQL_INSERT, model.getUserId(), model.getCookieValue());
//    }
//
//    @Override
//    public void delete(Auth model) {
//        jdbcTemplate.update(SQL_DELETE_BY_COOKIE_VALUE, model.getCookieValue());
//    }
//
//    @Override
//    public void update(Auth model) {
//    }
//
//    @Override
//    public Auth findByCookieValue(String cookieValue) {
//        try {
//            return jdbcTemplate.queryForObject(SQL_SELECT_BY_COOKIE_VALUE, authRowMapper, cookieValue);
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
//    }
//}
