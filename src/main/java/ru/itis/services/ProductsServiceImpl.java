package ru.itis.services;

import org.springframework.stereotype.Component;
import ru.itis.models.Product;
import ru.itis.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<Product> getAll(String str2) {
        return productsRepository.findAllProducts(str2);
    }

    @Override
    public List<Product> forTable() {
        return productsRepository.findAll();
    }

}
