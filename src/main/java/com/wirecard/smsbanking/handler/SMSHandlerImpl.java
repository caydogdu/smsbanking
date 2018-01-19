package com.wirecard.smsbanking.handler;

import com.wirecard.smsbanking.command.Command;
import com.wirecard.smsbanking.command.CommandFactory;
import com.wirecard.smsbanking.service.UserManager;

public class SMSHandlerImpl implements SMSHandler {

	private UserManager userManager; 
	
	@Override
	public String handleSmsRequest(String smsContent, String senderDeviceId) {
		String senderUsername = userManager.getUserNameForDeviceId(senderDeviceId);
		String[] parameters = smsContent.split("-");
		String commandType = parameters[0];
		Command command = CommandFactory.getFactory(commandType);
		String response = command.execute(parameters, senderUsername);
		return response;
	}

}
