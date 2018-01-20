package com.wirecard.smsbanking;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.wirecard.smsbanking.service.UserManager;

@Profile("test")
@Configuration
public class UserManagerTestConfig {
	
    @Bean
    @Primary
    public UserManager userManagerService() {
        return Mockito.mock(UserManager.class);
    }

}
