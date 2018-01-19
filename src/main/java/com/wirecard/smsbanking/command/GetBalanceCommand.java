package com.wirecard.smsbanking.command;

import java.math.BigDecimal;

import com.wirecard.smsbanking.service.UserManager;

public class GetBalanceCommand implements Command {

	private UserManager userManager;
	
	@Override
	public String execute(String[] parameters, String senderUsername) {
		
		BigDecimal balance = userManager.getBalance(senderUsername);
		return String.valueOf(balance);
		
	}

}
