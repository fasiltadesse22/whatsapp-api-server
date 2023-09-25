package com.hyperhire.whatsappapiserver.common.dto.user;

import lombok.Data;

@Data
public class CreateUserResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
}