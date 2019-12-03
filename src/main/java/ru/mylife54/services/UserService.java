package ru.mylife54.services;

import org.springframework.web.multipart.MultipartFile;
import ru.mylife54.exceptions.MediaTypeFormatExeption;
import ru.mylife54.exceptions.UserNotFoundException;
import ru.mylife54.models.User;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface UserService {
    Collection<User> getAllUsers();

    User getUser(long id);

    User getUser(String nickname);

    User addUser(User user) throws UserNotFoundException;

    User updateUser(User user, MultipartFile profile) throws IOException, MediaTypeFormatExeption;

    boolean removeUser(User user);

    boolean removeUser(long id);
}
