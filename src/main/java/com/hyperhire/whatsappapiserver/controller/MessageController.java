package com.hyperhire.whatsappapiserver.controller;

import com.hyperhire.whatsappapiserver.common.auth.UserAuthentication;
import com.hyperhire.whatsappapiserver.common.dto.Credential;
import com.hyperhire.whatsappapiserver.common.dto.message.*;
import com.hyperhire.whatsappapiserver.common.util.FileHelper;
import com.hyperhire.whatsappapiserver.entity.ContentType;
import com.hyperhire.whatsappapiserver.service.message.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    private final UserAuthentication userAuthentication;

    @PostMapping(value = "/text-message")
    public SendTextMessageResponse sendTextMessage(@Valid @RequestBody SendTextMessageRequest sendTextMessageRequest) {

        userAuthentication.authenticateUser(sendTextMessageRequest.getCredential());

        return messageService.sendTextMessage(sendTextMessageRequest);
    }

    @PostMapping(value = "/file-message", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public SendFileMessageResponse sendFileMessage(@RequestPart("file") MultipartFile file,
                                                   @RequestPart("username") String username,
                                                   @RequestPart("password") String password,
                                                   @RequestPart("chat-room-id") Long chatRoomId,
                                                   @RequestPart("content-type") String contentType
    ) {

        userAuthentication.authenticateUser(new Credential(username, password));

        return messageService.sendFileMessage(file, ContentType.valueOf(contentType), chatRoomId);
    }

    @GetMapping(value = "/message/chat-room/{chatRoomId}")
    public GetMessageByUserAndChatRoomResponse getMessageByUserAndChatRoom(@RequestBody Credential credential,
                                                                           @PathVariable Long chatRoomId) {

        userAuthentication.authenticateUser(credential);

        return messageService.getMessageByUserAndChatRoom(chatRoomId);
    }
}
