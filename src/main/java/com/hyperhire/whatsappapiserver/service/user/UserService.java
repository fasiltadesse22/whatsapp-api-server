package com.hyperhire.whatsappapiserver.service.user;

import com.hyperhire.whatsappapiserver.common.dto.user.*;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest createUserRequest);
    JoinOrLeaveChatRoomResponse joinChatRoom(JoinOrLeaveChatRoomRequest joinOrLeaveChatRoomRequest);

    JoinOrLeaveChatRoomResponse leaveChatRoom(JoinOrLeaveChatRoomRequest leaveChatRoomRequest);
}
