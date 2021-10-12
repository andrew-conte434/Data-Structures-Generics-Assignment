package HW17;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TransactionTicket {
	Calendar dateOfTransaction;
	int termOfCD;
	String typeOfTransaction;
	double amountOfTransaction;
	
	//parameterized constructor
	public TransactionTicket(Calendar date, String type, double amount, int term) {
		dateOfTransaction = date;
		typeOfTransaction = type;
		amountOfTransaction = amount;
		termOfCD = term;
	}
	
	//copy constructor
	public TransactionTicket(TransactionTicket ticket) {
		this.dateOfTransaction = ticket.getDateOfTransaction();
		this.typeOfTransaction = ticket.getTypeOfTransaction();
		this.amountOfTransaction = ticket.getAmountOfTransaction();
		this.termOfCD = ticket.getTermOfCD();
	}
	
	//returns dateOfTransaction
	public Calendar getDateOfTransaction() {
		return dateOfTransaction;
	}
	
	/**
	 * Method:
	 * 	getDateOfTransaction
	 * Input:
	 * 	none
	 * Process
	 * 	Creates SimpleDateFormat object of form mm/dd/yyyy
	 * 	Converts Calendar dateOfTransaction to Date object
	 * 	Converts Date to String in mm/dd/yyyy format
	 * @return
	 */
	public String getDateofTransactionString() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = dateOfTransaction.getTime();
		String format = sdf.format(date);
		return format;
	}
	
	//returns typeOfTransaction
	public String getTypeOfTransaction() {
		return typeOfTransaction;
	}
	
	//returns amountOfTransaction
	public double getAmountOfTransaction() {
		return amountOfTransaction;
	}
	
	//returns termOfCD
	public int getTermOfCD() {
		return termOfCD;
	}
	
	//sets amountOfTransaction to given double
	public void setAmountOfTransaction(double amount) {
		amountOfTransaction = amount;
	}
	
	@Override
	public String toString() {
		return String.format("Transaction Type: %s%n", typeOfTransaction);
	}
}
