package com.hyperhire.whatsappapiserver.common.dto.message;

import com.hyperhire.whatsappapiserver.common.dto.Credential;
import com.hyperhire.whatsappapiserver.entity.ContentType;
import lombok.Data;

@Data
public class SendFileMessageRequest {
    private Long chatRoomId;
    private ContentType contentType;
    private Credential credential;
}
