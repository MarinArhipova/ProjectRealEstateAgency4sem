package ru.itis.controllers;

import ru.itis.models.Basket;
import ru.itis.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.models.User;
import ru.itis.services.BasketService;
import ru.itis.services.ProductsService;
import ru.itis.services.UsersService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private UsersService usersService;

    private String currentUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String cookieValue = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth")) {
                cookieValue = cookie.getValue();
            }
        }
        return cookieValue;
    }

    @GetMapping(value = "/shop/{category}")
    public String getProductsPage(HttpServletRequest req, @PathVariable String category, ModelMap modelMap) {
            List<Product> products = productsService.getAll(category);
            modelMap.addAttribute("products", products);
            return "shop";
    }

    @PostMapping(value = "/shop")
    public void addProductInBasket(@RequestParam("id") Long id, HttpServletRequest request) {
//        System.out.println(id + " Ура, я дошел");
        String cookieValue =currentUser(request);
        User user = usersService.getUserByCookie(cookieValue);
        Basket usersBasket = basketService.getBasketByUserId(user.getUserID());
        List<Product> allproducts = basketService.findAllProductsByUserID(user);
        int i = 0;
        for (Product all : allproducts) {
            if (all.getId().equals(id)) i++;
        }
        if (i == 0) {
            basketService.addProductInBasket(usersBasket.getBasketID(), id);
        }
    }


}
