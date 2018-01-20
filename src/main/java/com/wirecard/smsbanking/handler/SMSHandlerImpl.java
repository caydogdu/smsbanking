package com.wirecard.smsbanking.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wirecard.smsbanking.command.Command;
import com.wirecard.smsbanking.command.CommandFactory;
import com.wirecard.smsbanking.service.TransferManager;
import com.wirecard.smsbanking.service.UserManager;

public class SMSHandlerImpl implements SMSHandler {

	private static final Logger logger = LoggerFactory.getLogger(SMSHandlerImpl.class);
	
	private UserManager userManager;
	
	private TransferManager transferManager; 
	
	@Override
	public String handleSmsRequest(String smsContent, String senderDeviceId) {
		
		logger.info("Incoming SMS request for " + senderDeviceId);
		String senderUsername = userManager.getUserNameForDeviceId(senderDeviceId);
		logger.info("SMS content : " + smsContent);
		
		String[] parameters = smsContent.split("-");
		String commandType = parameters[0];
		Command command = CommandFactory.getFactory(commandType);

		if(command == null){
			return "ERR – UNKNOWN COMMAND";
		}
		
		command.setUserManager(userManager);
		command.setTransferManager(transferManager);
		
		String response = command.execute(parameters, senderUsername);
		logger.info("Returning response is " + response );
		
		return response;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public TransferManager getTransferManager() {
		return transferManager;
	}

	public void setTransferManager(TransferManager transferManager) {
		this.transferManager = transferManager;
	}

}
