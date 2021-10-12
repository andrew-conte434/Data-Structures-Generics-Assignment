package HW17;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TransactionReceipt {
	TransactionTicket ticket;
	boolean successIndicatorFlag;
	String reasonForFailure = "";
	double preTransactionBalance, postTransactionBalance;
	Calendar postTransactionMaturityDate = Calendar.getInstance();
	String newMaturityDate = "";
	
	//parameterized constructor
	public TransactionReceipt(TransactionTicket ticket, boolean success, double preBalance,
			double postBalance) {
		this.ticket = ticket;
		this.successIndicatorFlag = success;
		this.preTransactionBalance = preBalance;
		this.postTransactionBalance = postBalance;
		postTransactionMaturityDate.add(Calendar.MONTH, ticket.getTermOfCD());
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = postTransactionMaturityDate.getTime();
		newMaturityDate = sdf.format(date);
	}

	//copy constructor
	public TransactionReceipt(TransactionReceipt receipt) {
		this.ticket = receipt.getTransactionTicket();
		this.successIndicatorFlag = receipt.getSuccessIndicatorFlag();
		this.preTransactionBalance = receipt.getPreTransactionBalance();
		this.postTransactionBalance = receipt.getPostTransactionBalance();
		this.postTransactionMaturityDate = Calendar.getInstance();
		this.reasonForFailure = receipt.getTransactionFailureReason();
		postTransactionMaturityDate.setTime(receipt.getMaturityDate().getTime());
		newMaturityDate = receipt.getmaturityDateString();
	}	
	
	//returns transaction ticket
	public TransactionTicket getTransactionTicket() {
		return ticket;
	}
	
	//returns successIndicatorFlag
	public boolean getSuccessIndicatorFlag() {
		return successIndicatorFlag;
	}
	
	//sets reasonForFailure to given string
	public void setReasonForFailure(String reason) {
		reasonForFailure = reason;
	}
	
	//returns reason for failure
	public String getTransactionFailureReason() {
		return reasonForFailure;
	}
	
	//returns pre transaction balance
	public double getPreTransactionBalance() {
		return preTransactionBalance;
	}
	
	//return post transaction balance
	public double getPostTransactionBalance() {
		return postTransactionBalance;
	}
	
	//set post transaction maturity date and updates String of maturity date
	public void setMaturityDate(Calendar newDate) {
		postTransactionMaturityDate.setTime(newDate.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = newDate.getTime();
		newMaturityDate = sdf.format(date);
	}
	
	//returns post transaction maturity date
	public Calendar getMaturityDate() {
		return postTransactionMaturityDate;
	}
	
	//returns maturity date as a mm/dd/yyyy string
	public String getmaturityDateString() {
		return newMaturityDate;
	}
	
	@Override
	public String toString() {
		String message = "";
		if(!successIndicatorFlag) {
			message = String.format("Transaction Type: %s%n"
								  + "%s%n"
								  + "Old Balance: $%.2f%n"
								  + "New Balance: $%.2f%n", ticket.getTypeOfTransaction(), this.getTransactionFailureReason(),
								  this.getPreTransactionBalance(), this.getPostTransactionBalance());
			return message;
		}
		switch(ticket.getTypeOfTransaction()) {
			case "Balance Inquiry":
				message = String.format("Transaction Type: %s%n"
									  + "Your Current Balance is: $%.2f%n", 
									  ticket.getTypeOfTransaction(), this.preTransactionBalance);
				break;
			case "Open Account":
				message = String.format("Transaction Type: %s%n"
									  + "Account opened on %s%n"
									  + "Initital balance: $%s%n", ticket.getTypeOfTransaction(), ticket
									  .getDateofTransactionString(), this.getPostTransactionBalance());
				if(ticket.getAmountOfTransaction() > 0) {
					message += String.format("Initial Maturity Date: %s%n", 
							this.newMaturityDate);
				}
				break;
			case "Deposit":
				message = String.format("Transation Type: %s%n"
								      + "Old Balance: $%.2f%n"
								      + "Deposit Amount: $%.2f%n"
								      + "New Balance: $%.2f%n", ticket.getTypeOfTransaction(),
								      this.getPreTransactionBalance(), ticket.getAmountOfTransaction(), 
								      this.getPostTransactionBalance());
				//adds new maturity date to receipts for CD accounts
				if(ticket.getTermOfCD() > 0) {
					message += String.format("Post-Transaction Maturity Date: %s%n", 
							this.newMaturityDate);
				}
				break;
			case "Withdrawal":
				message = String.format("Transaction Type: %s%n"
								      + "Old Balance: $%.2f%n"
								      + "Withdrawal Amount: $%.2f%n"
								      + "New Balance: $%.2f%n", ticket.getTypeOfTransaction(),
								      this.getPreTransactionBalance(), ticket.getAmountOfTransaction(), 
								      this.getPostTransactionBalance());
				//adds new maturity date to receipts for CD accounts
				if(ticket.getTermOfCD() > 0) {
					message += String.format("Post-Transaction Maturity Date: %s%n", 
							this.newMaturityDate);
				}
				break;
			case "Clear Check":
				message = String.format("Transaction Type: %s%n"
									  + "Old Balance: $%.2f%n"
									  + "Check Amount: $%.2f%n"
									  + "New Balance: $%.2f%n", ticket.getTypeOfTransaction(),
								      this.getPreTransactionBalance(), ticket.getAmountOfTransaction(), 
								      this.getPostTransactionBalance());
				break;
			case "Close Account":
				message = String.format("Transaction Type: %s%n"
									  + "Account closed successfully%n", ticket.getTypeOfTransaction());
				break;
			case "Reopen Account":
				message = String.format("Transaction Type: %s%n"
									  + "Account reopened successfully%n", ticket.getTypeOfTransaction());
				break;
			case "Delete Account":
				message = String.format("Transaction Type: %s%n"
									  + "Account deleted successfully", ticket.getTypeOfTransaction());
		}
		return message;
	}
}
