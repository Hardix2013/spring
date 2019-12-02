package ru.mylife54.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mylife54.models.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findUserById(long id);

    User findUserByNickname(String name);
}
