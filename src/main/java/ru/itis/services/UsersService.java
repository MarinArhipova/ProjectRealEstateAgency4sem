package ru.itis.services;

import ru.itis.forms.SignInForm;
import ru.itis.forms.SignUpForm;
import ru.itis.models.User;

public interface UsersService {
    String signIn(SignInForm signInForm);
    void signUp(SignUpForm form);
    User getUserByCookie(String cookie);
    boolean isExistByCookie(String cookieValue);
    boolean checkReg(String email);
}
