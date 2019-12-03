package ru.mylife54.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mylife54.exceptions.MediaTypeFormatExeption;
import ru.mylife54.exceptions.UserNotFoundException;
import ru.mylife54.models.User;
import ru.mylife54.services.StorageService;
import ru.mylife54.services.UserService;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StorageService storageService;

    @GetMapping
    public String add(@ModelAttribute User user,
                      ModelMap modelMap
    ) throws IOException, MediaTypeFormatExeption, UserNotFoundException {
        userService.addUser(user);
        return "index";
    }

    @GetMapping("/{imgName}")
    public String remove(@PathVariable String imgName) {
        storageService.deleteFile(imgName);
        return "index";
    }
}
