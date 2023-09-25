package com.hyperhire.whatsappapiserver.controller;

import com.hyperhire.whatsappapiserver.common.auth.UserAuthentication;
import com.hyperhire.whatsappapiserver.common.dto.user.*;
import com.hyperhire.whatsappapiserver.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserAuthentication userAuthentication;

    @PostMapping(value = "/users")
    public CreateUserResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {

        return userService.createUser(createUserRequest);
    }

    @PutMapping(value = "/users/join/chat-room")
    public JoinOrLeaveChatRoomResponse joinChatRoom(@Valid @RequestBody JoinOrLeaveChatRoomRequest joinOrLeaveChatRoomRequest) throws IllegalArgumentException {

        userAuthentication.authenticateUser(joinOrLeaveChatRoomRequest.getCredential());

        return userService.joinChatRoom(joinOrLeaveChatRoomRequest);
    }

    @PutMapping(value = "/users/leave/chat-room")
    public JoinOrLeaveChatRoomResponse leaveChatRoom(@Valid @RequestBody JoinOrLeaveChatRoomRequest leaveChatRoomRequest) throws IllegalArgumentException {

        userAuthentication.authenticateUser(leaveChatRoomRequest.getCredential());

        return userService.leaveChatRoom(leaveChatRoomRequest);
    }
}
