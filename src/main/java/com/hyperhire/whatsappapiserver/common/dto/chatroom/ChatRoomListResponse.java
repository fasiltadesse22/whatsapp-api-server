package com.hyperhire.whatsappapiserver.common.dto.chatroom;

import com.hyperhire.whatsappapiserver.common.dto.BasicChatRoomInfo;
import lombok.Data;

import java.util.List;

@Data
public class ChatRoomListResponse {
    private List<BasicChatRoomInfo> chatRooms;
}
