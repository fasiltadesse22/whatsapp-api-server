package com.hyperhire.whatsappapiserver.common.dto.user;

import com.hyperhire.whatsappapiserver.common.dto.Credential;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JoinOrLeaveChatRoomRequest {
    @NotNull(message = "chat room id is required")
    private Long chatRoomId;
    private Credential credential;
}
