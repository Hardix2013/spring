package ru.mylife54.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mylife54.models.User;
import ru.mylife54.services.StorageService;
import ru.mylife54.services.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StorageService storageService;

    @GetMapping
    public String add(@ModelAttribute User user,
                      @RequestParam("file") MultipartFile template,
                      ModelMap modelMap
    ) throws IOException {
        System.out.println(storageService.uploadFiles(new ArrayList<>(Arrays.asList(MediaType.IMAGE_JPEG)), template));
        return "index";
    }

    @GetMapping("/{imgName}")
    public String remove(@PathVariable String imgName) {
        storageService.deleteFile(imgName);
        return "index";
    }
}
