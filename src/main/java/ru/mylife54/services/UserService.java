package ru.mylife54.services;

import ru.mylife54.models.User;

import java.util.Collection;

public interface UserService {
    Collection<User> getAllUsers();
    User getUser(long id);
    User getUser(String nickname);
    User addUser(User user);
    User updateUser(User user);
    boolean removeUser(User user);
    boolean removeUser(long id);
}
