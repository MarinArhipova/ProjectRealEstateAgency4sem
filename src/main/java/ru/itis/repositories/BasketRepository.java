package ru.itis.repositories;

import ru.itis.models.Basket;
import ru.itis.models.Product;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends CrudRepository<Basket> {
    void save(Basket model);

    List<Product> findAllProductsByUserID(User user);

    void addProductToBasket(long productId, long basketId);

    Basket getBasketByUserId(long userId);

    void deleteProductsByUserID(Long id);

    List<Basket> findAll();

    void deleteOneProduct(Long basketId, Long productId);
}
