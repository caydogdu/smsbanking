package com.wirecard.smsbanking.command;

import com.wirecard.smsbanking.service.TransferManager;
import com.wirecard.smsbanking.service.UserManager;

/**
 * 
 * @author caydogdu
 *
 * This is a interface for commands
 * If there is a new command type, 
 * 	new command class must be implement his insterface
 * 
 */
public interface Command {

	void setUserManager(UserManager userManager);
	
	void setTransferManager(TransferManager transferManager);
	
	/**
	 * 
	 * @param parameters are the divided parts of the sms content
	 * @param senderUsername
	 * @return response of command
	 */
	String execute(String[] parameters, String senderUsername);
	
}
