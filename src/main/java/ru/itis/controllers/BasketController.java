package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.models.Basket;
import ru.itis.models.Product;
import ru.itis.models.User;
import ru.itis.repositories.BasketRepository;
import ru.itis.repositories.UsersRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BasketController {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping(value = "/basket")
    public String getBasketPage(HttpServletRequest request, ModelMap modelMap) {
        Cookie[] cookies = request.getCookies();
        String cookieValue = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth")) {
                cookieValue = cookie.getValue();
            }
        }
        User user = usersRepository.findByCookie(cookieValue);
        List<Product> products = basketRepository.findAllProductsByUserID(user);
        modelMap.addAttribute("products", products);
        return "basket";
    }

}
