package ru.mylife54.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import ru.mylife54.models.User;
import ru.mylife54.repositories.UserRepo;
import ru.mylife54.services.StorageService;
import ru.mylife54.services.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StorageService storageService;

    @Override
    public Collection<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(long id) {
        return userRepo.findUserById(id);
    }

    @Override
    public User getUser(String nickname) {
        return userRepo.findUserByNickname(nickname);
    }

    @Override
    public User addUser(User user) {
        if(user.getImageProfile()!=null){
            String fileName = null;
            try {
                fileName = storageService.uploadFiles(new ArrayList<>(Arrays.asList(
                         MediaType.IMAGE_JPEG, MediaType.IMAGE_GIF, MediaType.IMAGE_PNG)),
                         user.getImageProfile()).get(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            user.setProfile(fileName);
        }
        userRepo.saveAndFlush(user);
        return userRepo.findUserByNickname(user.getNickname());
    }

    @Override
    public User updateUser(User user) {
        if (user != null && userRepo.findUserById(user.getId()) != null) {
            userRepo.saveAndFlush(user);
        }
        return user;
    }

    @Override
    public boolean removeUser(User user) {
        if (user != null && userRepo.findUserByNickname(user.getNickname()) != null) {
            userRepo.delete(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeUser(long id) {
        User user = userRepo.findUserById(id);
        if (user != null) {
            userRepo.delete(user);
            return true;
        } else {
            return false;
        }
    }
}
