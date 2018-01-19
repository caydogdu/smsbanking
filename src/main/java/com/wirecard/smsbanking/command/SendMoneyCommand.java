package com.wirecard.smsbanking.command;

import java.math.BigDecimal;

import com.wirecard.smsbanking.service.TransferManager;
import com.wirecard.smsbanking.service.UserManager;

public class SendMoneyCommand implements Command {

	private UserManager userManager;
	
	private TransferManager transferManager;
	
	@Override
	public String execute(String[] parameters, String senderUsername) {
		
		BigDecimal amount = new BigDecimal(parameters[1]);
		String recipientUsername = parameters[2];
		
		if(!userManager.existsUser(recipientUsername)){
			return "ERR – NO USER";
		}
		
		if(userManager.getBalance(senderUsername).compareTo(amount) == -1){
			return "ERR – INSUFFICIENT FUNDS";
		}
		
		transferManager.sendMoney(senderUsername, recipientUsername, amount);
		
		return "OK";
	}

}
