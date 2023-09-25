package com.hyperhire.whatsappapiserver.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Credential {
    @NotBlank(message = "first name is required")
    private String username;
    @NotBlank(message = "first name is required")
    private String password;
}
