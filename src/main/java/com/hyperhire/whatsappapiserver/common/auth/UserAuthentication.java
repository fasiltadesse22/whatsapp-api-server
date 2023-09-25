package com.hyperhire.whatsappapiserver.common.auth;

import com.hyperhire.whatsappapiserver.common.dto.Credential;
import com.hyperhire.whatsappapiserver.entity.WhatsappUser;
import com.hyperhire.whatsappapiserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserAuthentication {

    private final UserRepository userRepository;

    // Request scoped object
    private final CurrentUser currentUser;

    public void authenticateUser(Credential credential) throws IllegalArgumentException {
        WhatsappUser user = userRepository.findByUsernameAndPassword(credential.getUsername(), credential.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credential"));

        currentUser.setUser(user);
    }
}