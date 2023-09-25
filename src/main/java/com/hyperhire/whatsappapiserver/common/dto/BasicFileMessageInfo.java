package com.hyperhire.whatsappapiserver.common.dto;

import com.hyperhire.whatsappapiserver.entity.ContentType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BasicFileMessageInfo {
    private Long id;
    private ContentType contentType;
    private String filePath;
    private LocalDateTime createdOn;
}
