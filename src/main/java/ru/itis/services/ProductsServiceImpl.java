package ru.itis.services;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.itis.models.Product;
import ru.itis.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class ProductsServiceImpl implements ProductsService {
//    private ProductsRepository productsRepository;
//
//    public ProductsServiceImpl(ProductsRepository productsRepository) {
//        this.productsRepository = productsRepository;
//    }
//
//    @Override
//    public List<Product> addProductToUserBasket(String cookieValue, Long productId) {
//        return null;
//    }
//    @Override
//    public List<Product> forTable() {
//        return productsRepository.findAllProducts();
//    }
//
////    @Override
////    public Optional<Product> findOneById(Long id) {
////        return productsRepository.findOne(id);
////    }
//
//    @Override
//    public List<Product> findByCount(Integer countOfRooms) {
//        return productsRepository.countOfRooms(countOfRooms);
//    }
//
//
//    @Override
//    public List<Product> getAll() {
//        return productsRepository.findAllProducts();
//    }
//    @Override
//    public Product getProductById(Long id) {
//        return productsRepository.findProductById(id);
//    }

    @Autowired
    private ProductsRepository productsRepository;

//    public ProductsServiceImpl(ProductsRepository productsRepository) {
//        this.productsRepository = productsRepository;
//    }

//    @Override
//    public List<Product> findByCountOfRooms(Integer countOfRooms) {
//        return productsRepository.findByCountOfRooms(countOfRooms);
//    }

    @Override
    public List<Product> getAll(String str2) {
        return productsRepository.findAllProducts(str2);
    }

    @Override
    public List<Product> forTable() {
        return productsRepository.findAll();
    }

//    @Override
//    public Product getCottageById(Long id) {
//        return productsRepository.findProductById(id);
//    }

//    @Override
//    public void addProduct(AddProductForm form) {
//        Product product = Product.builder()
//                .img(form.getImg())
//                .title(form.getTitle())
//                .countOfRooms(form.getCountOfRooms())
//                .price(form.getPrice())
//                .build();
//
//        productsRepository.save(product);
//    }
}
