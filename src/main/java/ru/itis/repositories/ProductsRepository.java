package ru.itis.repositories;

import ru.itis.models.Product;

import java.util.List;

public interface ProductsRepository extends CrudRepository<Product> {
    List<Product> findAllProducts(String category);
    Product findProductById(Long id);
}
