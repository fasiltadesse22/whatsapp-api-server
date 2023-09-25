package com.hyperhire.whatsappapiserver.common.auth;

import com.hyperhire.whatsappapiserver.entity.WhatsappUser;
import lombok.Data;

@Data
public class CurrentUser {
    private WhatsappUser user;
}
