package com.hyperhire.whatsappapiserver.controller;

import com.hyperhire.whatsappapiserver.common.auth.CurrentUser;
import com.hyperhire.whatsappapiserver.common.auth.UserAuthentication;
import com.hyperhire.whatsappapiserver.common.dto.Credential;
import com.hyperhire.whatsappapiserver.common.dto.chatroom.ChatRoomRequest;
import com.hyperhire.whatsappapiserver.common.dto.chatroom.ChatRoomResponse;
import com.hyperhire.whatsappapiserver.common.dto.chatroom.GetAllChatRoomsRequest;
import com.hyperhire.whatsappapiserver.common.dto.chatroom.ChatRoomListResponse;
import com.hyperhire.whatsappapiserver.service.chatroom.ChatRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final UserAuthentication userAuthentication;
    private final ChatRoomService chatRoomService;

    private final CurrentUser currentUser;

    @PostMapping(value = "/chat-rooms")
    public ChatRoomResponse createChatRoom(@Valid @RequestBody ChatRoomRequest createChatRoomRequest) throws IllegalArgumentException {

        userAuthentication.authenticateUser(createChatRoomRequest.getCredential());

        return chatRoomService.createChatRoom(createChatRoomRequest);
    }

    @GetMapping(value = "/chat-rooms/all")
    public ChatRoomListResponse viewAllChatRooms(GetAllChatRoomsRequest getAllChatRoomsRequest) throws IllegalArgumentException {

        userAuthentication.authenticateUser(getAllChatRoomsRequest.getCredential());

        return chatRoomService.getAllChatRooms(getAllChatRoomsRequest);
    }

    @GetMapping(value = "/chat-rooms/current-user")
    public ChatRoomListResponse getChatRoomsByUser(@RequestBody Credential credential) {

        userAuthentication.authenticateUser(credential);

        return chatRoomService.getCurrentUserChatRooms();
    }
}
