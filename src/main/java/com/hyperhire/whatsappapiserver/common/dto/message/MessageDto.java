package com.hyperhire.whatsappapiserver.common.dto.message;

import com.hyperhire.whatsappapiserver.entity.ContentType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private Long id;
    private ContentType contentType;
    private String filePath;
    private String text;
    private LocalDateTime createdOn;
}
