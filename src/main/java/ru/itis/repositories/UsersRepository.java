package ru.itis.repositories;

import ru.itis.models.User;

public interface UsersRepository extends CrudRepository<User> {
    //    Optional<User> findByEmail(String email);
    User findByEmail(String email);

//    Optional<User> findOneByConfirmString(String confirmString);

    User findByCookie(String cookieValue);
//    boolean addProduct(User user, Product product);
}
