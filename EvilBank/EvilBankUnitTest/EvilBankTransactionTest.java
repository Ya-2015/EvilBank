import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import org.junit.BeforeClass;
import org.junit.Test;

import banking.Transaction;


public class EvilBankTransactionTest {
	static Transaction t;
	@BeforeClass
	public static void initialTest(){
		t = new Transaction(500.0,"C",new GregorianCalendar(2013,01,12).getTime());
		
	}

	@Test
	public void testAmount() {
		
//		Transaction t = new Transaction(500.0,"C",new GregorianCalendar(2013,01,12).getTime());
		
		t.setAmount(600);
		assertEquals(600,t.getAmount(),0.0);
	}
		@Test
		public void testDate() {
			
//	    Transaction t = new Transaction(500.0,"C",new GregorianCalendar(2013,01,12).getTime());
		
		t.setDate(new GregorianCalendar(2013,01,15).getTime());
		assertEquals(new GregorianCalendar(2013,01,15).getTime(),t.getDate());
		
		
		}
		@Test
		public void testTransType() {
			
//		Transaction t = new Transaction(500.0,"C",new GregorianCalendar(2013,01,12).getTime());
		
		t.setTransType("DB");
		assertEquals("DB",t.getTransType());
		
		//fail("Not yet implemented");
	}
		

}
