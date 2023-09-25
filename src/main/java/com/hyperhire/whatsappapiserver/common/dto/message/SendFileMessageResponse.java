package com.hyperhire.whatsappapiserver.common.dto.message;

import com.hyperhire.whatsappapiserver.common.dto.BasicChatRoomInfo;
import com.hyperhire.whatsappapiserver.common.dto.BasicFileMessageInfo;
import com.hyperhire.whatsappapiserver.common.dto.BasicUserInfo;
import lombok.Data;

@Data
public class SendFileMessageResponse {
    private BasicUserInfo sender;
    private BasicChatRoomInfo chatRoom;
    private BasicFileMessageInfo message;
}
