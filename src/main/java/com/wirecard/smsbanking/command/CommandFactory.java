package com.wirecard.smsbanking.command;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author caydogdu
 *
 *         This is a factory class for creating command If there is a new command class, it must be added to factories
 *         map
 *
 */
public class CommandFactory {

    private static Map<String, Command> factories = new HashMap<>();

    static {
        factories.put("BALANCE", new GetBalanceCommand());
        factories.put("SEND", new SendMoneyCommand());
        factories.put("TOTAL", new TotalSentCommand());
    }

    public static Command getFactory(String factoryId) {
        return factories.get(factoryId);
    }

    private CommandFactory() {
    }
}
