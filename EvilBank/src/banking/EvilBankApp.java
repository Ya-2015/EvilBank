package banking;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;

import org.omg.IOP.TransactionService;



public class EvilBankApp {
	public static void main(String[] args) {
		
		
	String newUser = " ";
	
	Scanner sc = new Scanner(System.in);
	
	HashMap<String,Account> accountManager = new HashMap<String, Account>();
		
	
	do{
		
	newAccount(sc,accountManager);
	
	System.out.println("Would you like to DELETE ACCOUNT? y/n ");
	newUser = sc.next().toLowerCase();
	if(newUser.equals("y")){
		deleteAccount(sc,accountManager);
	
	System.out.println("Would you like to switch accounts or make another transaction with this account number? y/n ");
	newUser = sc.next().toLowerCase();
	
	
	}
	
	
	}while(!newUser.equals("n"));
	System.out.println("Closing...");
		
	
	}
	
	public static void newAccount(Scanner sc,HashMap<String,Account> accountManager)
	{
	
	
	Account account = new Account();
	
	
	
	String acctNum = "",acctName = "",transType = "";
	double amount = 0,acctBalance = 0;
	String dateStr= "";
	Date date; 
	
	System.out.println("Welcome to Evil Corp Savings and Loan");
	System.out.println("Please create the user account(s)");
	acctNum = Validator.getString(sc,"Enter the Account Number or -1 to stop entering accounts : ");
	
	account.setAccountNum(acctNum);
	

	
	if(!accountManager.containsKey(account.getAccountNum())){
		acctName = Validator.getString(sc,"Enter the Name for the Account Number : ");
		
		
		acctBalance = Validator.getDouble(sc,"Enter the balance for Account Number "+acctNum+": ",0,999999999);
		
		account.setAccountBalance(acctBalance);
		
//		acctNum = Validator.getString(sc,"Enter an account Number or -1 to stop entering accounts : \n");
		
		accountManager.put(account.getAccountNum(),account);
		
		
	}
	else
	{
		account = accountManager.get(acctNum);
	}
	
	String anotherTrans = "";
	do{
	
	transType = Validator.getStringType(sc,"Enter a transaction type (Check(C), Debit Card(DB), Deposit(DP), or Withdrawal(W)): ");
	
	
	
//	acctNum = Validator.getString(sc,"Enter the account Number: ");
	
	
	amount = Validator.getDouble(sc,"Enter the amount for the transaction: ",0,999999999);
	
	int month = Validator.getInteger(sc,"Enter the month of the transaction (MM): ",1,12);
	int day = Validator.getInteger(sc, "Please enter the day of transaction: (DD): ",1,31);
	int year = Validator.getInteger(sc, "Enter the year of the transaction: (YYYY): ",1970,Calendar.getInstance().get(Calendar.YEAR));
	
	
	
	date = new GregorianCalendar(year,month,day).getTime();
	
	
	if(!account.process(new Transaction(amount, transType, date),acctNum)){
		System.out.println("Error! Insufficient Funds in Account.");
		
	}
	
	anotherTrans = Validator.getString(sc,"WOULD YOU LIKE TO MAKE ANOTHER TRANSACTION OR END TRANSACTIONS FOR THIS ACCOUNT? :  Y/N  \n").toLowerCase();
	
	
	}while(!anotherTrans.equals("n"));
	account.processChecks();
	
	
	NumberFormat formatter = NumberFormat.getCurrencyInstance();
	String actString = formatter.format(account.getAccountBalance());
	
	
	System.out.println("THE BALLANCE OF ACCOUNT NUMBER "+account.getAccountNum()+" is: "+actString+"\n");
	for(Transaction t:account.getTransaction()){
		String amtString = formatter.format(t.getAmount());
		System.out.println("Transaction type selected: "+ t.getTransType()+"\n"+"Amount changed: " +amtString+"\n"+"Transaction date: " +t.getDate());
	}
	
	
	
	
	
}
	public static void deleteAccount(Scanner sc,HashMap<String,Account> accountManager){
		
		System.out.println("ENTER THE ACCOUNT NUMBER: ");
		String acctNum = Validator.getString(sc,"Enter the Account Number or -1 to stop entering accounts : ");
		
		if(!accountManager.containsKey(acctNum)){
			System.out.println("THIS ACCOUNT NUMBER DOES NOT EXIST!");
			
		}
		else
		{
			if(accountManager.get(acctNum).getAccountBalance() == 0){
				accountManager.remove(acctNum);
				System.out.println(acctNum + " WAS DELETED.");
				
			}
			else
				System.out.println("CURRENT BALANCE: "+accountManager.get(acctNum).getAccountBalance());
				System.out.println("OPERATION DENIED! BALANCE IS NOT $0.00");
		}
		
	}		
	

}
