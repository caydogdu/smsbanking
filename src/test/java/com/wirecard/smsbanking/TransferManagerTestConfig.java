package com.wirecard.smsbanking;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.wirecard.smsbanking.service.TransferManager;

@Profile("test")
@Configuration
public class TransferManagerTestConfig {
	
    @Bean
    @Primary
    public TransferManager transferManagerService() {
        return Mockito.mock(TransferManager.class);
    }

}
