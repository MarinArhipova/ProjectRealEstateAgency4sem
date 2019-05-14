package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
//
//    private Long currentUser(HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        for (int i = 0; i < cookies.length; i++) {
//            if (cookies[i].getName().equals("userId")) {
//                return Long.valueOf(cookies[i].getValue());
//            }
//        }
//        return null;
//    }

    @GetMapping(value = "/basket")
    public String getBasketPage(HttpServletRequest request, ModelMap modelMap) {
        Cookie[] cookies = request.getCookies();
        String cookieValue = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth")) {
                cookieValue = cookie.getValue();
            }
        }
//        Long userId = currentUser(request);
        User user = usersRepository.findByCookie(cookieValue);
//        User user = usersRepository.findById(userId);
        List<Product> products = basketRepository.findAllProductsByUserID(user);
        modelMap.addAttribute("products", products);
        return "basket";
    }

    @PostMapping(value = "/deleteproduct")
    public String addProductInBasket(@RequestParam("id") Long id, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String cookieValue = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth")) {
                cookieValue = cookie.getValue();
            }
        }
        User user = usersRepository.findByCookie(cookieValue);
        basketRepository.deleteOneProduct(basketRepository.getBasketByUserId(user.getUserID()).getBasketID(),id);
    return "redirect:/basket";
    }

    @PostMapping(value = "/mail")
    public String sendApp(HttpServletRequest request, ModelMap modelMap){
        Cookie[] cookies = request.getCookies();
        String cookieValue = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth")) {
                cookieValue = cookie.getValue();
            }
        }
        User user = usersRepository.findByCookie(cookieValue);
//        Long userId = currentUser(request);
//        User user = usersRepository.findById(userId);
        List<Product> products = basketRepository.findAllProductsByUserID(user);
        System.out.println("ФИО: "+ user.getFirstName()+" "+ user.getLastName() + " " + user.getPatronymic() + " " + user.getEmail());
        for (Product product : products) {
            System.out.println(product.getId() + "  " + product.getCategory()+ " " + product.getTitle());
        }
        basketRepository.deleteProductsByUserID(user.getUserID());
        return "redirect:/home";
    }

}
