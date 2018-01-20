package com.wirecard.smsbanking.command;

import com.wirecard.smsbanking.service.TransferManager;
import com.wirecard.smsbanking.service.UserManager;

public interface Command {

	void setUserManager(UserManager userManager);
	
	void setTransferManager(TransferManager transferManager);
	
	String execute(String[] parameters, String senderUsername);
	
	
}
