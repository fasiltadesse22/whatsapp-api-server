package com.hyperhire.whatsappapiserver.common.dto.chatroom;

import com.hyperhire.whatsappapiserver.common.dto.Credential;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatRoomRequest {
    @NotBlank(message = "name is required")
    private String name;
    private Credential credential;
}
