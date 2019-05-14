package ru.itis.repositories;

import org.springframework.stereotype.Component;
import ru.itis.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Component
public class ProductsRepositoryImpl implements ProductsRepository {
    //language=SQL
    private static final String SQL_INSERT =
            "insert into product (product_id, img, title, price, category) values (?, ?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_SELECT_ALL_PRODUCTS =
            "select * from product where category=?";

    //language=SQL
    private static final String SQL_SELECT_ALL =
            "select * from product";

    //language=SQL
    private static final String SQL_SELECT_GET_PRODUCT_BY_ID =
            "select * from product WHERE product_id = ?;";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductsRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private RowMapper<Product> productRowMapper = (resultSet, i) -> Product.builder()
            .id(resultSet.getLong("product_id"))
            .img(resultSet.getString("img"))
            .title(resultSet.getString("title"))
            .price(resultSet.getString("price"))
            .category(resultSet.getString("category"))
            .build();

    @Override
    public List<Product> findAllProducts(String category) {
        return jdbcTemplate.query(SQL_SELECT_ALL_PRODUCTS, productRowMapper, category);
    }

    @Override
    public Product findProductById(Long id) {
        return jdbcTemplate.query(SQL_SELECT_GET_PRODUCT_BY_ID, productRowMapper, id).get(0);

    }

    @Override
    public void save(Product model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                    ps.setLong(1, model.getId());
                    ps.setString(2, model.getImg());
                    ps.setString(3, model.getTitle());
                    ps.setString(4, model.getPrice());
                    ps.setString(5, model.getCategory());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void delete(Product model) {
    }


    @Override
    public void update(Product model) {
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, productRowMapper);
    }
}