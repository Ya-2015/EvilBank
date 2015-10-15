package banking;
import java.text.NumberFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.omg.IOP.TransactionService;



public class EvilBankApp {
	public static void main(String[] args) {
		
		
	String newUser = " ";
	
	Scanner sc = new Scanner(System.in);
		
		
	
	do{
		
	newAccount(sc);	
	System.out.println("Would you like to switch accounts? y/n");
	newUser = sc.next().toLowerCase();
	
	
	}while(!newUser.equals("n"));
	System.out.println("Closing...");
		
	
	}
	
	public static void newAccount(Scanner sc)
	{
	
	
	Account account = new Account();
	
	
	
	String acctNum = "",acctName = "",transType = "";
	double amount = 0,acctBalance = 0;
	String dateStr= "";
	Date date; 
	
	System.out.println("Welcome to Evil Corp Savings and Loan");
	System.out.println("Please create the user account(s)");
	System.out.println("Enter the Account Number or -1 to stop entering accounts : ");
	acctNum = sc.nextLine();
	account.setAccountNum(acctNum);
	
	System.out.print("Enter the Name for the Account Number : ");
	acctName = sc.next();
	
	System.out.print("Enter the balance for Account Number "+acctNum+": ");
	acctBalance = sc.nextDouble();
	account.setAccountBalance(acctBalance);
	
	System.out.print("Enter an account Number or -1 to stop entering accounts : \n");
	acctNum = sc.next();
	
	do{
	
	System.out.print("Enter a transaction type (Check(C), Debit Card(DB), Deposit(DP), or Withdrawal(W)): ");
	transType = sc.next().toUpperCase();
	
	
	System.out.print("Enter the account Number: ");
	acctNum = sc.next();
	
	System.out.print("Enter the amount for the transaction: ");
	amount = sc.nextDouble();
	System.out.print("Enter the date of the check: MM/DD/YYYY:");
	dateStr = sc.next();
	String[] parts = dateStr.split("/");
	int month = Integer.parseInt(parts[0])-1;
	int day = Integer.parseInt(parts[1]);
	int year = Integer.parseInt(parts[2]);
	
	date = new GregorianCalendar(year,month,day).getTime();
	
	
	if(!account.process(new Transaction(amount, transType, date),acctNum)){
		System.out.println("Error! Insufficient Funds in Account.");
		
	}
	
	System.out.print("Enter an account Number or -1 to stop entering accounts : \n");
	acctNum = sc.next();
	
	}while(!acctNum.equals("-1"));
	account.processChecks();
	
	
	NumberFormat formatter = NumberFormat.getCurrencyInstance();
	String actString = formatter.format(account.getAccountBalance());
	
	
	System.out.print("THE BALLANCE OF ACCOUNT NUMBER "+account.getAccountNum()+" is: "+actString+"\n");
	for(Transaction t:account.getTransaction()){
		String amtString = formatter.format(t.getAmount());
		System.out.println("Transaction type selected: "+ t.getTransType()+"\n"+"Amount changed: " +amtString+"\n"+"Transaction date: " +t.getDate());
	}
	
}
		
	

}
