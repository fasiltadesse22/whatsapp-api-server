package com.hyperhire.whatsappapiserver.common.dto.chatroom;

import com.hyperhire.whatsappapiserver.common.dto.Credential;
import lombok.Data;

@Data
public class GetAllChatRoomsRequest {
    private Credential credential;
}
