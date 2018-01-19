package com.wirecard.smsbanking.command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

	private static Map<String, Command> factories = new HashMap<String, Command>();

	private CommandFactory() {
	}

    static{
        factories.put("BALANCE", new GetBalanceCommand());
        factories.put("SEND", new SendMoneyCommand());
        factories.put("TOTAL", new TotalSentCommand());
    }
	
	public static Command getFactory(String factoryId) {
		return factories.get(factoryId);
	}
}
