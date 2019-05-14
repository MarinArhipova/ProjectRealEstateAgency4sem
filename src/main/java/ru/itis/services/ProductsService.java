package ru.itis.services;

import ru.itis.models.Product;

import java.util.List;

public interface ProductsService {
    List<Product> getAll(String str2);
}
