package ru.mylife54.services.impl;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.mylife54.services.StorageService;
import ru.mylife54.services.UserService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private UserService userService;

    @Autowired
    private Tika tika;

    private String uploadPath;

    {
        try {
            uploadPath = new File(".").getCanonicalPath() + File.separator + "files";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileFullPath(String filename) {
        return uploadPath + File.separator + filename;
    }

    @Override
    public boolean deleteFile(String filename) {
        if (filename != null) {
            File file = new File(uploadPath+File.separator+filename);
            file.delete();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<String> uploadFiles(List<MediaType> mediatypes, MultipartFile... multipartFiles) throws IOException {
        File dir = new File(uploadPath);
        List<String> files = new ArrayList<>();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (multipartFiles.length >= 1) {
            String filename = "";
            InputStream fileInputStream = null;
            for (MultipartFile file : multipartFiles) {
                filename = new StringBuilder().append(UUID.randomUUID().toString())
                        .append(file.getOriginalFilename().hashCode()).toString();
                fileInputStream = file.getInputStream();
                for (MediaType mediaType : mediatypes) {
                    if (mediatypes.toString().contains(tika.detect(fileInputStream))) {
                        file.transferTo(new File(getFileFullPath(filename)));
                        File uploadedFile = new File(getFileFullPath(filename));
                        if (uploadedFile.exists()) {
                            files.add(filename);
                        }
                    } else {
                        System.out.println("Не соответствует mediatype");
                    }
                }
                fileInputStream.close();
            }
        }
        return files;
    }
};
