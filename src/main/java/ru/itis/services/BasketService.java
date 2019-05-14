package ru.itis.services;


import ru.itis.models.Basket;
import ru.itis.models.Product;
import ru.itis.models.User;

import java.util.List;

public interface BasketService {
    void addProductInBasket(Long productId, Long basketId);
    void deleteOneProduct(Long basketId, Long productId);
    Basket getBasketByUserId(long userId);
    void deleteProductsByUserID(Long id);
    List<Product> findAllProductsByUserID(User user);

}
