package com.wirecard.smsbanking.command;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wirecard.smsbanking.service.TransferManager;
import com.wirecard.smsbanking.service.UserManager;

/**
 *
 * @author caydogdu
 *
 *         This is command class to calculate total amounts of transfers
 */
public class TotalSentCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(TotalSentCommand.class);

    private UserManager userManager;

    private TransferManager transferManager;

    @Override
    public String execute(String[] parameters, String senderUsername) {

        StringBuilder sb = new StringBuilder();

        for (int i = 2; i < parameters.length; i++) {
            String recipientUsername = parameters[i];
            if (!userManager.existsUser(recipientUsername)) {
                if (logger.isInfoEnabled()) {
                    logger.error(String.format("Recipient username : %s not found.", recipientUsername));
                }
                return "ERR – NO USER";
            }

            List<BigDecimal> allTransactions = transferManager.getAllTransactions(senderUsername, recipientUsername);
            long total = allTransactions.stream().mapToLong(BigDecimal::longValue).sum();
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(String.valueOf(total));

        }

        return sb.toString();
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
