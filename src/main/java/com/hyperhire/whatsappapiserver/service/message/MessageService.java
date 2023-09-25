package com.hyperhire.whatsappapiserver.service.message;

import com.hyperhire.whatsappapiserver.common.dto.message.*;
import com.hyperhire.whatsappapiserver.entity.ContentType;
import org.springframework.web.multipart.MultipartFile;

public interface MessageService {
    SendTextMessageResponse sendTextMessage(SendTextMessageRequest sendTextMessageRequest);
    SendFileMessageResponse sendFileMessage(MultipartFile file, ContentType contentType, Long chatRoomId);
    GetMessageByUserAndChatRoomResponse getMessageByUserAndChatRoom(Long chatRoomId);
}
