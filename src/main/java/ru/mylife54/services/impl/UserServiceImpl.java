package ru.mylife54.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.mylife54.exceptions.MediaTypeFormatExeption;
import ru.mylife54.exceptions.UserNotFoundException;
import ru.mylife54.models.User;
import ru.mylife54.repositories.UserRepo;
import ru.mylife54.services.StorageService;
import ru.mylife54.services.UserService;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.*;

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
    public User addUser(User user) throws UserNotFoundException {
        return Optional.ofNullable(userRepo.saveAndFlush(user))
                .orElseThrow(() -> new UserNotFoundException("Пользователь не сохранён"));
    }

    @Override
    public User updateUser(User user, MultipartFile profile) throws IOException, MediaTypeFormatExeption {
        User userFromDb = userRepo.findUserById(user.getId());
        if (user != null && userFromDb != null) {
            if (profile != null) {
                if (userFromDb.getProfile() != null) {
                    storageService.deleteFile(userFromDb.getProfile());
                }
                String profileName = storageService.uploadFiles(new ArrayList<>(Arrays.asList(
                        MediaType.IMAGE_JPEG,
                        MediaType.IMAGE_GIF,
                        MediaType.IMAGE_PNG)), profile).get(0);
            }
            Set<String> ignoredFields = new HashSet<>();
            ignoredFields.add("id");
            for (PropertyDescriptor pds : new BeanWrapperImpl().getPropertyDescriptors()) {
                if (pds.getValue(pds.getName())==null){
                    ignoredFields.add(pds.getName());
                }
            }
            BeanUtils.copyProperties(user, userFromDb, ignoredFields.toArray(new String[ignoredFields.size()]));
            userRepo.saveAndFlush(user);
        }
        return user;
    }

    @Override
    public boolean removeUser(User user) {
        if (user != null && userRepo.findUserByNickname(user.getNickname()) != null) {
            String profile = user.getProfile();
            if (profile != null) {
                storageService.deleteFile(profile);
            }
            userRepo.delete(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeUser(long id) {
        boolean result = false;
        User user = userRepo.findUserById(id);
        if (user != null) {
            result = removeUser(user);
        }
        return result;
    }
}
