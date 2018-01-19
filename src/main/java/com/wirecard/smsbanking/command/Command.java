package com.wirecard.smsbanking.command;

public interface Command {

	String execute(String[] parameters, String senderUsername);
	
}
