package com.wirecard.smsbanking.service;

import java.math.BigDecimal;

public interface UserManager {
	
	boolean existsUser(String username);
	
	BigDecimal getBalance(String username);
	
	String getUserNameForDeviceId(String deviceId);
}

