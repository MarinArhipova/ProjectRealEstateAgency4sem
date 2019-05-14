package ru.itis.controllers;

import ru.itis.models.Basket;
import ru.itis.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.models.User;
import ru.itis.repositories.BasketRepository;
import ru.itis.repositories.UsersRepository;
import ru.itis.services.ProductsService;
import ru.itis.services.UsersService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    private ProductsService productsService;
//
//    @Autowired
//    private UsersService usersService;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private UsersRepository usersRepository;

    private Long currentUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("userId")) {
                return Long.valueOf(cookies[i].getValue());
            }
        }
        return null;
    }

    @GetMapping(value = "/shop/{category}")
    public String getProductsPage(HttpServletRequest req, @PathVariable String category, ModelMap modelMap) {
//        Cookie authCookie = authCookie(req.getCookies());
//        if (authCookie != null) {
            List<Product> products = productsService.getAll(category);
            modelMap.addAttribute("products", products);
            return "shop";
//        }
//        return "signIn";
    }

    @PostMapping(value = "/shop")
    public void addProductInBasket(@RequestParam("id") Long id, HttpServletRequest request) {
        System.out.println(id + " Ура, я дошел");

        Cookie[] cookies = request.getCookies();
        String cookieValue = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth")) {
                cookieValue = cookie.getValue();
            }
        }
//        Long userId = currentUser(request);
//        if (authCookie != null) {
//            String auth = authCookie.getValue();
//            System.out.println("auth = "+auth);

            User user = usersRepository.findByCookie(cookieValue);
            Basket usersBasket = basketRepository.getBasketByUserId(user.getUserID());
//        Basket usersBasket = basketRepository.getBasketByUserId(userId);
//        User user = usersRepository.findById(userId);
        List<Product> allproducts = basketRepository.findAllProductsByUserID(user);
            int i=0;
            for (Product all: allproducts){
                if (all.getId().equals(id)) i++;
            }
            if (i==0){
            basketRepository.addProductToBasket(usersBasket.getBasketID(), id);}
        }


//    private Cookie authCookie(Cookie[] cookies) {
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("auth")) {
//                    if (usersService.isExistByCookie(cookie.getValue())) {
//                        return cookie;
//                    }
//                }
//            }
//        }
//        return null;
//    }

}
