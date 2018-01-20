package com.wirecard.smsbanking.command;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wirecard.smsbanking.service.TransferManager;
import com.wirecard.smsbanking.service.UserManager;

public class GetBalanceCommand implements Command {

	private static final Logger logger = LoggerFactory.getLogger(GetBalanceCommand.class);
	
	private UserManager userManager;
	
	@Override
	public String execute(String[] parameters, String senderUsername) {
		
		logger.info("Getting balance for " + senderUsername);
		BigDecimal balance = userManager.getBalance(senderUsername);
		
		logger.info("Returning " + balance + " for balance of " + senderUsername);
		
		return String.valueOf(balance);
		
	}

	@Override
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	@Override
	public void setTransferManager(TransferManager transferManager) {
	}

}
