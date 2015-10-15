package banking;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;


public class Account {
	private String accountNum;
	private String custName;
	private double accountBalance;
	private final double FINE = 35;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private ArrayList<Transaction> transChecks = new ArrayList<Transaction>();
	
	private final String C = "C";
	private final String DB = "DB";
	private final String DP = "DP";
	private final String W = "W";
	
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public boolean process(Transaction trans,String accountNum){
		if (!this.accountNum.equals(accountNum)){
			return false;
		}
		
		switch(trans.getTransType()){
		case DB:
		case W:	
			
			if(accountBalance < trans.getAmount()){
				return false;
				
			}
			accountBalance -= trans.getAmount();
			transactions.add(trans);
			break;
		case DP:
			accountBalance += trans.getAmount();
			transactions.add(trans);
			break;
		case C:
			lodgeChecks(trans);
			break;
		default:
			break;
		}
		
		return true;
	}

	public boolean lodgeChecks(Transaction trans){
		
		
		transChecks.add(trans);
		
		return true;
	}
	
	public void processChecks(){
		Collections.sort(transChecks);
		
		for (Transaction t : transChecks){
			accountBalance -= t.getAmount();
			
			if(accountBalance < 0){
				accountBalance -= FINE;
	
			}
		}
	}
	
	public ArrayList<Transaction> getTransaction(){
		ArrayList<Transaction> list = (ArrayList<Transaction>) transactions.clone();
		list.addAll(transChecks);
		
		return list;
	}
}
