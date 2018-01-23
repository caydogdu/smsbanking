package com.wirecard.smsbanking.handler;

/**
 * 
 * @author caydogdu
 *
 * This is interface for sms handling
 */
public interface SMSHandler {
	/**
	* @param smsContent the incoming SMS command string.
	* @param senderDeviceId is a unique string that uniquely identifies the customer’s mobile device. 
	* 	The UserManager proves a means to identify the sender user.
	* @return The SMS content that should be returned to the user.
	*/
	String handleSmsRequest(String smsContent, String senderDeviceId);
}

