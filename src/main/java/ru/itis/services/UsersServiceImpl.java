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

import java.util.List;
import java.util.UUID;

@Component
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;

//    @Autowired
//    private EmailService emailService;


    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthRepository authRepository;

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public String signIn(SignInForm signInForm) {
//        Optional<User> userOptional = usersRepository.findByEmail(signInForm.getEmail());
//        User user;
        User user = usersRepository.findByEmail(signInForm.getEmail());

//        if (userOptional.isPresent()) {
//            user = userOptional.get();
//            if (!passwordEncoder.matches(signInForm.getPassword(), user.getHashPassword())) {
//                throw new IllegalArgumentException("Wrong password or email");
//            }
//        } else throw new IllegalArgumentException("Wrong password or email");


        if (user != null && passwordEncoder.matches(signInForm.getPassword(), user.getHashPassword())) {
            String cookieValue = UUID.randomUUID().toString();
            Auth auth = Auth.builder()
//                    .user(user)
                    .id(user.getUserID())
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

//        String text = "<a href='http://localhost:8080/confirm/" + user.getConfirmString() + "'>" +"Пройдите по ссылке" + "</a>";
//        System.out.println(text);
//        emailService.sendMail("Подтвреждение регистрации", text, user.getEmail());


        Basket basket = Basket.builder()
                .userID(user.getUserID())
                .basketID(user.getUserID())
                .build();
        basketRepository.save(basket);
    }

//    @Override
//    public boolean checkReg(String email) {
//        if (authRepository.findOneByEmail(email) != null) {
//            return true;
//        }
//        return false;
//    }

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
