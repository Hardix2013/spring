package ru.mylife54.services;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageService {

    boolean deleteFile(String filename);

    List<String> uploadFiles(List<MediaType> mediaType, MultipartFile... multipartFiles) throws IOException;
//
//    String[] uploadFiles(MultipartFile... multipartFiles);
}
