package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.models.Product;
import ru.itis.models.User;
import ru.itis.services.BasketService;
import ru.itis.services.UsersService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BasketController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private UsersService usersService;

    private String currentUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();  //Извлечь информацию cookie из запроса можно с помощью метода
        //getCookies() объекта HttpServletRequest, который возвращает массив объектов, составляющих этот файл.
        //После этого для каждого объекта класса Cookie можно вызвать метод
        //getValue(), который возвращает строку String c содержимым блока cookie.
        //Объект Cookie имеет целый ряд параметров: путь, домен, номер версии, время жизни, комментарий. Одним
        // из важнейших является срок жизни в секундах от момента первой отправки клиенту. Если параметр не указан,
        // то cookie существует только до момента первого закрытия браузера.
        String cookieValue = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth")) { //getName(): возвращает ключ кук
                cookieValue = cookie.getValue();
            }
        }
        return cookieValue;
    }

    @GetMapping(value = "/basket")
    public String getBasketPage(HttpServletRequest request, ModelMap modelMap) {
        String cookieValue =currentUser(request);
        User user = usersService.getUserByCookie(cookieValue);
        List<Product> products = basketService.findAllProductsByUserID(user);
        modelMap.addAttribute("products", products);
        return "basket";
    }

    @PostMapping(value = "/deleteproduct")
    @ResponseBody
    public String deleteProductInBasketAsJson(@RequestParam("id") Long id, HttpServletRequest request) {
        String cookieValue =currentUser(request);
        User user = usersService.getUserByCookie(cookieValue);
        basketService.deleteOneProduct(basketService.getBasketByUserId(user.getUserID()).getBasketID(),id);
        return "redirect:/basket";
    }

    @PostMapping(value = "/mail")
    public String sendApp(HttpServletRequest request, ModelMap modelMap){
        String cookieValue =currentUser(request);
        User user = usersService.getUserByCookie(cookieValue);
        List<Product> products = basketService.findAllProductsByUserID(user);
        System.out.println("ФИО: "+ user.getFirstName()+" "+ user.getLastName() + " " + user.getPatronymic() + " " + user.getEmail());
        for (Product product : products) {
            System.out.println(product.getId() + "  " + product.getCategory()+ " " + product.getTitle());
        }
        basketService.deleteProductsByUserID(user.getUserID());
        return "redirect:/home";
    }

}
