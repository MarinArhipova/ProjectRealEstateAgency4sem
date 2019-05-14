package ru.itis.services;

import org.springframework.stereotype.Component;
import ru.itis.forms.SignInForm;
import ru.itis.forms.SignUpForm;
import ru.itis.models.Auth;
import ru.itis.models.Basket;
import ru.itis.repositories.AuthRepository;
import ru.itis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.repositories.BasketRepository;
import ru.itis.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.UUID;

@Component
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthRepository authRepository;

    @Override
    public String signIn(SignInForm signInForm) {
        User user = usersRepository.findByEmail(signInForm.getEmail());

        if (user != null && passwordEncoder.matches(signInForm.getPassword(), user.getHashPassword())) {
            String cookieValue = UUID.randomUUID().toString();
            Auth auth = Auth.builder()
                    .userId(user.getUserID())
                    .cookieValue(cookieValue)
                    .build();
            authRepository.save(auth);
            return cookieValue;
        }
        return null;
    }

    @Override
    public void signUp(SignUpForm form) {
        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .patronymic(form.getPatronymic())
                .phoneNumber(form.getPhoneNumber())
                .email(form.getEmail())
                .hashPassword(passwordEncoder.encode(form.getHashPassword()))
                .build();
        usersRepository.save(user);

        Basket basket = Basket.builder()
                .userID(user.getUserID())
                .basketID(user.getUserID())
                .build();
        basketRepository.save(basket);
    }

    @Override
    public boolean checkReg(String email) {
        if (usersRepository.findByEmail(email) != null) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserByCookie(String cookie) {
        return usersRepository.findByCookie(cookie);
    }

    @Override
    public boolean isExistByCookie(String cookieValue) {
        if (authRepository.findByCookieValue(cookieValue) != null) {
            return true;
        }
        return false;
    }


}
