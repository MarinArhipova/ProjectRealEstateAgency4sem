package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.itis.models.Basket;
import ru.itis.models.Product;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component
public class BasketRepositoryImpl implements BasketRepository {
    //language=sql
    private static final String SELECT_BASKET_BY_USER_ID =
            "select * from basket where basket.user_id = ?";
    //language=SQL
    private static final String SQL_INSERT_BASKET =
            "insert into basket(basket_id, user_id) values (?,? );";
    //language=SQL
    private static final String SQL_DELETE_ONE_PRODUCT =
            "delete from basket_product where basket_id = ? and product_id = ?";
    //language=SQL
    private static final String SQL_DELETE_PRODUCT =
            "delete from basket_product where basket_id = ?";

    //language=SQL
    private static final String SELECT_ALL_PRODUCTS_BY_BASKETID =
            "select product.* from product, basket_product where basket_id = ? and basket_product.product_id = product.product_id";

    //language=SQL
    private static final String INSERT_PRODUCT_TO_BASKET =
            "insert into basket_product(basket_id, product_id) values (?, ?)";

    //language=SQL
    private static final String DELETE_DATA_FROM_BASKET_BY_USERID =
            "delete from basket_product where basket_id in(select basket.basket_id from basket WHERE basket.user_id = ?)";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BasketRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Basket> basketRowMapper = (resultSet, i) -> Basket.builder()
            .basketID(resultSet.getLong("basket_id"))
            .userID(resultSet.getLong("user_id"))
            .build();

    private RowMapper<Product> productRowMapper = (resultSet, i) -> Product.builder()
            .id(resultSet.getLong("product_id"))
            .img(resultSet.getString("img"))
            .title(resultSet.getString("title"))
            .countOfRooms(resultSet.getInt("countOfRooms"))
            .price(resultSet.getString("price"))
            .category(resultSet.getString("category"))
            .build();

    @Override
    public void deleteOneProduct(Long basketId, Long productId) {
        jdbcTemplate.update(SQL_DELETE_ONE_PRODUCT, basketId, productId);
    }

    @Override
    public List<Product> findAllProductsByUserID(User user) {
        try {
            Basket basket = jdbcTemplate.queryForObject(SELECT_BASKET_BY_USER_ID, basketRowMapper, user.getUserID());
            return jdbcTemplate.query(SELECT_ALL_PRODUCTS_BY_BASKETID, productRowMapper, basket.getBasketID());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void addProductToBasket(long basketId, long productId) {
        jdbcTemplate.update(INSERT_PRODUCT_TO_BASKET, basketId, productId);

    }


    @Override
    public void update(Basket model) {

    }

    @Override
    public void save(Basket model) {
        jdbcTemplate.update(SQL_INSERT_BASKET, model.getBasketID(), model.getUserID());
    }

    @Override
    public void delete(Basket model) {
        jdbcTemplate.update(SQL_DELETE_PRODUCT, model.getBasketID());
    }


    @Override
    public Optional<Basket> findOne(Long id) {
        return Optional.empty();
    }


    @Override
    public Basket getBasketByUserId(long userId) {
        return jdbcTemplate.queryForObject(SELECT_BASKET_BY_USER_ID, basketRowMapper, userId);
    }

    @Override
    public void deleteProductsByUserID(Long id) {
        jdbcTemplate.update(DELETE_DATA_FROM_BASKET_BY_USERID, id);
    }

    @Override
    public List<Basket> findAll() {
        return null;
    }
}
