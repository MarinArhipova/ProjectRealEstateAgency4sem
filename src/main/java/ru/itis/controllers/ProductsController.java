package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
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

    @Autowired
    private UsersService usersService;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping(value = "/shop/{category}")
//    public String getProductsPage(@RequestParam(name="category") String category, ModelMap modelMap) {
    public String getProductsPage(HttpServletRequest req, @PathVariable String category, ModelMap modelMap) {
        Cookie authCookie = authCookie(req.getCookies());
        if (authCookie != null) {
//            User user = usersService.getUserByCookie(authCookie.getValue());
            List<Product> products = productsService.getAll(category);
            modelMap.addAttribute("products", products);
            return "shop";
        }
        return "signIn";
    }

    @PostMapping(value = "/shop")
    public void addProductInBasket(@RequestParam("id") Long id, HttpServletRequest request) {
        Cookie authCookie = authCookie(request.getCookies());
        if (authCookie != null) {
            String auth = authCookie.getValue();
            User user = usersRepository.findByCookie(auth);
            Basket usersBasket = basketRepository.getBasketByUserId(user.getUserID());
            basketRepository.addProductToBasket(usersBasket.getBasketID(), id);
        }
    }

    private Cookie authCookie(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    if (usersService.isExistByCookie(cookie.getValue())) {
                        return cookie;
                    }
                }
            }
        }
        return null;
    }

//    @PostMapping(value = "/users")
//    public String addUser(User user) {
//        usersService.addUser(user);
//        return "redirect:/users";
//    }

//    @PostMapping(value = "/api/shop", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public List<Product> getProductsAsJson( @RequestParam(name="category") String category) {
//        return productsService.getAll(category);
//    }

//    @PostMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public List<User> addUserAsJson(@RequestBody User user) {
//        usersService.addUser(user);
//        return usersService.getAllUsers();
//    }
}
