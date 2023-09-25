package com.hyperhire.whatsappapiserver.common.config;

import com.hyperhire.whatsappapiserver.common.auth.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class WhatsappApiServerConfig {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    @RequestScope
    public CurrentUser currentUser() {
        return new CurrentUser();
    }
}
