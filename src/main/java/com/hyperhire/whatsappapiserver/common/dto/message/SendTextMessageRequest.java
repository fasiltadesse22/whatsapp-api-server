package com.hyperhire.whatsappapiserver.common.dto.message;

import com.hyperhire.whatsappapiserver.common.dto.Credential;
import com.hyperhire.whatsappapiserver.entity.ContentType;
import lombok.Data;

@Data
public class SendTextMessageRequest {
    private Long chatRoomId;
    private ContentType contentType;
    private String text;
    private Credential credential;
}