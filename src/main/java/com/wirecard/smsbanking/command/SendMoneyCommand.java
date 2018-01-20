package com.wirecard.smsbanking.command;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wirecard.smsbanking.service.TransferManager;
import com.wirecard.smsbanking.service.UserManager;

public class SendMoneyCommand implements Command {

	private static final Logger logger = LoggerFactory.getLogger(SendMoneyCommand.class);
	
	private UserManager userManager;
	
	private TransferManager transferManager;
	
	@Override
	public String execute(String[] parameters, String senderUsername) {
		
		logger.info("Send money request for " + senderUsername);
		
		BigDecimal amount = new BigDecimal(parameters[1]);
		String recipientUsername = parameters[2];
		logger.info("Recipient username : " + recipientUsername + ", amount : " + amount);
		
		if(!userManager.existsUser(recipientUsername)){
			logger.error("Recipient username : " + recipientUsername + " not found.");
			return "ERR – NO USER";
		}
		
		if(userManager.getBalance(senderUsername).compareTo(amount) == -1){
			logger.error("Insufficient funds for " + senderUsername + " to transfer.");
			return "ERR – INSUFFICIENT FUNDS";
		}
		
		transferManager.sendMoney(senderUsername, recipientUsername, amount);
		logger.info("Transfer completed.");
		
		return "OK";
	}
	
	@Override
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	@Override
	public void setTransferManager(TransferManager transferManager) {
		this.transferManager = transferManager;
	}

}
