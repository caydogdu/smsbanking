package com.wirecard.smsbanking;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wirecard.smsbanking.handler.SMSHandlerImpl;
import com.wirecard.smsbanking.service.TransferManager;
import com.wirecard.smsbanking.service.UserManager;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SMSHandlerImplTest {

	private SMSHandlerImpl handler;
	
	@Autowired
    private UserManager userManager;
	
	@Autowired
    private TransferManager transferManager;
	
	@Test
	public void getBalance() throws Exception {
		
		handler = new SMSHandlerImpl();
		
		String smsContent = "BALANCE";
		String senderDeviceId = "senderDeviceId";
		
		handler.setUserManager(userManager);
		Mockito.when(userManager.getUserNameForDeviceId("senderDeviceId")).thenReturn("cemil");
		Mockito.when(userManager.getBalance("cemil")).thenReturn(new BigDecimal(1500));
		
		String response = handler.handleSmsRequest(smsContent, senderDeviceId);
		assertEquals("1500", response);
		
	}
	
	@Test
	public void sendMoneySuccess() throws Exception {
		
		handler = new SMSHandlerImpl();
		
		String smsContent = "SEND-100-FFRITZ";
		String senderDeviceId = "senderDeviceId";
		
		handler.setUserManager(userManager);
		handler.setTransferManager(transferManager);
		Mockito.when(userManager.getUserNameForDeviceId("senderDeviceId")).thenReturn("cemil");
		Mockito.when(userManager.existsUser("FFRITZ")).thenReturn(true);
		Mockito.when(userManager.getBalance("cemil")).thenReturn(new BigDecimal(1500));
		
		String response = handler.handleSmsRequest(smsContent, senderDeviceId);
		assertEquals("OK", response);
		
	}
	
	@Test
	public void sendMoneyInsufficientFunds() throws Exception {
		
		handler = new SMSHandlerImpl();
		
		String smsContent = "SEND-100-FFRITZ";
		String senderDeviceId = "senderDeviceId";
		
		handler.setUserManager(userManager);
		handler.setTransferManager(transferManager);
		Mockito.when(userManager.getUserNameForDeviceId("senderDeviceId")).thenReturn("cemil");
		Mockito.when(userManager.existsUser("FFRITZ")).thenReturn(true);
		Mockito.when(userManager.getBalance("cemil")).thenReturn(new BigDecimal(90));
		
		String response = handler.handleSmsRequest(smsContent, senderDeviceId);
		assertEquals("ERR – INSUFFICIENT FUNDS", response);
		
	}
	
	@Test
	public void sendMoneyNoUser() throws Exception {
		
		handler = new SMSHandlerImpl();
		
		String smsContent = "SEND-100-FFRITZ";
		String senderDeviceId = "senderDeviceId";
		
		handler.setUserManager(userManager);
		handler.setTransferManager(transferManager);
		Mockito.when(userManager.getUserNameForDeviceId("senderDeviceId")).thenReturn("cemil");
		Mockito.when(userManager.existsUser("FFRITZ")).thenReturn(false);
		Mockito.when(userManager.getBalance("cemil")).thenReturn(new BigDecimal(90));
		
		String response = handler.handleSmsRequest(smsContent, senderDeviceId);
		assertEquals("ERR – NO USER", response);
		
	}
	
	@Test
	public void totalSent() throws Exception {
		
		handler = new SMSHandlerImpl();
		
		String smsContent = "TOTAL-SENT-FFRITZ";
		String senderDeviceId = "senderDeviceId";
		
		List<BigDecimal> allTransactions = new ArrayList<>();
		allTransactions.add(new BigDecimal(560));
		
		handler.setUserManager(userManager);
		handler.setTransferManager(transferManager);
		Mockito.when(userManager.getUserNameForDeviceId("senderDeviceId")).thenReturn("cemil");
		Mockito.when(userManager.existsUser("FFRITZ")).thenReturn(true);
		Mockito.when(transferManager.getAllTransactions("cemil", "FFRITZ")).thenReturn(allTransactions);
		
		String response = handler.handleSmsRequest(smsContent, senderDeviceId);
		assertEquals("560", response);
		
	}
	
	@Test
	public void totalSentMultiUser() throws Exception {
		
		handler = new SMSHandlerImpl();
		
		String smsContent = "TOTAL-SENT-FFRITZ-MSMITH";
		String senderDeviceId = "senderDeviceId";
		
		List<BigDecimal> allTransactions1 = new ArrayList<>();
		allTransactions1.add(new BigDecimal(560));
		
		List<BigDecimal> allTransactions2 = new ArrayList<>();
		allTransactions2.add(new BigDecimal(250));
		
		handler.setUserManager(userManager);
		handler.setTransferManager(transferManager);
		Mockito.when(userManager.getUserNameForDeviceId("senderDeviceId")).thenReturn("cemil");
		Mockito.when(userManager.existsUser("FFRITZ")).thenReturn(true);
		Mockito.when(userManager.existsUser("MSMITH")).thenReturn(true);
		Mockito.when(transferManager.getAllTransactions("cemil", "FFRITZ")).thenReturn(allTransactions1);
		Mockito.when(transferManager.getAllTransactions("cemil", "MSMITH")).thenReturn(allTransactions2);
		
		String response = handler.handleSmsRequest(smsContent, senderDeviceId);
		assertEquals("560,250", response);
		
	}
	
	@Test
	public void totalSentNoUser() throws Exception {
		
		handler = new SMSHandlerImpl();
		
		String smsContent = "TOTAL-SENT-FFRITZ";
		String senderDeviceId = "senderDeviceId";

		handler.setUserManager(userManager);
		handler.setTransferManager(transferManager);
		Mockito.when(userManager.getUserNameForDeviceId("senderDeviceId")).thenReturn("cemil");
		Mockito.when(userManager.existsUser("FFRITZ")).thenReturn(false);
		
		String response = handler.handleSmsRequest(smsContent, senderDeviceId);
		assertEquals("ERR – NO USER", response);
		
	}
	
	@Test
	public void unknownCommand() throws Exception {
		
		handler = new SMSHandlerImpl();
		
		String smsContent = "XYZ";
		String senderDeviceId = "senderDeviceId";
		
		handler.setUserManager(userManager);
		
		String response = handler.handleSmsRequest(smsContent, senderDeviceId);
		assertEquals("ERR – UNKNOWN COMMAND", response);
		
	}
	
}