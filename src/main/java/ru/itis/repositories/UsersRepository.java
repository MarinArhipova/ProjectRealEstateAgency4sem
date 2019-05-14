package ru.itis.repositories;

import ru.itis.models.User;

public interface UsersRepository extends CrudRepository<User> {
    User findByEmail(String email);
//    User findById(Long id);
    User findByCookie(String cookieValue);
}
