package ru.itis.repositories;

import org.springframework.stereotype.Component;
import ru.itis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_INSERT =
            "insert into shop_user(firstname, lastname, patronymic, email, password, phonenumber) values (?, ?, ?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_SELECT_ALL =
            "select * from shop_user";

    //language=SQL
    private static final String SQL_SELECT_BY_ID =
            "select * from shop_user where user_id = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL =
            "select * from shop_user where email = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_COOKIE =
            "select * from shop_user where user_id IN (select user_id from auth where cookie_value = ?);";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (resultSet, i) -> User.builder()
            .userID(resultSet.getLong("user_id"))
            .firstName(resultSet.getString("firstname"))
            .lastName(resultSet.getString("lastname"))
            .patronymic(resultSet.getString("patronymic"))
            .email(resultSet.getString("email"))
            .phoneNumber(resultSet.getString("phonenumber"))
            .hashPassword(resultSet.getString("password"))
            .build();

    public User findByCookie(String cookie) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_COOKIE, userRowMapper, cookie);
    }

    @Override
    public void save(User model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT, new String[]{"user_id"});

                    ps.setString(1, model.getFirstName());
                    ps.setString(2, model.getLastName());
                    ps.setString(3, model.getPatronymic());
                    ps.setString(4, model.getEmail());
                    ps.setString(5, model.getHashPassword());
                    ps.setString(6, model.getPhoneNumber());
                    return ps;
                }, keyHolder);
        model.setUserID(keyHolder.getKey().longValue());
    }

    @Override
    public void delete(User model) {

    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }


    @Override
    public void update(User model) {
    }

    @Override
    public User findByEmail(String email) {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email);
    }

//    @Override
//    public Optional<User> findOneByConfirmString(String confirmString) {
//        return Optional.empty();
//    }
}
