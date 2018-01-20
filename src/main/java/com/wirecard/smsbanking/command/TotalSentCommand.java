package com.wirecard.smsbanking.command;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wirecard.smsbanking.service.TransferManager;
import com.wirecard.smsbanking.service.UserManager;

public class TotalSentCommand implements Command {

	private static final Logger logger = LoggerFactory.getLogger(TotalSentCommand.class);
	
	private UserManager userManager;
	
	private TransferManager transferManager;
	
	@Override
	public String execute(String[] parameters, String senderUsername) {
		
		logger.info("total sent request for " + senderUsername);
		StringBuilder sb = new StringBuilder();
		
		for(int i = 2; i < parameters.length; i++){
			String recipientUsername = parameters[i];
			logger.info("Recipient username : " + recipientUsername);
			
			if(!userManager.existsUser(recipientUsername)){
				logger.error("Recipient username : " + recipientUsername + " not found.");
				return "ERR – NO USER";
			}
			
			List<BigDecimal> allTransactions = transferManager.getAllTransactions(senderUsername, recipientUsername);
			long total = allTransactions.stream().mapToLong(t ->t.longValue()).sum();
			if(sb.length() > 0){
				sb.append(",");
			}
			sb.append(String.valueOf(total));

		}
		
		return sb.toString();
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
