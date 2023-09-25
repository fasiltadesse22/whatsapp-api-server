package com.hyperhire.whatsappapiserver.service.chatroom;

import com.hyperhire.whatsappapiserver.common.dto.chatroom.ChatRoomRequest;
import com.hyperhire.whatsappapiserver.common.dto.chatroom.ChatRoomResponse;
import com.hyperhire.whatsappapiserver.common.dto.chatroom.GetAllChatRoomsRequest;
import com.hyperhire.whatsappapiserver.common.dto.chatroom.ChatRoomListResponse;

public interface ChatRoomService {
    ChatRoomResponse createChatRoom(ChatRoomRequest createChatRoomRequest);
    ChatRoomListResponse getAllChatRooms(GetAllChatRoomsRequest getAllChatRoomsRequest);
    ChatRoomListResponse getCurrentUserChatRooms();
}
