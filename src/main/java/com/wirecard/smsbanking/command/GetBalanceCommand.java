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
 *         This is command class to getting balance of customer
 */
public class GetBalanceCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GetBalanceCommand.class);

    private UserManager userManager;

    @Override
    public String execute(String[] parameters, String senderUsername) {

        BigDecimal balance = userManager.getBalance(senderUsername);
        if (logger.isInfoEnabled()) {
            logger.info(String.format("Returning %s for balance of %s", balance, senderUsername));
        }

        return String.valueOf(balance);

    }

    @Override
    public void setTransferManager(TransferManager transferManager) {
        // no need to implement
    }

    @Override
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

}
