package ru.itis.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.models.Basket;
import ru.itis.models.Product;
import ru.itis.models.User;
import ru.itis.repositories.BasketRepository;

import java.util.List;

@Component
public class BasketServiceImpl implements BasketService {
    private BasketRepository basketRepository;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public void addProductInBasket(Long productId, Long basketId) {
        basketRepository.addProductToBasket(productId, basketId);
    }

    @Override
    public void deleteOneProduct(Long basketId, Long productId) {
        basketRepository.deleteOneProduct(basketId, productId);
    }

    @Override
    public Basket getBasketByUserId(long userId) {
        return basketRepository.getBasketByUserId(userId);
    }

    @Override
    public void deleteProductsByUserID(Long id) {
        basketRepository.deleteProductsByUserID(id);
    }

    @Override
    public List<Product> findAllProductsByUserID(User user) {
        return basketRepository.findAllProductsByUserID(user);
    }
}
