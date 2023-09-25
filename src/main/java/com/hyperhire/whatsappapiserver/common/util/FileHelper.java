package com.hyperhire.whatsappapiserver.common.util;

import com.hyperhire.whatsappapiserver.entity.ContentType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileHelper {

    public void upload(MultipartFile file, Path path) {
        try {
            Files.copy(file.getInputStream(), path);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
}

