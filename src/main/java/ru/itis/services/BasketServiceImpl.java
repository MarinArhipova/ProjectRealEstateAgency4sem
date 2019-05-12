package ru.itis.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.repositories.BasketRepository;
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
}
