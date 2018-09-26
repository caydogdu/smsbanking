package com.wirecard.smsbanking.command;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wirecard.smsbanking.service.TransferManager;
import com.wirecard.smsbanking.service.UserManager;

/**
 *
 * @author caydogdu
 *
 *         This is command class to transfer money
 */
public class SendMoneyCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(SendMoneyCommand.class);

    private UserManager userManager;

    private TransferManager transferManager;

    @Override
    public String execute(String[] parameters, String senderUsername) {

        BigDecimal amount = new BigDecimal(parameters[1]);
        String recipientUsername = parameters[2];

        if (!userManager.existsUser(recipientUsername)) {
            if (logger.isInfoEnabled()) {
                logger.error(String.format("Recipient username : %s not found.", recipientUsername));
            }
            return "ERR – NO USER";
        }

        if (userManager.getBalance(senderUsername).compareTo(amount) < 0) {
            if (logger.isInfoEnabled()) {
                logger.error(String.format("Insufficient funds for %s to transfer.", senderUsername));
            }
            return "ERR – INSUFFICIENT FUNDS";
        }

        transferManager.sendMoney(senderUsername, recipientUsername, amount);
        logger.info("Transfer completed.");

        return "OK";
    }

    @Override
    public void setTransferManager(TransferManager transferManager) {
        this.transferManager = transferManager;
    }

    @Override
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

}
