package com.hyperhire.whatsappapiserver.common.dto.message;

import com.hyperhire.whatsappapiserver.common.dto.BasicChatRoomInfo;
import com.hyperhire.whatsappapiserver.common.dto.BasicTextMessageInfo;
import com.hyperhire.whatsappapiserver.common.dto.BasicUserInfo;
import lombok.Data;

@Data
public class SendTextMessageResponse {
    private BasicUserInfo sender;
    private BasicChatRoomInfo chatRoom;
    private BasicTextMessageInfo message;
}