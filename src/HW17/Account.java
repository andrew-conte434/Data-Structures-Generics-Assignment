package HW17;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.IOException;

public class Account {
	Depositor depositor;
	int accountNumber;
	String accountType;
	boolean isActive;
	double accountBalance;
	ArrayList<TransactionReceipt> transactionReceipts;
	
	//no arg constructor
	Account(){
		this.depositor = new Depositor();
		this.accountNumber = 100000;
		this.accountType = "";
		this.accountBalance = 0.0;
		this.isActive = true;
		transactionReceipts = new ArrayList<TransactionReceipt>();

	}
	
	Account(Depositor depositor, int accountNumber, String accountType,
			double accountBalance){
		this.depositor = depositor;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.isActive = true;
		transactionReceipts = new ArrayList<TransactionReceipt>();

	}
	
	//copy constructor
	Account(Account account){
		this.depositor = account.getDepositor();
		this.accountBalance = account.getAccountBalance();
		this.accountNumber = account.getAccountNumber();
		this.accountType = account.getAccountType();
		this.isActive = account.getStatus();
		this.transactionReceipts = account.getReceipts();
	}
	
	/** method: setAccountType
	 * Input: 
	 * String accountType
	 * Process: 
	 * sets this.accountType to the given string
	 * Returns:
	 * void
	 */
	public void setAccountType(String accountType) throws Exception {
		this.accountType = accountType;
	}
	
	//returns accountType
	public String getAccountType() {
		return accountType;
	}
	
	//returns accountNumber
	public int getAccountNumber() {
		return accountNumber;
	}
	
	//sets accountNumber
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	//returns depositor
	public Depositor getDepositor() {
		return depositor;
	}
	
	//set depositor
	public void setDepositor(Depositor depositor) {
		this.depositor = depositor; 
	}
	
	//returns accountBalance
	public double getAccountBalance() {
		return accountBalance;
	}
	
	//sets accountBalance to given value
	public void setAccountBalance(double newBalance) {
		this.accountBalance = newBalance;
	}
	
	//returns ArrayList of transaction receipts
	public ArrayList<TransactionReceipt> getReceipts(){
		return transactionReceipts;
	}
	
	
	public TransactionReceipt getBalanceInquiry(TransactionTicket ticket) throws IOException{
		try {
			if(!this.isActive) {
				throw new AccountClosedException(accountNumber);
			}
		} catch(AccountClosedException ex) {
			TransactionReceipt receipt = new TransactionReceipt(ticket, false, 0, 0);
			receipt.setReasonForFailure(ex.getMessage());
			return receipt;
		}
		TransactionReceipt receipt = new TransactionReceipt(ticket, true, 
				this.getAccountBalance(), this.getAccountBalance());
		
		transactionReceipts.add(receipt);
		return (new TransactionReceipt(receipt));
	}
	
	//adds TransactionReceipt object to receipts ArrayList
	public void addReceipt(TransactionReceipt receipt) {
		transactionReceipts.add(receipt);
	}
	
	//sets isActive variable
	public void setStatus(boolean newStatus) {
		this.isActive = newStatus;
	}
	
	//flips boolean isActive to false
	public TransactionReceipt closeAccount(TransactionTicket ticket) {
		TransactionReceipt receipt = new TransactionReceipt(ticket, true, 
				this.accountBalance, this.accountBalance);
		this.setStatus(false);
		transactionReceipts.add(receipt);
		return receipt;
	}
	
	//flips boolean isActive to true
	public TransactionReceipt reopenAccount(TransactionTicket ticket) {
		TransactionReceipt receipt = new TransactionReceipt(ticket, true, 
				this.accountBalance, this.accountBalance);
		this.setStatus(true);
		transactionReceipts.add(receipt);
		return receipt;
	}
	
	/**
	 * Method makeDeposit()
	 * Input:
	 * 	reference to TransactionTicket
	 * Process:
	 * 	checks if account is closed, throws AccountClosedException if not
	 * 	creates TransactionReceipt
	 * 	adds transaction amount to account balance
	 * Output:
	 * 	returns copy of transaction receipt
	 */
	public TransactionReceipt makeDeposit(TransactionTicket ticket) {
		TransactionReceipt receipt = new TransactionReceipt(ticket, true,
				this.accountBalance, (this.accountBalance + ticket.getAmountOfTransaction()));
		if(this instanceof CDAccount) {
			((CDAccount) this).maturityDate.add(Calendar.MONTH, ticket.getTermOfCD());
			receipt.setMaturityDate(((CDAccount)this).getMaturityDate());
		}
		this.accountBalance += ticket.getAmountOfTransaction();
		transactionReceipts.add(receipt);
		return new TransactionReceipt(receipt);
	}
	
	/**
	 * Method makeWithdrawal
	 * Input:
	 * 	reference to transaction ticket object
	 * Process:
	 * 	checks if withdrawal amount is greater than account balance
	 * 	throws InsufficientFundsException and returns if yes
	 * 	subtracts withdrawal amount from account balance
	 * Output:
	 * 	returns reference to TransactionReceipt object
	 */
	public TransactionReceipt makeWithdrawal(TransactionTicket ticket) throws IOException{
		TransactionReceipt receipt;
		receipt = new TransactionReceipt(ticket, true, this.accountBalance,
				(this.accountBalance - ticket.getAmountOfTransaction()));
		this.accountBalance -= ticket.getAmountOfTransaction();
		transactionReceipts.add(receipt);
		return new TransactionReceipt(receipt);
	}
	
	/**
	 * Method clearCheck()
	 * Input:
	 * 	reference to TransactionTicket object
	 * 	reference to Check object
	 * Process:
	 * 	Checks if account is open, throws AccountClosedException if not
	 * 	Creates Expiration date Calendar object set to 6 months ago. 
	 * 	Checks if check is postdated, throws PostDatedCheckException if not
	 * 	Checks if check is older than expiration date, throws CheckTooOldException if not
	 * 	calls this.makeWithdrawal() if check is valid
	 * Output
	 * 	returns value of this.makeWithdrawal()
	 */
	public TransactionReceipt clearCheck(TransactionTicket ticket, Check check) throws IOException{
		TransactionReceipt receipt;
		if(check.getCheckAmount() > this.getAccountBalance()) {
			receipt = new TransactionReceipt(ticket, false, this.getAccountBalance(),
					(this.getAccountBalance() - 1.5));
			this.accountBalance -= 1.50;
			receipt.setReasonForFailure("Insufficient funds - bounce fee of $1.50 charged");
			return receipt;
		} else {
			receipt = new TransactionReceipt(ticket, true, this.getAccountBalance(),
					(this.accountBalance -= check.getCheckAmount()));
			return receipt;
		}
	}
	
	//gets account status
	public boolean getStatus() {
		return isActive;
	}
	
	//Overloads equals method
	public boolean equals(Account copy) {
		if(copy.getAccountNumber() == this.getAccountNumber()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		if(accountType.equals("CD")) {
			return depositor.toString() + String.format("%15d %15s %15.2f", 
					accountNumber, accountType, accountBalance);
		}
		return depositor.toString() + String.format("%15d %15s %15.2f", 
				accountNumber, accountType, accountBalance);
	}


}
