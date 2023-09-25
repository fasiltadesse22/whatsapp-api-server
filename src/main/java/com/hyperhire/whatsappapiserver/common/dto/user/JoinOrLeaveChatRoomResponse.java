package com.hyperhire.whatsappapiserver.common.dto.user;

import com.hyperhire.whatsappapiserver.common.dto.BasicChatRoomInfo;
import com.hyperhire.whatsappapiserver.common.dto.BasicUserInfo;
import lombok.Data;

@Data
public class JoinOrLeaveChatRoomResponse {
    private BasicUserInfo user;
    private BasicChatRoomInfo chatRoom;
}