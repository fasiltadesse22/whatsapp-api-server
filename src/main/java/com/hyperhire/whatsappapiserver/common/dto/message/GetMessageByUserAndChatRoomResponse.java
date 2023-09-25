package com.hyperhire.whatsappapiserver.common.dto.message;

import com.hyperhire.whatsappapiserver.common.dto.BasicChatRoomInfo;
import com.hyperhire.whatsappapiserver.common.dto.BasicUserInfo;
import lombok.Data;

import java.util.List;

@Data
public class GetMessageByUserAndChatRoomResponse {
    private BasicUserInfo sender;
    private BasicChatRoomInfo chatRoom;
    private List<MessageDto> messages;
}
