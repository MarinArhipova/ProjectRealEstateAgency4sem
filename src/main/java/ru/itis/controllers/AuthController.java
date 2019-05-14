package ru.itis.controllers;

import org.springframework.ui.Model;
import ru.itis.forms.SignInForm;
import ru.itis.forms.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.services.UsersService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {
    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/signUp")
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping(value = "/signUp")
    public String signUpUser(Model model, @RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName,
                             @RequestParam(name = "patronymic") String patronymic, @RequestParam(name = "email") String email,
                             @RequestParam(name = "phoneNumber") String phoneNumber, @RequestParam(name = "password") String password) {
        SignUpForm signUpForm = SignUpForm.builder()
                .firstName(firstName)
                .lastName(lastName)
                .patronymic(patronymic)
                .email(email)
                .phoneNumber(phoneNumber)
                .hashPassword(password)
                .build();

        if (!usersService.checkReg(signUpForm.getEmail())) {
            usersService.signUp(signUpForm);
            return "redirect:/signIn";

        } else {
            model.addAttribute("error", "error");
            return "signUp";
        }
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String getSignInPage() {
        return "signIn";
    }

    @PostMapping(value = "/signIn")
    public String signIn(HttpServletResponse resp, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        SignInForm signInForm = SignInForm.builder()
                .email(email)
                .password(password)
                .build();

        String cookieValue = usersService.signIn(signInForm);
        if (cookieValue != null) {
            Cookie auth = new Cookie("auth", cookieValue);
            resp.addCookie(auth);
            return "redirect:/home";
        }
        return "signIn";
    }

    @GetMapping(value = "/logout")
    @ResponseBody
    public String logOut(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            cookies[i].setMaxAge(0);
            resp.addCookie(cookies[i]);
        }
        return "redirect:/signIn";
    }

//        Cookie cookie = null;
//        Cookie[] cookies = req.getCookies();
//        for (int i = 0; i < cookies.length; i++) {
//            if (cookies[i].getName().equals("auth")) {
//                cookie = cookies[i];
//            }
//        }
//        usersService.delete(cookie.getValue());
//        return "redirect:/signIn";
//    }

}
