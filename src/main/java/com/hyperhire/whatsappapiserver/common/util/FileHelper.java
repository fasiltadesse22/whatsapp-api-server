package com.hyperhire.whatsappapiserver.common.util;

import com.hyperhire.whatsappapiserver.entity.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileHelper {

    @Value("${minimum.video.size}")
    private long minimumVideoSize;

    public void upload(MultipartFile file, Path path, ContentType contentType) {

        long fileSizeInMb = file.getSize() / 1024;

        if (contentType.equals(ContentType.VIDEO) && fileSizeInMb < minimumVideoSize)
            throw new RuntimeException("Video size is less than " + minimumVideoSize);

        try {
            Files.copy(file.getInputStream(), path);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
}

