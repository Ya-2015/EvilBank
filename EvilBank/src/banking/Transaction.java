package banking;

import java.util.Date;

public class Transaction implements Comparable <Transaction> {
	private double amount;
	private String transType;
	private Date date;

	public Transaction(Double amount, String transType,Date date){
		this.amount = amount;
		this.transType = transType;
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int compareTo(Transaction transCheck) {

		Date compareDate = ((Transaction) transCheck).getDate();

		// ascending order
		return this.getDate().compareTo(compareDate);

		// descending order
		// return compareQuantity - this.quantity;

	}
	

}
