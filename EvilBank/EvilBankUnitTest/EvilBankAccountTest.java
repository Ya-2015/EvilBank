import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Test;

import banking.Account;
import banking.Transaction;


public class EvilBankAccountTest {

	
	
	
	@Test
	public void testAccountNum() {
		
		Account a = new Account();
		a.setAccountNum("399");
		assertEquals("399",a.getAccountNum());
		
		
	}

	@Test
	public void testCustName() {
		Account a = new Account();
		a.setCustName("Joe");
		assertEquals("Joe",a.getCustName());
	}
	@Test
	public void testAccountBalance(){
		Account a = new Account();
		a.setAccountBalance(50000.45);
		assertEquals(50000.45,a.getAccountBalance(),0);
	}
	public void testProcess(){
		Account a = new Account();
		Transaction t = new Transaction(500.0,"C",new GregorianCalendar(2013,01,12).getTime());
		
		String accountNum = "68790";
		a.setAccountNum("68790");
		
		t.setTransType("W");
		t.setAmount(500.0);
		a.setAccountBalance(300.0);
		a.process(t, accountNum);
		assertTrue(500.0 == a.getAccountBalance());
		
	}
	public void testLodgeChecks() {
		Account a = new Account();
		Transaction t = new Transaction(500.0,"C",new GregorianCalendar(2013,01,12).getTime());
//		Transaction t2 = new Transaction(600.0,"C",new GregorianCalendar(2013,01,12).getTime());
		
		
		a.lodgeChecks(t);
		ArrayList<Transaction> processChecks = a.getTransaction();
		int size = processChecks.size();
		Transaction trans = processChecks.get(size-1);
		assertTrue(trans.getAmount()==t.getAmount() && trans.getTransType()==t.getTransType() && trans.getDate()==t.getDate());
		
	}
	
	public void testProcessChecksSort() {
		Account a = new Account();
		a.setAccountBalance(5000);
		/*
		ArrayList<Transaction> transChecks = new ArrayList<Transaction>();
		transChecks.add(new Transaction(900.0,"C",new GregorianCalendar(2013,01,12).getTime()));
		transChecks.add(new Transaction(500.0,"C",new GregorianCalendar(2010,4,9).getTime()));
		transChecks.add(new Transaction(600.0,"C",new GregorianCalendar(2015,1,31).getTime()));
		*/
		a.lodgeChecks(new Transaction(900.0,"C",new GregorianCalendar(2013,01,12).getTime()));
		a.lodgeChecks(new Transaction(500.0,"C",new GregorianCalendar(2010,4,9).getTime()));
		a.lodgeChecks(new Transaction(600.0,"C",new GregorianCalendar(2015,1,31).getTime()));
		a.processChecks();
		ArrayList<Transaction> processChecks = a.getTransaction();
		int size = processChecks.size();
		Transaction trans = processChecks.get(0);
		assertTrue(trans.getAmount()== 300 && trans.getTransType()== "C" && trans.getDate()== new GregorianCalendar(2010,4,9).getTime());
		
	}
	public void testProcessChecksAmount() {
		Account a = new Account();
		a.setAccountBalance(5000);
		
		ArrayList<Transaction> transChecks = new ArrayList<Transaction>();
		transChecks.add(new Transaction(900.0,"C",new GregorianCalendar(2013,01,12).getTime()));
		transChecks.add(new Transaction(500.0,"C",new GregorianCalendar(2010,4,9).getTime()));
		transChecks.add(new Transaction(600.0,"C",new GregorianCalendar(2015,1,31).getTime()));
		a.processChecks();
		
		assertEquals(3000,a.getAccountBalance(),0.0);
		
	}
	
}
